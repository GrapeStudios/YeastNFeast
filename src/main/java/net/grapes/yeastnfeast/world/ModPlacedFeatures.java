package net.grapes.yeastnfeast.world;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> WILD_BARLEY_PLACED_KEY = registerKey("wild_barley_placed");
    public static final RegistryKey<PlacedFeature> WILD_RYE_PLACED_KEY = registerKey("wild_rye_placed");
    public static final RegistryKey<PlacedFeature> WILD_GINGER_PLACED_KEY = registerKey("wild_ginger_placed");
    public static final RegistryKey<PlacedFeature> WILD_GARLIC_PLACED_KEY = registerKey("wild_garlic_placed");
    public static final RegistryKey<PlacedFeature> MAPLE_PLACED_KEY = registerKey("maple_placed");
    public static final RegistryKey<PlacedFeature> LEMON_PLACED_KEY = registerKey("lemon_placed");
    public static final RegistryKey<PlacedFeature> HAWTHORN_PLACED_KEY = registerKey("hawthorn_placed");
    public static final RegistryKey<PlacedFeature> ELDERBERRIES_PLACED_KEY = registerKey("elderberries_placed");
    public static final RegistryKey<PlacedFeature> ROSE_HIPS_PLACED_KEY = registerKey("rose_hips_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Wild Crops
        register(context, WILD_BARLEY_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_BARLEY_KEY),
                RarityFilterPlacementModifier.of(20),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(2, 0.2f, 3),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, WILD_RYE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_RYE_KEY),
                RarityFilterPlacementModifier.of(5),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(2, 0.2f, 3),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, WILD_GINGER_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_GINGER_KEY),
                RarityFilterPlacementModifier.of(10),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(4, 0.2f, 3),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, WILD_GARLIC_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILD_GARLIC_KEY),
                RarityFilterPlacementModifier.of(25),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(3, 0.2f, 1),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, ELDERBERRIES_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ELDERBERRIES_KEY),
                RarityFilterPlacementModifier.of(10),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(4, 0.2f, 1),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, ROSE_HIPS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ROSE_HIPS_KEY),
                RarityFilterPlacementModifier.of(10),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(4, 0.2f, 1),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        // Trees
        register(context, MAPLE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        RarityFilterPlacementModifier.of(5),
                        ModBlocks.MAPLE_SAPLING
                ));

        register(context, LEMON_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LEMON_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        RarityFilterPlacementModifier.of(15),
                        ModBlocks.LEMON_SAPLING
                ));

        register(context, HAWTHORN_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HAWTHORN_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        RarityFilterPlacementModifier.of(5),
                        ModBlocks.HAWTHORN_SAPLING
                ));

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(YeastNFeastMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
