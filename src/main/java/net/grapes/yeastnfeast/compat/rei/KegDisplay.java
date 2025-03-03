package net.grapes.yeastnfeast.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.grapes.yeastnfeast.recipe.KegRecipe;
import net.minecraft.recipe.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KegDisplay extends BasicDisplay {

    private final EntryIngredient tankardSlot;
    private final EntryIngredient yeastSlot;

    public KegDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, EntryIngredient tankardSlot, EntryIngredient yeastSlot) {
        super(inputs, outputs);
        this.tankardSlot = tankardSlot;
        this.yeastSlot = yeastSlot;
    }

    public KegDisplay(KegRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getOutput(null)))));
        this.tankardSlot = EntryIngredients.ofIngredient(recipe.getTankardSlot());
        this.yeastSlot = EntryIngredients.ofIngredient(recipe.getYeastSlot());
    }

    private static List<EntryIngredient> getInputList(KegRecipe recipe) {
        if (recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            list.add(EntryIngredients.ofIngredient(ingredient));
        }
        return list;
    }

    public EntryIngredient getTankardSlot() {
        return tankardSlot;
    }

    public EntryIngredient getYeastSlot() {
        return yeastSlot;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return KegCategory.KEG;
    }
}
