package net.grapes.yeastnfeast.block.custom;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class LemonLeavesBlock extends Block {

    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);

    public LemonLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int age = pState.getValue(AGE);
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (age == MAX_AGE) {
            int berryCount = 1 + pLevel.random.nextInt(2);
            popResource(pLevel, pPos, new ItemStack(ModItems.LEMON.get(), berryCount + 1));
            pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            pLevel.setBlock(pPos, pState.setValue(AGE, 0), Block.UPDATE_ALL);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, pState.setValue(AGE, 1)));
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < MAX_AGE && random.nextInt(5) == 0 && world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            world.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state.setValue(AGE, state.getValue(AGE) + 1)));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
