package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.upgrades.Upgrades;

public class CurrentFocus extends ItemFocusBasic {
	public CurrentFocus(String unlocName) {
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":"  + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return isUpgradedWith(focusstack, Upgrades.lava) ? 16762368 : 37119;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		double discount = isUpgradedWith(focusstack, FocusUpgradeType.frugal) ? getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / 10D : 0;
		if(isUpgradedWith(focusstack, Upgrades.lava))
		{
			ret.add(Aspect.FIRE, (int) Math.ceil(5000 - 5000 * discount));
		}
		else
		{
			ret.add(Aspect.WATER, (int) Math.ceil(500 - 500 * discount));
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
		if(mobj == null)
		{
			return wandstack;
		}
		int x = mobj.blockX, y = mobj.blockY, z = mobj.blockZ;
		int range = getUpgradeLevel(AdvThaumApi.getFocusStack(wandstack), FocusUpgradeType.enlarge);
		boolean drawed = false;
		for(int i = x - range; i < x + range + 1; i++)
		{
			for(int j = z - range; j < z + range + 1; j++)
			{
				if(!world.isRemote && AdvThaumApi.drawVis(wandstack, player, getVisCost(AdvThaumApi.getFocusStack(wandstack)), false))
				{
					if(place(i, y, j, world, wandstack, player) && !drawed)
					{
						AdvThaumApi.drawVis(wandstack, player, getVisCost(AdvThaumApi.getFocusStack(wandstack)), true);
						drawed = true;
					}
				}
			}
		}
		return wandstack;
	}
	
	public boolean place(int i, int y, int j, World world, ItemStack wandstack, EntityPlayer player)
	{
		if(world.getBlock(i, y, j).isReplaceable(world, i, y, j))
		{
			if(isUpgradedWith(AdvThaumApi.getFocusStack(wandstack), Upgrades.lava))
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
		else if(world.getBlock(i, y + 1, j).isReplaceable(world, i, y + 1, j))
		{
			if(isUpgradedWith(AdvThaumApi.getFocusStack(wandstack), Upgrades.lava))
			{
				world.setBlock(i, y + 1, j, Blocks.lava);
				return true;
			}
			else
			{
				world.setBlock(i, y + 1, j, Blocks.water);
				return true;
			}
		}
		return false;
		
	}
}
