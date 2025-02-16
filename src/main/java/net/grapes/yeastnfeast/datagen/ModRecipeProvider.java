package net.grapes.yeastnfeast.datagen;

import net.grapes.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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


        // Shapeless Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YEAST.get())
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Tags.Items.MUSHROOMS).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MILK_BOTTLE.get(), 4)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MILK_BUCKET).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MILK_BUCKET)
                .requires(Items.BUCKET)
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .unlockedBy("has_milk_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MILK_BOTTLE.get()).build()))
                .save(pWriter);

        // Shapeless Recipes for Seeds
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RYE_SEEDS.get())
                .requires(ModItems.RYE.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_SEEDS.get())
                .requires(ModItems.BARLEY.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(pWriter);
    }
}
