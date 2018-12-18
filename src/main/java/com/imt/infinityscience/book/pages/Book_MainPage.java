package com.imt.infinityscience.book.pages;

import java.util.ArrayList;

import com.imt.infinityscience.book.BookPage_List;

public class Book_MainPage extends BookPage_List {
	public Book_MainPage() {
		// NOTE: i may want some page's to be static-like (ones witch its contents ever change's)
		//       Not assigned on object creation but assigned above (don't know the name)
		this.contents = new ArrayList();
		this.pageIndex = 0;
		
		this.addNewListItem(null, "Test Item A", "Test Desc\nA", null);
		this.addNewListItem(null, "Test Item B", "Test Desc\nB", null);
		this.addNewListItem(null, "Test Item C", "Test Desc\nC", null);
		this.addNewListItem(null, "Test Item D", "Test Desc\nD", null);
		this.addNewListItem(null, "Test Item E", "Test Desc\nE", null);
		this.addNewListItem(null, "Test Item F", "Test Desc\nF", null);
		this.addNewListItem(null, "Test Item G", "Test Desc\nG", null);
		this.addNewListItem(null, "Test Item H", "Test Desc\nH", null);
		this.addNewListItem(null, "Test Item I", "Test Desc\nI", null);
		this.addNewListItem(null, "Test Item J", "Test Desc\nJ", null);
		this.addNewListItem(null, "Test Item K", "Test Desc\nk", null);
		this.addNewListItem(null, "Test Item l", "Test Desc\nL", null);
		this.addNewListItem(null, "Test Item M", "Test Desc\nM", null);
		this.addNewListItem(null, "Test Item N", "Test Desc\nN", null);
		this.addNewListItem(null, "Test Item O", "Test Desc\nO", null);
		this.addNewListItem(null, "Test Item P", "Test Desc\nP", null);
		this.addNewListItem(null, "Test Item Q", "Test Desc\nQ", null);
		this.addNewListItem(null, "Test Item R", "Test Desc\nR", null);
	}
}
