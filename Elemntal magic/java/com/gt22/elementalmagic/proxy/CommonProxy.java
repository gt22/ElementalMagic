package com.gt22.elementalmagic.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import com.gt22.elementalmagic.registry.ArcaneRecipeRegistry;
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
		String researchprefix = "em.research.";
		ResearchItem elementalmagic = new ResearchItem("elemmagic", tabname, new AspectList(), 0, 3, 1, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"));
		ResearchPage elemmagicpage = new ResearchPage(researchprefix + "elemmagic.text");
		elementalmagic.setRound().setPages(elemmagicpage).setAutoUnlock().registerResearchItem();
		ResearchItem elemfocies = new ResearchItem("elemfocies", tabname, new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.CRYSTAL, 5), 0, 0, 1, new ItemStack(ItemRegistry.craftItem, 1, 0));
		ResearchPage elemfociespage =  new ResearchPage(researchprefix + "elemfocies.text"), elemfociespage2 = new ResearchPage(ArcaneRecipeRegistry.inertFoci);
		elemfocies.setParents("elemmagic", "FOCUSFIRE").setConcealed().setPages(elemfociespage, elemfociespage2).registerResearchItem();
		ResearchItem windfocus = new ResearchItem("windfocus", tabname, new AspectList().add(Aspect.AIR, 10).add(Aspect.MOTION, 10), -1, -1, 1, new ItemStack(ItemRegistry.windFocus));
		ResearchPage windfocuspage = new ResearchPage(researchprefix + "windfocus.text"), windfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.windFoci);
		windfocus.setPages(windfocuspage, windfocuspage2).setParents("elemfocies").setConcealed().registerResearchItem();
		ResearchItem emberfocus = new ResearchItem("emberfocus", tabname, new AspectList().add(Aspect.FIRE, 10).add(Aspect.WEAPON, 10), -1, 1, 1, new ItemStack(ItemRegistry.emberFocus));
		ResearchPage emberfocuspage = new ResearchPage(researchprefix + "emberfocus.text"), emberfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.emberFoci);
		emberfocus.setParents("elemfocies").setPages(emberfocuspage, emberfocuspage2).setConcealed().registerResearchItem();
		ResearchItem currentfocus = new ResearchItem("currentfocus", tabname, new AspectList().add(Aspect.WATER, 10).add(Aspect.GREED, 10), 1, -1, 1, new ItemStack(ItemRegistry.currentFocus));
		ResearchPage currentfocuspage = new ResearchPage(researchprefix + "currentfocus.text"), currentfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.currentFoci);
		currentfocus.setParents("elemfocies").setPages(currentfocuspage, currentfocuspage2).setConcealed().registerResearchItem();
		ResearchItem naturefocus = new ResearchItem("naturefocus", tabname, new AspectList().add(Aspect.EARTH, 10).add(Aspect.PLANT, 10), 1, 1, 1, new ItemStack(ItemRegistry.natureFocus));
		ResearchPage naturefocuspage = new ResearchPage(researchprefix + "naturefocus.text"), naturefocuspage2 = new ResearchPage(ArcaneRecipeRegistry.natureFoci);
		naturefocus.setParents("elemfocies").setPages(naturefocuspage, naturefocuspage2).setConcealed().registerResearchItem();
	}

}
