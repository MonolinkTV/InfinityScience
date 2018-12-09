package com.imt.infinityscience.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public abstract class TileEntityBase extends TileEntity implements ITickable
{
	public final String name;

	public TileEntityBase(String name)
	{
		this.name = name;
	}

	public static void init()
	{

	}
}
