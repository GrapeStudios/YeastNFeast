package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.*;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
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
        
        
        // Loot for Wood-related Blocks
        addDrop(ModBlocks.MAPLE_LEAVES, leavesDrops(ModBlocks.MAPLE_LEAVES, ModBlocks.MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.MAPLE_LOG);
        addDrop(ModBlocks.MAPLE_WOOD);
        addDrop(ModBlocks.STRIPPED_MAPLE_LOG);
        addDrop(ModBlocks.STRIPPED_MAPLE_WOOD);
        addPottedPlantDrops(ModBlocks.POTTED_MAPLE_SAPLING);
        addDrop(ModBlocks.MAPLE_PLANKS);
        addDrop(ModBlocks.MAPLE_SAPLING);
        addDrop(ModBlocks.MAPLE_STAIRS);
        addDrop(ModBlocks.MAPLE_PRESSURE_PLATE);
        addDrop(ModBlocks.MAPLE_SLAB, slabDrops(ModBlocks.MAPLE_SLAB));
        addDrop(ModBlocks.MAPLE_BUTTON);
        addDrop(ModBlocks.MAPLE_FENCE);
        addDrop(ModBlocks.MAPLE_FENCE_GATE);
        addDrop(ModBlocks.MAPLE_TRAPDOOR);
        addDrop(ModBlocks.MAPLE_DOOR, doorDrops(ModBlocks.MAPLE_DOOR));
        addDrop(ModBlocks.MAPLE_SIGN);
        addDrop(ModBlocks.MAPLE_WALL_SIGN);
        addDrop(ModBlocks.MAPLE_HANGING_WALL_SIGN);
        addDrop(ModBlocks.MAPLE_HANGING_SIGN);

        addDrop(ModBlocks.LEMON_SAPLING);
        addDrop(ModBlocks.FLOWERING_LEMON_TREE_LEAVES, lemonLeavesDrop(ModBlocks.FLOWERING_LEMON_TREE_LEAVES,
                ModBlocks.LEMON_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.LEMON_TREE_LEAVES, lemonLeavesDrop(ModBlocks.LEMON_TREE_LEAVES,
                ModBlocks.LEMON_SAPLING, SAPLING_DROP_CHANCE));

        addDrop(ModBlocks.HAWTHORN_SAPLING);
        addDrop(ModBlocks.HAWTHORN_TREE_LEAVES, lemonLeavesDrop(ModBlocks.HAWTHORN_TREE_LEAVES,
                ModBlocks.HAWTHORN_SAPLING, SAPLING_DROP_CHANCE));
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

    public LootTable.Builder lemonLeavesDrop(Block leaves, Block drop, float... chance) {
        LootTable.Builder lootTable = dropsWithSilkTouchOrShears(
                leaves,
                ((LeafEntry.Builder) this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)))
                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, chance))
        ).pool(
                LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(
                                ((LeafEntry.Builder) this.applyExplosionDecay(
                                        leaves, ItemEntry.builder(Items.STICK)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                )).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE))
                        )
        );

        // Check if the block is Flowering Lemon Tree Leaves
        if (leaves == ModBlocks.FLOWERING_LEMON_TREE_LEAVES) {
            lootTable.pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F)) // Always rolls
                            .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS) // Only drops if no silk touch
                            .conditionally(BlockStatePropertyLootCondition.builder(leaves)
                                    .properties(StatePredicate.Builder.create().exactMatch(LemonLeavesBlock.AGE, 2)) // Correct age check
                            )
                            .with(
                                    this.applyExplosionDecay(
                                            leaves, ItemEntry.builder(ModItems.LEMON) // Drop a Lemon
                                    )
                            )
            );
        }

        return lootTable;
    }

}
