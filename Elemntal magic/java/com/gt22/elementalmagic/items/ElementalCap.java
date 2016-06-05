package com.gt22.elementalmagic.items;

import net.minecraft.item.Item;

import com.gt22.elementalmagic.core.ElementalMagic;

public class ElementalCap extends Item {
	public ElementalCap(String unlocaName) {
		super();
		setUnlocalizedName(unlocaName);
		setTextureName(ElementalMagic.modid + ":" + unlocaName);
		setCreativeTab(ElementalMagic.tab);
	}

}
