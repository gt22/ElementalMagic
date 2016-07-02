package com.gt22.elementalmagic.gui.container;

import thaumcraft.api.ItemApi;

import com.gt22.elementalmagic.tiles.TileElementalizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ElementalizerContainer extends ContainerWithPlayerInv {

	TileElementalizer te;
	public ElementalizerContainer(IInventory playerInv, TileElementalizer te) {
		super(playerInv);
		this.te = te;
		addSlotToContainer(new Slot(te, 0, 80, 12));
		addSlotToContainer(new Slot(te, 1, 80, 56));
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            
            
	            if (fromSlot < 2) {	      
	                if (!this.mergeItemStack(current, 3, 40, true))
	                    return null;
	            } else {	            		
	            		if (!this.mergeItemStack(current, 0, 1, false))
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
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}

}
