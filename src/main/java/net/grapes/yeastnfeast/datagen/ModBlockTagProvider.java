package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, YeastNFeastMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.CROPS)
                .add(ModBlocks.BARLEY_CROP.get(), ModBlocks.RYE_CROP.get(),
                        ModBlocks.GINGER_CROP.get(), ModBlocks.GARLIC_CROP.get(),
                        ModBlocks.MINT_CROP.get());

        this.tag(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.BARLEY_CROP.get(),
                        ModBlocks.RYE_CROP.get(), ModBlocks.GINGER_CROP.get(),
                        ModBlocks.GARLIC_CROP.get(), ModBlocks.MINT_CROP.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.KEG.get()).add(ModBlocks.TREE_TAP.get())
                .addTag(ModTags.Blocks.STORAGE_BLOCKS);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.KEG.get()).add(ModBlocks.TREE_TAP.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS);

        // Custom Tags
        this.tag(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.BAG_OF_ELDERBERRIES.get()).add(ModBlocks.BAG_OF_GARLIC.get())
                .add(ModBlocks.BAG_OF_GINGER.get()).add(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get())
                .add(ModBlocks.BAG_OF_LEMON.get()).add(ModBlocks.BAG_OF_MINT.get())
                .add(ModBlocks.BAG_OF_ROSE_HIPS.get()).add(ModBlocks.RYE_BLOCK.get())
                .add(ModBlocks.BARLEY_BLOCK.get());

        // Wood-related Tags
        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.MAPLE_LOGS);

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.MAPLE_LEAVES.get()).add(ModBlocks.LEMON_TREE_LEAVES.get())
                .add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get()).add(ModBlocks.HAWTHORN_TREE_LEAVES.get())
                .add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get());
        this.tag(BlockTags.SAPLINGS)
                .add(ModBlocks.MAPLE_SAPLING.get()).add(ModBlocks.LEMON_SAPLING.get())
                .add(ModBlocks.HAWTHORN_SAPLING.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.get());
        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.get());
        this.tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.get());
        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.get());

        this.tag(BlockTags.SIGNS)
                .add(ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get());
        this.tag(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.MAPLE_WALL_SIGN.get(), ModBlocks.MAPLE_HANGING_WALL_SIGN.get());

        this.tag(ModTags.Blocks.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.get(), ModBlocks.STRIPPED_MAPLE_LOG.get(),
                        ModBlocks.MAPLE_WOOD.get(), ModBlocks.STRIPPED_MAPLE_WOOD.get());
    }
}
