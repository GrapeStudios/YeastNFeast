package net.grapes.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.grapes.yeastnfeast.item.ModItems;
import net.grapes.yeastnfeast.util.ModTags;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
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

        // Shapeless Recipes for Seeds
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_SEEDS)
                .input(ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYE_SEEDS)
                .input(ModItems.RYE)
                .criterion(hasItem(ModItems.RYE), conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);
    }
}
