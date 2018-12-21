package com.imt.infinityscience.book.pagePresets;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import scala.actors.threadpool.Arrays;

// TODO: Text wrapping (not that simple D:)
// TODO: Optional Page Title (With Optional For Only First Page)

public class BookPage_Preset_List extends BookPage {
	protected Pair<Integer, Integer> listStartPos = new Pair(5, 5); // Based on GUI size
	protected Pair<Integer, Integer> listEndPos = new Pair(103, 140); // Based on GUI size
	
	protected String title = null; // TODO
	//                            Element Clicked Page, List Item
	protected ArrayList<Pair<Class<? extends BookPage>, BookPage_Element_ListItem>> contents;
	
	//        PageIndex ContentIndex, Same as Contents
	ArrayList<ArrayList<Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>>>> cachePages;
	//        Separation in pixels : get current item in this ArrayList to get pixels from previous item
	ArrayList<Integer> cacheItemSeperation;
	
	protected int pageIndex;
	
	public BookPage_Preset_List() {
		// WARNING: some reason mine craft is like NO to using my calculateCache methods in constructor (here), not a good idea anyway
		
		this.enableNextArrow = true;
		this.enablePrevArrow = true;
	}
	
	private int getIconHeight(BookTextureData icon) {
		if (icon != null) {
			return icon.height;
		}
		return 0;
	}
	
	private int getIconWidth(BookTextureData icon) {
		if (icon != null) {
			return icon.width;
		}
		return 0;
	}
	
	protected void calculateCacheItemSeperation() {
		Minecraft mc = Minecraft.getMinecraft();
		
		this.cacheItemSeperation = new ArrayList();
		for (int i = 0; i < this.contents.size(); i++) {
			// Get previous item to get current item separation from previous item (more math because we draw from topLeft)
			// BUT if there is no previous then we just use 0 (NOTE: when drawing first item in the page always ignores cacheSeperation)
			if (i == 0) {
				this.cacheItemSeperation.add(0);
				continue;
			}
			
			Pair<Class<? extends BookPage>, BookPage_Element_ListItem> pair = this.contents.get(i-1);
			BookPage_Element_ListItem item = pair.second();
			
			int iconHeight = getIconHeight(item.icon);
			int height = mc.fontRenderer.FONT_HEIGHT;
			if (height <= iconHeight)
				height = iconHeight+1;
			
			this.cacheItemSeperation.add(height);
		};
	}
	
	protected void calculateCachePages() {
		Minecraft mc = Minecraft.getMinecraft();
		
		this.cachePages = new ArrayList();
		ArrayList<Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>>> currentPage = new ArrayList();
		int currentY = this.listStartPos.second();
		for (int i = 0; i < this.contents.size(); i++) {
			Pair<Class<? extends BookPage>, BookPage_Element_ListItem> pair = this.contents.get(i);
			BookPage_Element_ListItem item = pair.second();
			
			if (currentPage.size() > 0)
				currentY += this.cacheItemSeperation.get(i);

			if (currentY+getIconHeight(item.icon) > this.listEndPos.second()) {
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
		ArrayList<Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>> pair = currentPageCache.get(i);
			Integer contentIndex = pair.first();
			BookPage_Element_ListItem item = pair.second().second();
			
			int iconWidth = getIconWidth(item.icon);
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0)
				y += this.cacheItemSeperation.get(contentIndex);
			
			if (item.icon != null) {
				gui.mc.getTextureManager().bindTexture(item.icon.texture);
				//gui.drawTexturedModalRect(
				//	x, y,
				//	item.icon.x, item.icon.y,
				//	item.icon.textureWidth, item.icon.textureHeight
				//);
				
				// Copied the tessellator, bufferbuilder section from gui.drawTexturedModalRect and changed it to work
				// as i don't know if i'm an idiot or not when it comes to 'normal' functions
				// But doing it this way has some bad image quality (pixels in wrong place or pixel holes near edge's)
				int width = item.icon.textureWidth;
		        int height = item.icon.textureHeight;
		        int textureX = item.icon.x;
		        int textureY = item.icon.y;
		        float widthM = item.icon.width;
		        float heightM = item.icon.height;
				
				float f = 0.00390625F;
		        float f1 = 0.00390625F;
		        Tessellator tessellator = Tessellator.getInstance();
		        BufferBuilder bufferbuilder = tessellator.getBuffer();
		        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		        bufferbuilder.pos((double)(x + 0), (double)(y + heightM), (double)999).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
		        bufferbuilder.pos((double)(x + widthM), (double)(y + heightM), (double)999).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
		        bufferbuilder.pos((double)(x + widthM), (double)(y + 0), (double)999).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
		        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)999).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
		        tessellator.draw();
				
				x += 1; // Spacing from icon
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
		ArrayList<Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>> pair = currentPageCache.get(i);
			Integer contentIndex = pair.first();
			BookPage_Element_ListItem item = pair.second().second();
			
			int iconWidth = getIconWidth(item.icon);
			int iconHeight = getIconHeight(item.icon);
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0) {
				y += this.cacheItemSeperation.get(contentIndex);
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
		if (super.pageClicked(mouseX, mouseY, mouseButton, gui))
			return true;
		
		calculateCacheIfNotCalculated();
		
		// NOTE: this is the same as normally drawing the content name's and icon's but doing actions if desired in contents depended on mouse click
		ArrayList<Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>>> currentPageCache = this.cachePages.get(this.pageIndex);
		int lastY = gui.getGuiTop() + this.listStartPos.second(); // Not best way but it will work just fine
		for (int i=0; i < currentPageCache.size(); i++) {
			Pair<Integer, Pair<Class<? extends BookPage>, BookPage_Element_ListItem>> pair = currentPageCache.get(i);
			Pair<Class<? extends BookPage>, BookPage_Element_ListItem> bookPair = pair.second();
			Class<? extends BookPage> desieredPage = bookPair.first();
			Integer contentIndex = pair.first();
			BookPage_Element_ListItem item = bookPair.second();
			
			int iconWidth = getIconWidth(item.icon);
			int iconHeight = getIconHeight(item.icon);
			int x = gui.getGuiLeft() + this.listStartPos.first();
			int y = lastY;
			if (i != 0) {
				y += this.cacheItemSeperation.get(contentIndex);
			}
			
			int addY = gui.mc.fontRenderer.FONT_HEIGHT;
			if (addY < iconHeight) addY = iconHeight;
			if (mouseX > x && mouseX < x+gui.mc.fontRenderer.getStringWidth(item.name)+iconWidth &&
				mouseY > y && mouseY < y+gui.mc.fontRenderer.FONT_HEIGHT) {
				BookPage newPage = contentItemClicked(item, desieredPage);
				if (newPage != null) {
					GUI_Book.setNewPage(newPage);
				}
				break;
			}
			
			lastY = y;
		}
		return false;
	}
	
	// Callback (can be override for any other use)
	protected BookPage contentItemClicked(BookPage_Element_ListItem item, Class<? extends BookPage> desieredPageClass) {
		if (desieredPageClass != null) {
			try {
				return desieredPageClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				return null;
			}
		} // else
		return null;
	}
	
	// Helper/Convenience method
	protected void addNewListItem(Class<? extends BookPage> pageClass, String name, String description, BookTextureData icon) {
		BookPage_Element_ListItem item = new BookPage_Element_ListItem(name, description, icon);
		cachePages = null; // Clear due to new item making cache out dated
		cacheItemSeperation = null; // Clear due to new item making cache out dated
		this.contents.add(new Pair(pageClass, item));
	}
}
