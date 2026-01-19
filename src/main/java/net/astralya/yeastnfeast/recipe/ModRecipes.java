package net.astralya.yeastnfeast.recipe;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<KegRecipe> KEG_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(YeastNFeastMod.MODID, "keg"),
            KegRecipe.Serializer.INSTANCE
    );

    public static final RecipeType<KegRecipe> KEG_TYPE = Registry.register(
            Registries.RECIPE_TYPE,
            new Identifier(YeastNFeastMod.MODID, "keg"),
            KegRecipe.Type.INSTANCE
    );

    public static final RecipeSerializer<CheesePressRecipe> CHEESE_PRESS_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER,
            new Identifier(YeastNFeastMod.MODID, "cheese_press"),
            CheesePressRecipe.Serializer.INSTANCE
    );

    public static final RecipeType<CheesePressRecipe> CHEESE_PRESS_TYPE = Registry.register(
            Registries.RECIPE_TYPE,
            new Identifier(YeastNFeastMod.MODID, "cheese_press"),
            CheesePressRecipe.Type.INSTANCE
    );

    public static void registerRecipes() {
        YeastNFeastMod.LOGGER.info("Registering Custom Recipes for " + YeastNFeastMod.MODID);
    }
}
