package com.gt22.elementalmagic.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.gt22.elementalmagic.registry.ItemRegistry;

public class ElemTab extends CreativeTabs {

	public ElemTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegistry.windFocus;
	}

}
