package com.imt.infinityscience.guis;

import java.io.IOException;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.book.pages.Book_MainPage;
import com.imt.infinityscience.containers.Container_Book;

import akka.japi.Pair;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import scala.annotation.meta.getter;
import scala.tools.nsc.typechecker.MethodSynthesis.MethodSynth.Getter;


/* Notes: 
 * I could have used GuiButton but i don't like it.
 * */


public class GUI_Book extends GuiContainer {
	// Static so currentPage stay when closing and reopening the book (persistent page)
	protected static BookPage currentPage = new Book_MainPage();
	
	// Variables
	ResourceLocation textures = new ResourceLocation(Global.MODID, "textures/gui/book_base_assets.png");
	
	public BookPage getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(BookPage currentPage) {
		this.currentPage = currentPage;
	}
	
	
	public GUI_Book() {
		super(new Container_Book());

		this.xSize = 109;
		this.ySize = 157;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);

		this.mc.getTextureManager().bindTexture(textures);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		getCurrentPage().drawPage(partialTicks, mouseX, mouseY, this);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		getCurrentPage().drawPageTop(partialTicks, mouseX, mouseY, this);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (currentPage.pageClicked(mouseX, mouseY, mouseButton, this)) {
			// do nothing, currentPage sorted it out.
		} else {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
}
