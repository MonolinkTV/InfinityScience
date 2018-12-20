package com.imt.infinityscience.book;

import akka.japi.Pair;
import net.minecraft.util.ResourceLocation;

public class BookTextureData {
	public int x;
	public int y;
	public int width; // Texture SRC Width
	public int height; // Texture SRC Height
	public int textureWidth; // Desired Width
	public int textureHeight; // Desired Height
	public ResourceLocation texture; // If null use's book_base_assets
	
	public BookTextureData(ResourceLocation texture, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textureWidth = width;
		this.textureHeight = height;
		this.texture = texture;
	}
	
	public BookTextureData(ResourceLocation texture, int x, int y, int textureWidth, int textureHeight, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.texture = texture;
	}
}
