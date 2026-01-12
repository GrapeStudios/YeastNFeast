package net.astralya.yeastnfeast.block.entity.custom;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.custom.KegMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class KegBlockEntity extends SyncBlockEntity implements MenuProvider {

    public final ItemStackHandler inventory = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private final IItemHandler southInputHandler = new SlotsView(inventory,
            new int[] { INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3, YEAST_SLOT, TANKARD_SLOT },
            true,
            false
    );

    private final IItemHandler bottomOutputHandler = new SlotsView(inventory,
            new int[] { OUTPUT_SLOT },
            false,
            true
    );

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int OUTPUT_SLOT = 3;
    public static final int YEAST_SLOT = 4;
    public static final int TANKARD_SLOT = 5;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;

    @Nullable private Player lastInteractedPlayer;

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

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
                else if (index == 1) maxProgress = value;
            }
            @Override public int getCount() { return 2; }
        };
    }

    public IItemHandler getItemHandler(Direction direction) {

        if (direction == Direction.SOUTH) {
            return southInputHandler;
        }
        if (direction == Direction.DOWN) {
            return bottomOutputHandler;
        }

        return EmptyHandler.INSTANCE;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
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
        progress = 0;
        maxProgress = 200;
    }

    private void craftItem() {
        Level level = getLevel();
        RecipeWrapper wrapper = new RecipeWrapper(inventory);
        Optional<RecipeHolder<KegRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), wrapper, level);

        match.ifPresent(holder -> {
            KegRecipe recipe = holder.value();
            ItemStack result = recipe.getResultItem(level.registryAccess());
            ItemStack current = inventory.getStackInSlot(OUTPUT_SLOT);

            if (current.isEmpty()) {
                inventory.setStackInSlot(OUTPUT_SLOT, result.copy());
            } else {
                ItemStack merged = current.copy();
                merged.grow(result.getCount());
                inventory.setStackInSlot(OUTPUT_SLOT, merged);
            }

            boolean[] used = new boolean[3];
            for (Ingredient ing : recipe.getIngredients()) {
                for (int i = INPUT_SLOT_1; i <= INPUT_SLOT_3; i++) {
                    if (!used[i] && ing.test(inventory.getStackInSlot(i))) {
                        inventory.extractItem(i, 1, false);
                        used[i] = true;
                        break;
                    }
                }
            }
            if (!recipe.getYeastSlot().isEmpty()) inventory.extractItem(YEAST_SLOT, 1, false);
            if (!recipe.getTankardSlot().isEmpty()) inventory.extractItem(TANKARD_SLOT, 1, false);

            addRecipeUsed(holder);

            level.playSound(null, worldPosition, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);

            setChanged(level, worldPosition, getBlockState());
        });
    }

    private void addRecipeUsed(RecipeHolder<?> holder) {
        recipesUsed.addTo(holder.id(), 1);
    }

    private boolean hasRecipe() {
        Level level = getLevel();
        RecipeWrapper wrapper = new RecipeWrapper(inventory);
        Optional<RecipeHolder<KegRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), wrapper, level);

        if (match.isEmpty()) return false;

        KegRecipe recipe = match.get().value();
        ItemStack result = recipe.getResultItem(level.registryAccess());
        maxProgress = recipe.getBrewTime();

        return canInsertItemIntoOutputSlot(result) && canInsertAmountIntoOutputSlot(result.getCount());
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        return out.isEmpty() || out.getCount() < out.getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        return out.isEmpty() || ItemStack.isSameItemSameComponents(out, output);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        int max = out.isEmpty() ? 64 : out.getMaxStackSize();
        return out.getCount() + count <= max;
    }

    public void dropExperienceForRecipesUsed(ServerPlayer player) {
        Level level = getLevel();
        if (!(level instanceof ServerLevel server)) return;

        recipesUsed.object2IntEntrySet().forEach(e -> {
            ResourceLocation id = e.getKey();
            int crafts = e.getIntValue();
            server.getRecipeManager().byKey(id).ifPresent(rec -> {
                if (rec.value() instanceof KegRecipe keg) {
                    float total = crafts * keg.getExperience();
                    int whole = Mth.floor(total);
                    float frac = total - whole;
                    if (frac > 0 && server.random.nextFloat() < frac) whole++;
                    if (whole > 0) ExperienceOrb.award(server, player.position(), whole);
                    player.awardRecipes(List.of(rec));
                }
            });
        });

        recipesUsed.clear();
    }

    public void dropExperienceForRecipesUsed() {
        if (lastInteractedPlayer instanceof ServerPlayer sp) {
            dropExperienceForRecipesUsed(sp);
        }
    }

    @Override public Component getDisplayName() {
        return Component.translatable("block.yeastnfeast.keg");
    }

    @Override public @Nullable AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new KegMenu(id, inv, this, data);
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        tag.put("inventory", inventory.serializeNBT(regs));
        tag.putInt("keg.progress", progress);
        tag.putInt("keg.max_progress", maxProgress);
        CompoundTag used = new CompoundTag();
        recipesUsed.forEach((rl, count) -> used.putInt(rl.toString(), count));
        tag.put("RecipesUsed", used);
        super.saveAdditional(tag, regs);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        inventory.deserializeNBT(regs, tag.getCompound("inventory"));
        progress = tag.getInt("keg.progress");
        maxProgress = tag.getInt("keg.max_progress");
        recipesUsed.clear();
        CompoundTag used = tag.getCompound("RecipesUsed");
        for (String k : used.getAllKeys()) {
            recipesUsed.put(ResourceLocation.parse(k), used.getInt(k));
        }
    }

    @Override public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override public CompoundTag getUpdateTag(HolderLookup.Provider regs) {
        return saveWithoutMetadata(regs);
    }

    public void drops() {
        if (level == null) return;
        SimpleContainer tmp = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) tmp.setItem(i, inventory.getStackInSlot(i));
        Containers.dropContents(level, worldPosition, tmp);
    }

    public void clearContents() {
        for (int i = 0; i < inventory.getSlots(); i++) inventory.setStackInSlot(i, ItemStack.EMPTY);
    }

    public void setLastInteractedPlayer(Player p) { lastInteractedPlayer = p; }

    private static final class SlotsView implements IItemHandler {
        private final ItemStackHandler backing;
        private final int[] slots;
        private final boolean allowInsert;
        private final boolean allowExtract;

        private SlotsView(ItemStackHandler backing, int[] slots, boolean allowInsert, boolean allowExtract) {
            this.backing = backing;
            this.slots = slots;
            this.allowInsert = allowInsert;
            this.allowExtract = allowExtract;
        }

        @Override
        public int getSlots() {
            return slots.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return backing.getStackInSlot(map(slot));
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (!allowInsert) return stack;
            return backing.insertItem(map(slot), stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (!allowExtract) return ItemStack.EMPTY;
            return backing.extractItem(map(slot), amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return backing.getSlotLimit(map(slot));
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (!allowInsert) return false;
            return backing.isItemValid(map(slot), stack);
        }

        private int map(int localSlot) {
            return slots[localSlot];
        }
    }

    private enum EmptyHandler implements IItemHandler {
        INSTANCE;

        @Override public int getSlots() { return 0; }
        @Override public ItemStack getStackInSlot(int slot) { return ItemStack.EMPTY; }
        @Override public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) { return stack; }
        @Override public ItemStack extractItem(int slot, int amount, boolean simulate) { return ItemStack.EMPTY; }
        @Override public int getSlotLimit(int slot) { return 0; }
        @Override public boolean isItemValid(int slot, ItemStack stack) { return false; }
    }
}