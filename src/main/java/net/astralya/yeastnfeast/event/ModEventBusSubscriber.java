package net.astralya.yeastnfeast.event;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.entity.layers.ModModelLayers;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = YeastNFeastMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MAPLE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MAPLE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

}
