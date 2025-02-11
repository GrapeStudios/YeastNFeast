package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Items {
        // Custom Item Tags

        // Common Item Tags
        public static final TagKey<Item> BERRIES = createCommonItemTag("berries");
        public static final TagKey<Item> SEEDS = createCommonItemTag("seeds");
        public static final TagKey<Item> MUSHROOMS = createCommonItemTag("mushrooms");

        private static TagKey<Item> createItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(YeastNFeastMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }

    public static class Blocks {
        // Custom Block Tags

        // Common Block Tags

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(YeastNFeastMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }
}
