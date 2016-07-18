package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.gt22core.baseclasses.item.ItemBase;
import com.gt22.gt22core.utils.ItemNbt;

public class BoundMatrix extends ItemBase
{
	public BoundMatrix()
	{
		super("BoundMatrix", Core.instance);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player,
			List list, boolean par4)
	{
		if(stack.hasTagCompound() && stack.stackTagCompound.hasKey("playerName"))
		{
			list.add("Bound");
		}
		super.addInformation(stack, player, list, par4);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player)
	{
		ItemNbt.initNBT(stack);
		if(stack.stackTagCompound.hasKey("playerName"))
		{
			if(player.isSneaking())
			{
				stack.stackTagCompound.removeTag("playerName");
			}
		}
		else
		{
			if(!player.isSneaking())
			{
				stack.stackTagCompound.setString("playerName", player.getCommandSenderName());
			}
		}
		return stack;
	}
}
