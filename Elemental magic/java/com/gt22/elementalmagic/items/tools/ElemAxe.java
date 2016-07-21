package com.gt22.elementalmagic.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.registry.Materials;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class ElemAxe extends ItemAxe {

	public ElemAxe(String unlocName) {
		super(Materials.toolMatElem);
		setUnlocalizedName(unlocName);
		setTextureName(Core.modid + ":" + unlocName);
		setCreativeTab(Core.tab);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World worldObj, int x, int y, int z,
			int p_77648_7_, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		if(worldObj.getBlock(x, y, z).isWood(worldObj, x, y, z))
		{
			
			Block bi = worldObj.getBlock(x, y, z);
		      if (!worldObj.isRemote)
		      {
		        AdvThaumApi.breakFurthestBlock(worldObj, x, y, z, bi, player, true, 10);
		      }
		      stack.damageItem(15, player);
		      return true;
		}
		return false;
	}
	

}
