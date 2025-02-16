package net.grapes.yeastnfeast.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class MilkBottleItem extends Item {
    public MilkBottleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        List<MobEffect> removableEffects = new ArrayList<>();

        for (MobEffectInstance effect : user.getActiveEffects()) {
            if (effect.getEffect().isBeneficial()) {
                removableEffects.add(effect.getEffect());
            }
        }

        if (!removableEffects.isEmpty()) {
            MobEffect selectedEffect = removableEffects.get(level.random.nextInt(removableEffects.size()));
            user.removeEffect(selectedEffect);
        }

        return super.finishUsingItem(stack, level, user);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }


}
