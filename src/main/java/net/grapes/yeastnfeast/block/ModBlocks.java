package net.grapes.yeastnfeast.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.custom.*;
import net.grapes.yeastnfeast.block.custom.signs.ModHangingSignBlock;
import net.grapes.yeastnfeast.block.custom.signs.ModStandingSignBlock;
import net.grapes.yeastnfeast.block.custom.signs.ModWallHangingSignBlock;
import net.grapes.yeastnfeast.block.custom.signs.ModWallSignBlock;
import net.grapes.yeastnfeast.util.ModWoodTypes;
import net.grapes.yeastnfeast.world.tree.HawthornSaplingGenerator;
import net.grapes.yeastnfeast.world.tree.LemonSaplingGenerator;
import net.grapes.yeastnfeast.world.tree.MapleSaplingGenerator;
import net.minecraft.block.*;
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
    public static final Block GARLIC_CROP = registerBlockWithoutBlockItem("garlic_crop",
            new GarlicCropBlock(FabricBlockSettings.copyOf(Blocks.POTATOES)));
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop",
            new MintCropBlock(FabricBlockSettings.copyOf(Blocks.POTATOES)));
    public static final Block ROSE_HIPS_BUSH = registerBlockWithoutBlockItem("rose_hips_bush",
            new RoseHipsBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));
    public static final Block ELDERBERRY_BUSH = registerBlockWithoutBlockItem("elderberry_bush",
            new ElderberryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));
    public static final Block TREE_TAP = registerBlock("tree_tap",
            new TreeTapBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    // Wild Crop Blocks
    public static final Block WILD_BARLEY = registerBlock("wild_barley",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block WILD_RYE = registerBlock("wild_rye",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block WILD_GINGER = registerBlock("wild_ginger",
            new FlowerBlock(StatusEffects.LUCK, 6, FabricBlockSettings.copyOf(Blocks.ALLIUM)));

    public static final Block FLOWERING_LEMON_TREE_LEAVES = registerBlock("flowering_lemon_tree_leaves",
            new LemonLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_SAPLING = registerBlock("lemon_sapling",
            new SaplingBlock(new LemonSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_LEMON_SAPLING = registerBlock("potted_lemon_sapling",
            new FlowerPotBlock(LEMON_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));

    public static final Block FLOWERING_HAWTHORN_TREE_LEAVES = registerBlock("flowering_hawthorn_tree_leaves",
            new HawthornLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            new SaplingBlock(new HawthornSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_HAWTHORN_SAPLING = registerBlock("potted_hawthorn_sapling",
            new FlowerPotBlock(HAWTHORN_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));

    // Wooden-Related Blocks
    public static final Block MAPLE_LOG = registerBlock("maple_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MAPLE_WOOD = registerBlock("maple_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MAPLE_PLANKS = registerBlock("maple_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves",
            new MapleLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));

    public static final Block MAPLE_STAIRS = registerBlock("maple_stairs",
            new StairsBlock(ModBlocks.MAPLE_PLANKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block MAPLE_SLAB = registerBlock("maple_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block MAPLE_BUTTON = registerBlock("maple_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    public static final Block MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Block MAPLE_FENCE = registerBlock("maple_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));

    public static final Block MAPLE_SIGN = registerBlockWithoutBlockItem("maple_sign",
            new ModStandingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN).nonOpaque(), ModWoodTypes.MAPLE));
    public static final Block MAPLE_WALL_SIGN = registerBlockWithoutBlockItem("maple_wall_sign",
            new ModWallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_SIGN = registerBlockWithoutBlockItem("maple_hanging_sign",
            new ModHangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_WALL_SIGN = registerBlockWithoutBlockItem("maple_hanging_wall_sign",
            new ModWallHangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_SAPLING = registerBlock("maple_sapling",
            new SaplingBlock(new MapleSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_MAPLE_SAPLING = registerBlock("potted_maple_sapling",
            new FlowerPotBlock(LEMON_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
    public static final Block MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).nonOpaque(), BlockSetType.OAK));
    public static final Block MAPLE_DOOR = registerBlock("maple_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).nonOpaque(), BlockSetType.OAK));

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
