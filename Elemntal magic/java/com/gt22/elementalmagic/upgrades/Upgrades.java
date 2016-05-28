package com.gt22.elementalmagic.upgrades;

import com.gt22.elementalmagic.core.ElementalMagic;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;

public class Upgrades {
	public static FocusUpgradeType reverse = new FocusUpgradeType(45, new ResourceLocation("thaumcraft", "textures/aspects/permutatio.png"), "em.upgrade.reverse", "em.upgrade.reverse.text", new AspectList().add(Aspect.EXCHANGE, 1).add(Aspect.AURA, 1));
	public static FocusUpgradeType seed = new FocusUpgradeType(46, new ResourceLocation("thaumcraft", "textures/aspects/herba.png"), "em.upgrade.seed", "em.upgrade.seed.text", new AspectList().add(Aspect.PLANT, 1));
	public static FocusUpgradeType grass = new FocusUpgradeType(47, new ResourceLocation("thaumcraft", "textures/aspects/messis.png"), "em.upgrade.grass", "em.upgrade.grass.text", new AspectList().add(Aspect.TAINT, 1));
	public static FocusUpgradeType podzol = new FocusUpgradeType(48, new ResourceLocation("thaumcraft", "textures/aspects/ordo.png"), "em.upgrade.podzol", "em.upgrade.podzol.text", new AspectList().add(Aspect.VOID, 1).add(Aspect.CRYSTAL, 1));
	public static FocusUpgradeType mushroom = new FocusUpgradeType(49, new ResourceLocation("thaumcraft", "textures/aspects/perditio.png"), "em.upgrade.mushroom", "em.upgrade.mushroom.text", new AspectList().add(Aspect.TAINT, 1).add(Aspect.DARKNESS, 1));
	public static FocusUpgradeType lava = new FocusUpgradeType(50, new ResourceLocation("thaumcraft", "textures/aspects/ignis.png"), "em.upgrade.lava", "em.upgrade.lava.text", new AspectList().add(Aspect.FIRE, 1).add(Aspect.WATER, 1));
	public static FocusUpgradeType drain = new FocusUpgradeType(51, new ResourceLocation("thaumcraft", "textures/aspects/vacuos.png"), "em.upgrade.drain", "em.upgrade.drain.text", new AspectList().add(Aspect.VOID, 1).add(Aspect.WATER, 1));
}
