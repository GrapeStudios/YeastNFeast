package net.grapes.yeastnfeast.event;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.entity.layers.ModModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YeastNFeastMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MAPLE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MAPLE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

    }

    @SubscribeEvent
    public static void entityAttributesEvent(EntityAttributeCreationEvent event) {

    }
}
