package com.imt.infinityscience.items;

import java.util.ArrayList;
import java.util.List;

import com.imt.infinityscience.book.GuideBook;
import com.imt.infinityscience.items.itemsubclasses.ItemBase;

import net.minecraft.item.Item;

public class InfinityItems
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Book
	public static final Item INFINITY_GUIDEBOOK = new GuideBook("book_start");
	
	//Items
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_TIN = new ItemBase("ingot_tin");
	
	//Tools
}
