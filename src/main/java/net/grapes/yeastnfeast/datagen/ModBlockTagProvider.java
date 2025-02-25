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

        /*getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR);*/
    }
}
