package com.gt22.elementalmagic.items;

import com.gt22.elementalmagic.core.Core;

import net.minecraft.item.Item;

public class ItemBase extends Item {
	
	public ItemBase(String unlocName) {
		this(unlocName, false);
	}
	
	/**
	 * @param useIcon if true texture doesn't seted, use for items with IIcon
	 */
	public ItemBase(String unlocName, boolean useIcon) {
		setCreativeTab(Core.tab);
		setUnlocalizedName(unlocName);
		if(!useIcon)
			setTextureName(Core.modid + ":" + unlocName);
	}

}
