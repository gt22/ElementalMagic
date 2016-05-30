package com.gt22.elementalmagic.items;

import thaumcraft.api.research.ResearchCategories;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.enums.MatrixType;
import com.gt22.elementalmagic.registry.ItemRegistry;

public class ElementalRod extends Item {
	public int capacity = 75;
	public ElementalRod(String unlocName) {
		super();
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}
	
}
