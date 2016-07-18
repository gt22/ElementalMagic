package com.gt22.elementalmagic.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.wands.IWandable;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.gt22core.baseclasses.block.BlockBase;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;
import com.gt22.gt22core.utils.ToolClass;

public class ElementalizerBase extends BlockBase implements IWandable
{
	public ElementalizerBase()
	{
		super(Material.iron, 10F, 10F, "ElementalizerBase", Core.instance, ToolClass.pickaxe, 2);
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
	{
		if (Elementalizer.getHolders(world, x, y, z) != null && ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, AdvThaumApi.getElementas(50), false, false))
		{
			ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, AdvThaumApi.getElementas(5000), true, false);
			world.setBlock(x, y, z, BlockRegistry.elementalizer);
			return 0;
		}
		return -1;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
	{
		return wandstack;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count)
	{
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count)
	{

	}
}
