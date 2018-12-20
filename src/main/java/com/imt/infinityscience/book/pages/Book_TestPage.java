package com.imt.infinityscience.book.pages;

import java.util.ArrayList;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookPage_List;
import com.imt.infinityscience.book.BookTextureData;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Book_TestPage extends BookPage_List {
	
	public Book_TestPage() {
		createList();
	}
	
	protected void createList() {
		this.contents = new ArrayList();
		this.pageIndex = 0;
		
		this.addNewListItem(null, "Testing", "Testing A", null);
		this.addNewListItem(null, "Chaning Page", "Testing B", null);
		this.addNewListItem(null, "In BookPage_List", "Testing C", null);
		this.addNewListItem(Book_MainPage.class, "To Book_MainPage", "Takes you to Book_MainPage", null);
	}
}