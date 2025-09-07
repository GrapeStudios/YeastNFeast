package net.astralya.yeastnfeast.particle;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticleType {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, YeastNFeastMod.MODID);

    public static final Supplier<SimpleParticleType> MAPLE_LEAVES = PARTICLE_TYPES.register("maple_leaves",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> DRIPPING_SYRUP = PARTICLE_TYPES.register("dripping_syrup",
            () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
