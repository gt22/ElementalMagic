package com.gt22.elementalmagic.items;

import thaumcraft.api.research.ResearchCategories;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.registry.ItemRegistry;

public class ElementalRod extends Item {
	public int capacity = 75;
	public ElementalRod(String unlocName) {
		super();
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_,
			EntityPlayer p_77659_3_) {
		ItemRegistry.elementalRod.setTag("blaze");
		System.out.println(ItemRegistry.elementalRod.getTag());
		return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
	}
	
}
