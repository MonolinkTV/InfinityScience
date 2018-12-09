package com.imt.infinityscience;

import com.imt.infinityscience.items.InfinityItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class InfinityTab extends CreativeTabs
{

	public InfinityTab(String label)
	{
		super("infinitytab");
		this.setBackgroundImageName("infinitycreativetab.png");
	}
	

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(InfinityItems.INFINITY_GUIDEBOOK);
	}
	
}
