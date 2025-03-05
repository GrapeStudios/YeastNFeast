package net.grapes.yeastnfeast.compat.hexalia;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class HexaliaCompat {
    public static void registerCompat() {
        if (FabricLoader.getInstance().isModLoaded("hexalia")) {
            RegistryKey<ItemGroup> farmersDelightGroupKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("hexalia", "hexalia"));
            ItemGroupEvents.modifyEntriesEvent(farmersDelightGroupKey).register(content ->
                    content.add(new ItemStack(ModItems.CHILLBERRIES_JAM)));
        }
    }
}
