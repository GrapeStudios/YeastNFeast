package net.grapes.yeastnfeast.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class KegRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final Ingredient yeastSlot;
    private final Ingredient tankardSlot;

    public KegRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, Ingredient yeastSlot, Ingredient tankardSlot) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.yeastSlot = yeastSlot;
        this.tankardSlot = tankardSlot;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        boolean[] slotsMatched = new boolean[inventory.size()];
        for (Ingredient ingredient : recipeItems) {
            boolean foundIngredient = false;
            for (int i = 0; i < inventory.size(); i++) {
                if (slotsMatched[i]) {
                    continue;
                }
                if (ingredient.test(inventory.getStack(i))) {
                    slotsMatched[i] = true;
                    foundIngredient = true;
                    break;
                }
            }
            if (!foundIngredient) {
                return false;
            }
        }

        if (!yeastSlot.test(inventory.getStack(4))) {
            return false;
        }

        if (!tankardSlot.test(inventory.getStack(5))) {
            return false;
        }

        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager manager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager manager) {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    public Ingredient getYeastSlot() {
        return yeastSlot;
    }

    public Ingredient getTankardSlot() {
        return tankardSlot;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<KegRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "keg";
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "keg";

        @Override
        public KegRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i).getAsJsonObject()));
            }

            Ingredient yeastSlot = Ingredient.fromJson(JsonHelper.getObject(json, "yeast_slot"));
            Ingredient tankardSlot = Ingredient.fromJson(JsonHelper.getObject(json, "tankard_slot"));

            return new KegRecipe(id, output, inputs, yeastSlot, tankardSlot);
        }

        @Override
        public KegRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));

            ItemStack output = buf.readItemStack();
            Ingredient yeastSlot = Ingredient.fromPacket(buf);
            Ingredient tankardSlot = Ingredient.fromPacket(buf);

            return new KegRecipe(id, output, inputs, yeastSlot, tankardSlot);
        }

        @Override
        public void write(PacketByteBuf buf, KegRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput(null));
            recipe.getYeastSlot().write(buf);
            recipe.getTankardSlot().write(buf);
        }
    }
}
