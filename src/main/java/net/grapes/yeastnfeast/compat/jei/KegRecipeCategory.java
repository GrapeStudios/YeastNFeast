package net.grapes.yeastnfeast.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.recipe.KegRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class KegRecipeCategory implements IRecipeCategory<KegRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(YeastNFeastMod.MOD_ID, "keg");
    public static final ResourceLocation TEXTURE = new ResourceLocation(YeastNFeastMod.MOD_ID, "textures/gui/keg_gui_2.png");

    public static final RecipeType<KegRecipe> KEG_TYPE = new RecipeType<>(UID, KegRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public KegRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0,0, 176, 81);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.KEG.get()));
    }

    @Override
    public RecipeType<KegRecipe> getRecipeType() {
        return KEG_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.yeastnfeast.keg");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KegRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 26).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 41, 26).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 59, 26).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 48).addIngredients(recipe.getYeastSlot());
        builder.addSlot(RecipeIngredientRole.INPUT, 31, 48).addIngredients(recipe.getTankardSlot());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 125, 28).addItemStack(recipe.getResultItem(null));
    }
}
