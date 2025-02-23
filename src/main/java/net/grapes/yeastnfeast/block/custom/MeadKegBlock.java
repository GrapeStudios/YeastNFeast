package net.grapes.yeastnfeast.block.custom;

import net.grapes.yeastnfeast.block.entity.MeadKegBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MeadKegBlock extends Block {
    public MeadKegBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MeadKegBlockEntity(pos, state);
    }
}




