package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.Configuration;
import net.astralya.yeastnfeast.block.custom.TreeTapBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeTapBlockEntity extends SyncBlockEntity {

    private int tickCounter = 0;

    public TreeTapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TREE_TAP, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TreeTapBlockEntity blockEntity) {
        if (!state.get(TreeTapBlock.DRIPPING)) {
            blockEntity.tickCounter++;
            if (blockEntity.tickCounter >= Configuration.get().treeTapDripDelay) {
                world.setBlockState(pos, state.with(TreeTapBlock.DRIPPING, true));
                blockEntity.tickCounter = 0;
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("tickCounter", tickCounter);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.tickCounter = nbt.getInt("tickCounter");
    }
}
