package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.custom.BarleyCropBlock;
import net.grapes.yeastnfeast.block.custom.RoseHipsBushBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, YeastNFeastMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Crop Blocks
        makeCrop(((BarleyCropBlock) ModBlocks.BARLEY_CROP.get()), "barley_stage", "barley_stage");
        makeRoseHipsBush(((RoseHipsBushBlock) ModBlocks.ROSE_HIPS_BUSH.get()), "rose_hips_bush_stage", "rose_hips_bush_stage");

        // Wild Crop Blocks
        simpleBlock(ModBlocks.WILD_BARLEY.get(), models().cross(blockTexture(ModBlocks.WILD_BARLEY.get()).getPath(),
                        blockTexture(ModBlocks.WILD_BARLEY.get())).renderType("cutout"));
        simpleBlock(ModBlocks.WILD_RYE.get(), models().cross(blockTexture(ModBlocks.WILD_RYE.get()).getPath(),
                        blockTexture(ModBlocks.WILD_RYE.get())).renderType("cutout"));
        simpleBlock(ModBlocks.WILD_GINGER.get(), models().cross(blockTexture(ModBlocks.WILD_GINGER.get()).getPath(),
                        blockTexture(ModBlocks.WILD_GINGER.get())).renderType("cutout"));
    }



    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    public void makeRoseHipsBush(BushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> roseHipsState(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }


    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BarleyCropBlock) block).getAgeProperty()),
                new ResourceLocation(YeastNFeastMod.MOD_ID, "block/" + textureName + state.getValue(((BarleyCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    private ConfiguredModel[] roseHipsState(BlockState state, BushBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(RoseHipsBushBlock.AGE),
                new ResourceLocation(YeastNFeastMod.MOD_ID, "block/" + textureName + state.getValue((RoseHipsBushBlock.AGE)))).renderType("cutout"));
        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}