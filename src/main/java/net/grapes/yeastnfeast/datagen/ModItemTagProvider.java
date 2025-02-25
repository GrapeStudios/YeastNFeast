package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.item.Items;
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
                .add(ModItems.RYE).add(ModItems.BARLEY)
                .add(Items.WHEAT);

        getOrCreateTagBuilder(ModTags.Items.GRAINS_WHEAT)
                .add(ModItems.RYE).add(ModItems.BARLEY)
                .add(Items.WHEAT);

        getOrCreateTagBuilder(ModTags.Items.MILKS)
                .add(ModItems.MILK_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.MILK_BOTTLE)
                .add(ModItems.MILK_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.COOKED_MEATS)
                .addTag(ModTags.Items.COOKED_BEEF)
                .addTag(ModTags.Items.COOKED_CHICKEN)
                .addTag(ModTags.Items.COOKED_MUTTON)
                .addTag(ModTags.Items.COOKED_PORK);

        getOrCreateTagBuilder(ModTags.Items.COOKED_BEEF)
                .add(Items.COOKED_BEEF);
        getOrCreateTagBuilder(ModTags.Items.COOKED_CHICKEN)
                .add(Items.COOKED_CHICKEN);
        getOrCreateTagBuilder(ModTags.Items.COOKED_MUTTON)
                .add(Items.COOKED_MUTTON);
        getOrCreateTagBuilder(ModTags.Items.COOKED_PORK)
                .add(Items.COOKED_PORKCHOP);

        getOrCreateTagBuilder(ModTags.Items.COOKED_SALMON)
                .add(Items.SALMON);

        getOrCreateTagBuilder(ModTags.Items.COOKED_COD)
                .add(Items.COD);
        
        // Wood-related Tags
        getOrCreateTagBuilder(ModTags.Items.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.asItem(), ModBlocks.STRIPPED_MAPLE_LOG.asItem(),
                        ModBlocks.MAPLE_WOOD.asItem(), ModBlocks.STRIPPED_MAPLE_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.MAPLE_LOGS);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.asItem());

        /*getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.asItem());*/
    }
}
