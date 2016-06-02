package com.gt22.elementalmagic.items;

import java.io.IOException;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gt22.elementalmagic.command.ElemComands;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.items.nbt.ItemNbt;

public class BoundMatrix extends Item
{
	public BoundMatrix(String unlocName)
	{
		setCreativeTab(ElementalMagic.tab);
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
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
