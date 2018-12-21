package com.imt.infinityscience.book.pagePresets;

import java.util.ArrayList;

import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.item.Item;

// NOTE: All text related stuff will need to support new line chars '\n' as it is not supported in minecraft very well
/* Features:
 * TODO: Simple Text
 * TODO: Image (any scale like in BookPage_List's icons)
 * TODO: CraftingTable recipe (maybe get the ingredients automatically if possible)
 * TODO: Item (item in a container like thing so you can use JEI shortcuts or get block name/tool-tip ECT and so you can see a visual of it)
 * TODO: Special Text With Features:
 *     TODO: Clicked on callback or alike (default callback going to a new BookPage)
 *     TODO: Text color
 *     TODO Maybe: Text bold/italic/underline
 */

public class BookPage_Preset_Content extends BookPage {
	ArrayList content;
	//                  x,       y,        Wrapped Object
	ArrayList<Pair<Pair<Integer, Integer>, BookPage_Element_ContentItem>> cacheContent;
	
	public BookPage_Preset_Content() {
		
	}
	
	@Override
	public void drawPage(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {
		super.drawPage(partialTicks, mouseX, mouseY, gui);
	}
	
	protected void addContent(String text) {
		
	}
	
	protected void addContent(BookTextureData texture) {
		
	}
	
	protected void addContent(Class<? extends Item> item) {
		
	}
}
