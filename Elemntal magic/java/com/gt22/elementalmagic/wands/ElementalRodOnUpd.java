package com.gt22.elementalmagic.wands;

import com.gt22.elementalmagic.api.AdvThaumApi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class ElementalRodOnUpd implements IWandRodOnUpdate {

	@Override
	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(!player.worldObj.isRemote && player.ticksExisted % 100 == 0)
		{
			
			if(AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100 < 74)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.FIRE, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100 < 75)
			{
				AdvThaumApi.insertVis(itemstack, Aspect.FIRE, 75 - AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100 < 74)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.EARTH, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100 < 75)
			{
				AdvThaumApi.insertVis(itemstack, Aspect.EARTH, 75 - AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100 < 74)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.AIR, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100 < 75)
			{
				AdvThaumApi.insertVis(itemstack, Aspect.AIR, 75 - AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100 < 74)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.WATER, 1, true);
			}
			else if (AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100 < 75)
			{
				AdvThaumApi.insertVis(itemstack, Aspect.WATER, 75 - AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100, true);
			}
			AdvThaumApi.setVis(itemstack, new AspectList().add(Aspect.ORDER, 0).add(Aspect.ENTROPY, 0));
		}
		
	}

}
