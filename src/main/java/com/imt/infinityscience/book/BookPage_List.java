package com.imt.infinityscience.book;

import java.awt.List;
import java.util.ArrayList;

import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import scala.Console;
import scala.actors.threadpool.Arrays;

// TODO: Text wrapping (not that simple D:)

public class BookPage_List extends BookPage {
	protected Pair<Integer, Integer> listStartPos = new Pair(5, 5); // Based on GUI size
	protected Pair<Integer, Integer> listEndPos = new Pair(103, 140); // Based on GUI size
	
	protected String title = null;
	protected ArrayList<Pair<BookPage, BookPage_ListItem>> contents;
	
	ArrayList<ArrayList<Pair<Integer, Pair<BookPage, BookPage_ListItem>>>> cachePages; 
	ArrayList<Integer> cacheItemSeperation;
	
	protected int pageIndex;
	
	public BookPage_List() {
		// WARNING: some reason mine craft is like NO to using my calculateCache methods in constructor (here), not a good idea anyway
		
		this.enableNextArrow = true;
		this.enablePrevArrow = true;
	}
	
	protected void calculateCacheItemSeperation() {
		Minecraft mc = Minecraft.getMinecraft();
		
		this.cacheItemSeperation = new ArrayList();
		for (int i = 0; i < this.contents.size(); i++) {
			// Get previous item to get current item separation from previous item (more math because we draw from topLeft)
			// BUT if there is no previous then we just use 0 (NOTE: when drawing first item in the page always ignores cacheSeperation)
			if (i-1 < 0) { // Currently only if i == 0 but being sure is nice
				this.cacheItemSeperation.add(0);
				continue;
			}
			
			Pair<BookPage, BookPage_ListItem> pair = this.contents.get(i-1);
			BookPage_ListItem item = pair.second();
			
			int iconHeight = 0;
			if (item.icon != null) {
				iconHeight = item.icon.height;
			}
			
			int height;
			if (iconHeight > mc.fontRenderer.FONT_HEIGHT) {
				height = iconHeight;
			} else {
				height = mc.fontRenderer.FONT_HEIGHT;
			};
			
			this.cacheItemSeperation.add(i, height);
		};
	}
	
	protected void calculateCachePages() {
		Minecraft mc = Minecraft.getMinecraft();
		
		this.cachePages = new ArrayList();
		ArrayList<Pair<Integer, Pair<BookPage, BookPage_ListItem>>> currentPage = new ArrayList();
		int currentY = this.listStartPos.second();
		for (int i = 0; i < this.contents.size(); i++) {
			Pair<BookPage, BookPage_ListItem> pair = this.contents.get(i);
			BookPage_ListItem item = pair.second();
			
			int iconHeight = 0;
			if (item.icon != null) {
				iconHeight = item.icon.height;
			}
			
			int height = mc.fontRenderer.FONT_HEIGHT;
			if (height < iconHeight) {
				height = iconHeight;
			};
			
			currentY += height;
			if (currentY > this.listEndPos.second()) {
				// Next Page
				this.cachePages.add(currentPage);
				currentPage = new ArrayList();
				currentY = this.listStartPos.second();
			}
			
			currentPage.add(new Pair(i, pair));
		};
		this.cachePages.add(currentPage);
	}
	
	protected void calculateCacheIfNotCalculated() {
		if (this.cacheItemSeperation == null) 	calculateCacheItemSeperation();
		if (this.cachePages == null) 			calculateCachePages();
	}

	@Override
	public boolean canGoForward() {
		calculateCacheIfNotCalculated();
		return this.pageIndex < this.cachePages.size()-1;
	}

	@Override
	public boolean canGoBack() {
		return this.pageIndex > 0;
	}

	@Override
	public BookPage nextPage() {
		// Sanity check
		if (this.pageIndex < this.cachePages.size()-1) {
			this.pageIndex += 1;
		} else {
			System.out.println("BookPage_List WARNING: we got asked to get next page but we don't have one.");
		}
		return null;
	}

	@Override
	public BookPage prevPage() {
		// Sanity check
		if (this.pageIndex > 0) {
			this.pageIndex -= 1;
		} else {
			System.out.println("BookPage_List WARNING: we got asked to get a previous page but we dont have one.");
		}
		return null;
	}

	@Override
	public void drawPage(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {
		super.drawPage(partialTicks, mouseX, mouseY, gui);
		
		calculateCacheIfNotCalculated();
		
		// NOTE: when drawing first item in the page always ignores cacheSeperation
		ArrayList<Pair<Integer, Pair<BookPage, BookPage_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<BookPage, BookPage_ListItem>> pair = currentPageCache.get(i);
			Integer contentIndex = pair.first();
			BookPage_ListItem item = pair.second().second();
			
			int iconWidth = 0;
			if (item.icon != null) {
				iconWidth = item.icon.width;
			}
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0) {
				y += this.cacheItemSeperation.get(i);
			}
			
			if (item.icon != null) {
				gui.mc.getTextureManager().bindTexture(item.icon.texture);
				gui.drawTexturedModalRect(x, y, item.icon.x, item.icon.y, item.icon.width, item.icon.height);
			}
			gui.mc.fontRenderer.drawString(item.name,
					x + iconWidth,
					y,
					item.nameColor);
			
			lastY = y;
		}
	}
	
	@Override
	public void drawPageTop(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {
		calculateCacheIfNotCalculated();
		
		// NOTE: when drawing first item in the page always ignores cacheSeperation
		// NOTE: this is the same as normally drawing the content name's and icon's but drawing different stuff depended on mouse position
		ArrayList<Pair<Integer, Pair<BookPage, BookPage_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<BookPage, BookPage_ListItem>> pair = currentPageCache.get(i);
			Integer contentIndex = pair.first();
			BookPage_ListItem item = pair.second().second();
			
			int iconWidth = 0;
			int iconHeight = 0;
			if (item.icon != null) {
				iconWidth = item.icon.width;
				iconHeight = item.icon.height;
			}
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0) {
				y += this.cacheItemSeperation.get(i);
			}
			
			int addY = gui.mc.fontRenderer.FONT_HEIGHT;
			if (addY < iconHeight) addY = iconHeight;
			if (mouseX > x && mouseX < x+gui.mc.fontRenderer.getStringWidth(item.name)+iconWidth &&
				mouseY > y && mouseY < y+gui.mc.fontRenderer.FONT_HEIGHT) {
				gui.drawHoveringText(Arrays.asList(item.description.split("\n")), mouseX, mouseY);
				break;
			}
			
			lastY = y;
		}
	}
	
	@Override
	public boolean pageClicked(int mouseX, int mouseY, int mouseButton, GUI_Book gui) {
		super.pageClicked(mouseX, mouseY, mouseButton, gui);
		
		calculateCacheIfNotCalculated();
		
		// NOTE: this is the same as normally drawing the content name's and icon's but doing actions if desired in contents depended on mouse click
		ArrayList<Pair<Integer, Pair<BookPage, BookPage_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<BookPage, BookPage_ListItem>> pair = currentPageCache.get(i);
			Integer contentIndex = pair.first();
			BookPage_ListItem item = pair.second().second();
			
			int iconWidth = 0;
			int iconHeight = 0;
			if (item.icon != null) {
				iconWidth = item.icon.width;
				iconHeight = item.icon.height;
			}
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0) {
				y += this.cacheItemSeperation.get(i);
			}
			
			int addY = gui.mc.fontRenderer.FONT_HEIGHT;
			if (addY < iconHeight) addY = iconHeight;
			if (mouseX > x && mouseX < x+gui.mc.fontRenderer.getStringWidth(item.name)+iconWidth &&
				mouseY > y && mouseY < y+gui.mc.fontRenderer.FONT_HEIGHT) {
				// TODO: call a callback or some thing for if a list item was clicked
				break;
			}
			
			lastY = y;
		}
		return false;
	}
	
	protected void addNewListItem(BookPage page, String name, String description, BookTextureData icon) {
		// Helper/Convenience method
		BookPage_ListItem item = new BookPage_ListItem(name, description, icon);
		this.contents.add(new Pair(page, item));
	}
}

// Used by BookPage_List class nothing else
class BookPage_ListItem {
	public String name;
	public String description;
	public BookTextureData icon;
	public int nameColor = 4210752;

	public BookPage_ListItem(String name, String description, BookTextureData icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
}
