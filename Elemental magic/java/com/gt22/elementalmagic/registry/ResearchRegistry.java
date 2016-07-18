package com.gt22.elementalmagic.registry;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import com.gt22.elementalmagic.core.Core;
import com.gt22.gt22core.baseclasses.block.MetalBlock;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class ResearchRegistry {
	public static final String tabname = "ELEMENTALMAGIC";
	private static ItemStack holder = new ItemStack(BlockRegistry.shardHolder), elementalizer = new ItemStack(BlockRegistry.elementalizerBase);
	private static final List elementalizerStructure = Arrays.asList(new Object[] {AdvThaumApi.getElementas(50), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(3), Arrays.asList(new ItemStack[]{
	    null, holder, null,
	holder, elementalizer, holder,
		null, holder, null
	})});
	
	public static void init()
	{
		ResearchCategories.registerCategory(tabname, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png"), new ResourceLocation(Core.modid, "textures/gui/researchback.png"));
		registerResearches();
	}
	private static void registerResearches()
	{
		String researchprefix = "em.research.";
		ResearchItem elementalmagic = new ResearchItem("elemmagic", tabname, new AspectList(), -1, 0, 1, new ResourceLocation("elemmagic", "textures/gui/TabIcon.png")).setRound().setPages(new ResearchPage(researchprefix + "elemmagic.text")).setAutoUnlock().registerResearchItem();;
		ResearchItem elemfocies = new ResearchItem("elemfocies", tabname, AdvThaumApi.getElementas(10).add(Aspect.CRYSTAL, 5), -1, -2, 1, new ItemStack(ItemRegistry.craftItem, 1, 0)).setParents("elemmagic", "FOCUSFIRE").setConcealed().setPages(new ResearchPage(researchprefix + "elemfocies.text"), new ResearchPage(ArcaneRecipeRegistry.inertFoci)).registerResearchItem();
		ResearchItem windfocus = new ResearchItem("windfocus", tabname, new AspectList().add(Aspect.AIR, 10).add(Aspect.MOTION, 10), -2, -3, 1, new ItemStack(ItemRegistry.windFocus)).setPages(new ResearchPage(researchprefix + "windfocus.text"), new ResearchPage(ArcaneRecipeRegistry.windFoci), new ResearchPage(researchprefix + "windfocusenlarge.text"), new ResearchPage(researchprefix + "windfocuspotency.text"), new ResearchPage(researchprefix + "windfocusreverse.text")).setParents("elemfocies").setConcealed().registerResearchItem();
		ResearchItem emberfocus = new ResearchItem("emberfocus", tabname, new AspectList().add(Aspect.FIRE, 10).add(Aspect.WEAPON, 10), -2, -1, 1, new ItemStack(ItemRegistry.emberFocus)).setParents("elemfocies").setPages(new ResearchPage(researchprefix + "emberfocus.text"), new ResearchPage(ArcaneRecipeRegistry.emberFoci), new ResearchPage(researchprefix + "emberfocusenlarge.text"), new ResearchPage(researchprefix + "emberfocuspotency.text")).setConcealed().registerResearchItem();
		ResearchItem currentfocus = new ResearchItem("currentfocus", tabname, new AspectList().add(Aspect.WATER, 10).add(Aspect.GREED, 10), 0, -3, 1, new ItemStack(ItemRegistry.currentFocus)).setParents("elemfocies").setPages(new ResearchPage(researchprefix + "currentfocus.text"), new ResearchPage(ArcaneRecipeRegistry.currentFoci), new ResearchPage(researchprefix + "currentfocusenlarge.text"), new ResearchPage(researchprefix + "currentfocuslava.text")).setConcealed().registerResearchItem();
		ResearchItem naturefocus = new ResearchItem("naturefocus", tabname, new AspectList().add(Aspect.EARTH, 10).add(Aspect.PLANT, 10), 0, -1, 1, new ItemStack(ItemRegistry.natureFocus)).setParents("elemfocies").setPages(new ResearchPage(researchprefix + "naturefocus.text"), new ResearchPage(ArcaneRecipeRegistry.natureFoci), new ResearchPage(researchprefix + "naturefocusenlarge.text"), new ResearchPage(researchprefix + "naturefocusseeds.text"), new ResearchPage(researchprefix + "naturefocusgrass.text"), new ResearchPage(researchprefix + "naturefocuspodzol.text"), new ResearchPage(researchprefix + "naturefocusmushroom.text")).setConcealed().registerResearchItem();
		ResearchItem elemrod = new ResearchItem("ROD_ELEMWAND", tabname, AdvThaumApi.getElementas(15).add(Aspect.MAGIC, 10), -2, 2, 2, new ItemStack(ItemRegistry.elemRod)).setParents("elemmagic", "ROD_greatwood", "INFUSION").setPages(new ResearchPage(researchprefix + "elemrod.text"), new ResearchPage(InfusionRecipeRegistry.elementalWand)).setConcealed().registerResearchItem();
		ResearchItem elemcap = new ResearchItem("CAP_ELEMCAP", tabname, AdvThaumApi.getElementas(15).add(Aspect.MAGIC, 5).add(Aspect.METAL, 5), 0, 2, 2, new ItemStack(ItemRegistry.elemCap)).setParents("elemmagic", "CAP_gold").setPages(new ResearchPage(researchprefix + "elemcap.text"), new ResearchPage(ArcaneRecipeRegistry.elemCap)).setConcealed().registerResearchItem();
		ResearchItem elemingot = new ResearchItem("elemingot", tabname, AdvThaumApi.getElementas(20).add(Aspect.METAL, 5), -3, 0, 2, new ItemStack(ItemRegistry.craftItem, 1, 1)).setParents("elemmagic", "THAUMIUM").setPages(new ResearchPage(researchprefix + "elemingot.text"), new ResearchPage(CrucibleRecipeRegistry.elemComponents), new ResearchPage(new ShapedArcaneRecipe[]{ArcaneRecipeRegistry.elemAxe, ArcaneRecipeRegistry.elemHoe, ArcaneRecipeRegistry.elemPcik, ArcaneRecipeRegistry.elemShovel, ArcaneRecipeRegistry.elemSword}), new ResearchPage(MetalBlock.recepies[0])).setConcealed().registerResearchItem();
		ResearchItem autodecomp = new ResearchItem("autodecomp", tabname, AdvThaumApi.getElementas(10).add(Aspect.CRAFT, 20), -4, 1, 2, new ItemStack(BlockRegistry.autoDecompTable)).setParents("elemingot", "DECONSTRUCTOR", "THAUMIUM", "INFUSION").setPages(new ResearchPage(researchprefix + "autodecomp.text"), new ResearchPage(InfusionRecipeRegistry.autoDecomTable), new ResearchPage(ArcaneRecipeRegistry.boundMatrix)).setConcealed().registerResearchItem();
		ResearchItem altrshardrecepie = new ResearchItem("altrsrd", tabname, AdvThaumApi.getElementas(20).add(Aspect.CRYSTAL, 10), -4, -1, 2, new ItemStack(ItemRegistry.craftItem, 1, 2)).setConcealed().setSecondary().setParents("elemingot").setPages(new ResearchPage(researchprefix + "altrsrd.text"), new ResearchPage(new ShapedArcaneRecipe[]{ArcaneRecipeRegistry.primalCharm, ArcaneRecipeRegistry.salisMundis})).registerResearchItem();
		ResearchItem elementalizer = new ResearchItem("elementalizer", tabname, AdvThaumApi.getElementas(10).add(Aspect.CRAFT, 10).add(Aspect.GREED, 10), -6, 0, 3, new ItemStack(BlockRegistry.elementalizerBase, 1, 0)).setConcealed().setParents("elemingot").setPages(new ResearchPage(researchprefix + "elementalizer.text"), new ResearchPage(elementalizerStructure), new ResearchPage(researchprefix + "elementalizer.recepies")).setSpecial().registerResearchItem();
		ResearchItem elementalCharm = new ResearchItem("elementalCharms", tabname, AdvThaumApi.getElementas(30), -1, -4, 2, new ItemStack(ItemRegistry.elemCharms)).setConcealed().setParents("naturefocus", "windfocus", "emberfocus", "currentfocus").setPages(new ResearchPage(researchprefix + "elemcharms.text"), new ResearchPage(new ShapedArcaneRecipe[] {ArcaneRecipeRegistry.neutralCharm, ArcaneRecipeRegistry.aerCharm, ArcaneRecipeRegistry.aquaCharm, ArcaneRecipeRegistry.terraCharm, ArcaneRecipeRegistry.ignisCharm})).registerResearchItem();
	}
}
