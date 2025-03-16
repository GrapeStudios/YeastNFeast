package net.grapes.yeastnfeast;

import com.mojang.logging.LogUtils;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.entity.ModBlockEntities;
import net.grapes.yeastnfeast.effect.ModEffects;
import net.grapes.yeastnfeast.entity.ModEntities;
import net.grapes.yeastnfeast.entity.client.ModBoatRenderer;
import net.grapes.yeastnfeast.item.ModCreativeModeTabs;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.particle.ModParticles;
import net.grapes.yeastnfeast.recipe.ModRecipes;
import net.grapes.yeastnfeast.screen.KegScreen;
import net.grapes.yeastnfeast.screen.ModMenuTypes;
import net.grapes.yeastnfeast.util.ModRegistries;
import net.grapes.yeastnfeast.util.ModWoodTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(YeastNFeastMod.MOD_ID)
public class YeastNFeastMod {
    public static final String MOD_ID = "yeastnfeast";
    private static final Logger LOGGER = LogUtils.getLogger();

    public YeastNFeastMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModParticles.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModRegistries.registerCompostables();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MAPLE_SAPLING.getId(), ModBlocks.POTTED_MAPLE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LEMON_SAPLING.getId(), ModBlocks.POTTED_LEMON_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HAWTHORN_SAPLING.getId(), ModBlocks.POTTED_HAWTHORN_SAPLING);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModCreativeModeTabs.YEASTNFEAST_TAB.get()) {
            if (ModList.get().isLoaded("hexalia")) {
                event.accept(ModItems.CHILLBERRIES_JAM);
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            Sheets.addWoodType(ModWoodTypes.MAPLE);

            MenuScreens.register(ModMenuTypes.KEG_MENU.get(), KegScreen::new);

        }
    }
}