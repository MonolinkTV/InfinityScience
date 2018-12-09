package com.imt.infinityscience.book;

import com.imt.infinityscience.InfinityScience;
import com.imt.infinityscience.items.itemsubclasses.ItemBase;
import com.imt.infinityscience.util.handlers.GUIHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuideBook extends ItemBase
{
	
	@SideOnly(Side.CLIENT)
	public static IBookletPage forcedPage;

	public GuideBook(String name)
	{
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack item = playerIn.getHeldItem(handIn);

		playerIn.openGui(InfinityScience.instance, GUIHandler.GuiTypes.BOOK, worldIn, x, y, z);

		return new ActionResult(EnumActionResult.SUCCESS, item);
	}

}