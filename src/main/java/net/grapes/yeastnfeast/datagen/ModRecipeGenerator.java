package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // Shaped Recipes for Food Items
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TANKARD)
                .pattern("S S")
                .pattern("PSP")
                .input('P', Items.IRON_NUGGET)
                .input('S', ItemTags.PLANKS)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TANKARD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ELDERBERRY_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.GRAINS_WHEAT)
                .input('S', ModItems.ELDERBERRIES)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ELDERBERRIES), conditionsFromItem(ModItems.ELDERBERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ELDERBERRY_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ROSE_TART)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.GRAINS_WHEAT)
                .input('S', ModItems.ROSE_HIPS)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ROSE_HIPS), conditionsFromItem(ModItems.ROSE_HIPS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ROSE_TART)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.APPLE_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.GRAINS_WHEAT)
                .input('S', Items.APPLE)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.APPLE_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BERRY_ROLL)
                .pattern(" S ")
                .pattern("SPS")
                .pattern("PHP")
                .input('P', ModTags.Items.GRAINS_WHEAT)
                .input('S', ModItems.HAWTHORN_BERRIES)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BERRY_ROLL)));

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.YEAST)
                .input(ModTags.Items.MUSHROOMS)
                .input(ModTags.Items.MUSHROOMS)
                .criterion("has_mushroom", conditionsFromTag(ModTags.Items.MUSHROOMS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILK_BOTTLE, 4)
                .input(Items.MILK_BUCKET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .criterion("has_milk_bucket", conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MILK_BUCKET)
                .input(Items.BUCKET)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .criterion("has_milk_bottle", conditionsFromItem(Items.BUCKET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEET_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.MOLASSES)
                .input(ModTags.Items.MILKS)
                .input(Items.WHEAT)
                .input(ModTags.Items.FOODS_BERRIES)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SPICED_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.MILKS)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_AND_BEEF_STEW)
                .input(Items.BOWL)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.COOKED_BEEF)
                .criterion("has_cooked_beef", conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MEAD_BRAISED_PORK)
                .input(Items.BOWL)
                .input(ModItems.HONEY_MEAD)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.COOKED_PORK)
                .criterion("has_cooked_pork", conditionsFromItem(Items.COOKED_PORKCHOP))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HERBED_COD)
                .input(Items.BOWL)
                .input(ModItems.MINT)
                .input(ModItems.GINGER)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_COD)
                .criterion("has_cooked_cod", conditionsFromItem(Items.COOKED_COD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LEMON_GLAZED_CHICKEN)
                .input(Items.BOWL)
                .input(Items.HONEY_BOTTLE)
                .input(ModItems.GARLIC)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_CHICKEN)
                .criterion("has_cooked_chicken", conditionsFromItem(Items.COOKED_CHICKEN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FORAGER_FEAST)
                .input(Items.BOWL)
                .input(ModItems.ELDERBERRIES)
                .input(ModItems.RYE)
                .input(ModItems.MINT)
                .input(ModTags.Items.COOKED_MUTTON)
                .criterion("has_cooked_mutton", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MAPLE_GLAZED_RABBIT)
                .input(Items.BOWL)
                .input(ModItems.MAPLE_SYRUP)
                .input(ModItems.BARLEY)
                .input(Items.BEETROOT)
                .input(Items.COOKED_RABBIT)
                .criterion("has_cooked_rabbit", conditionsFromItem(Items.COOKED_RABBIT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SALMON_CHOWDER)
                .input(Items.BOWL)
                .input(ModItems.LEMON)
                .input(ModTags.Items.MILKS)
                .input(Items.KELP)
                .input(ModTags.Items.COOKED_SALMON)
                .criterion("has_cooked_salmon", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_BREAD)
                .input(ModItems.BARLEY)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RYE_BREAD)
                .input(ModItems.RYE)
                .input(ModItems.RYE)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MOLASSES_BREAD)
                .input(ModTags.Items.GRAINS_WHEAT)
                .input(ModTags.Items.GRAINS_WHEAT)
                .input(ModItems.MOLASSES)
                .input(ModItems.YEAST)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_SEEDS)
                .input(ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYE_SEEDS)
                .input(ModItems.RYE)
                .criterion(hasItem(ModItems.RYE), conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING)
                .input(ModItems.LEMON)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.LEMON), conditionsFromItem(ModItems.LEMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING)
                .input(ModItems.HAWTHORN_BERRIES)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MOLASSES)
                .input(Items.GLASS_BOTTLE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .criterion(hasItem(Items.SUGAR_CANE), conditionsFromItem(Items.SUGAR_CANE))
                .offerTo(exporter);

        // Recipes for Wood-related Blocks & Items
        offerPlanksRecipe(exporter, ModBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS, 4);
        offerSingleOutputShapelessRecipe(exporter, ModBlocks.MAPLE_BUTTON, ModBlocks.MAPLE_PLANKS, "wooden_button");
        createTrapdoorRecipe(ModBlocks.MAPLE_TRAPDOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createDoorRecipe(ModBlocks.MAPLE_DOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.MAPLE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createStairsRecipe(ModBlocks.MAPLE_STAIRS, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createSlabRecipe(RecipeCategory.DECORATIONS, ModBlocks.MAPLE_SLAB, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createFenceRecipe(ModBlocks.MAPLE_FENCE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createFenceGateRecipe(ModBlocks.MAPLE_FENCE_GATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        offerBoatRecipe(exporter, ModItems.MAPLE_BOAT, ModBlocks.MAPLE_PLANKS);
        offerChestBoatRecipe(exporter, ModItems.MAPLE_CHEST_BOAT, ModItems.MAPLE_BOAT);
    }
}
