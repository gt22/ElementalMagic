package com.gt22.elementalmagic.creativetab;

import com.gt22.elementalmagic.registry.ItemRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ElemTab extends CreativeTabs {

	public ElemTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegistry.windFocus;
	}

}
