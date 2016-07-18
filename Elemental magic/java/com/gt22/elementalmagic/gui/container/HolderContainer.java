package com.gt22.elementalmagic.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gt22.elementalmagic.gui.slot.ShardSlot;
import com.gt22.elementalmagic.tiles.TileShardHolder;
import com.gt22.gt22core.baseclasses.container.ContainerWithPlayerInv;

public class HolderContainer extends ContainerWithPlayerInv {
	
	 private TileShardHolder te;
	 
	    public HolderContainer(IInventory playerInv, TileShardHolder te) {
	        super(playerInv, new Slot[] {new ShardSlot(te, 0, 80, 24)});
	    	this.te = te;
	    }
	    
	    
	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	        ItemStack previous = null;
	        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	        
	        if (slot != null && slot.getHasStack()) {
	            ItemStack current = slot.getStack();
	            previous = current.copy();
	            
	            
		            if (fromSlot < 1) {	      
		                if (!this.mergeItemStack(current, 1, 37, true))
		                    return null;
		            } 
		            else {	     
		            	if(ShardSlot.isElementalShard(current))
		            	{
		            		if (!this.mergeItemStack(current, 0, 1, false))
		            		{
		            				return null;
		            		}
		            	}
		            	else
		            	{
		            		System.out.println("Invalid item " + current.getItem() + ":" + current.getItemDamage());
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
