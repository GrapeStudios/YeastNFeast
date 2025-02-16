package net.grapes.yeastnfeast.world;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_BARLEY_KEY = registerKey("wild_barley");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_RYE_KEY = registerKey("wild_rye");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_GINGER_KEY = registerKey("wild_ginger");

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
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(YeastNFeastMod.MOD_ID, name));

    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
