package net.grapes.yeastnfeast.worldgen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.HawthornLeavesBlock;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_BARLEY_KEY = registerKey("wild_barley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_RYE_KEY = registerKey("wild_rye");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_GINGER_KEY = registerKey("wild_ginger");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_GARLIC_KEY = registerKey("wild_garlic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_MINT_KEY = registerKey("wild_mint");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_TREE_KEY = registerKey("lemon_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN_TREE_KEY = registerKey("hawthorn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELDERBERRIES_KEY = registerKey("elderberries");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_HIPS_KEY = registerKey("rose_hips");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        // Wild Crops
        register(context, WILD_BARLEY_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(2, 3, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_BARLEY.get())))));
        register(context, WILD_RYE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(2, 3, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_RYE.get())))));
        register(context, WILD_GINGER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(2, 3, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_GINGER.get())))));
        register(context, WILD_GARLIC_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(2, 3, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_GARLIC.get())))));
        register(context, WILD_MINT_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(2, 3, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_MINT.get())))));


        register(context, ELDERBERRIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(30, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ELDERBERRY_BUSH.get())))));
        register(context, ROSE_HIPS_KEY, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(30, 8, 4, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.ROSE_HIPS_BUSH.get())))));

        // Trees
        register(context, MAPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_LOG.get()),
                new StraightTrunkPlacer(4, 2, 1),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 3) {},
                new TwoLayersFeatureSize(1, 0, 2))
                .build());

        // Lemon tree with weighted leaf provider
        SimpleWeightedRandomList.Builder<BlockState> lemonLeavesBuilder = SimpleWeightedRandomList.builder();
        lemonLeavesBuilder.add(ModBlocks.LEMON_TREE_LEAVES.get().defaultBlockState(), 8);
        lemonLeavesBuilder.add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get().defaultBlockState()
                .setValue(HawthornLeavesBlock.AGE, 2), 1);

        register(context, LEMON_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(3, 2, 1),
                new WeightedStateProvider(lemonLeavesBuilder.build()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .build());

        // Hawthorn tree with weighted leaf provider
        SimpleWeightedRandomList.Builder<BlockState> hawthornLeavesBuilder = SimpleWeightedRandomList.builder();
        hawthornLeavesBuilder.add(ModBlocks.HAWTHORN_TREE_LEAVES.get().defaultBlockState(), 8);
        hawthornLeavesBuilder.add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get().defaultBlockState()
                .setValue(HawthornLeavesBlock.AGE, 2), 1);

        register(context, HAWTHORN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(3, 2, 1),
                new WeightedStateProvider(hawthornLeavesBuilder.build()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F,
                        0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}


