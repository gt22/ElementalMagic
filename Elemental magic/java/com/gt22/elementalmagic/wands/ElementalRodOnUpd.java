package com.gt22.elementalmagic.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;

import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class ElementalRodOnUpd implements IWandRodOnUpdate {

	
	@Override
	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(!player.worldObj.isRemote && player.ticksExisted % 100 == 0)
		{
			if(AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100 < AdvThaumApi.getMaxVis(itemstack) - 1)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.FIRE, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100 < AdvThaumApi.getMaxVis(itemstack))
			{
				AdvThaumApi.insertVis(itemstack, Aspect.FIRE, AdvThaumApi.getMaxVis(itemstack) - AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100 < AdvThaumApi.getMaxVis(itemstack) - 1)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.EARTH, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100 < AdvThaumApi.getMaxVis(itemstack))
			{
				AdvThaumApi.insertVis(itemstack, Aspect.EARTH, AdvThaumApi.getMaxVis(itemstack) - AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100 < AdvThaumApi.getMaxVis(itemstack) - 1)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.AIR, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100 < AdvThaumApi.getMaxVis(itemstack))
			{
				AdvThaumApi.insertVis(itemstack, Aspect.AIR, AdvThaumApi.getMaxVis(itemstack) - AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100 < AdvThaumApi.getMaxVis(itemstack) - 1)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.WATER, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100 < AdvThaumApi.getMaxVis(itemstack))
			{
				AdvThaumApi.insertVis(itemstack, Aspect.WATER, AdvThaumApi.getMaxVis(itemstack) - AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100, true);
			}
			AdvThaumApi.setVis(itemstack, new AspectList().add(Aspect.ORDER, 0).add(Aspect.ENTROPY, 0));
		}
		
	}

}
