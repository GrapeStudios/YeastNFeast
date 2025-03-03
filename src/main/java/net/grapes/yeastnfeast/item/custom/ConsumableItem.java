package net.grapes.yeastnfeast.item.custom;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class ConsumableItem extends HoneyBottleItem {
    public ConsumableItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient && (stack.getItem() == ModItems.MOLASSES || stack.getItem() == ModItems.MAPLE_SYRUP)) {
            user.removeStatusEffect(StatusEffects.MINING_FATIGUE);
        }

        boolean isSpecialItem = stack.getItem() == ModItems.MOLASSES || stack.getItem() == ModItems.MAPLE_SYRUP;
        ItemStack returnStack = isSpecialItem ? new ItemStack(Items.GLASS_BOTTLE) : new ItemStack(ModItems.JAR);

        if (user instanceof PlayerEntity playerEntity && !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return returnStack;
            } else {
                if (!playerEntity.getInventory().insertStack(returnStack)) {
                    playerEntity.dropItem(returnStack, false);
                }
            }
        }

        return stack;
    }
}