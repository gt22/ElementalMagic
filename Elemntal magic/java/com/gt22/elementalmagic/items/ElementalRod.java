package com.gt22.elementalmagic.items;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.wands.ElementalRodOnUpd;

public class ElementalRod extends Item {
	public int capacity = 75;
	public ElementalRod(String unlocName) {
		super();
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world,
			Entity e, int p_77663_4_, boolean p_77663_5_) {
		if(!e.worldObj.isRemote && e.ticksExisted % 200 == 0)
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
