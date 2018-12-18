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
	// Texture And GUI Positions
	BookTextureData leftArrow = new BookTextureData(null, 111, 0, 17, 9);
	BookTextureData rightArrow = new BookTextureData(null, 129, 0, 17, 9);
	Pair<Integer, Integer> leftArrowGuiPos = new Pair(5, 150-leftArrow.height);
	Pair<Integer, Integer> rightArrowGuiPos = new Pair(104-rightArrow.width, 150-rightArrow.height);
	
	public int pageStringColor = 4210752;
	
	// static so currentPage stay when closing and reopening the book (persistent page)
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
		// Maybe gray out or hide next/previous buttons if !currPage.canGoXXX()
		this.drawTexturedModalRect(this.guiLeft + leftArrowGuiPos.first(), this.guiTop + leftArrowGuiPos.second(), leftArrow.x, leftArrow.y, leftArrow.width, leftArrow.height);
		this.drawTexturedModalRect(this.guiLeft + rightArrowGuiPos.first(), this.guiTop + rightArrowGuiPos.second(), rightArrow.x, rightArrow.y, rightArrow.width, rightArrow.height);
		
		getCurrentPage().drawPage(partialTicks, mouseX, mouseY, this);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		getCurrentPage().drawPageTop(partialTicks, mouseX, mouseY, this);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		int guiMouseX = mouseX - this.guiLeft;
		int guiMouseY = mouseY - this.guiTop;
		if (currentPage.canGoBack() &&
			guiMouseX > leftArrowGuiPos.first() && guiMouseX < leftArrowGuiPos.first() + leftArrow.width &&
			guiMouseY > leftArrowGuiPos.second() && guiMouseY < leftArrowGuiPos.second() + leftArrow.height) {
			BookPage newPage = currentPage.prevPage();
			if (newPage != null) {
				currentPage = newPage;
			}
		} else if (currentPage.canGoForward() &&
				   guiMouseX > rightArrowGuiPos.first() && guiMouseX < rightArrowGuiPos.first() + rightArrow.width &&
				   guiMouseY > rightArrowGuiPos.second() && guiMouseY < rightArrowGuiPos.second() + rightArrow.height) {
			BookPage newPage = currentPage.nextPage();
			if (newPage != null) {
				currentPage = newPage;
			}
		} else if (currentPage.pageClicked(mouseX, mouseY, mouseButton, this)) {
			// do nothing, currentPage sorted it out.
		} else {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
}
