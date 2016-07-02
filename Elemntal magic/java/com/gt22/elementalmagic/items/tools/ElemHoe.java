package com.gt22.elementalmagic.items.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.registry.Materials;

public class ElemHoe extends ItemHoe {

	public ElemHoe(String unlocName) {
		super(Materials.toolMatElem);
		setUnlocalizedName(unlocName);
		setTextureName(Core.modid + ":" + unlocName);
		setCreativeTab(Core.tab);
	}
	
	private int side;
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	  {
	    if (player.isSneaking()) {
	      return super.onItemUse(stack, player, world, x, y, z, par7, par8, par9, par10);
	    }
	    boolean ret = false;
	    for (int xx = -1; xx <= 1; xx++) {
	      for (int zz = -1; zz <= 1; zz++) {
	        if (super.onItemUse(stack, player, world, x + xx, y, z + zz, par7, par8, par9, par10))
	        {
	          if (!ret) {
	            ret = true;
	          }
	        }
	      }
	    }
	    return ret;
	  }
}
