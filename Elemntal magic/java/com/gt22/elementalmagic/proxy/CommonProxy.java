package com.gt22.elementalmagic.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import com.gt22.elementalmagic.blocks.MetalBlock;
import com.gt22.elementalmagic.registry.ArcaneRecipeRegistry;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.CrucibleRecipeRegistry;
import com.gt22.elementalmagic.registry.InfusionRecipeRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.elementalmagic.registry.ResearchRegistry;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e)
	{
		ItemRegistry.init();
		BlockRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		MetalBlock.registerMetalBlocksRecipes();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		ArcaneRecipeRegistry.init();
		InfusionRecipeRegistry.init();
		CrucibleRecipeRegistry.init();
		ResearchRegistry.init();
	}
	
	
	

}
