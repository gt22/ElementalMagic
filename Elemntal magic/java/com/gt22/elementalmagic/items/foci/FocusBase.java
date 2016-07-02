package com.gt22.elementalmagic.items.foci;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.upgrades.Upgrades;

public abstract class FocusBase extends ItemFocusBasic {
	
	public FocusBase(String unlocName) {
		setCreativeTab(Core.tab);
		setTextureName(Core.modid + ":" + unlocName);
		setUnlocalizedName(unlocName);
	}
	
	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList cost = getVisCostNoFrugal(focusstack);
		double discount = (double) getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / (double) 10;
		Aspect[] aspects = cost.getAspects();
		AspectList ret = new AspectList();
		for(Aspect a : aspects)
		{
			ret.add(a, (int) Math.ceil(cost.getAmount(a) - cost.getAmount(a) * discount));
		}
		return ret;
	}
	
	/**
	 * Becouse thaumcraft handle frugal inside consumeVis method, but to add tooltip to foci frugal must be used
	 * @param focus
	 * @return
	 */
	public abstract AspectList getVisCostNoFrugal(ItemStack focus);

}
