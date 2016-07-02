package com.gt22.elementalmagic.items.foci;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
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

public class EmberFocus extends FocusBase {
	public EmberFocus() {
		super("EmberFocus");
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 16728615;
	}

	@Override
	public String getSortingHelper(ItemStack focusstack) {
		return "ef" + super.getSortingHelper(focusstack);
	}
	
	@Override
	public AspectList getVisCostNoFrugal(ItemStack focusstack) {
		AspectList ret = new AspectList();
		ret.add(Aspect.FIRE, 500 + getUpgradeLevel(focusstack, FocusUpgradeType.potency) * 1000 + getUpgradeLevel(focusstack, FocusUpgradeType.potency) * 500);
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
		if(ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(focus), false, false))
		{
			List <EntityLiving> list = world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge), 5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge), 5D + getUpgradeLevel(focus, FocusUpgradeType.enlarge)));
			if(list.size() > 0)
			{
				if(!world.isRemote)
				{
					ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, getVisCostNoFrugal(focus), true, false);
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
