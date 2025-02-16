package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public class ModRegistries {
    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.MINT_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ELDERBERRIES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.HAWTHORN_BERRIES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ROSE_HIPS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.3f);

        ComposterBlock.COMPOSTABLES.put(ModItems.GARLIC.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.MINT.get(), 0.5f);

        ComposterBlock.COMPOSTABLES.put(ModItems.GINGER.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE.get(), 0.5f);
    }
}
