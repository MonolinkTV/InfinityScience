package com.img.infinityscience;

import com.img.infinityscience.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Global.MODID, name = Global.NAME, version = Global.VERSION)
public class InfinityScience
{
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

	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{

	}
}
