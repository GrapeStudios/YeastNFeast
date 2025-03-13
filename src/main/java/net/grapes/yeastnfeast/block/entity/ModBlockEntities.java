package net.grapes.yeastnfeast.block.entity;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.block.entity.sign.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, YeastNFeastMod.MOD_ID);

    // Block Entities
    public static final RegistryObject<BlockEntityType<KegBlockEntity>> KEG_BE =
            BLOCK_ENTITIES.register("keg_block_entity",
                    () -> BlockEntityType.Builder.of(KegBlockEntity::new,
                            ModBlocks.KEG.get()).build(null));

    // Sign and Hanging Sign Entities
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,ModBlocks.MAPLE_SIGN.get(), 
                            ModBlocks.MAPLE_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<HangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(HangingSignBlockEntity::new, ModBlocks.MAPLE_HANGING_SIGN.get(), 
                            ModBlocks.MAPLE_HANGING_WALL_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
