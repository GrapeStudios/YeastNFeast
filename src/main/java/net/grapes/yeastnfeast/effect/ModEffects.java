package net.grapes.yeastnfeast.effect;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.effect.custom.OverfedEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, YeastNFeastMod.MOD_ID);

    public static final RegistryObject<MobEffect> STUFFED_EFFECT = MOB_EFFECTS.register("overfed",
            () -> new OverfedEffect(MobEffectCategory.BENEFICIAL, 0xDCD789));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
