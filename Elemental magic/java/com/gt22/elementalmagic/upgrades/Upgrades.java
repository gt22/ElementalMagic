package com.gt22.elementalmagic.upgrades;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;

import com.gt22.elementalmagic.config.CfgValues;

public class Upgrades {
	public static FocusUpgradeType reverse; 
	public static FocusUpgradeType seed;
	public static FocusUpgradeType grass;
	public static FocusUpgradeType podzol;
	public static FocusUpgradeType mushroom;
	public static FocusUpgradeType lava;
	
	public static void init()
	{
		reverse = new FocusUpgradeType(CfgValues.reverseID, new ResourceLocation("thaumcraft", "textures/aspects/permutatio.png"), "em.upgrade.reverse", "em.upgrade.reverse.text", new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.AURA, 1));
		seed = new FocusUpgradeType(CfgValues.seedID, new ResourceLocation("thaumcraft", "textures/aspects/herba.png"), "em.upgrade.seed", "em.upgrade.seed.text", new AspectList().add(Aspect.PLANT, 1));
		grass = new FocusUpgradeType(CfgValues.grassID, new ResourceLocation("thaumcraft", "textures/aspects/messis.png"), "em.upgrade.grass", "em.upgrade.grass.text", new AspectList().add(Aspect.TAINT, 1));
		podzol = new FocusUpgradeType(CfgValues.podzolID, new ResourceLocation("thaumcraft", "textures/aspects/ordo.png"), "em.upgrade.podzol", "em.upgrade.podzol.text", new AspectList().add(Aspect.VOID, 1).add(Aspect.CRYSTAL, 1));
		mushroom = new FocusUpgradeType(CfgValues.mushroomID, new ResourceLocation("thaumcraft", "textures/aspects/perditio.png"), "em.upgrade.mushroom", "em.upgrade.mushroom.text", new AspectList().add(Aspect.TAINT, 1).add(Aspect.DARKNESS, 1));
		lava = new FocusUpgradeType(CfgValues.lavaID, new ResourceLocation("thaumcraft", "textures/aspects/ignis.png"), "em.upgrade.lava", "em.upgrade.lava.text", new AspectList().add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
	}
}
