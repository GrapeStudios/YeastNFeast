package net.grapes.yeastnfeast.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<TreeTapBlockEntity> TREE_TAP_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(YeastNFeastMod.MOD_ID, "tree_tap_block_entity"),
                    FabricBlockEntityTypeBuilder.create(TreeTapBlockEntity::new,
                            ModBlocks.TREE_TAP).build(null));
    
    // Sign and Hanging Sign Entities
    public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(YeastNFeastMod.MOD_ID, "mod_sign_entity"),
            FabricBlockEntityTypeBuilder.create(ModSignBlockEntity::new,
                    ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN).build());
    public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(YeastNFeastMod.MOD_ID, "mod_hanging_sign_entity"),
            FabricBlockEntityTypeBuilder.create(ModHangingSignBlockEntity::new,
                    ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_HANGING_WALL_SIGN).build(null));

    public static void registerBlockEntities() {
        YeastNFeastMod.LOGGER.info("Registering Block Entities for " + YeastNFeastMod.MOD_ID);
    }
}
