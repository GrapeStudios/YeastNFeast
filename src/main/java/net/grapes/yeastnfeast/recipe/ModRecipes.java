package net.grapes.yeastnfeast.recipe;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, YeastNFeastMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<KegRecipe>> KEG_RECIPE_SERIALIZER =
            SERIALIZERS.register("keg", () -> KegRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
