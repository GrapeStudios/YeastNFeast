package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.datagen.custom.CheesePressRecipeBuilder;
import net.astralya.yeastnfeast.datagen.custom.KegRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        // Shaped Recipes for Food Items
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TANKARD)
                .pattern("S S")
                .pattern("PSP")
                .input('P', Items.IRON_NUGGET)
                .input('S', ItemTags.PLANKS)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.TANKARD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ELDERBERRY_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.ELDERBERRIES)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ELDERBERRIES), conditionsFromItem(ModItems.ELDERBERRIES))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.ELDERBERRY_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ROSE_TART)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.ROSE_HIPS)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ROSE_HIPS), conditionsFromItem(ModItems.ROSE_HIPS))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.ROSE_TART)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', Items.APPLE)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.APPLE_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINTED_CHEESE_TART)
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('R', ModItems.MINT)
                .input('S', ModItems.FRESHWHEEL_SLICE)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.FRESHWHEEL_SLICE), conditionsFromItem(ModItems.FRESHWHEEL_SLICE))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.MINTED_CHEESE_TART)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.QUICHE)
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('R', ModItems.GARLIC)
                .input('S', ModTags.Items.CHEESE_FOODS)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.CHEESE_SLICE), conditionsFromItem(ModItems.CHEESE_SLICE))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.QUICHE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BERRY_ROLL)
                .pattern(" S ")
                .pattern("SPS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.HAWTHORN_BERRIES)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.BERRY_ROLL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TREE_TAP)
                .pattern(" H")
                .pattern("PS")
                .input('P', Items.STICK)
                .input('S', ItemTags.PLANKS)
                .input('H', Items.IRON_NUGGET)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModBlocks.TREE_TAP)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.KEG)
                .pattern("PSP")
                .input('P', Items.STICK)
                .input('S', Blocks.BARREL)
                .criterion(hasItem(Blocks.BARREL), conditionsFromItem(Blocks.BARREL))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModBlocks.KEG)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JAR, 3)
                .pattern("PSP")
                .pattern(" P ")
                .input('P', Blocks.GLASS)
                .input('S', ItemTags.PLANKS)
                .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModItems.JAR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHEESE_PRESS)
                .pattern(" P ")
                .pattern("PSP")
                .pattern("HHH")
                .input('P', Items.STICK)
                .input('S', Items.CHAIN)
                .input('H', ItemTags.PLANKS)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .offerTo(recipeExporter, Identifier.of(getRecipeName(ModBlocks.CHEESE_PRESS)));

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.YEAST)
                .input(ModTags.Items.MUSHROOMS)
                .input(ModTags.Items.MUSHROOMS)
                .criterion("has_mushroom", conditionsFromTag(ModTags.Items.MUSHROOMS))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILK_BOTTLE, 4)
                .input(Items.MILK_BUCKET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .criterion("has_milk_bucket", conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MILK_BUCKET)
                .input(Items.BUCKET)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .criterion("has_milk_bottle", conditionsFromItem(Items.BUCKET))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RENNET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.ROTTEN_FLESH)
                .input(ModBlocks.THISTLE)
                .criterion("has_thistle", conditionsFromItem(ModBlocks.THISTLE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEET_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.MOLASSES)
                .input(ModTags.Items.MILKS)
                .input(Items.WHEAT)
                .input(ModTags.Items.BERRY_FOODS)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SPICED_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.MILKS)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_AND_BEEF_STEW)
                .input(Items.BOWL)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.COOKED_BEEF)
                .criterion("has_cooked_beef", conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MEAD_BRAISED_PORK)
                .input(Items.BOWL)
                .input(ModItems.HONEY_MEAD)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.COOKED_PORK)
                .criterion("has_cooked_pork", conditionsFromItem(Items.COOKED_PORKCHOP))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HERBAL_COD)
                .input(Items.BOWL)
                .input(ModItems.MINT)
                .input(ModItems.GINGER)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_FISH_COD)
                .criterion("has_cooked_cod", conditionsFromItem(Items.COOKED_COD))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LEMON_GLAZED_CHICKEN)
                .input(Items.BOWL)
                .input(Items.HONEY_BOTTLE)
                .input(ModItems.GARLIC)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_CHICKEN)
                .criterion("has_cooked_chicken", conditionsFromItem(Items.COOKED_CHICKEN))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FORAGER_FEAST)
                .input(Items.BOWL)
                .input(ModItems.ELDERBERRIES)
                .input(ModItems.RYE)
                .input(ModItems.MINT)
                .input(ModTags.Items.COOKED_MUTTON)
                .criterion("has_cooked_mutton", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MAPLE_GLAZED_RABBIT)
                .input(Items.BOWL)
                .input(ModItems.MAPLE_SYRUP)
                .input(ModItems.BARLEY)
                .input(Items.BEETROOT)
                .input(Items.COOKED_RABBIT)
                .criterion("has_cooked_rabbit", conditionsFromItem(Items.COOKED_RABBIT))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SALMON_CHOWDER)
                .input(Items.BOWL)
                .input(ModItems.LEMON)
                .input(ModTags.Items.MILKS)
                .input(Items.KELP)
                .input(ModTags.Items.COOKED_FISH_SALMON)
                .criterion("has_cooked_salmon", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.STUFFED_RYE_DUMPLINGS)
                .input(Items.BOWL)
                .input(ModItems.RYE)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .input(ModItems.SHARPWHEEL_SLICE)
                .input(ModItems.SHARPWHEEL_SLICE)
                .criterion("has_sharpwheel_slice", conditionsFromItem(ModItems.SHARPWHEEL_SLICE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.DUSKWHEEL_SKEWER)
                .input(Items.STICK)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.COOKED_CHICKEN)
                .input(ModItems.DUSKWHEEL_SLICE)
                .input(ModItems.DUSKWHEEL_SLICE)
                .criterion("has_duskwheel_slice", conditionsFromItem(ModItems.DUSKWHEEL_SLICE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CHEESE_SOUP)
                .input(Items.BOWL)
                .input(ModTags.Items.CHEESE_FOODS)
                .input(ModTags.Items.CHEESE_FOODS)
                .input(ModTags.Items.BREAD_FOODS)
                .input(ModItems.RYE)
                .input(ModItems.GARLIC)
                .criterion("has_cheese_slice", conditionsFromItem(ModItems.CHEESE_SLICE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_BREAD)
                .input(ModItems.BARLEY)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RYE_BREAD)
                .input(ModItems.RYE)
                .input(ModItems.RYE)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MOLASSES_BREAD)
                .input(ModTags.Items.WHEAT_CROPS)
                .input(ModTags.Items.WHEAT_CROPS)
                .input(ModItems.MOLASSES)
                .input(ModItems.YEAST)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(recipeExporter);

        /*ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HOMESTEADERS_HANDBOOK)
                .input(Items.WHEAT)
                .input(Items.BOOK)
                .criterion("has_book", conditionsFromItem(Items.BOOK))
                .offerTo(recipeExporter);*/

        // Convertible for Storage Bags
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.ELDERBERRIES, RecipeCategory.MISC, ModBlocks.BAG_OF_ELDERBERRIES);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.GARLIC, RecipeCategory.MISC, ModBlocks.BAG_OF_GARLIC);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.GINGER, RecipeCategory.MISC, ModBlocks.BAG_OF_GINGER);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.HAWTHORN_BERRIES, RecipeCategory.MISC, ModBlocks.BAG_OF_HAWTHORN_BERRIES);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.LEMON, RecipeCategory.MISC, ModBlocks.BAG_OF_LEMON);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.MINT, RecipeCategory.MISC, ModBlocks.BAG_OF_MINT);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.ROSE_HIPS, RecipeCategory.MISC, ModBlocks.BAG_OF_ROSE_HIPS);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModBlocks.THISTLE, RecipeCategory.MISC, ModBlocks.BAG_OF_THISTLE);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.BARLEY, RecipeCategory.MISC, ModBlocks.BARLEY_BLOCK);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.RYE, RecipeCategory.MISC, ModBlocks.RYE_BLOCK);

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_SEEDS)
                .input(ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYE_SEEDS)
                .input(ModItems.RYE)
                .criterion(hasItem(ModItems.RYE), conditionsFromItem(ModItems.RYE))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING)
                .input(ModItems.LEMON)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.LEMON), conditionsFromItem(ModItems.LEMON))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING)
                .input(ModItems.HAWTHORN_BERRIES)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MOLASSES)
                .input(Items.GLASS_BOTTLE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .criterion(hasItem(Items.SUGAR_CANE), conditionsFromItem(Items.SUGAR_CANE))
                .offerTo(recipeExporter);

        // Keg Recipe Generator
        KegRecipeBuilder.keg()
                .addIngredient(Items.APPLE)
                .addIngredient(Items.HONEY_BOTTLE)
                .addIngredient(Items.WHEAT)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.HONEY_MEAD, 1)
                .experience(0.50f)
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.HONEY_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.ELDERBERRIES)
                .addIngredient(ModItems.LEMON)
                .addIngredient(ModItems.GINGER)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.SOUR_MEAD, 1)
                .experience(0.50f)
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.SOUR_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.BARLEY)
                .addIngredient(ModItems.MOLASSES)
                .addIngredient(Items.SUGAR_CANE)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.MOLASSES_MEAD, 1)
                .experience(0.50f)
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.MOLASSES_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.HAWTHORN_BERRIES)
                .addIngredient(ModItems.ELDERBERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.THORNBERRY_MEAD, 1)
                .experience(0.50f)
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.THORNBERRY_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.ROSE_HIPS)
                .addIngredient(ModItems.MAPLE_SYRUP)
                .addIngredient(ModItems.MINT)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.BLOSSOM_MEAD, 1)
                .experience(0.50f) 
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.BLOSSOM_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.RYE)
                .addIngredient(ModItems.MAPLE_SYRUP)
                .addIngredient(ModItems.ROSE_HIPS)
                .yeast(ModItems.YEAST)
                .tankard(ModItems.TANKARD)
                .result(ModItems.AMBER_MEAD, 1)
                .experience(0.50f) 
                .brewTime(7200)
                .offerTo(recipeExporter, Identifier.of(ModItems.AMBER_MEAD + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.APPLE)
                .addIngredient(Items.APPLE)
                .addIngredient(Items.APPLE)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.APPLE_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.APPLE_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(Items.CHORUS_FRUIT)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.CHORUS_FRUIT_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.CHORUS_FRUIT_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.ELDERBERRIES)
                .addIngredient(ModItems.ELDERBERRIES)
                .addIngredient(ModItems.ELDERBERRIES)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.ELDERBERRIES_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.ELDERBERRIES_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.GLOW_BERRIES_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.GLOW_BERRIES_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.GOLDEN_APPLE)
                .addIngredient(Items.GOLDEN_APPLE)
                .addIngredient(Items.GOLDEN_APPLE)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.GOLDEN_APPLE_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.GOLDEN_APPLE_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.HAWTHORN_BERRIES)
                .addIngredient(ModItems.HAWTHORN_BERRIES)
                .addIngredient(ModItems.HAWTHORN_BERRIES)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.HAWTHORN_BERRIES_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.HAWTHORN_BERRIES_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.LEMON)
                .addIngredient(ModItems.LEMON)
                .addIngredient(ModItems.LEMON)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.LEMON_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.LEMON_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(ModItems.ROSE_HIPS)
                .addIngredient(ModItems.ROSE_HIPS)
                .addIngredient(ModItems.ROSE_HIPS)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.ROSE_HIPS_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.ROSE_HIPS_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.SWEET_BERRIES_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.SWEET_BERRIES_JAM + "_from_keg"));

        KegRecipeBuilder.keg()
                .addIngredient(Items.MELON_SLICE)
                .addIngredient(Items.MELON_SLICE)
                .addIngredient(Items.MELON_SLICE)
                .yeast(Items.SUGAR)
                .tankard(ModItems.JAR)
                .result(ModItems.MELON_JAM, 1)
                .experience(0.35f)
                .brewTime(3600)
                .offerTo(recipeExporter, Identifier.of(ModItems.MELON_JAM + "_from_keg"));

        // Cheese Press
        CheesePressRecipeBuilder.cheesePress()
                .addIngredient(ModTags.Items.MILKS)
                .addIngredient(ModItems.RENNET)
                .result(ModItems.CHEESE_WHEEL, 1)
                .experience(0.2f)
                .pressTime(7200)
                .offerTo(recipeExporter, Identifier.of("yeastnfeast", "cheese_wheel_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress()
                .addIngredient(ModTags.Items.MILKS)
                .addIngredient(ModItems.RENNET)
                .flavor(ModItems.ELDERBERRIES)
                .result(ModItems.DUSKWHEEL, 1)
                .experience(0.2f)
                .pressTime(7200)
                .offerTo(recipeExporter, Identifier.of("yeastnfeast", "duskwheel_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress()
                .addIngredient(ModTags.Items.MILKS)
                .addIngredient(ModItems.RENNET)
                .flavor(ModItems.GARLIC)
                .result(ModItems.SHARPWHEEL, 1)
                .experience(0.2f)
                .pressTime(7200)
                .offerTo(recipeExporter, Identifier.of("yeastnfeast", "sharpwheel_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress()
                .addIngredient(ModTags.Items.MILKS)
                .addIngredient(ModItems.RENNET)
                .flavor(ModItems.MINT)
                .result(ModItems.FRESHWHEEL, 1)
                .experience(0.2f)
                .pressTime(7200)
                .offerTo(recipeExporter, Identifier.of("yeastnfeast", "freshwheel_from_cheese_press"));

        // Recipes for Wood-related Blocks & Items
        offerPlanksRecipe(recipeExporter, ModBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS, 4);
        offerSingleOutputShapelessRecipe(recipeExporter, ModBlocks.MAPLE_BUTTON, ModBlocks.MAPLE_PLANKS, "wooden_button");
        createTrapdoorRecipe(ModBlocks.MAPLE_TRAPDOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createDoorRecipe(ModBlocks.MAPLE_DOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.MAPLE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createStairsRecipe(ModBlocks.MAPLE_STAIRS, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createSlabRecipe(RecipeCategory.DECORATIONS, ModBlocks.MAPLE_SLAB, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createFenceRecipe(ModBlocks.MAPLE_FENCE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        createFenceGateRecipe(ModBlocks.MAPLE_FENCE_GATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        offerBoatRecipe(recipeExporter, ModItems.MAPLE_BOAT, ModBlocks.MAPLE_PLANKS);
        offerChestBoatRecipe(recipeExporter, ModItems.MAPLE_CHEST_BOAT, ModItems.MAPLE_BOAT);

        createSignRecipe(ModItems.MAPLE_SIGN, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(recipeExporter);
        offerHangingSignRecipe(recipeExporter, ModItems.MAPLE_HANGING_SIGN, ModBlocks.STRIPPED_MAPLE_LOG);
    }
}
