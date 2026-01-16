package net.astralya.yeastnfeast.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.ArrayList;
import java.util.List;

public record CheesePressRecipeInput(List<ItemStack> stacks) implements RecipeInput {

    public CheesePressRecipeInput(ItemStack input1, ItemStack input2, ItemStack input3) {
        this(List.of(input1, input2, input3, ItemStack.EMPTY));
    }

    public CheesePressRecipeInput {
        if (stacks == null) {
            throw new IllegalArgumentException("stacks cannot be null");
        }
        if (stacks.size() == 3) {
            List<ItemStack> normalized = new ArrayList<>(4);
            normalized.add(stacks.get(0));
            normalized.add(stacks.get(1));
            normalized.add(stacks.get(2));
            normalized.add(ItemStack.EMPTY);
            stacks = List.copyOf(normalized);
        } else if (stacks.size() != 4) {
            throw new IllegalArgumentException("Expected 3 or 4 stacks (inputs[0-2], output placeholder at 3)");
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

    public List<ItemStack> getInputs() {
        return stacks.subList(0, 3);
    }
}