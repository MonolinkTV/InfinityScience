package com.imt.infinityscience.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.items.IItemHandler;

public class Container_Book extends Container {
	public Container_Book() {}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}
