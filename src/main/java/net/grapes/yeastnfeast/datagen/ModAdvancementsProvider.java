package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementsProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.YEAST.get()),
                        Component.translatable("advancements.yeastnfeast.root.title"),
                        Component.translatable("advancements.yeastnfeast.root.desc"),
                        new ResourceLocation("textures/block/spruce_planks"), FrameType.TASK,
                        true,true, false))
                .addCriterion("has_yeast", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.YEAST.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MOD_ID, "root"), existingFileHelper);

        Advancement tankard = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.TANKARD.get()),
                        Component.translatable("advancements.yeastnfeast.tankard.title"),
                        Component.translatable("advancements.yeastnfeast.tankard.desc"),
                        null, FrameType.TASK,
                        true, true, false))
                .parent(rootAdvancement)
                .addCriterion("has_tankard", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TANKARD.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MOD_ID, "tankard"), existingFileHelper);

        Advancement barleyBread = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.BARLEY_BREAD.get()),
                        Component.translatable("advancements.yeastnfeast.barley_bread.title"),
                        Component.translatable("advancements.yeastnfeast.barley_bread.desc"),
                        null, FrameType.TASK,
                        true, true, false))
                .parent(rootAdvancement)
                .addCriterion("consumed_barley_bread", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BARLEY_BREAD.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MOD_ID, "barley_bread"), existingFileHelper);
    }
}
