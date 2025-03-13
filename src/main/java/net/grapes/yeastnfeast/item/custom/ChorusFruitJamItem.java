package net.grapes.yeastnfeast.item.custom;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChorusFruitJamItem extends ConsumableItem {

    public ChorusFruitJamItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        ItemStack itemStack = super.finishUsingItem(stack, world, user);

        if (user instanceof ServerPlayer serverPlayer) {
            serverPlayer.awardStat(Stats.ITEM_USED.get(this)); // Proper stat tracking
        }

        if (!world.isClientSide) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();

            for (int i = 0; i < 16; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                double h = Mth.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8),
                        world.getMinBuildHeight(),
                        world.getMinBuildHeight() + ((ServerLevel) world).getLogicalHeight() - 1);
                double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;

                if (user.isPassenger()) {
                    user.stopRiding();
                }

                Vec3 oldPos = user.position();
                if (user.randomTeleport(g, h, j, true)) {
                    world.playSound(null, d, e, f, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    user.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }

            if (user instanceof Player player) {
                player.getCooldowns().addCooldown(this, 20); // Cooldown of 20 ticks
            }
        }

        if (user instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack jarStack = new ItemStack(ModItems.JAR.get());
            if (!player.getInventory().add(jarStack)) {
                player.drop(jarStack, false);
            }
        }

        return itemStack;
    }

}
