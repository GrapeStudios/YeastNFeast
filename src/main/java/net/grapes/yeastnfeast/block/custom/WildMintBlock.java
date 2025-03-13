package net.grapes.yeastnfeast.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WildMintBlock extends FlowerBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 0, 4, 12, 3, 12)
    );

    public WildMintBlock(MobEffect effect, int effectDuration, BlockBehaviour.Properties properties) {
        super(effect, effectDuration, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
