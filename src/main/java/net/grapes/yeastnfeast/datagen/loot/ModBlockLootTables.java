package net.grapes.yeastnfeast.datagen.loot;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.*;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // Drops for Wild Crops
        this.add(ModBlocks.WILD_BARLEY.get(), block ->
                createSingleItemTable(ModItems.BARLEY_SEEDS.get()));
        this.add(ModBlocks.WILD_RYE.get(), block ->
                createSingleItemTable(ModItems.RYE_SEEDS.get()));
        this.add(ModBlocks.WILD_GINGER.get(), block ->
                createSingleItemTable(ModItems.GINGER.get()));
        this.add(ModBlocks.WILD_MINT.get(), block ->
                createSingleItemTable(ModItems.MINT_SEEDS.get()));
        this.add(ModBlocks.WILD_GARLIC.get(), block ->
                createSingleItemTable(ModItems.GARLIC.get()));

        // Drops for Crops
        LootItemCondition.Builder builder0 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GINGER_CROP.get(), this.createSimpleCropBlock(ModBlocks.GINGER_CROP.get(),
                ModItems.GINGER.get(), builder0));
        
        LootItemCondition.Builder builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GarlicCropBlock.AGE, 3));
        this.add(ModBlocks.GARLIC_CROP.get(), this.createSimpleCropBlock(ModBlocks.GARLIC_CROP.get(),
                ModItems.GARLIC.get(), builder1));

        LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), this.createCropDrops(ModBlocks.RYE_CROP.get(),
                ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), builder2));

        LootItemCondition.Builder builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), this.createCropDrops(ModBlocks.BARLEY_CROP.get(),
                ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), builder3));

        LootItemCondition.Builder builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintCropBlock.AGE, 3));
        this.add(ModBlocks.MINT_CROP.get(), this.createCropDrops(ModBlocks.MINT_CROP.get(),
                ModItems.MINT.get(), ModItems.MINT_SEEDS.get(), builder4));

        this.add(ModBlocks.ROSE_HIPS_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ROSE_HIPS_BUSH.get(), ModItems.ROSE_HIPS.get(),
                RoseHipsBushBlock.AGE, 3, 2.0f, 3.0f));

        this.add(ModBlocks.ELDERBERRY_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ELDERBERRY_BUSH.get(), ModItems.ELDERBERRIES.get(),
                ElderberryBushBlock.AGE, 3, 2.0f, 3.0f));


        // General Loot
        this.dropSelf(ModBlocks.KEG.get());
        this.dropSelf(ModBlocks.TREE_TAP.get());
        this.dropSelf(ModBlocks.BAG_OF_ELDERBERRIES.get());
        this.dropSelf(ModBlocks.BAG_OF_GARLIC.get());
        this.dropSelf(ModBlocks.BAG_OF_GINGER.get());
        this.dropSelf(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get());
        this.dropSelf(ModBlocks.BAG_OF_LEMON.get());
        this.dropSelf(ModBlocks.BAG_OF_MINT.get());
        this.dropSelf(ModBlocks.BAG_OF_ROSE_HIPS.get());
        this.dropSelf(ModBlocks.BARLEY_BLOCK.get());
        this.dropSelf(ModBlocks.RYE_BLOCK.get());

        // Loot for Wood-related Blocks
        this.add(ModBlocks.MAPLE_LEAVES.get(), createLeavesDrops(ModBlocks.MAPLE_LEAVES.get(),
                ModBlocks.MAPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.MAPLE_LOG.get());
        this.dropSelf(ModBlocks.MAPLE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MAPLE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MAPLE_WOOD.get());
        this.dropSelf(ModBlocks.MAPLE_PLANKS.get());
        this.dropSelf(ModBlocks.MAPLE_SAPLING.get());
        this.add(ModBlocks.POTTED_MAPLE_SAPLING.get(), createPotFlowerItemTable(ModBlocks.MAPLE_SAPLING.get()));
        this.dropSelf(ModBlocks.MAPLE_STAIRS.get());
        this.dropSelf(ModBlocks.MAPLE_PRESSURE_PLATE.get());
        this.add(ModBlocks.MAPLE_SLAB.get(), createSlabItemTable(ModBlocks.MAPLE_SLAB.get()));
        this.dropSelf(ModBlocks.MAPLE_BUTTON.get());
        this.dropSelf(ModBlocks.MAPLE_FENCE.get());
        this.dropSelf(ModBlocks.MAPLE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.MAPLE_TRAPDOOR.get());
        this.add(ModBlocks.MAPLE_DOOR.get(), createDoorTable(ModBlocks.MAPLE_DOOR.get()));
        this.add(ModBlocks.MAPLE_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_SIGN.get()));
        this.add(ModBlocks.MAPLE_WALL_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_SIGN.get()));
        this.add(ModBlocks.MAPLE_HANGING_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_HANGING_SIGN.get()));
        this.add(ModBlocks.MAPLE_HANGING_WALL_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_HANGING_SIGN.get()));

        this.dropSelf(ModBlocks.LEMON_SAPLING.get());
        this.add(ModBlocks.POTTED_LEMON_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.LEMON_SAPLING.get()));
        this.add(ModBlocks.LEMON_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.LEMON_TREE_LEAVES.get(),
                ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), floweringLeavesBlock(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(),
                ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.HAWTHORN_SAPLING.get());
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.POTTED_HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.HAWTHORN_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.HAWTHORN_TREE_LEAVES.get(),
                ModBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), floweringLeavesBlock(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(),
                ModBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected LootTable.Builder createSimpleCropBlock(Block pCropBlock, Item pGrownCropItem, LootItemCondition.Builder pDropGrownCropCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(pGrownCropItem).when(pDropGrownCropCondition)
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected LootTable.Builder createHarvestablePlantBlock(Block bushBlock, Item harvestedItem, IntegerProperty ageProperty, int maxAge,
                                                            float minDropAtMaxAge, float maxDropAtMaxAge) {
        LootItemCondition.Builder maxAgeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(bushBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge));

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(harvestedItem))
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(harvestedItem)
                                .when(maxAgeCondition)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDropAtMaxAge, maxDropAtMaxAge)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 1, 2))
                        ));
    }

    public LootTable.Builder floweringLeavesBlock(Block leaves, Block drop, float... chance) {
        LootTable.Builder lootTable = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves))
                                .add(
                                        LootItem.lootTableItem(drop)
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chance))
                                                .when(ExplosionCondition.survivesExplosion())
                                )
                ).withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_NO_SILK_TOUCH)
                                .add(
                                        LootItem.lootTableItem(Items.STICK)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_SAPLING_CHANCES))
                                                .when(ExplosionCondition.survivesExplosion())
                                )
                );

        if (leaves == ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get()) {
            lootTable.withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SILK_TOUCH)
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(LemonLeavesBlock.AGE, 2)
                                    )
                            )
                            .add(
                                    LootItem.lootTableItem(ModItems.LEMON.get())
                                            .when(ExplosionCondition.survivesExplosion())
                            )
            );
        } else if (leaves == ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get()) {
            lootTable.withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SILK_TOUCH)
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(HawthornLeavesBlock.AGE, 2)
                                    )
                            )
                            .add(
                                    LootItem.lootTableItem(ModItems.HAWTHORN_BERRIES.get())
                                            .when(ExplosionCondition.survivesExplosion())
                            )
            );
        }

        return lootTable;
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}