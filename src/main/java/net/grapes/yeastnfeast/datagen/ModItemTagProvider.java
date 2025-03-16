package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, YeastNFeastMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Tags
        this.tag(ItemTags.FOX_FOOD)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get(),
                        ModItems.MINT_SEEDS.get());

        // Conventional Tags
        this.tag(ModTags.Items.BERRIES)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());
        this.tag(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get(),
                        ModItems.MINT_SEEDS.get());
        this.tag(ModTags.Items.MUSHROOM)
                .add(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM);

        this.tag(ModTags.Items.BREAD)
                .add(ModItems.BARLEY_BREAD.get(), ModItems.RYE_BREAD.get(),
                        ModItems.MOLASSES_BREAD.get());

        this.tag(ModTags.Items.CROPS)
                .add(ModItems.GARLIC.get(), ModItems.GINGER.get());

        this.tag(ModTags.Items.GRAINS)
                .add(ModItems.RYE.get(), ModItems.BARLEY.get(),
                        Items.WHEAT);
        this.tag(ModTags.Items.GRAINS_WHEAT)
                .add(ModItems.RYE.get(), ModItems.BARLEY.get(),
                        Items.WHEAT);

        this.tag(ModTags.Items.VEGETABLES)
                .add(ModItems.GARLIC.get());

        this.tag(ModTags.Items.MILK)
                .add(ModItems.MILK_BOTTLE.get());
        this.tag(ModTags.Items.MILK_BOTTLE)
                .add(ModItems.MILK_BOTTLE.get());

        this.tag(ModTags.Items.COOKED_BEEF)
                .add(Items.COOKED_BEEF);
        this.tag(ModTags.Items.COOKED_CHICKEN)
                .add(Items.COOKED_CHICKEN);
        this.tag(ModTags.Items.COOKED_MUTTON)
                .add(Items.COOKED_MUTTON);
        this.tag(ModTags.Items.COOKED_PORK)
                .add(Items.COOKED_PORKCHOP);

        this.tag(ModTags.Items.COOKED_COD)
                .add(Items.COOKED_COD);
        this.tag(ModTags.Items.COOKED_SALMON)
                .add(Items.COOKED_SALMON);

        // Wood-related Tags
        this.tag(ModTags.Items.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.get().asItem(), ModBlocks.STRIPPED_MAPLE_LOG.get().asItem(),
                        ModBlocks.MAPLE_WOOD.get().asItem(), ModBlocks.STRIPPED_MAPLE_WOOD.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.MAPLE_LOGS);

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.get().asItem());
        this.tag(ItemTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.get().asItem());
        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.get().asItem());

        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.get().asItem());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
