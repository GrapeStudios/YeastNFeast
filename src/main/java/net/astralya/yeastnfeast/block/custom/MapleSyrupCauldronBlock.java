package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class MapleSyrupCauldronBlock extends LeveledCauldronBlock {

    public MapleSyrupCauldronBlock(Settings settings) {
        super(Biome.Precipitation.NONE, CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR, settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(LEVEL, 1));
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return (6.0D + 3.0D * state.get(LEVEL)) / 16.0D;
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.CAULDRON);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(LEVEL);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(Items.GLASS_BOTTLE) && state.get(LEVEL) > 0) {
            if (!world.isClient) {
                if (!player.isCreative()) {
                    held.decrement(1);
                }

                ItemStack syrup = new ItemStack(ModItems.MAPLE_SYRUP);
                if (!player.getInventory().insertStack(syrup)) {
                    player.dropItem(syrup, false);
                }

                int newLevel = state.get(LEVEL) - 1;
                BlockState newState = newLevel == 0 ? Blocks.CAULDRON.getDefaultState() : state.with(LEVEL, newLevel);

                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(newState));
            }

            return ItemActionResult.success(world.isClient);
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}