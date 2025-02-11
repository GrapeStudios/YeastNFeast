package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Drops for Wild Crops
        addDrop(ModBlocks.WILD_BARLEY);
            this.addDrop(ModBlocks.WILD_BARLEY, ModItems.BARLEY_SEEDS);
        addDrop(ModBlocks.WILD_RYE);
            this.addDrop(ModBlocks.WILD_RYE, ModItems.RYE_SEEDS);
        addDrop(ModBlocks.WILD_GINGER);
            this.addDrop(ModBlocks.WILD_GINGER, ModItems.GINGER);
    }
}
