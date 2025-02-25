package net.grapes.yeastnfeast.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType MAPLE_LEAVES = registerParticle("maple_leaves", FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(YeastNFeastMod.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        YeastNFeastMod.LOGGER.info("Registering Particles for " + YeastNFeastMod.MOD_ID);
    }
}
