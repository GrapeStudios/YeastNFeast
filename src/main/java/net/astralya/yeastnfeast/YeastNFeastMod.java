package net.astralya.yeastnfeast;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.ModEntities;
import net.astralya.yeastnfeast.entity.boat.ModBoatRenderer;
import net.astralya.yeastnfeast.item.ModCreativeModeTabs;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.astralya.yeastnfeast.screen.custom.KegScreen;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.villager.ModVillagers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(YeastNFeastMod.MODID)
public class YeastNFeastMod {
    public static final String MODID = "yeastnfeast";
    public static final Logger LOGGER = LogUtils.getLogger();

    public YeastNFeastMod(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);
        modContainer.registerConfig(ModConfig.Type.CLIENT, Configuration.CLIENT_CONFIG);
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModParticleType.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LEMON_SAPLING.getId(), ModBlocks.POTTED_LEMON_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HAWTHORN_SAPLING.getId(), ModBlocks.POTTED_HAWTHORN_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MAPLE_SAPLING.getId(), ModBlocks.POTTED_MAPLE_SAPLING);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModCreativeModeTabs.YEASTNFEAST_TAB.get()) {
            if (ModList.get().isLoaded("patchouli")) {
                event.accept(ModItems.HOMESTEADERS_HANDBOOK);
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));

            Sheets.addWoodType(ModWoodTypes.MAPLE);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.KEG_MENU.get(), KegScreen::new);
        }
    }
}
