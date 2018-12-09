package com.imt.infinityscience.items.itemsubclasses;

import com.imt.infinityscience.InfinityScience;
import com.imt.infinityscience.items.InfinityItems;
import com.imt.infinityscience.proxy.ClientProxy;
import com.imt.infinityscience.util.interfaces.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(InfinityScience.infinitytab);
		
		InfinityItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		ClientProxy.registerModel();
	}
	
}
