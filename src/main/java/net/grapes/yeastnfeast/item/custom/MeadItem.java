package net.grapes.yeastnfeast.item.custom;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class MeadItem extends Item {
    private final Supplier<MobEffect> effectSupplier;
    private final int duration;
    private final int amplifier;
    private final Component tooltip;

    public MeadItem(Properties settings, Supplier<MobEffect> effectSupplier, int duration, int amplifier, Component tooltip) {
        super(settings);
        this.effectSupplier = effectSupplier;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip = tooltip;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        super.finishUsingItem(stack, world, user);

        if (user instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide) {
            MobEffect effect = effectSupplier.get();
            if (effect != null) {
                user.addEffect(new MobEffectInstance(effect, duration, amplifier));
            }
        }

        if (stack.isEmpty()) {
            return new ItemStack(ModItems.TANKARD.get());
        }

        if (user instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack tankardStack = new ItemStack(ModItems.TANKARD.get());
            stack.shrink(1);
            if (!player.getInventory().add(tankardStack)) {
                player.drop(tankardStack, false);
            }
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.startUsingItem(hand); // Forge method to start item use
        return InteractionResultHolder.consume(user.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(tooltip);
    }
}
