package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.*;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.IntProperty;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Drops for Wild Crops
        addDrop(ModBlocks.WILD_BARLEY);
            this.addDrop(ModBlocks.WILD_BARLEY, ModItems.BARLEY_SEEDS);
        addDrop(ModBlocks.WILD_RYE);
            this.addDrop(ModBlocks.WILD_RYE, ModItems.RYE_SEEDS);
        addDrop(ModBlocks.WILD_GINGER);
            this.addDrop(ModBlocks.WILD_GINGER, ModItems.GINGER);

        // Crops Loot
        BlockStatePropertyLootCondition.Builder builder0 = BlockStatePropertyLootCondition.builder(ModBlocks.GINGER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GingerCropBlock.AGE, 3));
        this.addDrop(ModBlocks.GINGER_CROP, this.createSimpleCropBlock(ModBlocks.GINGER_CROP, ModItems.GINGER, builder0));

        BlockStatePropertyLootCondition.Builder builder1 = BlockStatePropertyLootCondition.builder(ModBlocks.GARLIC_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GingerCropBlock.AGE, 3));
        this.addDrop(ModBlocks.GARLIC_CROP, this.createSimpleCropBlock(ModBlocks.GARLIC_CROP, ModItems.GARLIC, builder1));

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.BARLEY_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(BarleyCropBlock.AGE, 3));
        this.addDrop(ModBlocks.BARLEY_CROP, this.cropDrops(ModBlocks.BARLEY_CROP, ModItems.BARLEY, ModItems.BARLEY_SEEDS, builder2));

        BlockStatePropertyLootCondition.Builder builder3 = BlockStatePropertyLootCondition.builder(ModBlocks.RYE_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(RyeCropBlock.AGE, 3));
        this.addDrop(ModBlocks.RYE_CROP, this.cropDrops(ModBlocks.RYE_CROP, ModItems.RYE, ModItems.RYE_SEEDS, builder3));

        BlockStatePropertyLootCondition.Builder builder4 = BlockStatePropertyLootCondition.builder(ModBlocks.MINT_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(RyeCropBlock.AGE, 3));
        this.addDrop(ModBlocks.MINT_CROP, this.cropDrops(ModBlocks.MINT_CROP, ModItems.MINT, ModItems.MINT_SEEDS, builder4));

        this.addDrop(ModBlocks.ROSE_HIPS_BUSH, createHarvestablePlantBlock(
                ModBlocks.ROSE_HIPS_BUSH, ModItems.ROSE_HIPS,
                RoseHipsBushBlock.AGE, 3, 2.0F, 3.0F));

        this.addDrop(ModBlocks.ELDERBERRY_BUSH, createHarvestablePlantBlock(
                ModBlocks.ELDERBERRY_BUSH, ModItems.ELDERBERRIES,
                RoseHipsBushBlock.AGE, 3, 2.0F, 3.0F));
    }

    protected LootTable.Builder createSimpleCropBlock(Block cropBlock, Item cropItem, LootCondition.Builder dropGrownCropCondition) {
        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(cropItem))
                )
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(cropItem)
                                .conditionally(dropGrownCropCondition)
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                        )
                )
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(cropItem)
                                .conditionally(dropGrownCropCondition)
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3))
                        )
                );
    }

    protected LootTable.Builder createHarvestablePlantBlock(Block bushBlock, Item harvestedItem, IntProperty ageProperty, int maxAge,
                                                            float minDropAtMaxAge, float maxDropAtMaxAge) {
        LootCondition.Builder maxAgeCondition = BlockStatePropertyLootCondition.builder(bushBlock)
                .properties(StatePredicate.Builder.create().exactMatch(ageProperty, maxAge));

        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(harvestedItem))
                )
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(harvestedItem)
                                .conditionally(maxAgeCondition)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDropAtMaxAge, maxDropAtMaxAge)))
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 1, 2))));
    }
}
