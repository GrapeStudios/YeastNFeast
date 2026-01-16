package net.astralya.yeastnfeast;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.renderer.CheesePressBlockEntityRenderer;
import net.astralya.yeastnfeast.entity.boat.ModBoats;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.astralya.yeastnfeast.screen.ModScreenHandlers;
import net.astralya.yeastnfeast.screen.custom.KegScreen;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

public class YeastNFeastModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
        registerParticles();
        registerBlockEntityRenderers();
        registerWoodTypes();
        registerScreens();
    }

    private void registerBlockRenderLayers(){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.WILD_BARLEY, ModBlocks.WILD_RYE,
                ModBlocks.WILD_GINGER, ModBlocks.WILD_GARLIC,
                ModBlocks.BARLEY_CROP, ModBlocks.RYE_CROP,
                ModBlocks.GINGER_CROP, ModBlocks.GARLIC_CROP,
                ModBlocks.MINT_CROP,
                ModBlocks.ROSE_HIPS_BUSH, ModBlocks.ELDERBERRY_BUSH,
                ModBlocks.MAPLE_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING,
                ModBlocks.MAPLE_DOOR, ModBlocks.MAPLE_TRAPDOOR,
                ModBlocks.LEMON_SAPLING, ModBlocks.POTTED_LEMON_SAPLING,
                ModBlocks.LEMON_TREE_LEAVES, ModBlocks.FLOWERING_LEMON_TREE_LEAVES,
                ModBlocks.HAWTHORN_SAPLING, ModBlocks.POTTED_HAWTHORN_SAPLING,
                ModBlocks.HAWTHORN_TREE_LEAVES, ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES,
                ModBlocks.THISTLE, ModBlocks.POTTED_THISTLE,
                ModBlocks.CHEESE_PRESS
        );
    }

    private void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(ModParticleType.DRIPPING_SYRUP, DrippingSyrupParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleType.MAPLE_LEAVES, MapleLeavesParticle.Factory::new);
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntityTypes.MOD_SIGN, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.MOD_HANGING_SIGN, HangingSignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.CHEESE_PRESS, CheesePressBlockEntityRenderer::new);
    }

    private void registerWoodTypes() {
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ModWoodTypes.MAPLE_WOOD_TYPE, TexturedRenderLayers.getSignTextureId(ModWoodTypes.MAPLE_WOOD_TYPE));
        TerraformBoatClientHelper.registerModelLayers(ModBoats.MAPLE_BOAT_ID, false);
    }

    private void registerScreens() {
        HandledScreens.register(ModScreenHandlers.KEG_SCREEN, KegScreen::new);
    }
}
