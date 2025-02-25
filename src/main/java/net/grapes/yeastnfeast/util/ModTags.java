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
        public static final TagKey<Item> MAPLE_LOGS = createItemTag("maple_logs");

        // Common Item Tags
        public static final TagKey<Item> FOODS = createCommonItemTag("foods");

        public static final TagKey<Item> FOODS_BREADS = createCommonItemTag("foods/breads");
        public static final TagKey<Item> CROPS = createCommonItemTag("crops");
        public static final TagKey<Item> FOODS_BERRIES = createCommonItemTag("foods/berries");
        public static final TagKey<Item> FOODS_VEGETABLES = createCommonItemTag("foods/vegetables");
        public static final TagKey<Item> GRAINS = createCommonItemTag("grains");
        public static final TagKey<Item> GRAINS_WHEAT = createCommonItemTag("grains/wheats");

        public static final TagKey<Item> COOKED_MEATS = createCommonItemTag("foods/cooked_meats");
        public static final TagKey<Item> COOKED_BEEF = createCommonItemTag("foods/cooked_meats/cooked_beef");
        public static final TagKey<Item> COOKED_CHICKEN = createCommonItemTag("foods/cooked_meats/cooked_chicken");
        public static final TagKey<Item> COOKED_MUTTON = createCommonItemTag("foods/cooked_meats/cooked_mutton");
        public static final TagKey<Item> COOKED_PORK = createCommonItemTag("foods/cooked_meats/cooked_pork");
        public static final TagKey<Item> COOKED_SALMON = createCommonItemTag("foods/cooked_fishes/salmon");
        public static final TagKey<Item> COOKED_COD = createCommonItemTag("foods/cooked_fishes/cod");


        public static final TagKey<Item> MILKS = createCommonItemTag("milks");
        public static final TagKey<Item> MILK_BOTTLE = createCommonItemTag("milks/milk_bottles");

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

        public static final TagKey<Block> MAPLE_LOGS = createBlockTag("maple_logs");

        // Common Block Tags

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(YeastNFeastMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }
}
