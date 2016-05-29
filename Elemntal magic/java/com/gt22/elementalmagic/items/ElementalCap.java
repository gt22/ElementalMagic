package com.gt22.elementalmagic.items;

import com.gt22.elementalmagic.core.ElementalMagic;

import net.minecraft.item.Item;

public class ElementalCap extends Item {
	public ElementalCap(String unlocaName) {
		super();
		setUnlocalizedName(unlocaName);
		setTextureName(ElementalMagic.modid + ":" + unlocaName);
		setCreativeTab(ElementalMagic.tab);
	}

}
