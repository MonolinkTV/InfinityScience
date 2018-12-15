package com.imt.infinityscience.guis;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.containers.Container_Book;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GUI_Book extends GuiContainer {
	public GUI_Book() {
		super(new Container_Book());
		
		this.xSize = 109;
		this.ySize = 157;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Global.MODID, "textures/gui/book_base_assets.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}
}
