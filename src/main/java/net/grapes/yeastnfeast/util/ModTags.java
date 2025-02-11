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
        // Custom Item Tags
        public static final TagKey<Item> GRAIN = tag("grain");

        // Common Item Tags
        public static final TagKey<Item> BERRIES = forgeTag("berries");
        public static final TagKey<Item> SEEDS = forgeTag("seeds");
        public static final TagKey<Item> CROPS = forgeTag("crops");
    }

    private static TagKey<Item> tag(String name){
        return ItemTags.create(new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }
    private static TagKey<Item> forgeTag(String name){
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
