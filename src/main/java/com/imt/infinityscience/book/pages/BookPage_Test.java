package com.imt.infinityscience.book.pages;

import java.util.ArrayList;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.book.pagePresets.BookPage_Preset_List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class BookPage_Test extends BookPage_Preset_List {
	ResourceLocation iconTextures = new ResourceLocation(Global.MODID, "textures/gui/book_assets/test_icon.png");
	int textHight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
	BookTextureData icon_sixStar = new BookTextureData(iconTextures, 0, 0, 32, 32, textHight, textHight);
	BookTextureData icon_rArrow = new BookTextureData(iconTextures, 32, 0, 32, 32, textHight, textHight);
	BookTextureData icon_gear = new BookTextureData(iconTextures, 64, 0, 32, 32, textHight, textHight);
	BookTextureData icon_threeStar = new BookTextureData(iconTextures, 96, 0, 32, 32, textHight, textHight);
	
	public BookPage_Test() {
		createContent();
	}
	
	protected void createContent() {
		this.contents = new ArrayList();
		this.pageIndex = 0;
		
		this.addNewListItem(null, "Testing", "Testing A", null);
		this.addNewListItem(null, "Changing Page", "Testing B", null);
		this.addNewListItem(null, "In BookPage_List", "Testing C", null);
		this.addNewListItem(null, "Read my desc", "Richt-Click to go back", null);
		this.addNewListItem(BookPage_Home.class, "To Book_MainPage", "After clicking me you can right-click\nto go back multiple times :(\nSo avoid circular click target's", null);
		
		// From old BookPage_Home
		this.addNewListItem(null, "Long Desc Item", "Some rather long description\nfor no really good reason\nother than to test how it looks\nhope it works well.", icon_sixStar);
		this.addNewListItem(BookPage_Test.class, "Goto Book_TestPage", "Name said it all", null);
		this.addNewListItem(null, "Test Item A", "Test Desc\nA", icon_sixStar);
		this.addNewListItem(null, "Test Item B", "Test Desc\nB", icon_rArrow);
		this.addNewListItem(null, "Test Item C", "Test Desc\nC", null);
		this.addNewListItem(null, "Test Item D", "Test Desc\nD", null);
		this.addNewListItem(null, "Test Item E", "Test Desc\nE", icon_gear);
		this.addNewListItem(null, "Test Item F", "Test Desc\nF", icon_threeStar);
		this.addNewListItem(null, "Test Item G", "Test Desc\nG", null);
		this.addNewListItem(null, "Test Item H", "Test Desc\nH", null);
		this.addNewListItem(null, "Test Item I", "Test Desc\nI", icon_rArrow);
		this.addNewListItem(null, "Test Item J", "Test Desc\nJ", icon_gear);
		this.addNewListItem(null, "Test Item K", "Test Desc\nk", null);
		this.addNewListItem(null, "Test Item l", "Test Desc\nL", null);
		this.addNewListItem(null, "Test Item M", "Test Desc\nM", icon_sixStar);
		this.addNewListItem(null, "Test Item N", "Test Desc\nN", icon_threeStar);
		this.addNewListItem(null, "Test Item O", "Test Desc\nO", null);
		this.addNewListItem(null, "Test Item P", "Test Desc\nP", null);
		this.addNewListItem(null, "Test Item Q", "Test Desc\nQ", icon_gear);
		this.addNewListItem(null, "Test Item R", "Test Desc\nR", icon_sixStar);
		this.addNewListItem(null, "Test Item S", "Test Desc\nS", null);
		this.addNewListItem(null, "Test Item 1", "Test Desc\n1", null);
		this.addNewListItem(null, "Test Item 2", "Test Desc\n2", null);
		this.addNewListItem(null, "Test Item 3", "Test Desc\n3", null);
		this.addNewListItem(null, "Test Item 4", "Test Desc\n4", null);
		this.addNewListItem(null, "Test Item 5", "Test Desc\n5", null);
		this.addNewListItem(null, "Test Item 6", "Test Desc\n6", null);
		this.addNewListItem(null, "Test Item 7", "Test Desc\n7", null);
		this.addNewListItem(null, "Test Item 8", "Test Desc\n8", null);
		this.addNewListItem(null, "Test Item 9", "Test Desc\n9", null);
		this.addNewListItem(null, "Test Item 10", "Test Desc\n10", null);
		this.addNewListItem(null, "Test Item 11", "Test Desc\n11", null);
		this.addNewListItem(null, "Test Item 12", "Test Desc\n12", null);
	}
}