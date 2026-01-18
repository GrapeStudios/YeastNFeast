package net.astralya.yeastnfeast.particle;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticleType {

    public static final SimpleParticleType MAPLE_LEAVES = registerParticle("maple_leaves", FabricParticleTypes.simple());
    public static final SimpleParticleType DRIPPING_SYRUP = registerParticle("dripping_syrup", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(YeastNFeastMod.MODID, name), particleType);
    }

    public static void registerParticles() {
        YeastNFeastMod.LOGGER.info("Registering Particles for " + YeastNFeastMod.MODID);
    }
}
