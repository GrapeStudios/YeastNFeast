package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.BARLEY_CROP).add(ModBlocks.RYE_CROP)
                .add(ModBlocks.GINGER_CROP).add(ModBlocks.GARLIC_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.BARLEY_CROP).add(ModBlocks.RYE_CROP)
                .add(ModBlocks.GINGER_CROP).add(ModBlocks.GARLIC_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.KEG).add(ModBlocks.TREE_TAP)
                .addTag(ModTags.Blocks.STORED_CROPS);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.KEG).add(ModBlocks.TREE_TAP);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .addTag(ModTags.Blocks.STORED_CROPS);

        // Custom Tags
        getOrCreateTagBuilder(ModTags.Blocks.STORED_CROPS)
                .add(ModBlocks.BAG_OF_ELDERBERRIES).add(ModBlocks.BAG_OF_GARLIC)
                .add(ModBlocks.BAG_OF_GINGER).add(ModBlocks.BAG_OF_HAWTHORN_BERRIES)
                .add(ModBlocks.BAG_OF_LEMON).add(ModBlocks.BAG_OF_MINT)
                .add(ModBlocks.BAG_OF_ROSE_HIPS).add(ModBlocks.RYE_BLOCK)
                .add(ModBlocks.BARLEY_BLOCK);

        // Wood-related Tags
        getOrCreateTagBuilder(ModTags.Blocks.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG,
                        ModBlocks.MAPLE_WOOD, ModBlocks.STRIPPED_MAPLE_WOOD);
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.MAPLE_LOGS);
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.MAPLE_LEAVES).add(ModBlocks.LEMON_TREE_LEAVES)
                .add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES).add(ModBlocks.HAWTHORN_TREE_LEAVES)
                .add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.MAPLE_SAPLING).add(ModBlocks.LEMON_SAPLING)
                .add(ModBlocks.HAWTHORN_SAPLING);
    }
}
