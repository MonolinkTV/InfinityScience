package com.imt.infinityscience.util.handlers;

import com.imt.infinityscience.items.InfinityItems;
import com.imt.infinityscience.util.interfaces.IHasModel;

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
	public static void onModelegister(ModelRegistryEvent event)
	{
		for (Item item : InfinityItems.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel) item).registerModels();
			}

		}
	}
}
