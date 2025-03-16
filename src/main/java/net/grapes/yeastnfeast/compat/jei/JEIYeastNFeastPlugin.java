package net.grapes.yeastnfeast.compat.jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.recipe.KegRecipe;
import net.grapes.yeastnfeast.screen.KegScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIYeastNFeastPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(YeastNFeastMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new KegRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<KegRecipe> kegRecipes = recipeManager.getAllRecipesFor(KegRecipe.Type.INSTANCE);
        registration.addRecipes(KegRecipeCategory.KEG_TYPE,  kegRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(KegScreen.class, 89, 25, 24, 17,
                KegRecipeCategory.KEG_TYPE);
    }
}
