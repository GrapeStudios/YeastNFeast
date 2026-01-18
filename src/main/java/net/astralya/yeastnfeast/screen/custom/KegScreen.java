package net.astralya.yeastnfeast.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KegScreen extends HandledScreen<KegScreenHandler> {

    private static final Identifier GUI_TEXTURE = Identifier.of(YeastNFeastMod.MODID, "textures/gui/keg_gui.png");

    private static final int PROG_X = 81;
    private static final int PROG_Y = 26;
    private static final int PROG_V = 15;
    private static final int PROG_H = 15;

    public KegScreen(KegScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (handler.isCrafting()) {
            int w = handler.getScaledProgress();
            context.drawTexture(GUI_TEXTURE, x + PROG_X, y + PROG_Y, 176, PROG_V, w + 1, PROG_H);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
