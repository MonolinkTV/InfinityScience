package com.imt.infinityscience.util.handlers;

import com.imt.infinityscience.blocks.InfinityBlocks;
import com.imt.infinityscience.items.InfinityItems;
import com.imt.infinityscience.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(InfinityItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(InfinityBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelegister(ModelRegistryEvent event)
	{
		for (Item item : InfinityItems.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel) item).registerModels();
			}

		}
		
		for(Block block : InfinityBlocks.BLOCKS) 
		{
			if(block instanceof IHasModel) 
			{
				((IHasModel) block).registerModels();
			}
		}
	}
}
