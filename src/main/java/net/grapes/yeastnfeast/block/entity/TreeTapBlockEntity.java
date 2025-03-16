package net.grapes.yeastnfeast.block.entity;

import net.grapes.yeastnfeast.block.custom.TreeTapBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class TreeTapBlockEntity extends BlockEntity {

    private int tickCounter = 0;

    public TreeTapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TREE_TAP_BE.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, TreeTapBlockEntity blockEntity) {
        if (!state.getValue(TreeTapBlock.DRIPPING)) {
            blockEntity.tickCounter++;
            if (blockEntity.tickCounter >= 7200) {
                world.setBlock(pos, state.setValue(TreeTapBlock.DRIPPING, true), 3);
                blockEntity.tickCounter = 0;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("tickCounter", tickCounter);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        tickCounter = nbt.getInt("tickCounter");
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap) {
        return super.getCapability(cap);
    }
}