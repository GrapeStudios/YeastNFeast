package net.grapes.yeastnfeast.datagen.loot;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.BarleyCropBlock;
import net.grapes.yeastnfeast.block.custom.GingerCropBlock;
import net.grapes.yeastnfeast.block.custom.RyeCropBlock;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
        this.add(ModBlocks.ROSE_HIPS_BUSH.get(),
                createSingleAgeCropBlock(ModBlocks.ROSE_HIPS_BUSH.get(), ModItems.ROSE_HIPS.get()));

        // Drops for Crop Blocks
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), this.createCropDrops(ModBlocks.BARLEY_CROP.get(),
                ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), lootitemcondition$builder1));

        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), this.createCropDrops(ModBlocks.RYE_CROP.get(),
                ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), lootitemcondition$builder2));

        LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GINGER_CROP.get(), this.createSimpleCropBlock(ModBlocks.GINGER_CROP.get(),
                ModItems.GINGER.get(), lootitemcondition$builder4));
    }

    protected LootTable.Builder createSimpleCropBlock(Block pCropBlock, Item pGrownCropItem, LootItemCondition.Builder pDropGrownCropCondition) {
        return this.applyExplosionDecay(pCropBlock, LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(pGrownCropItem).when(pDropGrownCropCondition)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected LootTable.Builder createSingleAgeCropBlock(Block cropBlock, Item grownCropItem) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .add(LootItem.lootTableItem(grownCropItem)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 3)))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 3.0f)))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1)))
                        .setRolls(ConstantValue.exactly(1)));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}