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
import java.util.Optional;

public class CheesePressRecipe implements Recipe<CheesePressRecipeInput> {

    public static final int INPUT_SLOTS = 3;

    private final DefaultedList<Ingredient> ingredients;
    private final Ingredient flavorSlot;
    private final ItemStack output;
    private final float experience;
    private final int pressTime;

    public CheesePressRecipe(DefaultedList<Ingredient> ingredients, Ingredient flavorSlot, ItemStack output, float experience, int pressTime) {
        this.ingredients = ingredients;
        this.flavorSlot = flavorSlot;
        this.output = output;
        this.experience = experience;
        this.pressTime = pressTime;
    }

    public DefaultedList<Ingredient> getIngredientList() {
        return ingredients;
    }

    public Ingredient getFlavorSlot() {
        return flavorSlot;
    }

    public ItemStack getOutputStack() {
        return output;
    }

    public float getExperience() {
        return experience;
    }

    public int getPressTime() {
        return pressTime;
    }

    @Override
    public boolean matches(CheesePressRecipeInput input, World world) {
        int filled = 0;
        for (int i = 0; i < INPUT_SLOTS; i++) {
            if (!input.getStackInSlot(i).isEmpty()) {
                filled++;
            }
        }

        int required = ingredients.size() + (flavorSlot.isEmpty() ? 0 : 1);
        if (filled != required) {
            return false;
        }

        boolean[] used = new boolean[INPUT_SLOTS];

        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && ing.test(input.getStackInSlot(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        if (!flavorSlot.isEmpty()) {
            boolean foundFlavor = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && flavorSlot.test(input.getStackInSlot(i))) {
                    used[i] = true;
                    foundFlavor = true;
                    break;
                }
            }
            if (!foundFlavor) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(CheesePressRecipeInput input, RegistryWrapper.WrapperLookup registries) {
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
        return ModRecipes.CHEESE_PRESS_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHEESE_PRESS_TYPE;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.CHEESE_PRESS);
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheesePressRecipe that)) return false;
        return Float.compare(that.getExperience(), getExperience()) == 0
                && getPressTime() == that.getPressTime()
                && Objects.equals(getGroup(), that.getGroup())
                && ingredients.equals(that.ingredients)
                && output.equals(that.output)
                && flavorSlot.equals(that.flavorSlot);
    }

    @Override
    public int hashCode() {
        int result = getGroup().hashCode();
        result = 31 * result + ingredients.hashCode();
        result = 31 * result + output.hashCode();
        result = 31 * result + flavorSlot.hashCode();
        result = 31 * result + (getExperience() != 0.0f ? Float.floatToIntBits(getExperience()) : 0);
        result = 31 * result + getPressTime();
        return result;
    }

    public static class Serializer implements RecipeSerializer<CheesePressRecipe> {

        private static DefaultedList<Ingredient> toDefaulted(List<Ingredient> list) {
            DefaultedList<Ingredient> dl = DefaultedList.ofSize(list.size(), Ingredient.EMPTY);
            for (int i = 0; i < list.size(); i++) {
                dl.set(i, list.get(i));
            }
            return dl;
        }

        private static final MapCodec<CheesePressRecipe> JSON_CODEC =
                RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.listOf()
                                .fieldOf("ingredients")
                                .xmap(Serializer::toDefaulted, list -> list)
                                .forGetter(CheesePressRecipe::getIngredientList),

                        Ingredient.DISALLOW_EMPTY_CODEC.optionalFieldOf("flavor_slot")
                                .forGetter(r -> r.flavorSlot.isEmpty() ? Optional.empty() : Optional.of(r.flavorSlot)),

                        ItemStack.CODEC
                                .fieldOf("result")
                                .forGetter(CheesePressRecipe::getOutputStack),

                        Codec.FLOAT.optionalFieldOf("experience", 0.0F)
                                .forGetter(CheesePressRecipe::getExperience),

                        Codec.INT.optionalFieldOf("pressTime", 1200)
                                .forGetter(CheesePressRecipe::getPressTime)
                ).apply(instance, (ings, flavorOpt, result, xp, time) ->
                        new CheesePressRecipe(ings, flavorOpt.orElse(Ingredient.EMPTY), result, xp, time)));

        private static final PacketCodec<RegistryByteBuf, CheesePressRecipe> NET_CODEC =
                new PacketCodec<>() {
                    @Override
                    public void encode(RegistryByteBuf buf, CheesePressRecipe value) {
                        buf.writeVarInt(value.ingredients.size());
                        for (Ingredient ing : value.ingredients) {
                            Ingredient.PACKET_CODEC.encode(buf, ing);
                        }
                        Ingredient.PACKET_CODEC.encode(buf, value.flavorSlot);
                        ItemStack.PACKET_CODEC.encode(buf, value.output);
                        buf.writeFloat(value.experience);
                        buf.writeVarInt(value.pressTime);
                    }

                    @Override
                    public CheesePressRecipe decode(RegistryByteBuf buf) {
                        int count = buf.readVarInt();
                        DefaultedList<Ingredient> ingreds = DefaultedList.ofSize(count, Ingredient.EMPTY);
                        for (int i = 0; i < count; i++) {
                            ingreds.set(i, Ingredient.PACKET_CODEC.decode(buf));
                        }
                        Ingredient flavor = Ingredient.PACKET_CODEC.decode(buf);
                        ItemStack out = ItemStack.PACKET_CODEC.decode(buf);
                        float xp = buf.readFloat();
                        int time = buf.readVarInt();
                        return new CheesePressRecipe(ingreds, flavor, out, xp, time);
                    }
                };

        @Override
        public MapCodec<CheesePressRecipe> codec() {
            return JSON_CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CheesePressRecipe> packetCodec() {
            return NET_CODEC;
        }
    }
}