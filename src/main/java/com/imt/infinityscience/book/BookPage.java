package com.imt.infinityscience.book;

import com.imt.infinityscience.guis.GUI_Book;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/* NOTE: to refresh the default page easily while making/updating ECT use this snippet (really simple but good to note)
@Override
public void enterPage(BookPage fromPage) {
	createContent(); // Or what ever you do to create the content, donn't forget to clear previous content!
}
 */

public class BookPage {
	// Texture position and texture to bind with for left/right arrows
	protected BookTextureData leftArrow = new BookTextureData(null, 111, 0, 17, 9);
	protected BookTextureData rightArrow = new BookTextureData(null, 129, 0, 17, 9);
	// position on the GUI to draw the left/right arrows
	protected Pair<Integer, Integer> leftArrowGuiPos = new Pair(5, 152-leftArrow.height);
	protected Pair<Integer, Integer> rightArrowGuiPos = new Pair(104-rightArrow.width, 152-rightArrow.height);
	
	// Weather to use the rightArrow or not
	protected boolean enableNextArrow = false;
	// Weather to use the leftArrow or not
	protected boolean enablePrevArrow = false;
	
	// Called if rightArrow is enabled and to ask if you canGoForward for rightArrow
	public boolean canGoForward() {
		return false;
	}
	// Called if leftArrow is enabled and to ask if you canGoBack for leftArrow
	public boolean canGoBack() {
		return false;
	}
	
	// Called when rightArrow is enabled and clicked
	public BookPage nextPage() {
		return null;
	}
	// Called when leftArrow is enabled and clicked
	public BookPage prevPage() {
		return null;
	}
	
	// Left this page to go to nextPage due to left-click or alike (pageHistory)
	public void leftPage(BookPage nextPage) {
		
	}
	// Back from fromPage due to right-click or alike (pageHistory) (WARNING: Not called on the default page when first added!)
	public void enterPage(BookPage fromPage) {
		
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
				gui.setNewPage(newPage);
			}
			gui.mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("ui.button.click")), 0.25f, 1f);
			return true;
		} else if (enableNextArrow && canGoForward() &&
				   guiMouseX > rightArrowGuiPos.first() && guiMouseX < rightArrowGuiPos.first() + rightArrow.width &&
				   guiMouseY > rightArrowGuiPos.second() && guiMouseY < rightArrowGuiPos.second() + rightArrow.height) {
			BookPage newPage = nextPage();
			if (newPage != null) {
				gui.setNewPage(newPage);
			}
			gui.mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("ui.button.click")), 0.25f, 1f);
			return true;
		}
		return false;
	}
	
	// Called same as drawGuiContainerBackgroundLayer() with extra GUI argument
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
	
	// Called same as drawScreen() with extra GUI argument
	public void drawPageTop(float partialTicks, int mouseX, int mouseY, GUI_Book gui) {}
}
