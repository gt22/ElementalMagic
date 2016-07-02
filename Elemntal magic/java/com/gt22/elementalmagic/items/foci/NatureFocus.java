package com.gt22.elementalmagic.items.foci;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.upgrades.Upgrades;

public class NatureFocus extends FocusBase {
	
	private enum TransformType
	{
		GRASS(new ItemStack(Blocks.grass), new Item[] {Items.carrot, Items.potato, Items.wheat_seeds, Items.pumpkin_seeds, Items.melon_seeds, Items.poisonous_potato}, new Block[] {Blocks.tallgrass}),
		PODZOL(new ItemStack(Blocks.dirt, 1, 2), new Item[] {}, new Block[] {}),
		MYCELIUM(new ItemStack(Blocks.mycelium), new Item[] {Item.getItemFromBlock(Blocks.brown_mushroom), Item.getItemFromBlock(Blocks.red_mushroom)}, new Block[] {Blocks.brown_mushroom, Blocks.red_mushroom});
		
		public Block block;
		public int meta;
		public Item[] seeds;
		public Block[] grass;
		
		private TransformType(ItemStack block, Item[] seeds, Block[] grass) {
			this.block = Block.getBlockFromItem(block.getItem());
			this.meta = block.getItemDamage();
			this.seeds = seeds;
			this.grass = grass;
		}
	}
	
	
	public NatureFocus() {
		super("NatureFocus");
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 35840;
	}
	
	@Override
	public String getSortingHelper(ItemStack focusstack) {
		return "nf" + super.getSortingHelper(focusstack);
	}
	
	@Override
	public AspectList getVisCostNoFrugal(ItemStack focusstack)
	{
		return new AspectList().add(Aspect.EARTH, 2000 + getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 200 + getUpgradeLevel(focusstack, Upgrades.seed) * 500 + getUpgradeLevel(focusstack, Upgrades.grass) * 500 + + getUpgradeLevel(focusstack, Upgrades.podzol) * 700 + getUpgradeLevel(focusstack, Upgrades.mushroom) * 5000);
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
		if(!world.isRemote && ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(AdvThaumApi.getFocusStack(wandstack)), false, false))
		{
			int range = getUpgradeLevel(AdvThaumApi.getFocusStack(wandstack), FocusUpgradeType.enlarge);
			int x = mobj.blockX, y = mobj.blockY, z = mobj.blockZ;
			ItemStack foci = AdvThaumApi.getFocusStack(wandstack);
			if(player.isSneaking())
			{
				if(transform(x, y, z, world, foci))
				{
					ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCost(AdvThaumApi.getFocusStack(wandstack)), true, false);
				}
			}
			else
			{
				boolean drawed = false;
				for(int i = x - range; i < x + range + 1; i++)
				{
					for(int j = z - range; j < z + range + 1; j++)
					{					
						if(transform(x, y, z, world, foci) && !drawed)
						{
							ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(AdvThaumApi.getFocusStack(wandstack)), true, false);
						}
					}
				}
			}
		}
		return wandstack;
	}
	
	private boolean transform(int x, int y, int z, World world, ItemStack foci)
	{
		boolean ret;
		if(isUpgradedWith(foci, Upgrades.mushroom))
		{
			ret = transform(x, y, z, world, TransformType.MYCELIUM);
		}
		else if (isUpgradedWith(foci, Upgrades.podzol))
		{
			ret = transform(x, y, z, world, TransformType.PODZOL);
		}
		else
		{
			ret = transform(x, y, z, world, TransformType.GRASS);
		}
		return ret;
	}
	
	private boolean transform(int x, int y, int z, World world, TransformType type)
	{
		if(world.getBlock(x, y, z).equals(Blocks.dirt))
		{
			
			world.setBlock(x, y, z, type.block, type.meta, 7);
			processSeeds(world, x, y, z, type);
			processGrass(world, x, y, z, type);
			return true;
		}
		return false;
	}
	
	
	
	private void processSeeds(World world, int x, int y, int z, TransformType type)
	{
		if(world.rand.nextInt(10) == 9)
		{
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(type.seeds[world.rand.nextInt(type.seeds.length)])));
		}
	}
	
	private void processGrass(World world, int x, int y, int z, TransformType type)
	{
		if(world.rand.nextInt(10) == 9)
		{
			world.setBlock(x, y, z, type.grass[world.rand.nextInt(type.grass.length)]);
		}
	}
}