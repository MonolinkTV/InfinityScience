package com.imt.infinityscience.book.pages;

import java.util.ArrayList;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.book.pagePresets.BookPage_Preset_List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class BookPage_Test extends BookPage_Preset_List {
	
	public BookPage_Test() {
		createContent();
	}
	
	protected void createContent() {
		this.contents = new ArrayList();
		this.pageIndex = 0;
		
		this.addNewListItem(null, "Testing", "Testing A", null);
		this.addNewListItem(null, "Chaning Page", "Testing B", null);
		this.addNewListItem(null, "In BookPage_List", "Testing C", null);
		this.addNewListItem(null, "Read my desc", "Richt-Click to go back", null);
		this.addNewListItem(BookPage_Home.class, "To Book_MainPage", "After clicking me you can right-click\nto go back multiple times :(\nSo avoid circular click target's", null);
	}
}