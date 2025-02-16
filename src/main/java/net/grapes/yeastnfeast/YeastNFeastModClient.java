package net.grapes.yeastnfeast;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class YeastNFeastModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
    }

    private void registerBlockRenderLayers(){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.WILD_BARLEY, ModBlocks.WILD_RYE,
                ModBlocks.WILD_GINGER, ModBlocks.BARLEY_CROP,
                ModBlocks.RYE_CROP, ModBlocks.GINGER_CROP,
                ModBlocks.ROSE_HIPS_BUSH, ModBlocks.GARLIC_CROP,
                ModBlocks.ELDERBERRY_BUSH, ModBlocks.MINT_CROP
        );
    }
}
