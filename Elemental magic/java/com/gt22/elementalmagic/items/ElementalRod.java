package com.gt22.elementalmagic.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.gt22core.baseclasses.item.ItemBase;

public class ElementalRod extends ItemBase
{
	public int capacity = 75;

	public ElementalRod()
	{
		super("ElementalRod", Core.instance);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player)
	{
		return stack;
	}
}
