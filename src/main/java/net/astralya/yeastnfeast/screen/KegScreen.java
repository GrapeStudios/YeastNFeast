package net.astralya.yeastnfeast.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KegScreen extends HandledScreen<KegScreenHandler> {

    public static final Rect2i PROGRESS = new Rect2i(81, 26, 0, 15);

    public static final Identifier TEXTURE = new Identifier(YeastNFeastMod.MODID,
            "textures/gui/keg_gui.png");

    public KegScreen(KegScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 5;
        titleX = 5;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            int h = handler.getScaledProgress();
            context.drawTexture(TEXTURE, x + PROGRESS.getX(), y + PROGRESS.getY(),
                    176, 15, h + 4, PROGRESS.getHeight());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
