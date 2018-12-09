package com.imt.infinityscience.util.handlers;

import java.rmi.server.ExportException;

import com.imt.infinityscience.InfinityScience;
import com.imt.infinityscience.tile.TileEntityBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import scala.xml.pull.ExceptionEvent;

public class GUIHandler implements IGuiHandler
{

	public static void init()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(InfinityScience.instance, new GUIHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityBase tile = null;
		if (GuiTypes.values()[ID].checkTileEntity)
		{
			tile = (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z));
		}
		switch (GuiTypes.values()[ID])
		{
		case BOOK:
			if (ItemBooklet.forcedPage != null)
			{
				GuiBooklet gui = BookUtils.createBookletGuiFromPage(null, ItemBooklet.forcedPage);
				ItemBooklet.forcedPage = null;
				return gui;
			} 
			else
			{
				throw new ExportException("Failed to Initialize GUI for the book!");
			}
		default:
			return null;
		}
	}

	public enum GuiTypes
	{
		BOOK(false);
		public final boolean checkTileEntity;

		GuiTypes()
		{
			this(true);
		}

		GuiTypes(boolean checkTileEntity)
		{
			this.checkTileEntity = checkTileEntity;
		}
	}
}
