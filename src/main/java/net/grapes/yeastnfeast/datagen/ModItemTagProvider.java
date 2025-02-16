package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Tags
        getOrCreateTagBuilder(ItemTags.FOX_FOOD)
                .add(ModItems.ROSE_HIPS).add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS).add(ModItems.RYE_SEEDS)
                .add(ModItems.MINT_SEEDS);

        // Common Tags
        getOrCreateTagBuilder(ModTags.Items.FOODS)
                .add(ModItems.ROSE_HIPS).add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES).add(ModItems.BARLEY_BREAD)
                .add(ModItems.RYE_BREAD).add(ModItems.MOLASSES_BREAD)
                .add(ModItems.GINGER).add(ModItems.GARLIC)
                .add(ModItems.LEMON).add(ModItems.GINGER)
                .add(ModItems.BERRY_ROLL).add(ModItems.ROSE_TART)
                .add(ModItems.ELDERBERRY_PIE).add(ModItems.APPLE_PIE);

        getOrCreateTagBuilder(ModTags.Items.BERRIES)
                .add(ModItems.ROSE_HIPS).add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS).add(ModItems.RYE_SEEDS)
                .add(ModItems.MINT_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.FOODS_BREADS)
                .add(ModItems.BARLEY_BREAD).add(ModItems.RYE_BREAD)
                .add(ModItems.MOLASSES_BREAD);

        getOrCreateTagBuilder(ModTags.Items.CROPS)
                .add(ModItems.GINGER).add(ModItems.GARLIC);

        getOrCreateTagBuilder(ModTags.Items.FOODS_BERRIES)
                .add(ModItems.ROSE_HIPS).add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.FOODS_VEGETABLES)
                .add(ModItems.GARLIC);

        getOrCreateTagBuilder(ModTags.Items.GRAINS)
                .add(ModItems.RYE).add(ModItems.BARLEY);

        getOrCreateTagBuilder(ModTags.Items.MILKS)
                .add(ModItems.MILK_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.MILK_BOTTLE)
                .add(ModItems.MILK_BOTTLE);
    }
}
