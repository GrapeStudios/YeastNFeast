package net.astralya.yeastnfeast.compat.rei.cheese_press;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheesePressDisplay extends BasicDisplay {

    public CheesePressDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public CheesePressDisplay(RecipeEntry<CheesePressRecipe> entry) {
        this(entry.value());
    }

    public CheesePressDisplay(CheesePressRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getResult(null)))));
    }

    private static List<EntryIngredient> getInputList(CheesePressRecipe recipe) {
        if (recipe == null) return Collections.emptyList();

        List<EntryIngredient> list = new ArrayList<>();

        for (Ingredient ingredient : recipe.getIngredients()) {
            list.add(EntryIngredients.ofIngredient(ingredient));
        }

        Ingredient flavor = recipe.getFlavorSlot();
        if (flavor != null && !flavor.isEmpty()) {
            if (list.size() >= 3) {
                list.set(2, EntryIngredients.ofIngredient(flavor));
            } else {
                while (list.size() < 2) {
                    list.add(EntryIngredient.empty());
                }
                list.add(EntryIngredients.ofIngredient(flavor));
            }
        }

        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CheesePressCategory.CHEESE_PRESS;
    }
}
