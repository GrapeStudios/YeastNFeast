package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
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
        this.tag(ItemTags.FOX_FOOD)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get());

        // Custom Tags
        this.tag(ModTags.Items.GRAIN)
                .add(ModItems.BARLEY.get(), ModItems.RYE.get())
                .add(Items.WHEAT);

        // Custom Tags
        this.tag(ModTags.Items.BERRIES)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        this.tag(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get());

        this.tag(ModTags.Items.CROPS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get())
                .add(ModItems.BARLEY.get(), ModItems.RYE.get());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
