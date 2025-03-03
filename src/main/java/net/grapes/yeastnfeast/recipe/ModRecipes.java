package net.grapes.yeastnfeast.recipe;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static void registerRecipes() {
        // Keg Recipe
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(YeastNFeastMod.MOD_ID, KegRecipe.Serializer.ID),
                KegRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(YeastNFeastMod.MOD_ID, KegRecipe.Type.ID),
                KegRecipe.Type.INSTANCE);
    }
}
