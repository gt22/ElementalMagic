package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.enums.MatrixType;

public class VoidFocus extends ItemFocusBasic {
	
	
	
	public int getStableMatrixCost(ItemStack focus)
	{
		return 0;
	}

	public int getUnstableMatrixCost(ItemStack focus)
	{
		return 0;
	}
	
	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 4597855;
	}
	
	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		return new AspectList();
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		list.add("Matrix per cast");
		if(getStableMatrixCost(stack) != 0)
		{
			list.add("Stable: " + getStableMatrixCost(stack));
		}
		if(getUnstableMatrixCost(stack) != 0)
		{
			list.add("Unstable: " + getUnstableMatrixCost(stack));
		}
	}
	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {
		VoidMatrixApi.drawMatrix(MatrixType.STABLE, getStableMatrixCost(AdvThaumApi.getFocusStack(wandstack)), player);
		VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, getUnstableMatrixCost(AdvThaumApi.getFocusStack(wandstack)), player);
		return wandstack;
	}
	
}
