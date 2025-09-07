package net.astralya.yeastnfeast.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public abstract class AbstractConsumableItem extends Item {
    public AbstractConsumableItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(stack, level, livingEntity);

        if (livingEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
        }

        handleEffects(level, livingEntity, stack);

        ItemStack container = getReturnContainer(stack);
        if (stack.isEmpty()) return container;

        if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
            if (!player.getInventory().add(container)) {
                player.drop(container, false);
            }
        }

        return stack;
    }

    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {

    }

    protected abstract ItemStack getReturnContainer(ItemStack consumedStack);

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltip,
                                TooltipFlag flag) {
        Component line = getTooltip(stack);

        if (line != null) {
            tooltip.add(line);
        }
    }

    @Nullable
    protected Component getTooltip(ItemStack stack) {
        return null;
    }

}
