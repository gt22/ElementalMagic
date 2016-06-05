package com.gt22.elementalmagic.core;

import net.minecraftforge.common.config.Configuration;

import com.gt22.elementalmagic.command.ElemComands;
import com.gt22.elementalmagic.config.CfgValues;
import com.gt22.elementalmagic.creativetab.ElemTab;
import com.gt22.elementalmagic.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(version = ElementalMagic.version, modid = ElementalMagic.modid, name = ElementalMagic.name, dependencies = "required-after:Thaumcraft")
public class ElementalMagic {
	public static final String name = "Elemental magic";
	public static final String modid = "elemmagic";
	public static final int globalversion = 0;
	public static final int minorversion = 1;
	public static final int gameversion = 1710;
	public static final int bugfixversion = 0;
	public static final String version = globalversion + "." + minorversion + "." + gameversion + "." + bugfixversion;
	private static final String clientproxy = "com.gt22.elementalmagic.proxy.ClientProxy";
	private static final String serverproxy = "com.gt22.elementalmagic.proxy.ServerProxy";
	
	public static ElemTab tab = new ElemTab("ElementalMagic");
	public static Configuration cfg;
	@Instance(modid)
	public static ElementalMagic instance = new ElementalMagic();
	
	@SidedProxy(clientSide = clientproxy, serverSide= serverproxy)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		cfg = new Configuration(e.getSuggestedConfigurationFile());
		cfg.load();
		CfgValues.init(cfg);
		cfg.save();
		proxy.preInit(e);
	}
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init(e);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit(e);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new ElemComands());
	}
}
