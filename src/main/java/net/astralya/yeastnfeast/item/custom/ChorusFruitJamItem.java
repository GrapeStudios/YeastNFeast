package net.astralya.yeastnfeast.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ChorusFruitJamItem extends ConsumableItem {

    public ChorusFruitJamItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void handleEffects(World world, LivingEntity entity, ItemStack stack) {
        if (world.isClient || !(world instanceof ServerWorld serverWorld)) return;

        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        for (int i = 0; i < 16; ++i) {
            double targetX = x + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
            double targetY = MathHelper.clamp(
                    y + entity.getRandom().nextInt(16) - 8,
                    world.getBottomY(),
                    world.getBottomY() + serverWorld.getLogicalHeight() - 1
            );
            double targetZ = z + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;

            if (entity.hasVehicle()) {
                entity.stopRiding();
            }

            if (entity.teleport(targetX, targetY, targetZ, true)) {
                world.playSound(null, x, y, z, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                break;
            }
        }

        if (entity instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, 20);
        }
    }
}