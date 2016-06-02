package com.gt22.elementalmagic.items;

import net.minecraft.item.Item;

import com.gt22.elementalmagic.core.ElementalMagic;

public class ElementalRod extends Item {
	public int capacity = 75;
	public ElementalRod(String unlocName) {
		super();
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}
}
