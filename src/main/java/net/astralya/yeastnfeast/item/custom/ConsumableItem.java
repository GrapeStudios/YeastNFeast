package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ConsumableItem extends AbstractConsumableItem {
    public ConsumableItem(Properties props) {
        super(props);
    }

    @Override
    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
        if (!level.isClientSide && (stack.is(ModItems.MOLASSES.get()) || stack.is(ModItems.MAPLE_SYRUP.get()))) {
            entity.removeEffect(MobEffects.DIG_SLOWDOWN);
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return stack.is(ModItems.MOLASSES.get()) || stack.is(ModItems.MAPLE_SYRUP.get())
                ? new ItemStack(Items.GLASS_BOTTLE)
                : new ItemStack(ModItems.JAR.get());
    }
}

