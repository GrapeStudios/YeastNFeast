package net.astralya.yeastnfeast.block.entity.custom;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.KegRecipeInput;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.custom.KegScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class KegBlockEntity extends SyncBlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {

    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();

    private static final int SIZE = 6;
    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int OUTPUT_SLOT = 3;
    public static final int YEAST_SLOT = 4;
    public static final int TANKARD_SLOT = 5;

    private static final int DEFAULT_MAX_PROGRESS = 200;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);

    private int progress = 0;
    private int maxProgress = DEFAULT_MAX_PROGRESS;

    @Nullable
    private PlayerEntity lastInteractedPlayer;

    private final PropertyDelegate properties = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }
        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
            }
        }
        @Override
        public int size() {
            return 2;
        }
    };

    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.KEG, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world == null || world.isClient) return;

        if (hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            progress++;
            markDirtyAndSync();
            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.yeastnfeast.keg");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new KegScreenHandler(syncId, inv, this, properties);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        nbt.putInt("keg.progress", progress);
        nbt.putInt("keg.max_progress", maxProgress);
        Inventories.writeNbt(nbt, items, registries);

        NbtCompound used = new NbtCompound();
        recipesUsed.forEach((id, count) -> used.putInt(id.toString(), count));
        nbt.put("RecipesUsed", used);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        progress = nbt.getInt("keg.progress");
        maxProgress = nbt.getInt("keg.max_progress");
        Collections.fill(items, ItemStack.EMPTY);
        Inventories.readNbt(nbt, items, registries);

        recipesUsed.clear();
        NbtCompound used = nbt.getCompound("RecipesUsed");
        for (String key : used.getKeys()) {
            recipesUsed.put(Identifier.of(key), used.getInt(key));
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack s : items) if (!s.isEmpty()) return false;
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < SIZE ? items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot < 0 || slot >= SIZE || amount <= 0) return ItemStack.EMPTY;
        ItemStack current = items.get(slot);
        if (current.isEmpty()) return ItemStack.EMPTY;
        ItemStack taken = current.split(amount);
        if (current.isEmpty()) items.set(slot, ItemStack.EMPTY);
        markDirtyAndSync();
        return taken;
    }

    @Override
    public ItemStack removeStack(int slot) {
        if (slot < 0 || slot >= SIZE) return ItemStack.EMPTY;
        ItemStack out = items.get(slot);
        items.set(slot, ItemStack.EMPTY);
        markDirtyAndSync();
        return out;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot < 0 || slot >= SIZE) return;
        items.set(slot, stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
        ItemStack s = items.get(slot);
        if (!s.isEmpty() && s.getCount() > s.getMaxCount()) {
            items.set(slot, s.copyWithCount(s.getMaxCount()));
        }
        markDirtyAndSync();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (world == null) return false;
        if (world.getBlockEntity(pos) != this) return false;
        return player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < SIZE; i++) items.set(i, ItemStack.EMPTY);
        markDirtyAndSync();
    }

    public void setLastInteractedPlayer(@Nullable PlayerEntity player) {
        lastInteractedPlayer = player;
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack out = items.get(OUTPUT_SLOT);
        return out.isEmpty() || out.getCount() < out.getMaxCount();
    }

    private boolean hasRecipe() {
        World w = getWorld();
        if (w == null) return false;

        Optional<RecipeEntry<KegRecipe>> match = w.getRecipeManager()
                .getFirstMatch(ModRecipes.KEG_TYPE, buildInput(), w);
        if (match.isEmpty()) return false;

        KegRecipe recipe = match.get().value();
        ItemStack result = recipe.getResult(w.getRegistryManager());
        maxProgress = recipe.getBrewTime();

        return canInsertItemIntoOutput(result) && canInsertAmountIntoOutput(result.getCount());
    }

    private void craftItem() {
        World w = getWorld();
        if (w == null) return;

        Optional<RecipeEntry<KegRecipe>> match = w.getRecipeManager()
                .getFirstMatch(ModRecipes.KEG_TYPE, buildInput(), w);
        if (match.isEmpty()) return;

        RecipeEntry<KegRecipe> entry = match.get();
        KegRecipe recipe = entry.value();
        ItemStack result = recipe.getResult(w.getRegistryManager());
        ItemStack current = items.get(OUTPUT_SLOT);

        items.set(OUTPUT_SLOT, new ItemStack(result.getItem(), current.getCount() + result.getCount()));

        addRecipeUsed(entry);

        w.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0f, 1.0f);

        markDirtyAndSync();
    }

    private void addRecipeUsed(RecipeEntry<?> entry) {
        Identifier id = entry.id();
        if (id != null) {
            recipesUsed.addTo(id, 1);
        }
    }

    private KegRecipeInput buildInput() {
        return new KegRecipeInput(
                items.get(INPUT_SLOT_1).copy(),
                items.get(INPUT_SLOT_2).copy(),
                items.get(INPUT_SLOT_3).copy(),
                items.get(YEAST_SLOT).copy(),
                items.get(TANKARD_SLOT).copy()
        );
    }

    public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        if (!(player.getWorld() instanceof ServerWorld sw)) return;
        Vec3d posVec = Vec3d.ofCenter(getPos());

        recipesUsed.object2IntEntrySet().forEach(entry -> {
            Identifier id = entry.getKey();
            int crafts = entry.getIntValue();
            sw.getRecipeManager().get(id).ifPresent(rec -> {
                if (rec.value() instanceof KegRecipe keg) {
                    dropExperience(sw, posVec, crafts, keg.getExperience());
                    player.unlockRecipes(List.of(rec));
                }
            });
        });

        recipesUsed.clear();
    }

    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float xpPerCraft) {
        int i = MathHelper.floor(multiplier * xpPerCraft);
        float frac = MathHelper.fractionalPart(multiplier * xpPerCraft);
        if (frac != 0.0F && world.random.nextFloat() < frac) {
            i++;
        }
        if (i > 0) {
            ExperienceOrbEntity.spawn(world, pos, i);
        }
    }

    private boolean canInsertItemIntoOutput(ItemStack output) {
        ItemStack out = items.get(OUTPUT_SLOT);
        return out.isEmpty() || out.isOf(output.getItem());
    }

    private boolean canInsertAmountIntoOutput(int count) {
        ItemStack out = items.get(OUTPUT_SLOT);
        int max = out.isEmpty() ? 64 : out.getMaxCount();
        return out.getCount() + count <= max;
    }

    private void decrementSlot(int slot) {
        ItemStack s = items.get(slot);
        if (s.isEmpty()) return;
        int remain = s.getCount() - 1;
        items.set(slot, remain > 0 ? s.copyWithCount(remain) : ItemStack.EMPTY);
    }

    private void markDirtyAndSync() {
        markDirty();
        World w = getWorld();
        if (w instanceof ServerWorld sw) {
            sw.getChunkManager().markForUpdate(getPos());
            w.updateListeners(getPos(), getCachedState(), getCachedState(), 3);
        }
    }
}