package net.grapes.yeastnfeast.mixin;

import net.grapes.yeastnfeast.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityFallDamageMixin {
    @ModifyVariable(method = "handleFallDamage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float modifyFallDistance(float fallDistance) {
        LivingEntity entity = (LivingEntity) (Object) this;

        StatusEffectInstance effect = entity.getStatusEffect(ModEffects.VIGOROUS);
        if (effect != null) {
            float reduction = 0.36f * (effect.getAmplifier() + 1);
            reduction = Math.min(reduction, 1.0f);

            return fallDistance * (1 - reduction);
        }

        return fallDistance;
    }
}
