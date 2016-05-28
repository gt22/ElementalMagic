package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;

public class EmberFocus extends ItemFocusBasic {
	public EmberFocus(String unlocName) {
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":"  + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 16728615;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		double discount = getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / 10;
		ret.add(Aspect.FIRE, (int) Math.ceil(500 - 500 * discount));
		ret.add(Aspect.ENTROPY, (int) Math.ceil(500 - 500 * discount));
		return ret;
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack,
			int rank) {
		FocusUpgradeType[] ret = null;
		switch(rank)
		{
		case(1):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.frugal;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.enlarge;
		}
		case(2):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.frugal;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.enlarge;
		}
		case(3):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.frugal;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.enlarge;
		}
		case(4):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.frugal;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.enlarge;
		}
		case(5):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.frugal;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.enlarge;
		}
		}
		
		
		return ret;
	}
	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
		ItemStack focus = AdvThaumApi.getFocusStack(wandstack);
		if(AdvThaumApi.drawVis(wandstack, player, getVisCost(focus), false))
		{
			List <EntityLiving> list = world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge), 5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge), 5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge)));
			if(list.size() > 0)
			{
				if(!world.isRemote)
				{
					AdvThaumApi.drawVis(wandstack, player, getVisCost(focus), true);
				}
				for(int i = 0; i < list.size(); i++)
				{
					
					EntityLiving entity = list.get(i);
					if(!world.isRemote)
					{
						entity.setFire(10 + getUpgradeLevel(focus, FocusUpgradeType.potency));
					}
					else
					{
						for(int j = 0; j < 10; j++)
						{
							world.spawnParticle("flame", entity.posX + world.rand.nextDouble(), entity.posY, entity.posZ + world.rand.nextDouble(), 0, 0.2D, 0);
						}
					}
				}
			}
		}
		return wandstack;
	}
	
	@Override
	public int getActivationCooldown(ItemStack focusstack) {
		return 3000;
	}
}
