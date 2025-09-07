package net.astralya.yeastnfeast.event;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = YeastNFeastMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleType.MAPLE_LEAVES.get(), MapleLeavesParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ModParticleType.DRIPPING_SYRUP.get(), DrippingSyrupParticle.Factory::new);
    }
}
