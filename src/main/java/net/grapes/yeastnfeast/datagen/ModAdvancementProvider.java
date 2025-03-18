package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {

    public static final Identifier BACKGROUND = new Identifier(YeastNFeastMod.MOD_ID,
            "textures/block/maple_log.png");

    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.YEAST),
                        Text.translatable("advancements.yeastnfeast.root.title"),
                        Text.translatable("advancements.yeastnfeast.root.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_yeast", InventoryChangedCriterion.Conditions.items(ModItems.YEAST))
                .build(consumer, YeastNFeastMod.MOD_ID + ":root");

        Advancement kegAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.KEG),
                        Text.translatable("advancements.yeastnfeast.keg.title"),
                        Text.translatable("advancements.yeastnfeast.keg.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(rootAdvancement)
                .criterion("has_keg", InventoryChangedCriterion.Conditions.items(ModBlocks.KEG))
                .build(consumer, YeastNFeastMod.MOD_ID + ":keg");

        Advancement sweetAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.HONEY_MEAD),
                        Text.translatable("advancements.yeastnfeast.sweet.title"),
                        Text.translatable("advancements.yeastnfeast.sweet.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(kegAdvancement)
                .criterion("has_honey_mead", InventoryChangedCriterion.Conditions.items(ModItems.HONEY_MEAD))
                .criterion("has_molasses_mead", InventoryChangedCriterion.Conditions.items(ModItems.MOLASSES_MEAD))
                .build(consumer, YeastNFeastMod.MOD_ID + ":sweet");

        Advancement sourAdvancements = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.SOUR_MEAD),
                        Text.translatable("advancements.yeastnfeast.sour.title"),
                        Text.translatable("advancements.yeastnfeast.sour.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(kegAdvancement)
                .criterion("has_sour_mead", InventoryChangedCriterion.Conditions.items(ModItems.SOUR_MEAD))
                .criterion("has_thornberry_mead", InventoryChangedCriterion.Conditions.items(ModItems.THORNBERRY_MEAD))
                .build(consumer, YeastNFeastMod.MOD_ID + ":sour");

        Advancement floralAdvancements = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.BLOSSOM_MEAD),
                        Text.translatable("advancements.yeastnfeast.floral.title"),
                        Text.translatable("advancements.yeastnfeast.floral.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(kegAdvancement)
                .criterion("has_blossom_mead", InventoryChangedCriterion.Conditions.items(ModItems.BLOSSOM_MEAD))
                .criterion("has_amber_mead", InventoryChangedCriterion.Conditions.items(ModItems.AMBER_MEAD))
                .build(consumer, YeastNFeastMod.MOD_ID + ":floral");

        Advancement jarAdvancements = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.JAR),
                        Text.translatable("advancements.yeastnfeast.jar.title"),
                        Text.translatable("advancements.yeastnfeast.jar.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(kegAdvancement)
                .criterion("has_jar", InventoryChangedCriterion.Conditions.items(ModItems.JAR))
                .build(consumer, YeastNFeastMod.MOD_ID + ":jar");

        Advancement jamsAdvancements = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.APPLE_JAM),
                        Text.translatable("advancements.yeastnfeast.jam.title"),
                        Text.translatable("advancements.yeastnfeast.jam.desc"),
                        BACKGROUND, AdvancementFrame.CHALLENGE,
                        true, true, false))
                .parent(jarAdvancements)
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
                .build(consumer, YeastNFeastMod.MOD_ID + ":jam");

        Advancement cropsAdvancements = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.BARLEY),
                        Text.translatable("advancements.yeastnfeast.crops.title"),
                        Text.translatable("advancements.yeastnfeast.crops.desc"),
                        BACKGROUND,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(rootAdvancement)
                .criterion("has_crop", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create()
                                .items(ModItems.ELDERBERRIES, ModItems.HAWTHORN_BERRIES,
                                        ModItems.LEMON, ModItems.ROSE_HIPS, ModItems.GARLIC,
                                        ModItems.BARLEY, ModItems.RYE, ModItems.MINT,
                                        ModItems.GINGER)
                                .build()
                ))
                .build(consumer, YeastNFeastMod.MOD_ID + ":crops");

        Advancement berryRollAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.BERRY_ROLL),
                        Text.translatable("advancements.yeastnfeast.berry_roll.title"),
                        Text.translatable("advancements.yeastnfeast.berry_roll.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(cropsAdvancements)
                .criterion("has_berry_roll", InventoryChangedCriterion.Conditions.items(ModItems.BERRY_ROLL))
                .build(consumer, YeastNFeastMod.MOD_ID + ":berry_roll");

        Advancement bowlAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.BARLEY_AND_BEEF_STEW),
                        Text.translatable("advancements.yeastnfeast.bowl.title"),
                        Text.translatable("advancements.yeastnfeast.bowl.desc"),
                        BACKGROUND, AdvancementFrame.CHALLENGE,
                        true, true, false))
                .parent(cropsAdvancements)
                .criterion("eat_sweet_porridge", ConsumeItemCriterion.Conditions.item(ModItems.SWEET_PORRIDGE))
                .criterion("eat_spiced_porridge", ConsumeItemCriterion.Conditions.item(ModItems.SPICED_PORRIDGE))
                .criterion("eat_barley_stew", ConsumeItemCriterion.Conditions.item(ModItems.BARLEY_AND_BEEF_STEW))
                .criterion("eat_salmon_chowder", ConsumeItemCriterion.Conditions.item(ModItems.SALMON_CHOWDER))
                .build(consumer, YeastNFeastMod.MOD_ID + ":bowl");

        Advancement platesAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.MAPLE_GLAZED_RABBIT),
                        Text.translatable("advancements.yeastnfeast.plate.title"),
                        Text.translatable("advancements.yeastnfeast.plate.desc"),
                        BACKGROUND, AdvancementFrame.CHALLENGE,
                        true, true, false))
                .parent(cropsAdvancements)
                .criterion("eat_mead_pork", ConsumeItemCriterion.Conditions.item(ModItems.MEAD_BRAISED_PORK))
                .criterion("eat_herbal_cod", ConsumeItemCriterion.Conditions.item(ModItems.HERBAL_COD))
                .criterion("eat_lemon_chicken", ConsumeItemCriterion.Conditions.item(ModItems.LEMON_GLAZED_CHICKEN))
                .criterion("eat_forager_feast", ConsumeItemCriterion.Conditions.item(ModItems.FORAGER_FEAST))
                .criterion("eat_maple_rabbit", ConsumeItemCriterion.Conditions.item(ModItems.MAPLE_GLAZED_RABBIT))
                .build(consumer, YeastNFeastMod.MOD_ID + ":plate");

        Advancement mapleAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.MAPLE_SYRUP),
                        Text.translatable("advancements.yeastnfeast.maple.tile"),
                        Text.translatable("advancements.yeastnfeast.maple.desc"),
                        BACKGROUND, AdvancementFrame.TASK,
                        true, true, false))
                .parent(rootAdvancement)
                .criterion("has_maple_syrup", InventoryChangedCriterion.Conditions.items(ModItems.MAPLE_SYRUP))
                .build(consumer, YeastNFeastMod.MOD_ID + ":maple");
    }
}
