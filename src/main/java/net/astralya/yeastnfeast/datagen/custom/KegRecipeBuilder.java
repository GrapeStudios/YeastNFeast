package net.astralya.yeastnfeast.datagen.custom;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class KegRecipeBuilder implements CraftingRecipeJsonBuilder {

    private final DefaultedList<Ingredient> ingredients = DefaultedList.of();
    private Ingredient yeast = Ingredient.EMPTY;
    private Ingredient tankard = Ingredient.EMPTY;
    private ItemStack result = ItemStack.EMPTY;
    private float experience = 0.0f;
    private int brewTime = 200;

    public KegRecipeBuilder() {}

    public static KegRecipeBuilder keg() {
        return new KegRecipeBuilder();
    }

    public KegRecipeBuilder addIngredient(ItemConvertible item) {
        ingredients.add(Ingredient.ofItems(item));
        return this;
    }

    public KegRecipeBuilder yeast(ItemConvertible item) {
        yeast = Ingredient.ofItems(item);
        return this;
    }

    public KegRecipeBuilder tankard(ItemConvertible item) {
        tankard = Ingredient.ofItems(item);
        return this;
    }

    public KegRecipeBuilder result(ItemConvertible item, int count) {
        result = new ItemStack(item, count);
        return this;
    }

    public KegRecipeBuilder experience(float xp) {
        experience = xp;
        return this;
    }

    public KegRecipeBuilder brewTime(int ticks) {
        brewTime = ticks;
        return this;
    }

    @Override
    public KegRecipeBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        return this;
    }

    @Override
    public KegRecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result.getItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        Advancement.Builder adv = Advancement.Builder.createUntelemetered()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);

        Identifier advId = Identifier.of(recipeId.getNamespace(), "recipes/keg/" + recipeId.getPath());
        AdvancementEntry advEntry = adv.build(advId);

        KegRecipe recipe = new KegRecipe(
                ingredients,
                yeast,
                tankard,
                result,
                experience,
                brewTime
        );

        exporter.accept(recipeId, recipe, advEntry);
    }
}