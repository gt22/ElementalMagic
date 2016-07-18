package com.gt22.elementalmagic.proxy;

import net.minecraftforge.common.config.Configuration;
import com.gt22.elementalmagic.config.CfgValues;
import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.gui.GuiHandler;
import com.gt22.elementalmagic.registry.ArcaneRecipeRegistry;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.CrucibleRecipeRegistry;
import com.gt22.elementalmagic.registry.InfusionRecipeRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.elementalmagic.registry.ResearchRegistry;
import com.gt22.elementalmagic.registry.TileRegistry;
import com.gt22.elementalmagic.registry.WandAndCapsRegistry;
import com.gt22.elementalmagic.upgrades.Upgrades;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e)
	{
		CfgValues.init(new Configuration(e.getModConfigurationDirectory()));
		Upgrades.init();
		ItemRegistry.init();
		BlockRegistry.init();
		WandAndCapsRegistry.init();
		TileRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		BlockRegistry.metalBlocks.registerMetalBlocksRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(Core.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		ArcaneRecipeRegistry.init();
		InfusionRecipeRegistry.init();
		CrucibleRecipeRegistry.init();
		ResearchRegistry.init();
	}

}
