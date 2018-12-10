package com.imt.infinityscience.util.compat;

import com.imt.infinityscience.blocks.InfinityBlocks;
import com.imt.infinityscience.items.InfinityItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {

	public static void registerOres()
	{
		//Ores
		OreDictionary.registerOre("oreCopper", InfinityBlocks.BLOCK_COPPERORE);
		OreDictionary.registerOre("oreTin", InfinityBlocks.BLOCK_TINORE);
	}
}
