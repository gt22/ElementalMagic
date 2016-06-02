package com.gt22.elementalmagic.gui.slot;

import com.gt22.elementalmagic.registry.ItemRegistry;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBoundMatrix extends Slot
{

	public SlotBoundMatrix(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	public static boolean isMatrix(ItemStack stack)
	{
		return stack.getItem() == ItemRegistry.boundMatrix;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return isMatrix(stack);
	}
}
