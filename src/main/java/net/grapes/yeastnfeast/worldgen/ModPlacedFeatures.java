package net.grapes.yeastnfeast.worldgen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WILD_BARLEY_PLACED_KEY = registerKey("wild_barley_placed");
    public static final ResourceKey<PlacedFeature> WILD_RYE_PLACED_KEY = registerKey("wild_rye_placed");
    public static final ResourceKey<PlacedFeature> WILD_GINGER_PLACED_KEY = registerKey("wild_ginger_placed");
    public static final ResourceKey<PlacedFeature> WILD_GARLIC_PLACED_KEY = registerKey("wild_garlic_placed");
    public static final ResourceKey<PlacedFeature> MAPLE_PLACED_KEY = registerKey("maple_placed");
    public static final ResourceKey<PlacedFeature> LEMON_PLACED_KEY = registerKey("lemon_placed");
    public static final ResourceKey<PlacedFeature> HAWTHORN_PLACED_KEY = registerKey("hawthorn_placed");
    public static final ResourceKey<PlacedFeature> ELDERBERRIES_PLACED_KEY = registerKey("elderberries_placed");
    public static final ResourceKey<PlacedFeature> ROSE_HIPS_PLACED_KEY = registerKey("rose_hips_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Wild Crops
        register(context, WILD_BARLEY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_BARLEY_KEY),
                List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.countExtra(2, 0.2f, 3),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, WILD_RYE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_RYE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.countExtra(2, 0.2f, 3),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        
        register(context, WILD_GARLIC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_GARLIC_KEY),
                List.of(RarityFilter.onAverageOnceEvery(25), InSquarePlacement.spread(), PlacementUtils.countExtra(4, 0.2f, 1),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, WILD_GINGER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_GINGER_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.countExtra(4, 0.2f, 1),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        
        register(context, ELDERBERRIES_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ELDERBERRIES_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.countExtra(4, 0.2f, 1),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        
        register(context, ROSE_HIPS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROSE_HIPS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.countExtra(4, 0.2f, 1),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        // Trees
        register(context, MAPLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(5),
                        ModBlocks.MAPLE_SAPLING.get()));

        register(context, LEMON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_TREE_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(15),
                        ModBlocks.LEMON_SAPLING.get()));

        register(context, HAWTHORN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_TREE_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(5),
                        ModBlocks.HAWTHORN_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
