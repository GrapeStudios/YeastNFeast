package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.block.ComposterBlock;

public class ModRegistries {
    public static void registerModStuff(){
        registerModCompostable();
    }

    private static void registerModCompostable() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BARLEY_SEEDS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RYE_SEEDS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MINT_SEEDS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ELDERBERRIES, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.HAWTHORN_BERRIES, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ROSE_HIPS, 0.3f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.LEMON, 0.3f);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GARLIC, 0.5f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MINT, 0.5f);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GINGER, 0.6f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BARLEY, 0.6f);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RYE, 0.6f);
    }
}
