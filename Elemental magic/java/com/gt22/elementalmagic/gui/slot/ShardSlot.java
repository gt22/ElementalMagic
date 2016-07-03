package com.gt22.elementalmagic.gui.slot;

import thaumcraft.api.ItemApi;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ShardSlot extends Slot
{

	public ShardSlot(IInventory inventory, int idx, int x, int y)
	{
		super(inventory, idx, x, y);
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	public static boolean isElementalShard(ItemStack stack)
	{
		if(stack.getItem() == ItemApi.getItem("itemShard", 0).getItem() && stack.getItemDamage() < 4)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return isElementalShard(stack);
	}

}
