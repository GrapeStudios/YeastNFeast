package net.grapes.yeastnfeast.item.custom;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ConsumableItem extends HoneyBottleItem {
    public ConsumableItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        ItemStack copy = stack.copy(); // Make a copy of the original stack
        super.finishUsingItem(stack, world, user);

        if (user instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!world.isClientSide && (copy.getItem() == ModItems.MOLASSES.get() || copy.getItem() == ModItems.MAPLE_SYRUP.get())) {
            user.removeEffect(MobEffects.DIG_SLOWDOWN);
        }

        boolean isSpecialItem = copy.getItem() == ModItems.MOLASSES.get() || copy.getItem() == ModItems.MAPLE_SYRUP.get();
        ItemStack returnStack = isSpecialItem ? new ItemStack(Items.GLASS_BOTTLE) : new ItemStack(ModItems.JAR.get());

        if (user instanceof Player player && !player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return returnStack;
            } else {
                if (!player.getInventory().add(returnStack)) {
                    player.drop(returnStack, false);
                }
            }
        }

        return stack.isEmpty() ? returnStack : stack;
    }
}
