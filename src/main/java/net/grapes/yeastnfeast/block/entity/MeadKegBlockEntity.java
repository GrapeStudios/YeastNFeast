package net.grapes.yeastnfeast.block.entity;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MeadKegBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);
    private LazyOptional<ItemStackHandler> handlerOptional = LazyOptional.of(() -> itemHandler);

    public MeadKegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEAD_KEG.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handlerOptional.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) { // 🔹 FIX: Usar ForgeCapabilities.ITEM_HANDLER
            return handlerOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    private boolean canFerment() {
        return itemHandler.getStackInSlot(0).getItem() == Items.HONEY_BOTTLE
                && itemHandler.getStackInSlot(1).getItem() == Items.WATER_BUCKET
                && itemHandler.getStackInSlot(2).getItem() == ModItems.YEAST.get();
    }
}

