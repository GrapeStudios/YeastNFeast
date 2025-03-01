package net.grapes.yeastnfeast.world;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_BARLEY_KEY = registerKey("wild_barley");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_RYE_KEY = registerKey("wild_rye");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_GINGER_KEY = registerKey("wild_ginger");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEMON_TREE_KEY = registerKey("lemon_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HAWTHORN_TREE_KEY = registerKey("hawthorn_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Wild Crops
        register(context, WILD_BARLEY_KEY, Feature.FLOWER, ConfiguredFeatures.createRandomPatchFeatureConfig(3,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_BARLEY)))));
        register(context, WILD_RYE_KEY, Feature.FLOWER, ConfiguredFeatures.createRandomPatchFeatureConfig(3,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_RYE)))));
        register(context, WILD_GINGER_KEY, Feature.FLOWER, ConfiguredFeatures.createRandomPatchFeatureConfig(3,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_GINGER)))));

        // Trees
        register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.MAPLE_LOG),
                new StraightTrunkPlacer(4, 2, 1),
                BlockStateProvider.of(ModBlocks.MAPLE_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3) {},
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .build());

        register(context, LEMON_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(3, 2, 1),
                new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(ModBlocks.LEMON_TREE_LEAVES.getDefaultState(), 8)
                        .add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.getDefaultState(), 1)
                        .build()),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        )
                .ignoreVines()
                .build());

        register(context, HAWTHORN_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(3, 2, 1),
                new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(ModBlocks.HAWTHORN_TREE_LEAVES.getDefaultState(), 8)
                        .add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.getDefaultState(), 1)
                        .build()),
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.25F,
                        0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2)
        )
                .ignoreVines()
                .build());

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(YeastNFeastMod.MOD_ID, name));

    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
