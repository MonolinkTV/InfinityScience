package com.imt.infinityscience;

import com.imt.infinityscience.gen.oreGen;
import com.imt.infinityscience.proxy.CommonProxy;
import com.imt.infinityscience.recipes.InfinityRecipes;
import com.imt.infinityscience.util.compat.OreDictionaryCompat;
import com.imt.infinityscience.util.handlers.GuiHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Global.MODID, name = Global.NAME, version = Global.VERSION)
public class InfinityScience
{

	public static final CreativeTabs infinitytab = new InfinityTab("infinitytab");

	@Instance
	public static InfinityScience instance;

	@SidedProxy(clientSide = Global.CLIENT_PROXY_CLASS, serverSide = Global.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new oreGen(), 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(InfinityScience.instance, new GuiHandler());
		InfinityRecipes.init();
		System.out.println("INFINITY AND BEYOND!");
		OreDictionaryCompat.registerOres();
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{

	}
}
