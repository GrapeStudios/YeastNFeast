package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {

        // Custom Item Tags
        public static final TagKey<Item> MAPLE_LOGS = tag("maple_logs");

        // Conventional Item Tags
        public static final TagKey<Item> BREAD = forgeItemTag("bread");
        public static final TagKey<Item> CROPS = forgeItemTag("crops.json");
        public static final TagKey<Item> BERRIES = forgeItemTag("berries");
        public static final TagKey<Item> VEGETABLES = forgeItemTag("vegetables");
        public static final TagKey<Item> GRAINS = forgeItemTag("grains");
        public static final TagKey<Item> GRAINS_WHEAT = forgeItemTag("grains/wheat");

        public static final TagKey<Item> COOKED_BEEF = forgeItemTag("cooked_beef");
        public static final TagKey<Item> COOKED_CHICKEN = forgeItemTag("cooked_chicken");
        public static final TagKey<Item> COOKED_MUTTON = forgeItemTag("cooked_mutton");
        public static final TagKey<Item> COOKED_PORK = forgeItemTag("cooked_pork");

        public static final TagKey<Item> COOKED_COD = forgeItemTag("cooked_fishes/cod");
        public static final TagKey<Item> COOKED_SALMON = forgeItemTag("cooked_fishes/salmon");

        public static final TagKey<Item> MILK = forgeItemTag("milk");
        public static final TagKey<Item> MILK_BOTTLE = forgeItemTag("milk/milk_bottle");

        public static final TagKey<Item> SEEDS = forgeItemTag("seeds");
        public static final TagKey<Item> MUSHROOM = forgeItemTag("mushroom");
    }

    private static TagKey<Item> tag(String name){
        return ItemTags.create(new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }
    private static TagKey<Item> forgeItemTag(String name){
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    public static class Blocks {

        // Custom Block Tags
        public static final TagKey<Block> MAPLE_LOGS = tag("maple_logs");
        public static final TagKey<Block> STORAGE_BLOCKS = tag("storage_blocks");

        // Conventional Block Tags

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(YeastNFeastMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_ROSE_HIPS = TagKey.create(Registries.BIOME,
                (new ResourceLocation(YeastNFeastMod.MOD_ID, "has_rose_hips")));
    }
}
