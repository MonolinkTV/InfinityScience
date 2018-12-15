package com.imt.infinityscience.util.handlers;

import com.imt.infinityscience.containers.Container_Book;
import com.imt.infinityscience.guis.GUI_Book;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	public static int INFINITY_SCIENCE_BOOK = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == INFINITY_SCIENCE_BOOK) {
			return new Container_Book();
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == INFINITY_SCIENCE_BOOK) {
			return new GUI_Book();
		}
		return null;
	}
}
