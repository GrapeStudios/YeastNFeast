package net.astralya.yeastnfeast.block.custom;

import com.mojang.serialization.MapCodec;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CheesePressBlock extends BlockWithEntity implements Waterloggable {

    public static final MapCodec<CheesePressBlock> CODEC = createCodec(CheesePressBlock::new);

    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.Type.HORIZONTAL);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty PRESSING = BooleanProperty.of("pressing");
    public static final BooleanProperty POWERED = Properties.POWERED;

    public CheesePressBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
                .with(PRESSING, false)
                .with(POWERED, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        boolean water = world.getFluidState(pos).getFluid() == Fluids.WATER;
        boolean powered = world.isReceivingRedstonePower(pos);

        return getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, water)
                .with(PRESSING, false)
                .with(POWERED, powered);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Boolean.TRUE.equals(state.get(WATERLOGGED)) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (Boolean.TRUE.equals(state.get(WATERLOGGED))) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;

        boolean nowPowered = world.isReceivingRedstonePower(pos);
        boolean wasPowered = state.get(POWERED);

        if (nowPowered != wasPowered) {
            world.setBlockState(pos, state.with(POWERED, nowPowered), Block.NOTIFY_ALL);

            if (nowPowered) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof CheesePressBlockEntity press) {
                    boolean started = press.tryStartPressing();
                    if (started) {
                        press.setPressing(true);
                    }
                }
            }
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, PRESSING, POWERED);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, net.minecraft.entity.player.PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ItemActionResult.SUCCESS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CheesePressBlockEntity press)) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!stack.isEmpty()) {
            boolean inserted = press.insertOne(player, hand);
            return inserted ? ItemActionResult.SUCCESS : ItemActionResult.CONSUME;
        }

        if (player.isSneaking()) {
            boolean started = press.tryStartPressing();
            if (started) {
                press.setPressing(true);
                return ItemActionResult.SUCCESS;
            }
            return ItemActionResult.CONSUME;
        }

        if (press.isPressing()) {
            return ItemActionResult.CONSUME;
        }

        ItemStack extracted = press.extractOne(player);
        return !extracted.isEmpty() ? ItemActionResult.SUCCESS : ItemActionResult.CONSUME;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, net.minecraft.entity.player.PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CheesePressBlockEntity press)) {
            return ActionResult.PASS;
        }

        if (player.isSneaking()) {
            boolean started = press.tryStartPressing();
            if (started) {
                press.setPressing(true);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        if (press.isPressing()) {
            return ActionResult.CONSUME;
        }

        ItemStack extracted = press.extractOne(player);
        return !extracted.isEmpty() ? ActionResult.SUCCESS : ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            if (world.getBlockEntity(pos) instanceof CheesePressBlockEntity be) {
                be.drops();
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheesePressBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) {
            return null;
        }
        return validateTicker(type, ModBlockEntityTypes.CHEESE_PRESS, (w, p, s, be) -> be.tick(w, p, s));
    }
}