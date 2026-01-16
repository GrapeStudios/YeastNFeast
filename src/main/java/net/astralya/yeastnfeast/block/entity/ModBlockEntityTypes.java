package net.astralya.yeastnfeast.block.entity;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.TreeTapBlockEntity;
import net.astralya.yeastnfeast.block.entity.wood.ModHangingSignBlockEntity;
import net.astralya.yeastnfeast.block.entity.wood.ModSignBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {

    public static final BlockEntityType<TreeTapBlockEntity> TREE_TAP = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(YeastNFeastMod.MODID, "tree_tap"), BlockEntityType.Builder.create(TreeTapBlockEntity::new, ModBlocks.TREE_TAP).build(null));
    public static final BlockEntityType<KegBlockEntity> KEG = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(YeastNFeastMod.MODID, "keg"), BlockEntityType.Builder.create(KegBlockEntity::new, ModBlocks.KEG).build(null));
    public static final BlockEntityType<CheesePressBlockEntity> CHEESE_PRESS = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(YeastNFeastMod.MODID, "cheese_press"), BlockEntityType.Builder.create(CheesePressBlockEntity::new, ModBlocks.CHEESE_PRESS).build(null));

    // Signs
    public static final BlockEntityType<ModSignBlockEntity> MOD_SIGN = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(YeastNFeastMod.MODID, "mod_sign"), BlockEntityType.Builder.create(
                    ModSignBlockEntity::new,
                    ModBlocks.MAPLE_SIGN,
                    ModBlocks.MAPLE_WALL_SIGN
            ).build(null));

    public static final BlockEntityType<ModHangingSignBlockEntity> MOD_HANGING_SIGN = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(YeastNFeastMod.MODID, "mod_sign"), BlockEntityType.Builder.create(
                    ModHangingSignBlockEntity::new,
                    ModBlocks.MAPLE_HANGING_SIGN,
                    ModBlocks.MAPLE_HANGING_WALL_SIGN
            ).build(null));

    public static void registerBlockEntities() {
        YeastNFeastMod.LOGGER.info("Registering Block Entities for " + YeastNFeastMod.MODID);
    }
}
