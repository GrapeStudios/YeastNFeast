package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.IntProperty;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {

    private final RegistryWrapper.WrapperLookup registries;

    public ModBlockLootTableGenerator(FabricDataOutput out, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(out, registries);
        this.registries = registries.join();
    }

    @Override
    public void generate() {
        generatePlantsAndFlowers();
        generateFunctionalBlocks();
        generateCrops();
        generateTreeBlocks();
    }

    private void generatePlantsAndFlowers() {
        addDrop(ModBlocks.LEMON_SAPLING);
        addDrop(ModBlocks.HAWTHORN_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_MAPLE_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_LEMON_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_HAWTHORN_SAPLING);
    }

    private void generateFunctionalBlocks() {
        addDrop(ModBlocks.KEG);
        addDrop(ModBlocks.TREE_TAP);
        addDrop(ModBlocks.BAG_OF_ELDERBERRIES);
        addDrop(ModBlocks.BAG_OF_GARLIC);
        addDrop(ModBlocks.BAG_OF_GINGER);
        addDrop(ModBlocks.BAG_OF_HAWTHORN_BERRIES);
        addDrop(ModBlocks.BAG_OF_LEMON);
        addDrop(ModBlocks.BAG_OF_MINT);
        addDrop(ModBlocks.BAG_OF_ROSE_HIPS);
        addDrop(ModBlocks.BARLEY_BLOCK);
        addDrop(ModBlocks.RYE_BLOCK);
    }

    private void generateCrops() {
        // Wild crops
        this.addDrop(ModBlocks.WILD_RYE, ModItems.RYE_SEEDS);
        this.addDrop(ModBlocks.WILD_BARLEY, ModItems.BARLEY_SEEDS);
        this.addDrop(ModBlocks.WILD_GINGER, ModItems.GINGER);
        this.addDrop(ModBlocks.WILD_GARLIC, ModItems.GARLIC);

        // Main crops
        var matureGinger = BlockStatePropertyLootCondition.builder(ModBlocks.GINGER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GingerCropBlock.AGE, 3));
        this.addDrop(ModBlocks.GINGER_CROP,
                this.createSimpleCropBlock(ModBlocks.GINGER_CROP, ModItems.GINGER, matureGinger));

        var matureGarlic = BlockStatePropertyLootCondition.builder(ModBlocks.GARLIC_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(GarlicCropBlock.AGE, 3));
        this.addDrop(ModBlocks.GARLIC_CROP,
                this.createSimpleCropBlock(ModBlocks.GARLIC_CROP, ModItems.GARLIC, matureGarlic));

        var matureRye = BlockStatePropertyLootCondition.builder(ModBlocks.RYE_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(RyeCropBlock.AGE, 3));
        this.addDrop(ModBlocks.RYE_CROP,
                this.cropDrops(ModBlocks.RYE_CROP, ModItems.RYE, ModItems.RYE_SEEDS, matureRye));

        var matureBarley = BlockStatePropertyLootCondition.builder(ModBlocks.BARLEY_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(BarleyCropBlock.AGE, 3));
        this.addDrop(ModBlocks.BARLEY_CROP,
                this.cropDrops(ModBlocks.BARLEY_CROP, ModItems.BARLEY, ModItems.BARLEY_SEEDS, matureBarley));

        var matureMint = BlockStatePropertyLootCondition.builder(ModBlocks.MINT_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, 3));
        this.addDrop(ModBlocks.MINT_CROP,
                this.cropDrops(ModBlocks.MINT_CROP, ModItems.MINT, ModItems.MINT_SEEDS, matureMint));

        // Special crops
        this.addDrop(ModBlocks.ROSE_HIPS_BUSH,
                createHarvestablePlantBlock(ModBlocks.ROSE_HIPS_BUSH, ModItems.ROSE_HIPS,
                        RoseHipsBushBlock.AGE, 3, 2.0F, 3.0F));

        this.addDrop(ModBlocks.ELDERBERRY_BUSH,
                createHarvestablePlantBlock(ModBlocks.ELDERBERRY_BUSH, ModItems.ELDERBERRIES,
                        ElderberryBushBlock.AGE, 3, 2.0F, 3.0F));

        this.addDrop(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES,
                floweringLeavesWithFruit(
                        ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES,
                        ModBlocks.HAWTHORN_SAPLING.asItem(),
                        ModItems.HAWTHORN_BERRIES,
                        HawthornLeavesBlock.AGE
                ));

        this.addDrop(ModBlocks.FLOWERING_LEMON_TREE_LEAVES,
                floweringLeavesWithFruit(
                        ModBlocks.FLOWERING_LEMON_TREE_LEAVES,
                        ModBlocks.LEMON_SAPLING.asItem(),
                        ModItems.LEMON,
                        LemonLeavesBlock.AGE
                ));
    }

    private void generateTreeBlocks() {
        // Maple
        addDrop(ModBlocks.MAPLE_LEAVES, leavesDrops(ModBlocks.MAPLE_LEAVES, ModBlocks.MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.MAPLE_LOG);
        addDrop(ModBlocks.MAPLE_WOOD);
        addDrop(ModBlocks.STRIPPED_MAPLE_LOG);
        addDrop(ModBlocks.STRIPPED_MAPLE_WOOD);
        addDrop(ModBlocks.MAPLE_PLANKS);
        addDrop(ModBlocks.MAPLE_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_MAPLE_SAPLING);
        addDrop(ModBlocks.MAPLE_STAIRS);
        addDrop(ModBlocks.MAPLE_PRESSURE_PLATE);
        addDrop(ModBlocks.MAPLE_SLAB, slabDrops(ModBlocks.MAPLE_SLAB));
        addDrop(ModBlocks.MAPLE_BUTTON);
        addDrop(ModBlocks.MAPLE_FENCE);
        addDrop(ModBlocks.MAPLE_FENCE_GATE);
        addDrop(ModBlocks.MAPLE_TRAPDOOR);
        addDrop(ModBlocks.MAPLE_DOOR, doorDrops(ModBlocks.MAPLE_DOOR));
        // signs
        addDrop(ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_SIGN.asItem());
        addDrop(ModBlocks.MAPLE_WALL_SIGN, ModBlocks.MAPLE_SIGN.asItem());
        addDrop(ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_HANGING_SIGN.asItem());
        addDrop(ModBlocks.MAPLE_HANGING_WALL_SIGN, ModBlocks.MAPLE_HANGING_SIGN.asItem());

        // Fruit Bearing Trees
        addDrop(ModBlocks.LEMON_TREE_LEAVES, leavesDrops(ModBlocks.MAPLE_LEAVES, ModBlocks.MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.HAWTHORN_TREE_LEAVES, leavesDrops(ModBlocks.MAPLE_LEAVES, ModBlocks.MAPLE_SAPLING, SAPLING_DROP_CHANCE));

    }

    protected LootTable.Builder createSimpleCropBlock(Block cropBlock, Item grownItem, LootCondition.Builder whenMature) {
        RegistryEntry<Enchantment> fortune = this.registries
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.FORTUNE);
        LootTable.Builder table = LootTable.builder()
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(grownItem)
                                .conditionally(whenMature)
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(fortune, 0.5714286F, 3))));
        return this.applyExplosionDecay(cropBlock, table);
    }

    protected LootTable.Builder createHarvestablePlantBlock(Block bushBlock, Item harvestedItem, IntProperty ageProperty, int maxAge, float minDropAtMax, float maxDropAtMax) {
        RegistryEntry<Enchantment> fortune = this.registries
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.FORTUNE);
        LootCondition.Builder maxAgeCondition = BlockStatePropertyLootCondition.builder(bushBlock)
                .properties(StatePredicate.Builder.create().exactMatch(ageProperty, maxAge));
        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(harvestedItem)))
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(harvestedItem)
                                .conditionally(maxAgeCondition)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDropAtMax, maxDropAtMax)))
                                .apply(ApplyBonusLootFunction.binomialWithBonusCount(fortune, 1, 2))));
    }

    protected LootTable.Builder floweringLeavesWithFruit(Block leaves,
                                                         Item sapling,
                                                         Item fruit,
                                                         IntProperty ageProp) {

        RegistryEntry<Enchantment> FORTUNE = this.registries
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.FORTUNE);
        RegistryEntry<Enchantment> SILK_TOUCH = this.registries
                .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.SILK_TOUCH);

        // Tool has Silk Touch >= 1
        LootCondition.Builder toolHasSilkTouch = MatchToolLootCondition.builder(
                ItemPredicate.Builder.create().subPredicate(
                        ItemSubPredicateTypes.ENCHANTMENTS,
                        EnchantmentsPredicate.enchantments(
                                java.util.List.of(new EnchantmentPredicate(SILK_TOUCH, NumberRange.IntRange.atLeast(1)))
                        )
                )
        );

        LootCondition.Builder noSilkTouch = InvertedLootCondition.builder(toolHasSilkTouch);

        // Block state age == 2 (fully grown)
        LootCondition.Builder fullyGrown = BlockStatePropertyLootCondition.builder(leaves)
                .properties(StatePredicate.Builder.create().exactMatch(ageProp, 2));

        // Sapling: Fortune flat chance table (like vanilla leaves)
        LootPool.Builder saplingPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(sapling))
                .conditionally(TableBonusLootCondition.builder(FORTUNE,
                        0.05F, 0.0625F, 0.083333336F, 0.1F));

        // Sticks: only when NOT Silk Touch, count 1–2, uses same Fortune table
        LootPool.Builder sticksPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(noSilkTouch)
                .with(ItemEntry.builder(Items.STICK)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                        .conditionally(TableBonusLootCondition.builder(FORTUNE,
                                0.05F, 0.0625F, 0.083333336F, 0.1F)));

        // Fruit: only when NOT Silk Touch AND fully grown
        LootPool.Builder fruitPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(noSilkTouch)
                .conditionally(fullyGrown)
                .with(ItemEntry.builder(fruit));

        return this.applyExplosionDecay(leaves,
                LootTable.builder()
                        .pool(saplingPool)
                        .pool(sticksPool)
                        .pool(fruitPool));
    }
}
