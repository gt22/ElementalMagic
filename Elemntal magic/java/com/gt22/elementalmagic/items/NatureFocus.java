package com.gt22.elementalmagic.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.upgrades.Upgrades;

public class NatureFocus extends ItemFocusBasic {
	public NatureFocus(String unlocName) {
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":"  + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 35840;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		double discount = getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / 10;
		ret.add(Aspect.EARTH, (int) Math.ceil(500 - 500 * discount));
		return ret;
	}
	
	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
		FocusUpgradeType[] ret = null;
		switch(rank)
		{
		case(1):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = Upgrades.seed;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(2):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = Upgrades.grass;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(3):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = Upgrades.podzol;
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
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = Upgrades.mushroom;
			ret[2] = FocusUpgradeType.frugal;
		}
		}
		
		return ret;
	}
	
	@Override
	public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
		if(rank == 3 && type == Upgrades.podzol)
		{
			return !isUpgradedWith(focusstack, Upgrades.seed) && !isUpgradedWith(focusstack, Upgrades.grass);
		}
		if(rank == 5 && type == Upgrades.mushroom)
		{
			return !isUpgradedWith(focusstack, Upgrades.podzol);
		}
		return true;
	}
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition mobj) {
		if(!world.isRemote && ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCost(AdvThaumApi.getFocusStack(wandstack)), false, false))
		{
			int range = getUpgradeLevel(AdvThaumApi.getFocusStack(wandstack), FocusUpgradeType.enlarge);
			ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCost(AdvThaumApi.getFocusStack(wandstack)), true, false);
			int x = mobj.blockX, y = mobj.blockY, z = mobj.blockZ;
			ItemStack foci = AdvThaumApi.getFocusStack(wandstack);
			if(player.isSneaking())
			{
				transform(x, y, z, wandstack, world, foci);
			}
			else
			{
				for(int i = x - range; i < x + range + 1; i++)
				{
					for(int j = z - range; j < z + range + 1; j++)
					{					
						transform(i, y, j, wandstack, world, foci);
					}
				}
			}
		}
		return wandstack;
	}
	
	private void transform(int x, int y, int z, ItemStack wandstack, World world, ItemStack foci)
	{
		if(world.getBlock(x, y, z).equals(Blocks.dirt))
		{
			if(isUpgradedWith(foci, Upgrades.podzol))
			{
				world.setBlock(x, y, z, Blocks.dirt, 2, 7);
			}
			else if (isUpgradedWith(foci, Upgrades.mushroom))
			{
				world.setBlock(x, y, z, Blocks.mycelium);
				if(isUpgradedWith(foci, Upgrades.seed))
				{
					if(world.rand.nextInt(10) == 9 && !world.isRemote)
					{
						EntityItem ent;
						if(world.rand.nextBoolean())
						{
							ent = new EntityItem(world, x, y, z, new ItemStack(Blocks.red_mushroom));
						}
						else
						{
							ent = new EntityItem(world, x, y, z, new ItemStack(Blocks.brown_mushroom));
						}
						world.spawnEntityInWorld(ent);
					}
				}
				if(isUpgradedWith(foci, Upgrades.grass))
				{
					if(isUpgradedWith(foci, Upgrades.seed))
					{
						if(world.rand.nextInt(10) == 9)
						{
							
							if(world.rand.nextBoolean() && world.getBlock(x, y + 1, z).isAir(world, x, y, z))
							{
								world.setBlock(x, y + 1, z, Blocks.red_mushroom);
							}
							else
							{
								world.setBlock(x, y + 1, z, Blocks.brown_mushroom);
							}
						}
					}
				}
			}
			else
			{
				world.setBlock(x, y, z, Blocks.grass);
				if(isUpgradedWith(foci, Upgrades.seed))
				{
					if(world.rand.nextInt(10) == 9)
					{
						EntityItem ent;
						ItemStack item;
						switch(world.rand.nextInt(5))
						{
						case(0):
						{
							item = new ItemStack(Items.carrot);
							break;
						}
						case(1):
						{
							item = new ItemStack(Items.potato);
							break;
						}
						case(2):
						{
							item = new ItemStack(Items.pumpkin_seeds);
							break;
						}
						case(3):
						{
							item = new ItemStack(Items.melon_seeds);
							break;
						}
						case(4):
						{
							item = new ItemStack(Items.poisonous_potato);
							break;
						}
						default:
						{
							item = new ItemStack(Items.wheat_seeds);
						}
						}
						ent = new EntityItem(world, x, y, z, item);
						world.spawnEntityInWorld(ent);
					}
				}
				if(isUpgradedWith(foci, Upgrades.grass))
				{
					if(isUpgradedWith(foci, Upgrades.seed))
					{
						if(world.rand.nextInt(10) == 9)
						{
							if(world.getBlock(x, y + 1, z).isAir(world, x, y, z))
							{
								world.setBlock(x, y + 1, z, Blocks.tallgrass, 1, 7);
							}
							
						}
					}
				}
			}
		}
	}
}
