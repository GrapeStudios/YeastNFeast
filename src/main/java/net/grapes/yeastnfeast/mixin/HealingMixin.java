package net.grapes.yeastnfeast.mixin;

import net.grapes.yeastnfeast.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class HealingMixin {
    @Inject(method = "heal", at = @At("HEAD"), cancellable = true)
    private void modifyHealing(float amount, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(ModEffects.VIGOROUS.get())) {
            MobEffectInstance effectInstance = entity.getEffect(ModEffects.VIGOROUS.get());
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float boost = amount * (0.25f + (0.25f * amplifier));
                entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + boost));
                ci.cancel();
            }
        }
    }
}