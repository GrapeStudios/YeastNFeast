package net.grapes.yeastnfeast.datagen.loot;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.*;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
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

        // Crops Loot        
        LootItemCondition.Builder builder0 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GINGER_CROP.get(), this.createSimpleCropBlock(ModBlocks.GINGER_CROP.get(), ModItems.GINGER.get(), builder0));

        LootItemCondition.Builder builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GARLIC_CROP.get(), this.createSimpleCropBlock(ModBlocks.GARLIC_CROP.get(), ModItems.GARLIC.get(), builder1));

        LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), this.createCropDrops(ModBlocks.BARLEY_CROP.get(), ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), builder2));

        LootItemCondition.Builder builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), this.createCropDrops(ModBlocks.RYE_CROP.get(), ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), builder3));

        LootItemCondition.Builder builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.MINT_CROP.get(), this.createCropDrops(ModBlocks.MINT_CROP.get(), ModItems.MINT.get(), ModItems.MINT_SEEDS.get(), builder4));
        
        this.add(ModBlocks.ROSE_HIPS_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ROSE_HIPS_BUSH.get(), ModItems.ROSE_HIPS.get(),
                RoseHipsBushBlock.AGE, 3, 2.0f, 3.0f));
        
        this.add(ModBlocks.ELDERBERRY_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ELDERBERRY_BUSH.get(), ModItems.ELDERBERRIES.get(),
                ElderberryBushBlock.AGE, 3, 2.0f, 3.0f));

        // Blocks
        this.dropSelf(ModBlocks.MEAD_KEG.get());
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



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}