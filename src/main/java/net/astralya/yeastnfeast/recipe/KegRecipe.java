package net.astralya.yeastnfeast.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class KegRecipe implements Recipe<KegRecipeInput> {

    public static final int INPUT_SLOTS = 3;

    private final DefaultedList<Ingredient> ingredients;
    private final Ingredient yeastSlot;
    private final Ingredient tankardSlot;
    private final ItemStack output;
    private final float experience;
    private final int brewTime;

    public KegRecipe(DefaultedList<Ingredient> ingredients, Ingredient yeastSlot, Ingredient tankardSlot, ItemStack output, float experience, int brewTime) {
        this.ingredients = ingredients;
        this.yeastSlot = yeastSlot;
        this.tankardSlot = tankardSlot;
        this.output = output;
        this.experience = experience;
        this.brewTime = brewTime;
    }

    public DefaultedList<Ingredient> getIngredientList() {
        return ingredients;
    }

    public Ingredient getYeastSlot() {
        return yeastSlot;
    }

    public Ingredient getTankardSlot() {
        return tankardSlot;
    }

    public ItemStack getOutputStack() {
        return output;
    }

    public float getExperience() {
        return experience;
    }

    public int getBrewTime() {
        return brewTime;
    }

    @Override
    public boolean matches(KegRecipeInput input, World world) {
        int inputsCount = 0;
        for (int j = 0; j < 3; j++) {
            if (!input.getStackInSlot(j).isEmpty()) inputsCount++;
        }
        if (inputsCount != this.ingredients.size()) return false;

        boolean[] used = new boolean[3];
        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int j = 0; j < 3; j++) {
                if (!used[j] && ing.test(input.getStackInSlot(j))) {
                    used[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        ItemStack yeast = input.getStackInSlot(4);
        ItemStack tankard = input.getStackInSlot(5);
        boolean yeastOk = yeastSlot.isEmpty() || yeastSlot.test(yeast);
        boolean tankardOk = tankardSlot.isEmpty() || tankardSlot.test(tankard);
        return yeastOk && tankardOk;
    }

    @Override
    public ItemStack craft(KegRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registries) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.KEG_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.KEG_TYPE;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.KEG);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KegRecipe that)) return false;
        return Float.compare(that.getExperience(), getExperience()) == 0
                && getBrewTime() == that.getBrewTime()
                && Objects.equals(getGroup(), that.getGroup())
                && ingredients.equals(that.ingredients)
                && output.equals(that.output)
                && yeastSlot.equals(that.yeastSlot)
                && tankardSlot.equals(that.tankardSlot);
    }

    @Override
    public int hashCode() {
        int result = getGroup().hashCode();
        result = 31 * result + ingredients.hashCode();
        result = 31 * result + output.hashCode();
        result = 31 * result + yeastSlot.hashCode();
        result = 31 * result + tankardSlot.hashCode();
        result = 31 * result + (getExperience() != 0.0f ? Float.floatToIntBits(getExperience()) : 0);
        result = 31 * result + getBrewTime();
        return result;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {

        private static DefaultedList<Ingredient> toDefaulted(List<Ingredient> list) {
            DefaultedList<Ingredient> dl = DefaultedList.ofSize(list.size(), Ingredient.EMPTY);
            for (int i = 0; i < list.size(); i++) dl.set(i, list.get(i));
            return dl;
        }

        private static final MapCodec<KegRecipe> JSON_CODEC =
                RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.listOf()
                                .fieldOf("ingredients")
                                .xmap(Serializer::toDefaulted, list -> list)
                                .forGetter(KegRecipe::getIngredientList),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("yeast_slot")
                                .forGetter(KegRecipe::getYeastSlot),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .fieldOf("tankard_slot")
                                .forGetter(KegRecipe::getTankardSlot),
                        ItemStack.CODEC
                                .fieldOf("result")
                                .forGetter(KegRecipe::getOutputStack),
                        Codec.FLOAT.optionalFieldOf("experience", 0.0F)
                                .forGetter(KegRecipe::getExperience),
                        Codec.INT.optionalFieldOf("brewtime", 200)
                                .forGetter(KegRecipe::getBrewTime)
                ).apply(instance, (ings, yeast, tankard, result, xp, time) ->
                        new KegRecipe(ings, yeast, tankard, result, xp, time)));

        private static final PacketCodec<RegistryByteBuf, KegRecipe> NET_CODEC =
                new PacketCodec<>() {
                    @Override
                    public void encode(RegistryByteBuf buf, KegRecipe value) {
                        buf.writeVarInt(value.ingredients.size());
                        for (Ingredient ing : value.ingredients) {
                            Ingredient.PACKET_CODEC.encode(buf, ing);
                        }
                        Ingredient.PACKET_CODEC.encode(buf, value.yeastSlot);
                        Ingredient.PACKET_CODEC.encode(buf, value.tankardSlot);
                        ItemStack.PACKET_CODEC.encode(buf, value.output);
                        buf.writeFloat(value.experience);
                        buf.writeVarInt(value.brewTime);
                    }

                    @Override
                    public KegRecipe decode(RegistryByteBuf buf) {
                        int count = buf.readVarInt();
                        DefaultedList<Ingredient> ingreds = DefaultedList.ofSize(count, Ingredient.EMPTY);
                        for (int i = 0; i < count; i++) {
                            ingreds.set(i, Ingredient.PACKET_CODEC.decode(buf));
                        }
                        Ingredient yeast = Ingredient.PACKET_CODEC.decode(buf);
                        Ingredient tankard = Ingredient.PACKET_CODEC.decode(buf);
                        ItemStack out = ItemStack.PACKET_CODEC.decode(buf);
                        float xp = buf.readFloat();
                        int time = buf.readVarInt();
                        return new KegRecipe(ingreds, yeast, tankard, out, xp, time);
                    }
                };

        @Override
        public MapCodec<KegRecipe> codec() {
            return JSON_CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, KegRecipe> packetCodec() {
            return NET_CODEC;
        }
    }
}