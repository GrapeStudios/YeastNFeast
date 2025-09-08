package net.astralya.yeastnfeast.screen.custom;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class KegScreenHandler extends ScreenHandler {

    private static final int TE_SIZE = 6;

    private final PropertyDelegate properties;
    private final ScreenHandlerContext context;
    public final KegBlockEntity blockEntity;

    public KegScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(2));
    }

    public KegScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity be, PropertyDelegate properties) {
        super(ModScreenHandlers.KEG_SCREEN, syncId);
        Inventory inventory = (Inventory) be;
        this.blockEntity = (KegBlockEntity) be;
        this.properties = properties;
        this.context = ScreenHandlerContext.create(playerInventory.player.getWorld(), blockEntity.getPos());

        checkSize(inventory, TE_SIZE);

        addSlot(new Slot(inventory, 0, 23, 26) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        addSlot(new Slot(inventory, 1, 41, 26) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        addSlot(new Slot(inventory, 2, 59, 26) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });

        addSlot(new Slot(inventory, 3, 125, 28) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);
                if (!player.getWorld().isClient && player instanceof ServerPlayerEntity sp) {
                    blockEntity.dropExperienceForRecipesUsed(sp);
                }
            }
        });

        addSlot(new Slot(inventory, 4, 52, 48) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.YEAST) || stack.isOf(Items.SUGAR);
            }
        });

        addSlot(new Slot(inventory, 5, 31, 48) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.TANKARD) || stack.isOf(ModItems.JAR);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(properties);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(context, player, ModBlocks.KEG);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        blockEntity.setLastInteractedPlayer(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        Slot slot = slots.get(invSlot);
        if (!slot.hasStack()) return ItemStack.EMPTY;

        ItemStack source = slot.getStack();
        ItemStack newStack = source.copy();

        int teStart = 0;
        int playerStart = teStart + TE_SIZE;
        int hotbarStart = playerStart + 27;
        int hotbarEnd = hotbarStart + 9;

        if (invSlot < playerStart) {
            if (!insertItem(source, playerStart, hotbarEnd, true)) return ItemStack.EMPTY;
        } else {
            if (source.isOf(ModItems.YEAST) || source.isOf(Items.SUGAR)) {
                if (!insertItem(source, 4, 5, false)) return ItemStack.EMPTY;
            } else if (source.isOf(ModItems.TANKARD) || source.isOf(ModItems.JAR)) {
                if (!insertItem(source, 5, 6, false)) return ItemStack.EMPTY;
            } else {
                if (!insertItem(source, 0, 3, false)) return ItemStack.EMPTY;
            }
        }

        if (source.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        slot.onTakeItem(player, source);

        return newStack;
    }

    public boolean isCrafting() {
        return properties.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = properties.get(0);
        int max = properties.get(1);
        int px = 26;
        return max != 0 && progress != 0 ? progress * px / max : 0;
    }

    private boolean isValidIngredient(ItemStack stack) {
        if (stack.isOf(ModItems.TANKARD) || stack.isOf(ModItems.JAR)) return false;
        if (stack.isOf(ModItems.YEAST) || stack.isOf(Items.SUGAR)) return false;
        return true;
    }

    private void addPlayerInventory(PlayerInventory inv) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory inv) {
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
    }
}