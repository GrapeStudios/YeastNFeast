package net.astralya.yeastnfeast.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MilkBottleItem extends AbstractConsumableItem {

    public MilkBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void handleEffects(World world, LivingEntity entity, ItemStack stack) {
        if (world.isClient) return;

        List<RegistryEntry<StatusEffect>> removable = new ArrayList<>();
        for (StatusEffectInstance inst : entity.getStatusEffects()) {
            removable.add(inst.getEffectType());
        }

        if (!removable.isEmpty()) {
            RegistryEntry<StatusEffect> toRemove = removable.get(world.getRandom().nextInt(removable.size()));
            entity.removeStatusEffect(toRemove);
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack consumedStack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    protected Text getTooltip(ItemStack stack) {
        return Text.translatable("tooltip.yeastnfeast.milk_bottle").formatted(Formatting.BLUE);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 40;
    }
}