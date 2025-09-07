package net.astralya.yeastnfeast.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KegRecipe implements Recipe<RecipeWrapper> {

    public static final int INPUT_SLOTS = 3;

    private final NonNullList<Ingredient> ingredients;
    private final Ingredient yeastSlot;
    private final Ingredient tankardSlot;
    private final ItemStack output;
    private final float experience;
    private final int brewTime;

    public KegRecipe(NonNullList<Ingredient> ingredients, Ingredient yeastSlot, Ingredient tankardSlot, ItemStack output, float experience, int brewTime) {
        this.ingredients = ingredients;
        this.yeastSlot = yeastSlot;
        this.tankardSlot = tankardSlot;
        this.output = output;
        this.experience = experience;
        this.brewTime = brewTime;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getYeastSlot() {
        return yeastSlot;
    }

    public Ingredient getTankardSlot() {
        return tankardSlot;
    }

    public float getExperience() {
        return experience;
    }

    public int getBrewTime() {
        return brewTime;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level level) {
        int inputsCount = 0;
        for (int i = 0; i < 3; i++) {
            if (!inv.getItem(i).isEmpty()) inputsCount++;
        }

        if (inputsCount != ingredients.size()) return false;

        boolean[] used = new boolean[3];
        for (Ingredient ingredient : ingredients) {
            boolean found = false;
            for (int i = 0; i < 3; i++) {
                if (!used[i] && ingredient.test(inv.getItem(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        return (yeastSlot.isEmpty() || yeastSlot.test(inv.getItem(4)))
                && (tankardSlot.isEmpty() || tankardSlot.test(inv.getItem(5)));
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.KEG_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.KEG_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.KEG.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof KegRecipe other)) return false;
        return Float.compare(other.experience, experience) == 0 &&
                brewTime == other.brewTime &&
                Objects.equals(ingredients, other.ingredients) &&
                Objects.equals(output, other.output) &&
                Objects.equals(yeastSlot, other.yeastSlot) &&
                Objects.equals(tankardSlot, other.tankardSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, yeastSlot, tankardSlot, output, experience, brewTime);
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {

        private static final MapCodec<KegRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap(list -> {
                    NonNullList<Ingredient> result = NonNullList.create();
                    result.addAll(list);
                    return result;
                }, list -> list).forGetter(KegRecipe::getIngredients),
                Ingredient.CODEC.fieldOf("yeast_slot").forGetter(KegRecipe::getYeastSlot),
                Ingredient.CODEC.fieldOf("tankard_slot").forGetter(KegRecipe::getTankardSlot),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.output),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(KegRecipe::getExperience),
                Codec.INT.optionalFieldOf("brewtime", 200).forGetter(KegRecipe::getBrewTime)
        ).apply(inst, KegRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, KegRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<KegRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, KegRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static KegRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            int size = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            ingredients.replaceAll(i -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            Ingredient yeast = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient tankard = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            float experience = buf.readFloat();
            int brewTime = buf.readVarInt();
            return new KegRecipe(ingredients, yeast, tankard, result, experience, brewTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, KegRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient i : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, i);
            }
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.yeastSlot);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.tankardSlot);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.brewTime);
        }
    }
}