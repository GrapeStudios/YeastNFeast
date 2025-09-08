package net.astralya.yeastnfeast.effect;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.effect.custom.OverfedEffect;
import net.astralya.yeastnfeast.effect.custom.VigorousEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> OVERFED = registerStatusEffect("overfed",
            new OverfedEffect(StatusEffectCategory.NEUTRAL, 0x90C19A).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    Identifier.of(YeastNFeastMod.MODID, "overfed"), -0.1f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> VIGOROUS = registerStatusEffect("vigorous",
            new VigorousEffect(StatusEffectCategory.BENEFICIAL, 0xB02B2B, 1.0f).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,
                    Identifier.of(YeastNFeastMod.MODID, "vigorous"), 6.0f, EntityAttributeModifier.Operation.ADD_VALUE));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(YeastNFeastMod.MODID, name), statusEffect);
    }

    public static void registerEffects() {
        YeastNFeastMod.LOGGER.info("Registering Mod Effects for " + YeastNFeastMod.MODID);
    }
}
