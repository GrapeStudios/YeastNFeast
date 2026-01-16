package net.astralya.yeastnfeast.datagen.custom;

import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class CheesePressRecipeBuilder implements CraftingRecipeJsonBuilder {

    private final DefaultedList<Ingredient> ingredients = DefaultedList.of();
    private Ingredient flavorSlot = Ingredient.EMPTY;

    private ItemStack result = ItemStack.EMPTY;
    private float experience = 0.0f;
    private int pressTime = 1200;

    public CheesePressRecipeBuilder() {
    }

    public static CheesePressRecipeBuilder cheesePress() {
        return new CheesePressRecipeBuilder();
    }

    public CheesePressRecipeBuilder addIngredient(ItemConvertible item) {
        ingredients.add(Ingredient.ofItems(item));
        return this;
    }

    public CheesePressRecipeBuilder addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    public CheesePressRecipeBuilder addIngredient(TagKey<Item> tag) {
        ingredients.add(Ingredient.fromTag(tag));
        return this;
    }

    public CheesePressRecipeBuilder flavor(ItemConvertible item) {
        flavorSlot = Ingredient.ofItems(item);
        return this;
    }

    public CheesePressRecipeBuilder flavor(Ingredient ingredient) {
        flavorSlot = ingredient;
        return this;
    }

    public CheesePressRecipeBuilder result(ItemConvertible item, int count) {
        result = new ItemStack(item, count);
        return this;
    }

    public CheesePressRecipeBuilder experience(float xp) {
        experience = xp;
        return this;
    }

    public CheesePressRecipeBuilder pressTime(int ticks) {
        pressTime = ticks;
        return this;
    }

    @Override
    public CheesePressRecipeBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        return this;
    }

    @Override
    public CheesePressRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result.getItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        if (ingredients.isEmpty()) {
            throw new IllegalStateException("Cheese Press recipe with no ingredients: " + recipeId);
        }
        if (ingredients.size() > CheesePressRecipe.INPUT_SLOTS) {
            throw new IllegalStateException("Too many ingredients for Cheese Press recipe (max " + CheesePressRecipe.INPUT_SLOTS + "): " + recipeId);
        }

        Advancement.Builder adv = Advancement.Builder.createUntelemetered()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);

        Identifier advId = Identifier.of(recipeId.getNamespace(), "recipes/cheese_press/" + recipeId.getPath());
        AdvancementEntry advEntry = adv.build(advId);

        CheesePressRecipe recipe = new CheesePressRecipe(
                ingredients,
                flavorSlot,
                result,
                experience,
                pressTime
        );

        exporter.accept(recipeId, recipe, advEntry);
    }
}