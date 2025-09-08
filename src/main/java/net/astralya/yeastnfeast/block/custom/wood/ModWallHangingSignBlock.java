package net.astralya.yeastnfeast.block.custom.wood;

import net.astralya.yeastnfeast.block.entity.wood.ModHangingSignBlockEntity;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.List;

public class ModWallHangingSignBlock extends WallHangingSignBlock {

    private final WoodType woodType;

    public ModWallHangingSignBlock(WoodType woodType, Settings settings) {
        super(woodType, settings);
        this.woodType = woodType;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        if (woodType == ModWoodTypes.MAPLE_WOOD_TYPE) {
            return Collections.singletonList(new ItemStack(ModItems.MAPLE_HANGING_SIGN));
        }
        return super.getDroppedStacks(state, builder);
    }
}
