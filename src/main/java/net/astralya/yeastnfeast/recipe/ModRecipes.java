package net.astralya.yeastnfeast.recipe;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<KegRecipe> KEG_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(YeastNFeastMod.MODID, "keg"), new KegRecipe.Serializer());
    public static final RecipeType<KegRecipe> KEG_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(YeastNFeastMod.MODID, "keg"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "small_cauldron";
                }
            });

    public static void registerRecipes() {
        YeastNFeastMod.LOGGER.info("Registering Custom Recipes for " + YeastNFeastMod.MODID);
    }
}
