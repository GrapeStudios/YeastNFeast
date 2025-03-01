package net.grapes.yeastnfeast.block.entity;

import net.grapes.yeastnfeast.block.custom.TreeTapBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeTapBlockEntity extends BlockEntity {
    private int tickCounter = 0;

    public TreeTapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TREE_TAP_BE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TreeTapBlockEntity blockEntity) {
        if (!state.get(TreeTapBlock.DRIPPING)) {
            blockEntity.tickCounter++;
            if (blockEntity.tickCounter >= 60) {
                world.setBlockState(pos, state.with(TreeTapBlock.DRIPPING, true));
                blockEntity.tickCounter = 0;
            }
        }
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("tickCounter", tickCounter);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tickCounter = nbt.getInt("tickCounter");
    }
}
