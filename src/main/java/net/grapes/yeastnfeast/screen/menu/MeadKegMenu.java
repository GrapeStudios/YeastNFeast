package net.grapes.yeastnfeast.screen.menu;

import net.grapes.yeastnfeast.block.entity.MeadKegBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class MeadKegMenu extends AbstractContainerMenu {
    public final MeadKegBlockEntity blockEntity;

    public MeadKegMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.MEAD_KEG_MENU.get(), pContainerId);
        this.blockEntity = ((MeadKegBlockEntity) entity);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 17));
            this.addSlot(new SlotItemHandler(handler, 1, 56, 53));
            this.addSlot(new SlotItemHandler(handler, 2, 116, 35));
        });

        addDataSlots(data);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }
}



