package com.imt.infinityscience.book;

import com.imt.infinityscience.guis.GUI_Book;

public class BookPage {
	public boolean canGoForward() {
		return false;
	}
	public boolean canGoBack() {
		return false;
	}
	
	public BookPage nextPage() {
		return null;
	}
	public BookPage prevPage() {
		return null;
	}
	
	// Called same as mouseClicked() with a returned boolean for if it handled the click or not
	public boolean pageClicked(int mouseX, int mouseY, int mouseButton, GUI_Book gui_Book) {
		return false;
	}
	
	// Called same as drawGuiContainerBackgroundLayer()
	public void drawPage(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {}
	
	// Called same as drawScreen()
	public void drawPageTop(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {}
}
