package com.imt.infinityscience.book;

import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class BookPage {
	// Used by other BookPage's if they want to use next/previous arrows (enableXXXArrow)
	protected BookTextureData leftArrow = new BookTextureData(null, 111, 0, 17, 9);
	protected BookTextureData rightArrow = new BookTextureData(null, 129, 0, 17, 9);
	protected Pair<Integer, Integer> leftArrowGuiPos = new Pair(5, 150-leftArrow.height);
	protected Pair<Integer, Integer> rightArrowGuiPos = new Pair(104-rightArrow.width, 150-rightArrow.height);
	
	protected boolean enableNextArrow = false;
	protected boolean enablePrevArrow = false;
	
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
	public boolean pageClicked(int mouseX, int mouseY, int mouseButton, GUI_Book gui) {
		int guiMouseX = mouseX - gui.getGuiLeft();
		int guiMouseY = mouseY - gui.getGuiTop();
		if (enablePrevArrow && canGoBack() &&
			guiMouseX > leftArrowGuiPos.first() && guiMouseX < leftArrowGuiPos.first() + leftArrow.width &&
			guiMouseY > leftArrowGuiPos.second() && guiMouseY < leftArrowGuiPos.second() + leftArrow.height) {
			BookPage newPage = prevPage();
			if (newPage != null) {
				gui.setCurrentPage(newPage);
			}
			gui.mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("ui.button.click")), 0.25f, 1f);
			return true;
		} else if (enableNextArrow && canGoForward() &&
				   guiMouseX > rightArrowGuiPos.first() && guiMouseX < rightArrowGuiPos.first() + rightArrow.width &&
				   guiMouseY > rightArrowGuiPos.second() && guiMouseY < rightArrowGuiPos.second() + rightArrow.height) {
			BookPage newPage = nextPage();
			if (newPage != null) {
				gui.setCurrentPage(newPage);
			}
			gui.mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("ui.button.click")), 0.25f, 1f);
			return true;
		}
		return false;
	}
	
	// Called same as drawGuiContainerBackgroundLayer()
	public void drawPage(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {
		// NOTE: may want to draw different arrow when disabled, so just add else with the magic
		if (enablePrevArrow) {
			if (canGoBack())
				gui.drawTexturedModalRect(gui.getGuiLeft() + leftArrowGuiPos.first(), gui.getGuiTop() + leftArrowGuiPos.second(), leftArrow.x, leftArrow.y, leftArrow.width, leftArrow.height);
		}
		if (enableNextArrow) {
			if (canGoForward())
				gui.drawTexturedModalRect(gui.getGuiLeft() + rightArrowGuiPos.first(), gui.getGuiTop() + rightArrowGuiPos.second(), rightArrow.x, rightArrow.y, rightArrow.width, rightArrow.height);
		}
		
		GlStateManager.color(1.0f, 1.0f, 1.0f);
	}
	
	// Called same as drawScreen()
	public void drawPageTop(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {}
}
