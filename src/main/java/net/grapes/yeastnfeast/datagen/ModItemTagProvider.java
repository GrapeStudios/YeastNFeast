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
                .add(ModItems.BARLEY_SEEDS).add(ModItems.RYE_SEEDS);

        // Common Tags
        getOrCreateTagBuilder(ModTags.Items.BERRIES)
                .add(ModItems.ROSE_HIPS).add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS).add(ModItems.RYE_SEEDS);
    }
}
