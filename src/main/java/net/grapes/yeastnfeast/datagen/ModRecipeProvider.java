package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // Shaped Recipes for Food Items ' '

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.TANKARD.get())
                .pattern("S S")
                .pattern("PSP")
                .define('S', ItemTags.PLANKS)
                .define('P', Items.IRON_NUGGET)
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(pWriter);

        // Shapeless Recipes for Food Items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ROSE_PIE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(ModItems.ROSE_HIPS.get())
                .unlockedBy("has_rose_hips", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROSE_HIPS.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ELDERBERRY_PIE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(ModItems.ELDERBERRIES.get())
                .unlockedBy("has_elderberries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELDERBERRIES.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.APPLE_PIE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(Items.APPLE)
                .unlockedBy("has_apple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.APPLE).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESECAKE.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(Items.MILK_BUCKET)
                .unlockedBy("has_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MILK_BUCKET).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHOCOLATE_BUN.get())
                .requires(Items.EGG)
                .requires(ModItems.YEAST.get())
                .requires(Items.COCOA_BEANS)
                .unlockedBy("has_cocoa_beans", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COCOA_BEANS).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BERRY_ROLL.get())
                .requires(Items.EGG)
                .requires(ModItems.YEAST.get())
                .requires(ModItems.HAWTHORN_BERRIES.get())
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RYE_BREAD.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BARLEY_BREAD.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(pWriter);

        // Shapeless Recipes for Seeds
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RYE_SEEDS.get())
                .requires(ModItems.RYE.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VANILLA_SEEDS.get())
                .requires(ModItems.VANILLA.get())
                .unlockedBy("has_vanilla", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.VANILLA.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_SEEDS.get())
                .requires(ModItems.BARLEY.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(pWriter);

        // Shapeless Misc Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YEAST.get())
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Tags.Items.MUSHROOMS).build()))
                .save(pWriter);
    }
}
