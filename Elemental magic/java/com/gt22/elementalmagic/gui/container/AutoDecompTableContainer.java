package com.gt22.elementalmagic.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;

public class AutoDecompTableContainer extends ContainerWithPlayerInv {
	
	 private TileAutoDecompTable te;
	 
	    public AutoDecompTableContainer(IInventory playerInv, TileAutoDecompTable te) {
	        super(playerInv);
	    	this.te = te;
	        this.addSlotToContainer(new Slot(te, 0, 63, 15));
	    }
	    

	    
	    /*Standart look:
	        @Override
			public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
    		ItemStack previous = null;
    		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

    		if (slot != null && slot.getHasStack()) {
        	ItemStack current = slot.getStack();
        	previous = current.copy();

        	// [...] Custom behaviour

        	if (current.stackSize == 0)
            	slot.putStack((ItemStack) null);
        	else
            	slot.onSlotChanged();

        	if (current.stackSize == previous.stackSize)
            	return null;
        	slot.onPickupFromSlot(playerIn, current);
    		}
    	}
    return previous;
	     */
	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	        ItemStack previous = null;
	        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	        
	        if (slot != null && slot.getHasStack()) {
	            ItemStack current = slot.getStack();
	            previous = current.copy();
	            
	            
		            if (fromSlot < 2) {	      
		                if (!this.mergeItemStack(current, 2, 38, true))
		                    return null;
		            } else {	           
		            	if (current.getItem() == ItemRegistry.boundMatrix)
		            	{
            				if(!this.mergeItemStack(current, 1, 2, false))
            				{
            					if (!this.mergeItemStack(current, 0, 1, false))
            					{
		            					return null;
            					}
            				}
		            	}
		            	else
		            	{
		            		if (!this.mergeItemStack(current, 0, 1, false))
        					{
	            					return null;
        					}
		            	}
		            }
	            if (current.stackSize == 0)
	                slot.putStack((ItemStack) null);
	            else
	                slot.onSlotChanged();

	            if (current.stackSize == previous.stackSize)
	                return null;
	            slot.onPickupFromSlot(playerIn, current);
	        }
	        return previous;
	    }

	    
	    @Override
	    public boolean canInteractWith(EntityPlayer playerIn) {
	        return this.te.isUseableByPlayer(playerIn);
	    }

}
