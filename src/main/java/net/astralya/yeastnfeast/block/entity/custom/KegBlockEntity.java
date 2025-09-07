package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.custom.KegMenu;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KegBlockEntity extends SyncBlockEntity implements MenuProvider {

    public final ItemStackHandler inventory = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int OUTPUT_SLOT   = 3;
    public static final int YEAST_SLOT    = 4;
    public static final int TANKARD_SLOT  = 5;

    private final ContainerData data;
    private int progress     = 0;
    private int maxProgress  = 200;
    private final int DEFAULT_MAX_PROGRESS = 200;

    @Nullable private Player lastInteractedPlayer;

    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.KEG.get(), pos, state);

        this.data = new ContainerData() {
            @Override public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> 0;
                };
            }
            @Override public void set(int index, int value) {
                if (index == 0) progress = value;
                else if (index == 1)     maxProgress = value;
            }
            @Override public int getCount() { return 2; }
        };
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            progress++;
            setChanged(level, pos, state);

            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress    = 0;
        maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Level level = getLevel();
        if (level == null) return;

        RecipeWrapper wrapper = new RecipeWrapper(inventory);
        Optional<RecipeHolder<KegRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), wrapper, level);

        match.ifPresent(holder -> {
            KegRecipe recipe   = holder.value();
            ItemStack result   = recipe.getResultItem(level.registryAccess());
            ItemStack current  = inventory.getStackInSlot(OUTPUT_SLOT);

            inventory.setStackInSlot(
                    OUTPUT_SLOT,
                    new ItemStack(result.getItem(), current.getCount() + result.getCount())
            );

            boolean[] used = new boolean[3];
            for (Ingredient ing : recipe.getIngredients()) {
                for (int i = 0; i < 3; i++) {
                    if (!used[i] && ing.test(inventory.getStackInSlot(i))) {
                        inventory.extractItem(i, 1, false);
                        used[i] = true;
                        break;
                    }
                }
            }
            if (!recipe.getYeastSlot().isEmpty())   inventory.extractItem(YEAST_SLOT,   1, false);
            if (!recipe.getTankardSlot().isEmpty()) inventory.extractItem(TANKARD_SLOT, 1, false);

            if (lastInteractedPlayer != null)
                grantExperience(lastInteractedPlayer, recipe.getExperience());

            setChanged(level, worldPosition, getBlockState());
        });
    }

    private boolean hasRecipe() {
        Level level = getLevel();
        if (level == null) return false;

        RecipeWrapper wrapper = new RecipeWrapper(inventory);
        Optional<RecipeHolder<KegRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), wrapper, level);

        if (match.isEmpty()) return false;

        KegRecipe recipe   = match.get().value();
        ItemStack result   = recipe.getResultItem(level.registryAccess());
        maxProgress        = recipe.getBrewTime();

        return canInsertItemIntoOutputSlot(result) &&
                canInsertAmountIntoOutputSlot(result.getCount());
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                inventory.getStackInSlot(OUTPUT_SLOT).getCount() <
                        inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                inventory.getStackInSlot(OUTPUT_SLOT).is(output.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int max = inventory.getStackInSlot(OUTPUT_SLOT).isEmpty()
                ? 64
                : inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        return inventory.getStackInSlot(OUTPUT_SLOT).getCount() + count <= max;
    }

    private void grantExperience(Player player, float xp) {
        if (xp > 0 && !player.level().isClientSide())
            player.giveExperiencePoints((int) xp);
    }

    @Override public Component getDisplayName() {
        return Component.translatable("block.yeastnfeast.keg");
    }
    @Override public @Nullable AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new KegMenu(id, inv, this, data);
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        tag.put("inventory", inventory.serializeNBT(regs));
        tag.putInt("keg.progress",      progress);
        tag.putInt("keg.max_progress",  maxProgress);
        super.saveAdditional(tag, regs);
    }
    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        inventory.deserializeNBT(regs, tag.getCompound("inventory"));
        progress    = tag.getInt("keg.progress");
        maxProgress = tag.getInt("keg.max_progress");
    }
    @Override public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override public CompoundTag getUpdateTag(HolderLookup.Provider regs) {
        return saveWithoutMetadata(regs);
    }

    public void drops() {
        SimpleContainer tmp = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++)
            tmp.setItem(i, inventory.getStackInSlot(i));
        Containers.dropContents(level, worldPosition, tmp);
    }
    public void clearContents() {
        for (int i = 0; i < inventory.getSlots(); i++)
            inventory.setStackInSlot(i, ItemStack.EMPTY);
    }
    public void setLastInteractedPlayer(Player p) { lastInteractedPlayer = p; }
}
