package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.Configuration;
import net.astralya.yeastnfeast.block.custom.TreeTapBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TreeTapBlockEntity extends SyncBlockEntity {

    private int tickCounter = 0;

    public TreeTapBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.TREE_TAP.get(), pos, blockState);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, TreeTapBlockEntity blockEntity) {
        if (!state.getValue(TreeTapBlock.DRIPPING)) {
            blockEntity.tickCounter++;
            if (blockEntity.tickCounter >= Configuration.TREE_TAP_DRIP_DELAY.get()) {
                world.setBlock(pos, state.setValue(TreeTapBlock.DRIPPING, true), 3);
                blockEntity.tickCounter = 0;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("tickCounter", tickCounter);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tickCounter = tag.getInt("tickCounter");
    }
}
