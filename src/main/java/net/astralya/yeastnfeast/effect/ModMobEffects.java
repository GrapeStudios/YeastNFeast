package net.astralya.yeastnfeast.effect;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.effect.custom.OverfedEffect;
import net.astralya.yeastnfeast.effect.custom.VigorousEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, YeastNFeastMod.MODID);

    public static final Holder<MobEffect> OVERFED = MOB_EFFECTS.register("overfed",
            () -> new OverfedEffect(MobEffectCategory.NEUTRAL, 0x36ebab).addAttributeModifier(Attributes.MOVEMENT_SPEED,
                    ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "overfed"), -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> VIGOROUS = MOB_EFFECTS.register("vigorous",
            () -> new VigorousEffect(MobEffectCategory.BENEFICIAL, 0x90C19A, 0).addAttributeModifier(Attributes.MAX_HEALTH,
                    ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "vigorous"), 6.0f, AttributeModifier.Operation.ADD_VALUE));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
