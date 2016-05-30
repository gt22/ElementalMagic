package com.gt22.elementalmagic.items;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.enums.MatrixType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class VoidAspecterFoci extends VoidFocus {
	
	public VoidAspecterFoci(String unlocName) {
		super();
		setCreativeTab(ElementalMagic.tab);
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
	}
	
	@Override
	public int getStableMatrixCost(ItemStack focus) {
		return 5;
	}
	
	@Override
	public int getUnstableMatrixCost(ItemStack focus) {
		return 5;
	}
	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
		if(!world.isRemote)
		{
			if(AdvThaumApi.getVis(wandstack, Aspect.ENTROPY) / 100 < AdvThaumApi.getMaxVis(wandstack) - 50 && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, player) >= 5)
			{
				AdvThaumApi.insertVis(wandstack, Aspect.ENTROPY, 50, true);
				VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, 5, player);
				System.out.println(VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, player));
			}
			if(AdvThaumApi.getVis(wandstack, Aspect.ORDER) / 100 < AdvThaumApi.getMaxVis(wandstack) - 50 && VoidMatrixApi.getMatrix(MatrixType.STABLE, player) >= 5)
			{
				AdvThaumApi.insertVis(wandstack, Aspect.ORDER, 50, true);
				VoidMatrixApi.drawMatrix(MatrixType.STABLE, 5, player);
			}
		}
		return wandstack;
	}
}
