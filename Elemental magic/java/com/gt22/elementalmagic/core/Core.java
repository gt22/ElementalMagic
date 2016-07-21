package com.gt22.elementalmagic.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;
import com.gt22.elementalmagic.command.ElemComands;
import com.gt22.elementalmagic.proxy.CommonProxy;
import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.gt22core.interfaces.IMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(version = Core.version, modid = Core.modid, name = Core.name, dependencies = "required-after:gt22core; required-after:Thaumcraft")
public class Core implements IMod
{
	public static final String name = "Elemental magic";
	public static final String modid = "elemmagic";
	public static final int globalversion = 0;
	public static final int minorversion = 1;
	public static final int gameversion = 1710;
	public static final int bugfixversion = 0;
	public static final String version = globalversion + "." + minorversion + "." + gameversion + "." + bugfixversion;
	private static final String proxypath = "com.gt22.elementalmagic.proxy";

	public static CreativeTabs tab = new CreativeTabs("ElementalMagic")
	{
		@Override
		public Item getTabIconItem()
		{
			return ItemRegistry.windFocus;
		}
	};

	@Override
	public CreativeTabs[] getCreativeTabs()
	{
		return new CreativeTabs[]
		{ tab };
	}

	@Override
	public String getModid()
	{
		return modid;
	}

	@Instance(modid)
	public static Core instance = new Core();

	@SidedProxy(clientSide = proxypath + ".ClientProxy", serverSide = proxypath + ".ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
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
	public void serverLoad(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new ElemComands());
	}
}
