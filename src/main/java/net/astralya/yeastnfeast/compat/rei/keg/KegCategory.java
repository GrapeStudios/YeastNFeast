package net.astralya.yeastnfeast.compat.rei.keg;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.compat.rei.YeastNFeastREIPlugin;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class KegCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = Identifier.of(YeastNFeastMod.MODID, "textures/gui/keg_gui.png");
    public static final CategoryIdentifier<KegDisplay> KEG = CategoryIdentifier.of(YeastNFeastMod.MODID, "keg");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return KEG;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.yeastnfeast.keg");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModItems.KEG);
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point origin = bounds.getLocation();
        List<Widget> widgets = new LinkedList<>();
        int guiWidth = 140;
        int guiHeight = 60;

        int fixedY = 33;
        int fixedY2 = 11;

        widgets.add(Widgets.createRecipeBase(bounds));
        Rectangle startPoint = YeastNFeastREIPlugin.centeredIntoRecipeBase(origin, guiWidth, guiHeight);
        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint, 20, 15));

        List<EntryIngredient> ingredientEntries = display.getInputEntries();
        if (ingredientEntries != null && ingredientEntries.size() >= 3) {
            Point slot1Loc = new Point(startPoint.x + 3, startPoint.y + fixedY2);
            Point slot2Loc = new Point(startPoint.x + 21, startPoint.y + fixedY2);
            Point slot3Loc = new Point(startPoint.x + 39, startPoint.y + fixedY2);

            widgets.add(Widgets.createSlot(slot1Loc).entries(ingredientEntries.get(0)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(slot2Loc).entries(ingredientEntries.get(1)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(slot3Loc).entries(ingredientEntries.get(2)).markInput().disableBackground());
        }

        KegDisplay kegDisplay = (KegDisplay) display;
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 11, startPoint.y + fixedY))
                .entries(kegDisplay.getTankardSlot()).markInput().disableBackground());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 32, startPoint.y + fixedY))
                .entries(kegDisplay.getYeastSlot()).markInput().disableBackground());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 105, startPoint.y + 12))
                .entries(display.getOutputEntries().getFirst()).markOutput().disableBackground());
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
