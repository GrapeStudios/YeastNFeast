package net.astralya.yeastnfeast.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class CheeseWheelBlock extends Block {

    public static final IntegerProperty CUTS = IntegerProperty.create("cuts", 0, 3);

    private static final VoxelShape NW = Block.box(1, 0, 1, 8, 5, 8);
    private static final VoxelShape NE = Block.box(8, 0, 1, 15, 5, 8);
    private static final VoxelShape SE = Block.box(8, 0, 8, 15, 5, 15);
    private static final VoxelShape SW = Block.box(1, 0, 8, 8, 5, 15);

    private static final VoxelShape FRESH_NW = Block.box(2, 0, 2, 8, 5, 8);
    private static final VoxelShape FRESH_NE = Block.box(8, 0, 2, 14, 5, 8);
    private static final VoxelShape FRESH_SE = Block.box(8, 0, 8, 14, 5, 14);
    private static final VoxelShape FRESH_SW = Block.box(2, 0, 8, 8, 5, 14);

    private static VoxelShape union(VoxelShape... shapes) {
        VoxelShape out = Shapes.empty();
        for (VoxelShape s : shapes) out = Shapes.or(out, s);
        return out;
    }

    private static final VoxelShape SHAPE_FULL = union(NW, NE, SE, SW);
    private static final VoxelShape SHAPE_3_4 = union(NE, SE, SW);
    private static final VoxelShape SHAPE_2_4 = union(SE, SW);
    private static final VoxelShape SHAPE_1_4 = SW;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            SHAPE_FULL, SHAPE_3_4, SHAPE_2_4, SHAPE_1_4
    };

    private static final VoxelShape FRESH_SHAPE_FULL = union(FRESH_NW, FRESH_NE, FRESH_SE, FRESH_SW);
    private static final VoxelShape FRESH_SHAPE_3_4 = union(FRESH_NE, FRESH_SE, FRESH_SW);
    private static final VoxelShape FRESH_SHAPE_2_4 = union(FRESH_SE, FRESH_SW);
    private static final VoxelShape FRESH_SHAPE_1_4 = FRESH_SW;

    private static final VoxelShape[] FRESH_SHAPES = new VoxelShape[] {
            FRESH_SHAPE_FULL, FRESH_SHAPE_3_4, FRESH_SHAPE_2_4, FRESH_SHAPE_1_4
    };

    public static final MapCodec<CheeseWheelBlock> CODEC = simpleCodec(CheeseWheelBlock::new);

    private final Supplier<Item> sliceItem;
    private final boolean freshwheelShape;

    public CheeseWheelBlock(BlockBehaviour.Properties properties, Supplier<Item> sliceItem, boolean freshwheelShape) {
        super(properties);
        this.sliceItem = sliceItem;
        this.freshwheelShape = freshwheelShape;
        this.registerDefaultState(this.stateDefinition.any().setValue(CUTS, 0));
    }

    public CheeseWheelBlock(BlockBehaviour.Properties properties, Supplier<Item> sliceItem) {
        this(properties, sliceItem, false);
    }

    public CheeseWheelBlock(BlockBehaviour.Properties properties) {
        this(properties, () -> null, false);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        VoxelShape[] set = freshwheelShape ? FRESH_SHAPES : SHAPES;
        return set[cuts];
    }

    @Override
    protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(state, level, pos, CollisionContext.empty());
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 4 - state.getValue(CUTS);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        return takeSlice(state, level, pos, player);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        InteractionResult res = takeSlice(state, level, pos, player);
        return res.consumesAction()
                ? ItemInteractionResult.sidedSuccess(level.isClientSide)
                : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private InteractionResult takeSlice(BlockState state, Level level, BlockPos pos, Player player) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        Item slice = sliceItem.get();
        if (slice == null) return InteractionResult.PASS;

        ItemStack give = new ItemStack(slice);
        if (give.isEmpty()) return InteractionResult.PASS;

        if (!player.addItem(give)) {
            player.drop(give, false);
        }

        level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.6f, 1.0f);

        int cuts = state.getValue(CUTS);
        if (cuts < 3) {
            BlockState updated = state.setValue(CUTS, cuts + 1);
            level.setBlock(pos, updated, 3);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CUTS);
    }
}
