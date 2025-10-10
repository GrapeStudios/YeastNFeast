package net.astralya.yeastnfeast.screen.custom;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class KegMenu extends AbstractContainerMenu {

    public final KegBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public KegMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public KegMenu(int containerId, Inventory inv, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.KEG_MENU.get(), containerId);
        this.blockEntity = (KegBlockEntity) blockEntity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 0, 23, 26) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 1, 41, 26) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 2, 59, 26) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 3, 125, 28) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
            @Override
            public void onTake(Player player, ItemStack taken) {
                super.onTake(player, taken);
                if (player instanceof ServerPlayer sp) {
                    ((KegBlockEntity) blockEntity).setLastInteractedPlayer(player);
                    ((KegBlockEntity) blockEntity).dropExperienceForRecipesUsed(sp);
                } else {
                    ((KegBlockEntity) blockEntity).setLastInteractedPlayer(player);
                    ((KegBlockEntity) blockEntity).dropExperienceForRecipesUsed();
                }
            }
            @Override
            protected void onQuickCraft(ItemStack from, int amount) {
                super.onQuickCraft(from, amount);
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 4, 52, 48) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.YEAST.get()) || stack.is(Items.SUGAR);
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, 5, 31, 48) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.TANKARD.get()) || stack.is(ModItems.JAR.get());
            }
        });

        addDataSlots(data);
    }

    private boolean isValidIngredient(ItemStack stack) {
        if (stack.is(ModItems.TANKARD.get()) || stack.is(ModItems.JAR.get())) return false;
        return !stack.is(ModItems.YEAST.get()) && !stack.is(Items.SUGAR);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        blockEntity.setLastInteractedPlayer(player);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressBarSize / maxProgress : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 6;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (sourceStack.is(ModItems.YEAST.get()) || sourceStack.is(Items.SUGAR)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + 4, TE_INVENTORY_FIRST_SLOT_INDEX + 5, false))
                    return ItemStack.EMPTY;
            } else if (sourceStack.is(ModItems.TANKARD.get()) || sourceStack.is(ModItems.JAR.get())) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + 5, TE_INVENTORY_FIRST_SLOT_INDEX + 6, false))
                    return ItemStack.EMPTY;
            } else {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + 3, false))
                    return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
                return ItemStack.EMPTY;
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.KEG.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
