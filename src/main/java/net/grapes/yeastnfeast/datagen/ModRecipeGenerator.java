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
import net.minecraft.item.Item;
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
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TANKARD)));

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

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SWEET_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.MOLASSES)
                .input(ModTags.Items.MILKS)
                .input(Items.WHEAT)
                .input(ModTags.Items.FOODS_BERRIES)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPICED_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.MILKS)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_AND_BEEF_STEW)
                .input(Items.BOWL)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.COOKED_BEEF)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MEAD_BRAISED_PORK)
                .input(Items.BOWL)
                .input(ModItems.HONEY_MEAD)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.FOODS_VEGETABLES)
                .input(ModTags.Items.COOKED_PORK)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HERBED_COD)
                .input(Items.BOWL)
                .input(ModItems.MINT)
                .input(ModItems.GINGER)
                .input(ModItems.LEMON)
                .input(Items.COOKED_COD)
                .criterion("has_mint", conditionsFromItem(ModItems.MINT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LEMON_GLAZED_CHICKEN)
                .input(Items.BOWL)
                .input(Items.HONEY_BOTTLE)
                .input(ModItems.GARLIC)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_CHICKEN)
                .criterion("has_lemon", conditionsFromItem(ModItems.LEMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FORAGER_FEAST)
                .input(Items.BOWL)
                .input(ModItems.ELDERBERRIES)
                .input(ModItems.RYE)
                .input(ModItems.MINT)
                .input(ModTags.Items.COOKED_MUTTON)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAPLE_GLAZED_RABBIT)
                .input(Items.BOWL)
                .input(ModItems.MAPLE_SYRUP)
                .input(ModItems.BARLEY)
                .input(Items.BEETROOT)
                .input(Items.COOKED_RABBIT)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        // Shapeless Recipes for Seeds
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_SEEDS)
                .input(ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYE_SEEDS)
                .input(ModItems.RYE)
                .criterion(hasItem(ModItems.RYE), conditionsFromItem(ModItems.RYE))
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
