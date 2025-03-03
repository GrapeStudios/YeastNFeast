package net.grapes.yeastnfeast.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.grapes.yeastnfeast.YeastNFeastMod;
import net.grapes.yeastnfeast.block.ModBlocks;
import net.grapes.yeastnfeast.recipe.KegRecipe;
import net.grapes.yeastnfeast.screen.KegScreen;

public class YeastNFeastREIPlugin implements REIClientPlugin {
    public static final CategoryIdentifier<KegDisplay> KEG = CategoryIdentifier.of(YeastNFeastMod.MOD_ID, "keg");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new KegCategory());
        registry.addWorkstations(KEG, EntryStacks.of(ModBlocks.KEG.asItem().getDefaultStack()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(KegRecipe.class, KegRecipe.Type.INSTANCE,
                KegDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(89, 25, 24, 17), KegScreen.class,
                KegCategory.KEG);
    }

    public static Rectangle centeredIntoRecipeBase(Point origin, int width, int height) {
        return centeredInto(new Rectangle(origin.x, origin.y, 150, 66), width, height);
    }

    public static Rectangle centeredInto(Rectangle origin, int width, int height) {
        return new Rectangle(origin.x + (origin.width - width) / 2, origin.y + (origin.height - height) / 2, width, height);
    }
}
