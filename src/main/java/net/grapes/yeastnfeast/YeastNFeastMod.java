package net.grapes.yeastnfeast;

import com.mojang.logging.LogUtils;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModCreativeModeTabs;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.ELDERBERRIES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.HAWTHORN_BERRIES.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.ROSE_HIPS.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.MINT.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.GINGER.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.VANILLA.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.RYE.get(), 0.30f);
            ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY_SEEDS.get(), 0.20f);
            ComposterBlock.COMPOSTABLES.put(ModItems.RYE_SEEDS.get(), 0.20f);
            ComposterBlock.COMPOSTABLES.put(ModItems.VANILLA_SEEDS.get(), 0.20f);
        });
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}