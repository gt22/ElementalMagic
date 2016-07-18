package com.gt22.elementalmagic.items.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.upgrades.Upgrades;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;
import com.gt22.gt22core.integration.thaumcraft.item.FocusBase;

public class CurrentFocus extends FocusBase {
	public CurrentFocus() {
		super("CurrentFocus", Core.instance);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return isUpgradedWith(focusstack, Upgrades.lava) ? 16762368 : 37119;
	}

	@Override
	public String getSortingHelper(ItemStack focusstack) {
		return "cf" + super.getSortingHelper(focusstack);
	}
	
	@Override
	public AspectList getVisCostNoFrugal(ItemStack focusstack) {
		AspectList ret = new AspectList();
		double discount = isUpgradedWith(focusstack, FocusUpgradeType.frugal) ? getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / 10D : 0;
		if(isUpgradedWith(focusstack, Upgrades.lava))
		{
			ret.add(Aspect.FIRE, 50000 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 30000);
		}
		else
		{
			ret.add(Aspect.WATER, 500 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 400);
		}
		return ret;
	}
	
	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
		FocusUpgradeType[] ret = null;
		switch(rank)
		{
		case(1):
		{
			ret = new FocusUpgradeType[2];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.frugal;
			break;
		}
		case(2):
		{
			ret = new FocusUpgradeType[2];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.frugal;
			break;
		}
		case(3):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = Upgrades.lava;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(4):
		{
			ret = new FocusUpgradeType[2];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.frugal;
			break;
		}
		case(5):
		{
			ret = new FocusUpgradeType[2];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.frugal;
		}
		}
		return ret;
	}
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition mobj) {
		ItemStack focus = AdvThaumApi.getFocusStack(wandstack);
		if(mobj == null)
		{
			return wandstack;
		}
		int x = mobj.blockX, y = mobj.blockY, z = mobj.blockZ;
		int range = getUpgradeLevel(focus, FocusUpgradeType.enlarge);
		boolean drawed = false;
		for(int i = x - range; i < x + range + 1; i++)
		{
			for(int j = z - range; j < z + range + 1; j++)
			{
				if(!world.isRemote && ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(focus), false, false))
				{
					
					if(place(i, y, j, world, wandstack, focus, player, ForgeDirection.getOrientation(mobj.sideHit)) && !drawed)
					{
						ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(focus), true, false);
						drawed = true;
					}
				}
			}
		}
		return wandstack;
	}
	
	public boolean place(int i, int y, int j, World world, ItemStack wandstack, ItemStack focus, EntityPlayer player, ForgeDirection dir)
	{
		if(world.getBlock(i, y, j).isReplaceable(world, i, y, j))
		{
			if(isUpgradedWith(focus, Upgrades.lava))
			{
				world.setBlock(i, y, j, Blocks.lava);
				return true;
			}
			else
			{
				world.setBlock(i, y, j, Blocks.water);
				return true;
			}
			
		}
		else if(world.getBlock(i + dir.offsetX, y + dir.offsetY, j + dir.offsetZ).isReplaceable(world, i + dir.offsetX, y + dir.offsetY, j + dir.offsetZ))
		{
			if(isUpgradedWith(focus, Upgrades.lava))
			{
				world.setBlock(i + dir.offsetX, y + dir.offsetY, j + dir.offsetZ, Blocks.lava);
				return true;
			}
			else
			{
				world.setBlock(i + dir.offsetX, y + dir.offsetY, j + dir.offsetZ, Blocks.water);
				return true;
			}
		}
		return false;
		
	}
}
