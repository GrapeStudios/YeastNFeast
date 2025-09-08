package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {

    public static final Identifier BACKGROUND = Identifier.of(YeastNFeastMod.MODID, "textures/block/maple_log.png");

    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup,
                                    Consumer<AdvancementEntry> consumer) {

        AdvancementEntry root = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.YEAST),
                        Text.translatable("advancements.yeastnfeast.root.title"),
                        Text.translatable("advancements.yeastnfeast.root.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .criterion("has_yeast", InventoryChangedCriterion.Conditions.items(ModItems.YEAST))
                .build(consumer, YeastNFeastMod.MODID + ":root");

        AdvancementEntry keg = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModBlocks.KEG),
                        Text.translatable("advancements.yeastnfeast.keg.title"),
                        Text.translatable("advancements.yeastnfeast.keg.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(root)
                .criterion("has_keg", InventoryChangedCriterion.Conditions.items(ModBlocks.KEG))
                .build(consumer, YeastNFeastMod.MODID + ":keg");

        AdvancementEntry sweet = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.HONEY_MEAD),
                        Text.translatable("advancements.yeastnfeast.sweet.title"),
                        Text.translatable("advancements.yeastnfeast.sweet.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(keg)
                .criterion("has_honey_mead", InventoryChangedCriterion.Conditions.items(ModItems.HONEY_MEAD))
                .criterion("has_molasses_mead", InventoryChangedCriterion.Conditions.items(ModItems.MOLASSES_MEAD))
                .build(consumer, YeastNFeastMod.MODID + ":sweet");

        AdvancementEntry sour = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.SOUR_MEAD),
                        Text.translatable("advancements.yeastnfeast.sour.title"),
                        Text.translatable("advancements.yeastnfeast.sour.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(keg)
                .criterion("has_sour_mead", InventoryChangedCriterion.Conditions.items(ModItems.SOUR_MEAD))
                .criterion("has_thornberry_mead", InventoryChangedCriterion.Conditions.items(ModItems.THORNBERRY_MEAD))
                .build(consumer, YeastNFeastMod.MODID + ":sour");

        AdvancementEntry floral = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.BLOSSOM_MEAD),
                        Text.translatable("advancements.yeastnfeast.floral.title"),
                        Text.translatable("advancements.yeastnfeast.floral.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(keg)
                .criterion("has_blossom_mead", InventoryChangedCriterion.Conditions.items(ModItems.BLOSSOM_MEAD))
                .criterion("has_amber_mead", InventoryChangedCriterion.Conditions.items(ModItems.AMBER_MEAD))
                .build(consumer, YeastNFeastMod.MODID + ":floral");

        AdvancementEntry jar = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.JAR),
                        Text.translatable("advancements.yeastnfeast.jar.title"),
                        Text.translatable("advancements.yeastnfeast.jar.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(keg)
                .criterion("has_jar", InventoryChangedCriterion.Conditions.items(ModItems.JAR))
                .build(consumer, YeastNFeastMod.MODID + ":jar");

        AdvancementEntry jam = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.APPLE_JAM),
                        Text.translatable("advancements.yeastnfeast.jam.title"),
                        Text.translatable("advancements.yeastnfeast.jam.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.CHALLENGE,
                        true, true, false
                ))
                .parent(jar)
                .criterion("has_apple_jam", InventoryChangedCriterion.Conditions.items(ModItems.APPLE_JAM))
                .criterion("has_chorus_fruit_jam", InventoryChangedCriterion.Conditions.items(ModItems.CHORUS_FRUIT_JAM))
                .criterion("has_elderberries_jam", InventoryChangedCriterion.Conditions.items(ModItems.ELDERBERRIES_JAM))
                .criterion("has_glow_berries_jam", InventoryChangedCriterion.Conditions.items(ModItems.GLOW_BERRIES_JAM))
                .criterion("has_golden_apple_jam", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_APPLE_JAM))
                .criterion("has_hawthorn_berries_jam", InventoryChangedCriterion.Conditions.items(ModItems.HAWTHORN_BERRIES_JAM))
                .criterion("has_lemon_jam", InventoryChangedCriterion.Conditions.items(ModItems.LEMON_JAM))
                .criterion("has_melon_jam", InventoryChangedCriterion.Conditions.items(ModItems.MELON_JAM))
                .criterion("has_rose_hips_jam", InventoryChangedCriterion.Conditions.items(ModItems.ROSE_HIPS_JAM))
                .criterion("has_sweet_berries_jam", InventoryChangedCriterion.Conditions.items(ModItems.SWEET_BERRIES_JAM))
                .build(consumer, YeastNFeastMod.MODID + ":jam");

        AdvancementEntry crops = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.BARLEY),
                        Text.translatable("advancements.yeastnfeast.crops.title"),
                        Text.translatable("advancements.yeastnfeast.crops.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(root)
                .criterion("has_crop", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create()
                                .items(
                                        ModItems.ELDERBERRIES, ModItems.HAWTHORN_BERRIES,
                                        ModItems.LEMON, ModItems.ROSE_HIPS, ModItems.GARLIC,
                                        ModItems.BARLEY, ModItems.RYE, ModItems.MINT,
                                        ModItems.GINGER
                                ).build()
                ))
                .build(consumer, YeastNFeastMod.MODID + ":crops");

        AdvancementEntry berryRoll = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.BERRY_ROLL),
                        Text.translatable("advancements.yeastnfeast.berry_roll.title"),
                        Text.translatable("advancements.yeastnfeast.berry_roll.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(crops)
                .criterion("has_berry_roll", InventoryChangedCriterion.Conditions.items(ModItems.BERRY_ROLL))
                .build(consumer, YeastNFeastMod.MODID + ":berry_roll");

        AdvancementEntry bowl = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.BARLEY_AND_BEEF_STEW),
                        Text.translatable("advancements.yeastnfeast.bowl.title"),
                        Text.translatable("advancements.yeastnfeast.bowl.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.CHALLENGE,
                        true, true, false
                ))
                .parent(crops)
                .criterion("eat_sweet_porridge", ConsumeItemCriterion.Conditions.item(ModItems.SWEET_PORRIDGE))
                .criterion("eat_spiced_porridge", ConsumeItemCriterion.Conditions.item(ModItems.SPICED_PORRIDGE))
                .criterion("eat_barley_stew", ConsumeItemCriterion.Conditions.item(ModItems.BARLEY_AND_BEEF_STEW))
                .criterion("eat_salmon_chowder", ConsumeItemCriterion.Conditions.item(ModItems.SALMON_CHOWDER))
                .build(consumer, YeastNFeastMod.MODID + ":bowl");

        AdvancementEntry plate = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.MAPLE_GLAZED_RABBIT),
                        Text.translatable("advancements.yeastnfeast.plate.title"),
                        Text.translatable("advancements.yeastnfeast.plate.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.CHALLENGE,
                        true, true, false
                ))
                .parent(crops)
                .criterion("eat_mead_pork",    ConsumeItemCriterion.Conditions.item(ModItems.MEAD_BRAISED_PORK))
                .criterion("eat_herbal_cod",   ConsumeItemCriterion.Conditions.item(ModItems.HERBAL_COD))
                .criterion("eat_lemon_chicken",ConsumeItemCriterion.Conditions.item(ModItems.LEMON_GLAZED_CHICKEN))
                .criterion("eat_forager_feast",ConsumeItemCriterion.Conditions.item(ModItems.FORAGER_FEAST))
                .criterion("eat_maple_rabbit", ConsumeItemCriterion.Conditions.item(ModItems.MAPLE_GLAZED_RABBIT))
                .build(consumer, YeastNFeastMod.MODID + ":plate");

        AdvancementEntry maple = Advancement.Builder.create()
                .display(new AdvancementDisplay(
                        new ItemStack(ModItems.MAPLE_SYRUP),
                        Text.translatable("advancements.yeastnfeast.maple.title"),
                        Text.translatable("advancements.yeastnfeast.maple.desc"),
                        Optional.of(BACKGROUND),
                        AdvancementFrame.TASK,
                        true, true, false
                ))
                .parent(root)
                .criterion("has_maple_syrup", InventoryChangedCriterion.Conditions.items(ModItems.MAPLE_SYRUP))
                .build(consumer, YeastNFeastMod.MODID + ":maple");
    }
}
