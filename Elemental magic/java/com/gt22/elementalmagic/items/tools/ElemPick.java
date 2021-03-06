package com.gt22.elementalmagic.items.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.registry.Materials;

public class ElemPick extends ItemPickaxe {

	public ElemPick(String unlocName) {
		super(Materials.toolMatElem);
		setUnlocalizedName(unlocName);
		setTextureName(Core.modid + ":" + unlocName);
		setCreativeTab(Core.tab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldObj, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(3, 200, 1));
		stack.damageItem(15, player);
		return stack;
	}

}
