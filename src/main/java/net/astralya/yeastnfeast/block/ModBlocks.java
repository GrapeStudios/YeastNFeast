package net.astralya.yeastnfeast.block;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.block.custom.wood.*;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.worldgen.tree.ModTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(YeastNFeastMod.MODID);

    
    // Crop Blocks
    public static final DeferredBlock<Block> BARLEY_CROP = BLOCKS.register("barley_crop",
            () -> new BarleyCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final DeferredBlock<Block> RYE_CROP = BLOCKS.register("rye_crop",
            () -> new RyeCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final DeferredBlock<Block> GINGER_CROP = BLOCKS.register("ginger_crop",
            () -> new GingerCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final DeferredBlock<Block> GARLIC_CROP = BLOCKS.register("garlic_crop",
            () -> new GarlicCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final DeferredBlock<Block> MINT_CROP = BLOCKS.register("mint_crop",
            () -> new MintCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final DeferredBlock<Block> ROSE_HIPS_BUSH = registerBlock("rose_hips_bush",
            () -> new RoseHipsBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final DeferredBlock<Block> ELDERBERRY_BUSH = registerBlock("elderberry_bush",
            () -> new ElderberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    // Functional Blocks
    public static final DeferredBlock<Block> TREE_TAP = registerBlock("tree_tap",
            () -> new TreeTapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final DeferredBlock<Block> KEG = BLOCKS.register("keg",
            () -> new KegBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).noOcclusion()));

    // Storage Blocks
    public static final DeferredBlock<Block> BAG_OF_ELDERBERRIES = registerBlock("bag_of_elderberries",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_GARLIC = registerBlock("bag_of_garlic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_GINGER = registerBlock("bag_of_ginger",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_HAWTHORN_BERRIES = registerBlock("bag_of_hawthorn_berries",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_LEMON = registerBlock("bag_of_lemon",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_MINT = registerBlock("bag_of_mint",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BAG_OF_ROSE_HIPS = registerBlock("bag_of_rose_hips",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> BARLEY_BLOCK = registerBlock("barley_block",
            () -> new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));
    public static final DeferredBlock<Block> RYE_BLOCK = registerBlock("rye_block",
            () -> new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));

    // Wild Crop Blocks
    public static final DeferredBlock<Block> WILD_BARLEY = registerBlock("wild_barley",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> WILD_RYE = registerBlock("wild_rye",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> WILD_GINGER = registerBlock("wild_ginger",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<Block> WILD_GARLIC = registerBlock("wild_garlic",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    // Flowering Tree Blocks
    public static final DeferredBlock<Block> FLOWERING_LEMON_TREE_LEAVES  = registerBlock("flowering_lemon_tree_leaves",
            () -> new LemonLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final DeferredBlock<Block> LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final DeferredBlock<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            () -> new SaplingBlock(ModTreeGrower.LEMON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final DeferredBlock<Block> POTTED_LEMON_SAPLING = BLOCKS.register("potted_lemon_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), LEMON_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));
    public static final DeferredBlock<Block> FLOWERING_HAWTHORN_TREE_LEAVES  = registerBlock("flowering_hawthorn_tree_leaves",
            () -> new HawthornLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final DeferredBlock<Block> HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final DeferredBlock<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            () -> new SaplingBlock(ModTreeGrower.HAWTHORN, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final DeferredBlock<Block> POTTED_HAWTHORN_SAPLING = BLOCKS.register("potted_hawthorn_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), HAWTHORN_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    // Tree-Related Blocks
    public static final DeferredBlock<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new MapleLeavesBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(ModTreeGrower.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final DeferredBlock<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), MAPLE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));
    public static final DeferredBlock<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () -> new StairBlock(ModBlocks.MAPLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_BUTTON = registerBlock("maple_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()));
    public static final DeferredBlock<Block> MAPLE_SIGN = BLOCKS.register("maple_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_WALL_SIGN  = BLOCKS.register("maple_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_HANGING_SIGN  = BLOCKS.register("maple_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAPLE_HANGING_WALL_SIGN = BLOCKS.register("maple_hanging_wall_sign",
            () -> new ModWallHangingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
