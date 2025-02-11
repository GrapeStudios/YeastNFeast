package net.grapes.yeastnfeast.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.custom.BarleyCropBlock;
import net.grapes.yeastnfeast.block.custom.GingerCropBlock;
import net.grapes.yeastnfeast.block.custom.RoseHipsBushBlock;
import net.grapes.yeastnfeast.block.custom.RyeCropBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Crop Blocks
    public static final Block BARLEY_CROP = registerBlockWithoutBlockItem("barley_crop",
            new BarleyCropBlock(FabricBlockSettings.copyOf(Blocks.POTATOES)));
    public static final Block RYE_CROP = registerBlockWithoutBlockItem("rye_crop",
            new RyeCropBlock(FabricBlockSettings.copyOf(Blocks.POTATOES)));
    public static final Block GINGER_CROP = registerBlockWithoutBlockItem("ginger_crop",
            new GingerCropBlock(FabricBlockSettings.copyOf(Blocks.POTATOES)));
    public static final Block ROSE_HIPS_BUSH = registerBlockWithoutBlockItem("rose_hips_bush",
            new RoseHipsBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));

    // Wild Crop Blocks
    public static final Block WILD_BARLEY = registerBlock("wild_barley",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block WILD_RYE = registerBlock("wild_rye",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block WILD_GINGER = registerBlock("wild_ginger",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(YeastNFeastMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(YeastNFeastMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(YeastNFeastMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        YeastNFeastMod.LOGGER.info("Registering Blocks for " + YeastNFeastMod.MOD_ID);
    }
}
