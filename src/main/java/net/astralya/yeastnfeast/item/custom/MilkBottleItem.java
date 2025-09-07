package net.astralya.yeastnfeast.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MilkBottleItem extends AbstractConsumableItem  {

    public MilkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
        Iterator<MobEffectInstance> iterator = entity.getActiveEffects().iterator();
        List<Holder<MobEffect>> removable = new ArrayList<>();

        while (iterator.hasNext()) {
            MobEffectInstance inst = iterator.next();
            if (inst.getCures().contains(EffectCures.MILK)) {
                removable.add(inst.getEffect());
            }
        }

        if (!removable.isEmpty()) {
            Holder<MobEffect> toRemove = removable.get(level.random.nextInt(removable.size()));
            MobEffectInstance inst = entity.getEffect(toRemove);
            if (inst != null && !net.neoforged.neoforge.event.EventHooks.onEffectRemoved(entity, inst, EffectCures.MILK)) {
                entity.removeEffect(toRemove);
            }
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    protected Component getTooltip(ItemStack stack) {
        return Component.translatable("tooltip.yeastnfeast.milk_bottle").withStyle(ChatFormatting.BLUE);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }
}
