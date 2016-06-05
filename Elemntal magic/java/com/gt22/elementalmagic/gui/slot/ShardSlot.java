package com.gt22.elementalmagic.gui.slot;

import thaumcraft.api.ItemApi;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ShardSlot extends Slot
{

	public ShardSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if(stack.getItem() == ItemApi.getItem("itemShard", 0).getItem() && stack.getItemDamage() < 4)
		{
			return true;
		}
		return false;
	}

}
