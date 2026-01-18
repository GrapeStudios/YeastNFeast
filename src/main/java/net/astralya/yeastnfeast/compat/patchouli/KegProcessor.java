package net.astralya.yeastnfeast.compat.patchouli;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.List;

public class KegProcessor implements IComponentProcessor {

    private KegRecipe recipe;

    @Override
    public void setup(World level, IVariableProvider variables) {
        String recipeIdStr = variables.get("recipe", level.getRegistryManager()).asString();
        Identifier id = Identifier.of(recipeIdStr);

        List<RecipeEntry<KegRecipe>> all =
                level.getRecipeManager().listAllOfType(ModRecipes.KEG_TYPE);

        this.recipe = all.stream()
                .filter(entry -> entry.id().equals(id))
                .findFirst()
                .map(RecipeEntry::value)
                .orElseThrow(() -> new IllegalArgumentException("Keg recipe not found: " + id));
    }

    @Override
    public IVariable process(World level, String key) {
        return switch (key) {
            case "output" -> {
                ItemStack result = recipe.getOutputStack().copy();
                yield IVariable.from(result, level.getRegistryManager());
            }
            case "header" -> {
                ItemStack result = recipe.getOutputStack();
                yield IVariable.from(result.getName(), level.getRegistryManager());
            }
            case "yeast_slot" -> {
                ItemStack[] stacks = recipe.getYeastSlot().getMatchingStacks();
                yield stacks.length > 0
                        ? IVariable.from(stacks[0].copy(), level.getRegistryManager())
                        : null;
            }
            case "tankard_slot" -> {
                ItemStack[] stacks = recipe.getTankardSlot().getMatchingStacks();
                yield stacks.length > 0
                        ? IVariable.from(stacks[0].copy(), level.getRegistryManager())
                        : null;
            }
            default -> {
                if (key.startsWith("ingredients")) {
                    try {
                        int idx = Integer.parseInt(key.substring("ingredients".length()));
                        List<Ingredient> ings = recipe.getIngredientList();
                        if (idx >= 0 && idx < ings.size()) {
                            ItemStack[] stacks = ings.get(idx).getMatchingStacks();
                            yield stacks.length > 0
                                    ? IVariable.from(stacks[0].copy(), level.getRegistryManager())
                                    : null;
                        }
                    } catch (NumberFormatException ignored) {}
                }
                yield null;
            }
        };
    }
}