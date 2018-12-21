package com.imt.infinityscience.book.pagePresets;

import com.imt.infinityscience.book.BookTextureData;
import com.imt.infinityscience.guis.GUI_Book;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BookPage_Element_ContentItem {
	public static Class<? extends BookPage_Element_ContentItem> getContentItem(String str, int color) {
		return ContentItem_String.class;
	}
	public static Class<? extends BookPage_Element_ContentItem> getContentItem(BookTextureData texture) {
		return ContentItem_BookTextureData.class;
	}
	public static Class<? extends BookPage_Element_ContentItem> getContentItem(Item itemContent) {
		return ContentItem_Item.class;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	// Same as BookPage drawPage
	public abstract void drawItem(int x, int y, GUI_Book gui);
	// Same as BookPage drawPageTop
	public void drawItemTop(int x, int y, int mouseX, int mouseY, GUI_Book gui) {}
}

class ContentItem_String extends BookPage_Element_ContentItem {
	String str;
	int color;
	FontRenderer mcFontRenderer = Minecraft.getMinecraft().fontRenderer;
	
	public ContentItem_String(String str, int color) {
		this.str = str;
		this.color = color;
	}

	@Override
	public int getWidth() {
		return mcFontRenderer.getStringWidth(str);
	}

	@Override
	public int getHeight() {
		return mcFontRenderer.FONT_HEIGHT;
	}

	@Override
	public void drawItem(int x, int y, GUI_Book gui) {
		gui.drawString(mcFontRenderer, str, x, y, color);
	}
}

class ContentItem_BookTextureData extends BookPage_Element_ContentItem {
	BookTextureData texture;
	
	public ContentItem_BookTextureData(BookTextureData texture) {
		this.texture = texture;
	}

	@Override
	public int getWidth() {
		return texture.width;
	}

	@Override
	public int getHeight() {
		return texture.height;
	}

	@Override
	public void drawItem(int x, int y, GUI_Book gui) {
		// Copied the tessellator, bufferbuilder section from gui.drawTexturedModalRect and changed it to work
		// as i don't know if i'm an idiot or not when it comes to 'normal' functions
		// But doing it this way has some bad image quality (pixels in wrong place or pixel holes near edge's)
		int width = texture.textureWidth;
        int height = texture.textureHeight;
        int textureX = texture.x;
        int textureY = texture.y;
        float widthM = texture.width;
        float heightM = texture.height;
		
		float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + heightM), (double)999).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + widthM), (double)(y + heightM), (double)999).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + widthM), (double)(y + 0), (double)999).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)999).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
	}
}

class ContentItem_Item extends BookPage_Element_ContentItem {
	Item item;
	ItemStack itemStack;
	
	public ContentItem_Item(Item item) {
		this.item = item;
		this.itemStack = new ItemStack(item, 1);
	}

	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public void drawItem(int x, int y, GUI_Book gui) {
		gui.mc.getRenderItem().renderItemIntoGUI(itemStack, x, y);
	}
	
	@Override
	public void drawItemTop(int x, int y, int mouseX, int mouseY, GUI_Book gui) {
		if (mouseX >= x && mouseX <= x+getWidth() &&
			mouseY >= y && mouseY <= y+getHeight()) {
			gui.drawHoveringText(itemStack.getTooltip(null, null), x, y);
		}
	}
}
