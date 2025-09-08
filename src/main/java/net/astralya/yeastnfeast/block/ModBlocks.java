package net.astralya.yeastnfeast.block;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.block.custom.wood.ModHangingSignBlock;
import net.astralya.yeastnfeast.block.custom.wood.ModSignBlock;
import net.astralya.yeastnfeast.block.custom.wood.ModWallHangingSignBlock;
import net.astralya.yeastnfeast.block.custom.wood.ModWallSignBlock;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.worldgen.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Crop Blocks
    public static final Block BARLEY_CROP = registerBlockWithoutBlockItem("barley_crop",
            new BarleyCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block RYE_CROP = registerBlockWithoutBlockItem("rye_crop",
            new RyeCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block GINGER_CROP = registerBlockWithoutBlockItem("ginger_crop",
            new GingerCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block GARLIC_CROP = registerBlockWithoutBlockItem("garlic_crop",
            new GarlicCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop",
            new MintCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block ROSE_HIPS_BUSH = registerBlockWithoutBlockItem("rose_hips_bush",
            new RoseHipsBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block ELDERBERRY_BUSH = registerBlockWithoutBlockItem("elderberry_bush",
            new ElderberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    // Functional Blocks
    public static final Block TREE_TAP = registerBlock("tree_tap",
            new TreeTapBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block KEG = registerBlockWithoutBlockItem("keg",
            new KegBlock(AbstractBlock.Settings.copy(Blocks.BARREL).nonOpaque()));

    // Storage Blocks
    public static final Block BAG_OF_ELDERBERRIES = registerBlock("bag_of_elderberries",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_GARLIC = registerBlock("bag_of_garlic",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_GINGER = registerBlock("bag_of_ginger",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_HAWTHORN_BERRIES = registerBlock("bag_of_hawthorn_berries",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_LEMON = registerBlock("bag_of_lemon",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_MINT = registerBlock("bag_of_mint",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_ROSE_HIPS = registerBlock("bag_of_rose_hips",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BARLEY_BLOCK = registerBlock("barley_block",
            new HayBlock(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK)));
    public static final Block RYE_BLOCK = registerBlock("rye_block",
            new HayBlock(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK)));

    // Wild Crops
    public static final Block WILD_BARLEY = registerBlock("wild_barley",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_RYE = registerBlock("wild_rye",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_GINGER = registerBlock("wild_ginger",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_GARLIC = registerBlock("wild_garlic",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));

    //Flowering Trees
    public static final Block FLOWERING_LEMON_TREE_LEAVES = registerBlock("flowering_lemon_tree_leaves",
            new LemonLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_SAPLING = registerBlock("lemon_sapling",
            new SaplingBlock(ModSaplingGenerators.LEMON, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final Block POTTED_LEMON_SAPLING = registerBlockWithoutBlockItem("potted_lemon_sapling",
            new FlowerPotBlock(LEMON_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block FLOWERING_HAWTHORN_TREE_LEAVES = registerBlock("flowering_hawthorn_tree_leaves",
            new HawthornLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            new SaplingBlock(ModSaplingGenerators.HAWTHORN, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final Block POTTED_HAWTHORN_SAPLING = registerBlockWithoutBlockItem("potted_hawthorn_sapling",
            new FlowerPotBlock(HAWTHORN_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));

    // Maple
    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves",
            new MapleLeavesBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LEAVES).strength(0.2f)));
    public static final Block MAPLE_SAPLING = registerBlock("maple_sapling",
            new SaplingBlock(ModSaplingGenerators.MAPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_MAPLE_SAPLING = registerBlockWithoutBlockItem("potted_maple_sapling",
            new FlowerPotBlock(MAPLE_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING)));
    public static final Block MAPLE_LOG = registerBlock("maple_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block MAPLE_WOOD = registerBlock("maple_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block MAPLE_PLANKS = registerBlock("maple_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block MAPLE_STAIRS = registerBlock("maple_stairs",
            new StairsBlock(ModBlocks.MAPLE_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.BIRCH_STAIRS)));
    public static final Block MAPLE_SLAB = registerBlock("maple_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_SLAB)));
    public static final Block MAPLE_BUTTON = registerBlock("maple_button",
            new ButtonBlock(BlockSetType.BIRCH, 10, AbstractBlock.Settings.copy(Blocks.BIRCH_BUTTON)));
    public static final Block MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            new PressurePlateBlock(BlockSetType.BIRCH, AbstractBlock.Settings.copy(Blocks.BIRCH_PRESSURE_PLATE)));
    public static final Block MAPLE_FENCE = registerBlock("maple_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_FENCE)));
    public static final Block MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            new FenceGateBlock(WoodType.BIRCH, AbstractBlock.Settings.copy(Blocks.BIRCH_FENCE_GATE)));
    public static final Block MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            new TrapdoorBlock(BlockSetType.BIRCH, AbstractBlock.Settings.copy(Blocks.BIRCH_TRAPDOOR).nonOpaque()));
    public static final Block MAPLE_DOOR = registerBlock("maple_door",
            new DoorBlock(BlockSetType.BIRCH, AbstractBlock.Settings.copy(Blocks.BIRCH_DOOR).nonOpaque()));
    public static final Block MAPLE_SIGN = registerBlockWithoutBlockItem("maple_sign",
            new ModSignBlock(ModWoodTypes.MAPLE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.BIRCH_SIGN).nonOpaque()));
    public static final Block MAPLE_WALL_SIGN = registerBlockWithoutBlockItem("maple_wall_sign",
            new ModWallSignBlock(ModWoodTypes.MAPLE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.BIRCH_WALL_SIGN).nonOpaque()));
    public static final Block MAPLE_HANGING_SIGN = registerBlockWithoutBlockItem("maple_hanging_sign",
            new ModHangingSignBlock(ModWoodTypes.MAPLE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.BIRCH_SIGN).nonOpaque()));
    public static final Block MAPLE_HANGING_WALL_SIGN = registerBlockWithoutBlockItem("maple_hanging_wall_sign",
            new ModWallHangingSignBlock(ModWoodTypes.MAPLE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.BIRCH_SIGN).nonOpaque()));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(YeastNFeastMod.MODID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(YeastNFeastMod.MODID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(YeastNFeastMod.MODID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        YeastNFeastMod.LOGGER.info("Registering Mod Blocks for " + YeastNFeastMod.MODID);
    }
}
