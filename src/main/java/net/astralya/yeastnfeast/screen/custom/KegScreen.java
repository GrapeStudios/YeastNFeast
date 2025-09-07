package net.astralya.yeastnfeast.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KegScreen extends AbstractContainerScreen<KegMenu> {

    public static final Rect2i PROGRESS = new Rect2i(81, 26, 0, 15);

    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID,
            "textures/gui/keg_gui.png");

    public KegScreen(KegMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 1000;
        this.titleLabelY = 1000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderElements(guiGraphics, x, y);
    }

    private void renderElements(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            int progress = menu.getScaledProgress();
            guiGraphics.blit(GUI_TEXTURE,
                    x + PROGRESS.getX(), y + PROGRESS.getY(),
                    176, 15, progress + 1, PROGRESS.getHeight());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
