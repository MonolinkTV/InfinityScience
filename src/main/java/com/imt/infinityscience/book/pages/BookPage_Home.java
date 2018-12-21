package com.imt.infinityscience.book.pages;

import java.util.ArrayList;

import com.imt.infinityscience.Global;
import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.book.pagePresets.BookPage_Preset_Content;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class BookPage_Home extends BookPage_Preset_Content {
	ResourceLocation iconTextures = new ResourceLocation(Global.MODID, "textures/gui/book_assets/test_icon.png");
	int textHight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
	BookTextureData icon_sixStar = new BookTextureData(iconTextures, 0, 0, 32, 32, textHight, textHight);
	BookTextureData icon_rArrow = new BookTextureData(iconTextures, 32, 0, 32, 32, textHight, textHight);
	BookTextureData icon_gear = new BookTextureData(iconTextures, 64, 0, 32, 32, textHight, textHight);
	BookTextureData icon_threeStar = new BookTextureData(iconTextures, 96, 0, 32, 32, textHight, textHight);
	
	public BookPage_Home() {
		createContent();
	}
	
	protected void createContent() {
		this.content = new ArrayList();
		this.pageIndex = 0;
		
		this.addContent("Adding string content");
	}
}
