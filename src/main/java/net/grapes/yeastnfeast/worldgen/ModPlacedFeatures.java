package net.grapes.yeastnfeast.worldgen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WILD_BARLEY_PLACED_KEY = registerKey("wild_barley_placed");
    public static final ResourceKey<PlacedFeature> WILD_RYE_PLACED_KEY = registerKey("wild_rye_placed");
    public static final ResourceKey<PlacedFeature> WILD_GINGER_PLACED_KEY = registerKey("wild_ginger_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Wild Crops
        register(context, WILD_BARLEY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_BARLEY_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));
        register(context, WILD_RYE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_RYE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));
        register(context, WILD_GINGER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_GINGER_KEY),
                List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(YeastNFeastMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
