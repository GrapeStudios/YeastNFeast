package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
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
    }
}
