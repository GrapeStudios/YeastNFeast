package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                .add(ModBlocks.BARLEY_CROP.get(),
                        ModBlocks.RYE_CROP.get(), ModBlocks.GINGER_CROP.get(),
                        ModBlocks.GARLIC_CROP.get(), ModBlocks.MINT_CROP.get());

        this.tag(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.BARLEY_CROP.get(),
                        ModBlocks.RYE_CROP.get(), ModBlocks.GINGER_CROP.get(),
                        ModBlocks.GARLIC_CROP.get(), ModBlocks.MINT_CROP.get());
    }
}
