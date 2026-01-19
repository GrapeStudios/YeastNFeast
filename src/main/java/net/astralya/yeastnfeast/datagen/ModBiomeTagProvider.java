package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, YeastNFeastMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Biome Tags
        this.tag(ModTags.Biomes.HAS_ROSE_HIPS)
                .add(Biomes.JUNGLE)
                .add(Biomes.SPARSE_JUNGLE);
    }
}


