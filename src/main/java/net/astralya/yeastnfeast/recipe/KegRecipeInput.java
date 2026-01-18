package net.astralya.yeastnfeast.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.ArrayList;
import java.util.List;

public record KegRecipeInput(List<ItemStack> stacks) implements RecipeInput {

    public KegRecipeInput(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack yeast, ItemStack tankard) {
        this(List.of(input1, input2, input3, yeast, tankard));
    }

    public KegRecipeInput {
        if (stacks == null) {
            throw new IllegalArgumentException("stacks cannot be null");
        }
        if (stacks.size() == 5) {
            List<ItemStack> normalized = new ArrayList<>(6);
            normalized.add(stacks.get(0));
            normalized.add(stacks.get(1));
            normalized.add(stacks.get(2));
            normalized.add(ItemStack.EMPTY);
            normalized.add(stacks.get(3));
            normalized.add(stacks.get(4));
            stacks = List.copyOf(normalized);
        } else if (stacks.size() != 6) {
            throw new IllegalArgumentException("Expected 5 or 6 stacks (inputs[0-2], empty/output placeholder at 3, yeast at 4, tankard at 5)");
        } else {
            stacks = List.copyOf(stacks);
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Override
    public int getSize() {
        return stacks.size();
    }

    public ItemStack getYeast() {
        return stacks.get(4);
    }

    public ItemStack getTankard() {
        return stacks.get(5);
    }

    public List<ItemStack> getInputs() {
        return stacks.subList(0, 3);
    }
}
