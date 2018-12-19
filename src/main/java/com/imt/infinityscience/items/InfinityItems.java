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
	public static final Item INGOT_STEEL = new ItemBase("ingot_steel");
	public static final Item MATERIAL_IRIDIUM = new ItemBase("material_iridium");
	public static final Item MATERIAL_LITHIUM = new ItemBase("material_lithium");
	public static final Item MATERIAL_SODIUM = new ItemBase("material_sodium");
	public static final Item MATERIAL_POTASSIUM = new ItemBase("material_potassium");
	public static final Item MATERIAL_RUBIDIUM = new ItemBase("material_rubidium");
	public static final Item MATERIAL_CESIUM = new ItemBase("material_cesium");
	public static final Item MATERIAL_FRANCIUM = new ItemBase("material_francium");
	public static final Item MATERIAL_BERYLLIUM = new ItemBase("material_beryllium");
	public static final Item MATERIAL_CALCIUM = new ItemBase("material_calcium");
	public static final Item MATERIAL_STRONTIUM = new ItemBase("material_strontium");
	public static final Item MATERIAL_BARIUM = new ItemBase("material_barium");
	public static final Item MATERIAL_RADIUM = new ItemBase("material_radium");
	
	//Tools
}
