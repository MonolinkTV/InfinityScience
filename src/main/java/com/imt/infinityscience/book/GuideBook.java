package com.imt.infinityscience.book;

import com.imt.infinityscience.items.itemsubclasses.ItemBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GuideBook extends ItemBase
{

	public GuideBook(String name)
	{
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		return null;
		
	}

}
