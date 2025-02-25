package net.grapes.yeastnfeast;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.entity.ModBlockEntities;
import net.grapes.yeastnfeast.entity.ModBoats;
import net.grapes.yeastnfeast.particle.ModParticles;
import net.grapes.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.grapes.yeastnfeast.util.ModWoodTypes;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

public class YeastNFeastModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
        registerBlockEntityRenderers();
        registerWoodTypes();
        registerParticles();
    }

    private void registerBlockRenderLayers(){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.WILD_BARLEY, ModBlocks.WILD_RYE,
                ModBlocks.WILD_GINGER, ModBlocks.BARLEY_CROP,
                ModBlocks.RYE_CROP, ModBlocks.GINGER_CROP,
                ModBlocks.ROSE_HIPS_BUSH, ModBlocks.GARLIC_CROP,
                ModBlocks.ELDERBERRY_BUSH, ModBlocks.MINT_CROP,
                ModBlocks.MAPLE_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING,
                ModBlocks.MAPLE_DOOR, ModBlocks.MAPLE_TRAPDOOR
        );
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntities.MOD_SIGN_BLOCK_ENTITY, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MOD_HANGING_SIGN_BLOCK_ENTITY, HangingSignBlockEntityRenderer::new);
    }

    private void registerWoodTypes() {
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ModWoodTypes.MAPLE,
                TexturedRenderLayers.getSignTextureId(ModWoodTypes.MAPLE));
        TerraformBoatClientHelper.registerModelLayers(ModBoats.MAPLE_BOAT_ID, false);
    }

    private void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.MAPLE_LEAVES, MapleLeavesParticle.Factory::new);
    }
}
