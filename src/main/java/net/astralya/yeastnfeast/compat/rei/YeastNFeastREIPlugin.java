package net.astralya.yeastnfeast.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.compat.rei.cheese_press.CheesePressCategory;
import net.astralya.yeastnfeast.compat.rei.cheese_press.CheesePressDisplay;
import net.astralya.yeastnfeast.compat.rei.keg.KegCategory;
import net.astralya.yeastnfeast.compat.rei.keg.KegDisplay;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.screen.KegScreen;

public class YeastNFeastREIPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<KegDisplay> KEG = CategoryIdentifier.of(YeastNFeastMod.MODID, "keg");
    public static final CategoryIdentifier<CheesePressDisplay> CHEESE_PRESS = CategoryIdentifier.of(YeastNFeastMod.MODID, "cheese_press");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new KegCategory());
        registry.addWorkstations(KEG, EntryStacks.of(ModBlocks.KEG.asItem().getDefaultStack()));

        registry.add(new CheesePressCategory());
        registry.addWorkstations(CHEESE_PRESS, EntryStacks.of(ModBlocks.CHEESE_PRESS.asItem().getDefaultStack()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(KegRecipe.class, KegRecipe.Type.INSTANCE, KegDisplay::new);
        registry.registerRecipeFiller(CheesePressRecipe.class, CheesePressRecipe.Type.INSTANCE, CheesePressDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(89, 25, 24, 17), KegScreen.class, KegCategory.KEG);
    }

    public static Rectangle centeredIntoRecipeBase(Point origin, int width, int height) {
        return centeredInto(new Rectangle(origin.x, origin.y, 150, 66), width, height);
    }

    public static Rectangle centeredInto(Rectangle origin, int width, int height) {
        return new Rectangle(origin.x + (origin.width - width) / 2, origin.y + (origin.height - height) / 2, width, height);
    }
}