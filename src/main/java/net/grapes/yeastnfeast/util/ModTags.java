package net.grapes.yeastnfeast.util;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {

        // Conventional Item Tags
        public static final TagKey<Item> BREAD = forgeItemTag("bread");

        public static final TagKey<Item> CROPS = forgeItemTag("crops");

        public static final TagKey<Item> VEGETABLES = forgeItemTag("vegetables");

        public static final TagKey<Item> BERRIES = forgeItemTag("berries");

        public static final TagKey<Item> SEEDS = forgeItemTag("seeds");

        public static final TagKey<Item> GRAIN = forgeItemTag("grain");

        public static final TagKey<Item> MILK = forgeItemTag("milk");
        public static final TagKey<Item> MILK_BOTTLE = forgeItemTag("milk/milk_bottle");
    }

    private static TagKey<Item> tag(String name){
        return ItemTags.create(new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }
    private static TagKey<Item> forgeItemTag(String name){
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    public static class Blocks {

        // Custom Block Tags

        // Common Block Tags

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(YeastNFeastMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
