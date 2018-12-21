package com.imt.infinityscience.book.pagePresets;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.item.Item;

// NOTE: All text related stuff will need to support new line chars '\n' as it is not supported in minecraft very well
/* Features:
 * TODO: Image (any scale like in BookPage_List's icons)
 * TODO: CraftingTable recipe (maybe get the ingredients automatically if possible)
 * TODO: Item (item in a container like thing so you can use JEI shortcuts or get block name/tool-tip ECT and so you can see a visual of it)
 * Text (With Features):
 *     TODO: Clicked on callback or alike (default callback going to a new BookPage)
 *     Text color
 *     TODO Maybe: Text bold/italic/underline
 */

public class BookPage_Preset_Content extends BookPage {
	protected ArrayList<BookPage_Element_ContentItem> content;
	//                  x,       y,        Wrapped Object
	ArrayList<Pair<Pair<Integer, Integer>, BookPage_Element_ContentItem>> cacheContent;

	protected String title = null; // TODO
	protected boolean firsPageTitleOnly = false; // TODO
	protected int pageIndex = 0;
	
	public BookPage_Preset_Content() {
		this.enableNextArrow = true;
		this.enablePrevArrow = true;
	}
	
	protected void calculateCacheContent() {
		this.cacheContent = new ArrayList();
		for (int i = 0;  i < content.size(); i++) {
			BookPage_Element_ContentItem item = content.get(i);
			
		}
	}
	
	@Override
	public void drawPage(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {
		super.drawPage(partialTicks, mouseX, mouseY, gui);
		//
	}
	
	protected void addContent(String text) {
		addContent(text, 4210752);
	}
	protected void addContent(String text, int color) {
		for (String str : text.split(" ")) {
			Class<? extends BookPage_Element_ContentItem> itemClass = BookPage_Element_ContentItem.getContentItem(str, color);
			try {
				BookPage_Element_ContentItem item = itemClass.getConstructor(String.class, Integer.class).newInstance(str, color);
				content.add(item);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Error with newInstance of '" + itemClass.getName() + "' Skipping. (may cause missing content)");
				e.printStackTrace();
			}
		};
	}
	
	protected void addContent(BookTextureData texture) {
		Class<? extends BookPage_Element_ContentItem> itemClass = BookPage_Element_ContentItem.getContentItem(texture);
		try {
			BookPage_Element_ContentItem item = itemClass.getConstructor(BookTextureData.class).newInstance(texture);
			content.add(item);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Error with creating new instance of '" + itemClass.getName() + "' Skipping. (may cause missing content)");
			e.printStackTrace();
		}
	}
	
	protected void addContent(Item itemContent) {
		Class<? extends BookPage_Element_ContentItem> itemClass = BookPage_Element_ContentItem.getContentItem(itemContent);
		try {
			BookPage_Element_ContentItem item = itemClass.getConstructor(Item.class).newInstance(itemContent);
			content.add(item);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Error with creating new instance of '" + itemClass.getName() + "' Skipping. (may cause missing content)");
			e.printStackTrace();
		}
	}
}
