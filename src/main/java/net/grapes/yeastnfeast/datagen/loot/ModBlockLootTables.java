package net.grapes.yeastnfeast.datagen.loot;

import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.BarleyCropBlock;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), this.createCropDrops(ModBlocks.BARLEY_CROP.get(),
                ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), lootitemcondition$builder1));
        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), this.createCropDrops(ModBlocks.RYE_CROP.get(),
                ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), lootitemcondition$builder2));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}