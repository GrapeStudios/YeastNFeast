package net.astralya.yeastnfeast;

import net.astralya.yeastnfeast.datagen.*;
import net.astralya.yeastnfeast.worldgen.ModConfiguredFeatures;
import net.astralya.yeastnfeast.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class YeastNFeastModDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModBlockLootTableGenerator::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider(ModPoiTagProvider::new);
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}
