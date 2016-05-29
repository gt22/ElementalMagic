package com.gt22.elementalmagic.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ResearchRegistry {
	public static final String tabname = "ELEMENTALMAGIC";
	public static void init()
	{
		ResearchCategories.registerCategory(tabname, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		registerResearches();
	}
	public static AspectList elems = new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10);
	private static void registerResearches()
	{
		String researchprefix = "em.research.";
		ResearchItem elementalmagic = new ResearchItem("elemmagic", tabname, new AspectList(), 0, 0, 1, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"));
		ResearchPage elemmagicpage = new ResearchPage(researchprefix + "elemmagic.text");
		elementalmagic.setRound().setPages(elemmagicpage).setAutoUnlock().registerResearchItem();
		ResearchItem elemfocies = new ResearchItem("elemfocies", tabname, new AspectList().add(elems).add(Aspect.CRYSTAL, 5), 0, -2, 1, new ItemStack(ItemRegistry.craftItem, 1, 0));
		ResearchPage elemfociespage =  new ResearchPage(researchprefix + "elemfocies.text"), elemfociespage2 = new ResearchPage(ArcaneRecipeRegistry.inertFoci);
		elemfocies.setParents("elemmagic", "FOCUSFIRE").setConcealed().setPages(elemfociespage, elemfociespage2).registerResearchItem();
		ResearchItem windfocus = new ResearchItem("windfocus", tabname, new AspectList().add(Aspect.AIR, 10).add(Aspect.MOTION, 10), -1, -3, 1, new ItemStack(ItemRegistry.windFocus));
		ResearchPage windfocuspage = new ResearchPage(researchprefix + "windfocus.text"), windfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.windFoci), windfocusenlarge = new ResearchPage(researchprefix + "windfocusenlarge.text"), windfocuspotency = new ResearchPage(researchprefix + "windfocuspotency.text"), windfocusreverse = new ResearchPage(researchprefix + "windfocusreverse.text");
		windfocus.setPages(windfocuspage, windfocuspage2, windfocusenlarge, windfocuspotency, windfocusreverse).setParents("elemfocies").setConcealed().registerResearchItem();
		ResearchItem emberfocus = new ResearchItem("emberfocus", tabname, new AspectList().add(Aspect.FIRE, 10).add(Aspect.WEAPON, 10), -1, -1, 1, new ItemStack(ItemRegistry.emberFocus));
		ResearchPage emberfocuspage = new ResearchPage(researchprefix + "emberfocus.text"), emberfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.emberFoci), emberfocusenlarge = new ResearchPage(researchprefix + "emberfocusenlarge.text"), emberfocuspotency = new ResearchPage(researchprefix + "emberfocuspotency.text");
		emberfocus.setParents("elemfocies").setPages(emberfocuspage, emberfocuspage2, emberfocusenlarge, emberfocuspotency).setConcealed().registerResearchItem();
		ResearchItem currentfocus = new ResearchItem("currentfocus", tabname, new AspectList().add(Aspect.WATER, 10).add(Aspect.GREED, 10), 1, -3, 1, new ItemStack(ItemRegistry.currentFocus));
		ResearchPage currentfocuspage = new ResearchPage(researchprefix + "currentfocus.text"), currentfocuspage2 = new ResearchPage(ArcaneRecipeRegistry.currentFoci), currentfocusenlarge = new ResearchPage(researchprefix + "currentfocusenlarge.text"), currentfocuslava = new ResearchPage(researchprefix + "currentfocuslava.text");
		currentfocus.setParents("elemfocies").setPages(currentfocuspage, currentfocuspage2, currentfocusenlarge, currentfocuslava).setConcealed().registerResearchItem();
		ResearchItem naturefocus = new ResearchItem("naturefocus", tabname, new AspectList().add(Aspect.EARTH, 10).add(Aspect.PLANT, 10), 1, -1, 1, new ItemStack(ItemRegistry.natureFocus));
		ResearchPage naturefocuspage = new ResearchPage(researchprefix + "naturefocus.text"), naturefocuspage2 = new ResearchPage(ArcaneRecipeRegistry.natureFoci), naturefocusenlarge = new ResearchPage(researchprefix + "naturefocusenlarge.text"), naturefocusseeds = new ResearchPage(researchprefix + "naturefocusseeds.text"), naturefocusgrass = new ResearchPage(researchprefix + "naturefocusgrass.text"), naturefocuspodzol = new ResearchPage(researchprefix + "naturefocuspodzol.text"), naturefocusmushroom = new ResearchPage(researchprefix + "naturefocusmushroom.text");
		naturefocus.setParents("elemfocies").setPages(naturefocuspage, naturefocuspage2, naturefocusenlarge, naturefocusseeds, naturefocusgrass, naturefocuspodzol, naturefocusmushroom).setConcealed().registerResearchItem();
		ResearchItem elemrod = new ResearchItem("ROD_ELEM", tabname, new AspectList().add(elems).add(Aspect.MAGIC, 5), -1, 2, 1, new ItemStack(ItemRegistry.elemRod));
		ResearchPage elemrodpage = new ResearchPage(researchprefix + "elemroc.text"), elemrodpage2 = new ResearchPage(InfusionRecipeRegistry.elementalwand);
		elemrod.setParents("elemmagic", "ROD_greatwood").setPages(elemrodpage, elemrodpage2).setConcealed().registerResearchItem();
		ResearchItem elemcap = new ResearchItem("CAP_ELEMCAP", tabname, new AspectList().add(elems).add(Aspect.MAGIC, 5), 1, 2, 1, new ItemStack(ItemRegistry.elemCap));
		ResearchPage elemcappage = new ResearchPage(researchprefix + "elemcap.text"), elemcappage2 = new ResearchPage(ArcaneRecipeRegistry.elemCap);
		elemcap.setParents("elemmagic", "CAP_gold").setPages(elemcappage, elemcappage2).setConcealed().registerResearchItem();
	}
}
