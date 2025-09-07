package net.astralya.yeastnfeast.datagen.custom;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class KegRecipeBuilder implements RecipeBuilder {

    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private Ingredient yeastSlot = Ingredient.EMPTY;
    private Ingredient tankardSlot = Ingredient.EMPTY;
    private final ItemStack output;
    private final Item result;
    private float experience = 0f;
    private int brewTime = 200;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private KegRecipeBuilder(ItemStack output) {
        this.output = output;
        this.result = output.getItem();
    }

    public static KegRecipeBuilder kegRecipe(ItemStack output) {
        return new KegRecipeBuilder(output);
    }

    public KegRecipeBuilder addIngredient(Ingredient ing)   { ingredients.add(ing); return this; }
    public KegRecipeBuilder addIngredient(Item i)           { return addIngredient(Ingredient.of(i)); }
    public KegRecipeBuilder addIngredient(ItemStack s)      { return addIngredient(Ingredient.of(s)); }

    public KegRecipeBuilder yeast(Ingredient ing)           { this.yeastSlot = ing; return this; }
    public KegRecipeBuilder yeast(Item item)                { return yeast(Ingredient.of(item)); }

    public KegRecipeBuilder tankard(Ingredient ing)         { this.tankardSlot = ing; return this; }
    public KegRecipeBuilder tankard(Item item)              { return tankard(Ingredient.of(item)); }

    public KegRecipeBuilder experience(float xp)            { this.experience = xp; return this; }
    public KegRecipeBuilder brewTime(int ticks)             { this.brewTime = ticks; return this; }

    @Override public KegRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion); return this;
    }

    public KegRecipeBuilder unlockedByItems(String name, Item... items) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    @Override public RecipeBuilder group(@Nullable String s) { return this; }
    @Override public Item getResult() { return result; }

    @Override
    public void save(RecipeOutput out, ResourceLocation id) {
        if (ingredients.isEmpty())
            throw new IllegalStateException("Keg recipe with no ingredients: " + id);
        if (ingredients.size() > KegRecipe.INPUT_SLOTS)
            throw new IllegalStateException("Too many ingredients for Keg recipe (max "
                    + KegRecipe.INPUT_SLOTS + "): " + id);

        Advancement.Builder adv = out.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(adv::addCriterion);

        KegRecipe recipe = new KegRecipe(
                ingredients, yeastSlot, tankardSlot,
                output, experience, brewTime);

        out.accept(id, recipe, adv.build(id.withPrefix("recipes/keg/")));
    }
}