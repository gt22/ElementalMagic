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
			
			if(AdvThaumApi.getVis(itemstack, Aspect.FIRE) / 100 < 75)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.FIRE, 1, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.EARTH) / 100 < 75)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.EARTH, 1, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.AIR) / 100 < 75)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.AIR, 1, true);
			}
			if(AdvThaumApi.getVis(itemstack, Aspect.WATER) / 100 < 75)
			{	
				AdvThaumApi.insertVis(itemstack, Aspect.WATER, 1, true);
			}
			if(!AdvThaumApi.drawVis(itemstack, player, new AspectList().add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), true))
			{
				AdvThaumApi.drawVis(itemstack, player, new AspectList().add(Aspect.ORDER, AdvThaumApi.getVis(itemstack, Aspect.ORDER)).add(Aspect.ENTROPY, AdvThaumApi.getVis(itemstack, Aspect.ENTROPY)), true);
			}
		}
		
	}

}
