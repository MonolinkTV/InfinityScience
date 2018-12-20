package com.imt.infinityscience.book.pagePresets;

import java.util.ArrayList;

import com.imt.infinityscience.book.BookPage;
import com.imt.infinityscience.book.BookTextureData;

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

public class BookPage_Content extends BookPage {
	// NOTE when implementing: may want an internal class to wrap all these supported types so code is cleaner? (class BookPage_ConentItem {})
	ArrayList content; // TODO: add typing for a list of optional types of items (or if i use BookPage_ConentItem just use that)
	
	public BookPage_Content() {
		
	}
	
	protected void addContent(String text) {
		this.content.add(text);
	}
	
	protected void addContent(BookTextureData texture) {
		this.content.add(texture);
	}
	
	protected void addContent(Class<? extends Item> item) {
		this.content.add(item);
	}
}
