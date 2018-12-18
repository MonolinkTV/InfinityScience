package com.imt.infinityscience.book;

import net.minecraft.util.ResourceLocation;

public class BookTextureData {
	public int x;
	public int y;
	public int width;
	public int height;
	ResourceLocation texture; // If null use's book_base_assets
	
	public BookTextureData(ResourceLocation texture, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}
}
