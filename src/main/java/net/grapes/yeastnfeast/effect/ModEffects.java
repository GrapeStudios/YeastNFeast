package net.grapes.yeastnfeast.effect;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.effect.custom.OverfedEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, YeastNFeastMod.MOD_ID);

    public static final RegistryObject<MobEffect> OVERFED_EFFECT = MOB_EFFECTS.register("overfed",
            () -> new OverfedEffect(MobEffectCategory.BENEFICIAL, 0xDCD789)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "BF8B6E3F-3328-4C0A-AA66-3BA6BB6DBEF6", -0.1f,
                            AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
