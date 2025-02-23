package net.grapes.yeastnfeast.block.entity;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, YeastNFeastMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MeadKegBlockEntity>> MEAD_KEG =
            BLOCK_ENTITIES.register("mead_keg",
                    () -> BlockEntityType.Builder.of(MeadKegBlockEntity::new, ModBlocks.MEAD_KEG.get()).build(null));
}
