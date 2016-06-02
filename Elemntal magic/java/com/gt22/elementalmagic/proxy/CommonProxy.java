package com.gt22.elementalmagic.proxy;

import com.gt22.elementalmagic.blocks.MetalBlock;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.handler.GuiHandler;
import com.gt22.elementalmagic.registry.ArcaneRecipeRegistry;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.CrucibleRecipeRegistry;
import com.gt22.elementalmagic.registry.InfusionRecipeRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.elementalmagic.registry.ResearchRegistry;
import com.gt22.elementalmagic.registry.TileRegistry;
import com.gt22.elementalmagic.registry.WandAndCapsRegistry;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e)
	{
		ItemRegistry.init();
		BlockRegistry.init();
		WandAndCapsRegistry.init();
		TileRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		MetalBlock.registerMetalBlocksRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(ElementalMagic.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		ArcaneRecipeRegistry.init();
		InfusionRecipeRegistry.init();
		CrucibleRecipeRegistry.init();
		ResearchRegistry.init();
	}
	
	
	

}
