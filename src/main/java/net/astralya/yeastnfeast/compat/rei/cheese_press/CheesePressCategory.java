package net.astralya.yeastnfeast.compat.rei.cheese_press;

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

public class CheesePressCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = Identifier.of(YeastNFeastMod.MODID, "textures/gui/cheese_press_gui.png");
    public static final CategoryIdentifier<CheesePressDisplay> CHEESE_PRESS = CategoryIdentifier.of(YeastNFeastMod.MODID, "cheese_press");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CHEESE_PRESS;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.yeastnfeast.cheese_press");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModItems.CHEESE_PRESS);
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point origin = bounds.getLocation();
        List<Widget> widgets = new LinkedList<>();

        int guiWidth = 150;
        int guiHeight = 66;

        widgets.add(Widgets.createRecipeBase(bounds));
        Rectangle startPoint = YeastNFeastREIPlugin.centeredIntoRecipeBase(origin, guiWidth, guiHeight);

        widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint, 0, 0));

        List<EntryIngredient> inputs = display.getInputEntries();
        if (inputs != null && !inputs.isEmpty()) {
            int[][] inputSlots = new int[][]{
                    {23, 26},
                    {41, 26},
                    {59, 26}
            };

            int count = Math.min(3, inputs.size());
            for (int i = 0; i < count; i++) {
                widgets.add(Widgets.createSlot(new Point(startPoint.x + inputSlots[i][0], startPoint.y + inputSlots[i][1]))
                        .entries(inputs.get(i))
                        .markInput()
                        .disableBackground());
            }
        }

        if (!display.getOutputEntries().isEmpty() && !display.getOutputEntries().getFirst().isEmpty()) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 125, startPoint.y + 28))
                    .entries(display.getOutputEntries().getFirst())
                    .markOutput()
                    .disableBackground());
        }

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 66;
    }
}