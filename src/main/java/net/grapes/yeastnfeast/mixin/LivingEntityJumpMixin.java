package net.grapes.yeastnfeast.mixin;

import net.grapes.yeastnfeast.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityJumpMixin {
    @Inject(method = "jump", at = @At("TAIL"))
    private void onJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        StatusEffectInstance effect = entity.getStatusEffect(ModEffects.VIGOROUS);
        if (effect != null) {
            float jumpBoost = 0.1f * (effect.getAmplifier() + 1);
            entity.addVelocity(0, jumpBoost, 0);
        }
    }
}
