package net.astralya.yeastnfeast.effect.custom;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class VigorousEffect extends StatusEffect {

    protected final double modifier;

    public VigorousEffect(StatusEffectCategory category, int color, double modifier) {
        super(category, color);
        this.modifier = modifier;
    }

    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
        return this.modifier * (double)(amplifier + 1);
    }
}
