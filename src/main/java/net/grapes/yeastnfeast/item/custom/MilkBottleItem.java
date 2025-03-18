package net.grapes.yeastnfeast.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

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
            if (!effect.getEffect().isBeneficial()) {
                removableEffects.add(effect.getEffect());
            }
        }
        if (!removableEffects.isEmpty()) {
            MobEffect selectedEffect = removableEffects.get(level.random.nextInt(removableEffects.size()));
            user.removeEffect(selectedEffect);
        }

        if (user instanceof Player) {
            Player player = (Player) user;
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    return new ItemStack(Items.GLASS_BOTTLE);
                } else {
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
            }
        }

        return stack;
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.yeastnfeast.milk_bottle").withStyle(ChatFormatting.BLUE));
    }
}