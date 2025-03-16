package net.grapes.yeastnfeast.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;
import java.util.UUID;

public class VigorousEffect extends MobEffect {

    private static final UUID HEALTH_BOOST_ID = UUID.fromString("7c7b5f69-45a3-4e6f-b0a2-9db8cf8c94b7");

    public VigorousEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        if (!entity.level().isClientSide()) {
            Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH))
                    .addTransientModifier(new AttributeModifier(HEALTH_BOOST_ID,
                            "Vigorous Health Bonus", 6.0 * (amplifier + 1),
                            AttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        if (!entity.level().isClientSide()) {
            Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH))
                    .removeModifier(HEALTH_BOOST_ID);
        }
    }
}