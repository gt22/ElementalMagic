package com.gt22.elementalmagic.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import com.gt22.elementalmagic.registry.ItemRegistry;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e)
	{
		ItemRegistry.init();
	}
	
	public void init(FMLInitializationEvent e)
	{
		
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		registerTab();
		
	}
	public static final String tabname = "ELEMENTALMAGIC";
	private void registerTab()
	{
		ResearchCategories.registerCategory(tabname, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		registerResearches();
	}
	private void registerResearches()
	{
		ResearchItem elementalmagic = new ResearchItem("elemmagic", tabname, new AspectList().add(Aspect.CRAFT, 1).add(Aspect.MAGIC, 1), 0, 0, 5, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"));
		ResearchPage elemmagicpage = new ResearchPage("em.research.elemmagic.text");
		elementalmagic.setRound().setPages(elemmagicpage).setAutoUnlock().registerResearchItem();
	}
}
