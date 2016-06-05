package com.gt22.elementalmagic.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;

import com.gt22.elementalmagic.gui.slot.ShardSlot;
import com.gt22.elementalmagic.tiles.TileShardHolder;

public class HolderContainer extends Container {
	
	 private TileShardHolder te;
	 
	    public HolderContainer(IInventory playerInv, TileShardHolder te) {
	        this.te = te;
	        
	        this.addSlotToContainer(new ShardSlot(te, 0, 80, 24));
	        for (int y = 0; y < 3; ++y) {
	            for (int x = 0; x < 9; ++x) {
	                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	            }
	        }

	        for (int x = 0; x < 9; x++) {
	            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	        }
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
	            
	            
		            if (fromSlot < 1) {	      
		                if (!this.mergeItemStack(current, 2, 37, true))
		                    return null;
		            } else {	            		
		            		if (!this.mergeItemStack(current, 0, 1, false))
		            			if (current.getItem() == ItemApi.getItem("itemShard", 0).getItem() && current.getItemDamage() < 4)
		            				if(!this.mergeItemStack(current, 1, 2, false))
		            					return null;
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
