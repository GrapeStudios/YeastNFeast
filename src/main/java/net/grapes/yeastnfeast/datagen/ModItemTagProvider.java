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
        // Tags
        this.tag(ItemTags.FOX_FOOD)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get(),
                        ModItems.MINT_SEEDS.get());

        // Conventional Tags
        this.tag(ModTags.Items.BREAD)
                .add(Items.BREAD);

        this.tag(ModTags.Items.BERRIES)
                .add(ModItems.ELDERBERRIES.get()).add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        this.tag(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS.get(), ModItems.RYE_SEEDS.get(),
                        ModItems.MINT_SEEDS.get());

        this.tag(ModTags.Items.CROPS)
                .add(ModItems.GINGER.get(), ModItems.GARLIC.get());

        this.tag(ModTags.Items.VEGETABLES)
                .add(ModItems.GARLIC.get());

        this.tag(ModTags.Items.GRAIN)
                .add(ModItems.RYE.get(), ModItems.BARLEY.get());

        this.tag(ModTags.Items.MILK)
                .add(ModItems.MILK_BOTTLE.get());

        this.tag(ModTags.Items.MILK_BOTTLE)
                .add(ModItems.MILK_BOTTLE.get());
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
