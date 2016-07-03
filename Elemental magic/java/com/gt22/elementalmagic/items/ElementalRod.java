package com.gt22.elementalmagic.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.registry.WandAndCapsRegistry;

public class ElementalRod extends ItemBase {
	public int capacity = 75;
	public ElementalRod() {
		super("ElementalRod");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_,
			EntityPlayer player)
	{
		System.out.println(ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), WandAndCapsRegistry.elementalRod.getResearch()));
		return stack;
	}
}
