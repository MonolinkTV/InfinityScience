package com.imt.infinityscience.book.pagePresets;

import com.imt.infinityscience.book.BookTextureData;

// Used by BookPage_List class and callback contentItemClicked (Helper/Convenience Class)
public class BookPage_Element_ListItem {
	public String name;
	public String description;
	public BookTextureData icon;
	public int nameColor = 4210752;

	public BookPage_Element_ListItem(String name, String description, BookTextureData icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
	
	public BookPage_Element_ListItem(String name, String description, BookTextureData icon, int nameColor) {
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.nameColor = nameColor;
	}
}
