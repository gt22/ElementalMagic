package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.upgrades.Upgrades;

public class WindFocus extends ItemFocusBasic {
	public WindFocus(String unlocName) {
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":"  + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 16771399;
	}
	
	@Override
	public boolean isVisCostPerTick(ItemStack focusstack) {
	return true;
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
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(2):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(3):
		{
			ret = new FocusUpgradeType[4];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = Upgrades.reverse;
			ret[3] = FocusUpgradeType.frugal;
			break;
		}
		case(4):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.frugal;
			break;
		}
		case(5):
		{
			ret = new FocusUpgradeType[3];
			ret[0] = FocusUpgradeType.enlarge;
			ret[1] = FocusUpgradeType.potency;
			ret[2] = FocusUpgradeType.frugal;
		}
		}
		
		return ret;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		double discount = getUpgradeLevel(focusstack, FocusUpgradeType.frugal) / 10;
		ret.add(Aspect.AIR, (int) Math.ceil(20 - 20 * discount));
		return ret;
	}

	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
		player.setItemInUse(wandstack, Integer.MAX_VALUE);
		AdvThaumApi.setCooldown(player, -1);
		return wandstack;
	}
	
	@Override
	public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {
		if(!AdvThaumApi.drawVis(wandstack, player, getVisCost(new ItemStack(AdvThaumApi.getFoci(wandstack))), false))
		{
			player.stopUsingItem();
			return;
		}
		int range = 3 + getUpgradeLevel(AdvThaumApi.getFocusStack(wandstack), FocusUpgradeType.enlarge);
		List <Entity> list = player.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range));
		if(player.worldObj.isRemote && AdvThaumApi.drawVis(wandstack, player,  getVisCost(new ItemStack(AdvThaumApi.getFoci(wandstack))), false))
		{
			player.worldObj.spawnParticle("smoke", player.posX + player.worldObj.rand.nextDouble(), player.posY + player.worldObj.rand.nextDouble(), player.posZ, 0, 0.1, 0);
		}
		if(!player.worldObj.isRemote && AdvThaumApi.drawVis(wandstack, player,  getVisCost(new ItemStack(AdvThaumApi.getFoci(wandstack))), true))
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) == player)
				{
					continue;
				}
				double x, y, z;
				Entity entity = list.get(i);
				if(getUpgradeLevel(AdvThaumApi.getFocusStack(wandstack), Upgrades.reverse) > 0)
				{
					 x = entity.posX < player.posX ? 1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posX > player.posX ? -1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
					 y = entity.posY < player.posY ? 0.1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posZ > player.posY ? -0.1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
					 z = entity.posZ < player.posZ ? 1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posZ > player.posZ ? -1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
					 	
				}
				else
				{
					 x = entity.posX > player.posX ? 1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posX < player.posX ? -1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
					 y = entity.posY > player.posY ? 0.1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posZ < player.posY ? -0.1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
					 z = entity.posZ > player.posZ ? 1 + (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : entity.posZ < player.posZ ? -1 - (getUpgradeLevel(new ItemStack(AdvThaumApi.getFoci(wandstack)), FocusUpgradeType.potency) / 2) : 0;
				}
				entity.addVelocity(x, y, z);
			}
		}
	}
	
	  @Override
	public WandFocusAnimation getAnimation(ItemStack focusstack) {
		return WandFocusAnimation.CHARGE;
	}
	  
	@Override
	public int getActivationCooldown(ItemStack focusstack) {
		return 0;
	}
	
	
}
