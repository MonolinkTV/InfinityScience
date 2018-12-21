package com.imt.infinityscience.guis;

import java.io.IOException;
import java.util.ArrayList;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.pages.BookPage_Home;
import com.imt.infinityscience.containers.Container_Book;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;


/* Notes: 
 * I could have used GuiButton but i don't like it.
 * */


public class GUI_Book extends GuiContainer {
	// Static so currentPage stay when closing and reopening the book (persistent page) (public so BookPage can set it and maybe else where)
	//public static BookPage currentPage = new BookPage_Home();
	// Static so pageHistory will stay when closing then reopening the book (persistent history/page), Public so it can be changed else where (EG. BookPage class)
	public static ArrayList<BookPage> pageHistory = new ArrayList(); // WARNING: this is a stack based, last item added is first item (index 0)
	public static BookPage getCurrentPage() {
		return pageHistory.get(0);
	}
	public static void setCurrentPage(BookPage newPage) { // NOTE: setCurrentPage while on default page will change default page to newPage!
		getCurrentPage().leftPage(newPage);
		pageHistory.set(0, newPage);
	}
	public static void setNewPage(BookPage newPage) {
		getCurrentPage().leftPage(newPage);
		pageHistory.add(0, newPage);
	}
	public static void setPreviousPage() {
		if (pageHistory.size() > 1) { // NEVER remove the starting page
			BookPage page = pageHistory.remove(0);
			getCurrentPage().enterPage(page);
		}
	}
	// Add new starting page, Edit \/ as desired.
	static { pageHistory.add(new BookPage_Home()); }
	
	// Variables
	ResourceLocation textures = new ResourceLocation(Global.MODID, "textures/gui/book_base_assets.png");
	
	
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
		if (mouseButton == 1) {
			setPreviousPage();
		} else if (getCurrentPage().pageClicked(mouseX, mouseY, mouseButton, this)) {
			// do nothing, currentPage sorted it out.
		} else {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
}
