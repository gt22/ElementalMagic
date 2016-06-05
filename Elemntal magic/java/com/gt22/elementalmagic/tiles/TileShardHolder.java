package com.gt22.elementalmagic.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;

import com.gt22.elementalmagic.registry.ItemRegistry;



public class TileShardHolder extends TileEntity implements IInventory {
	
	public TileShardHolder() {
		inventory = new ItemStack[getSizeInventory()];
	}
	
	public int drawVis(Aspect aspect, int amount)
	{
		return VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, aspect, amount);
	}
	
	public ItemStack[] inventory;
	
	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
	    if (this.getStackInSlot(index) != null) {
	        ItemStack itemstack;

	        if (this.getStackInSlot(index).stackSize <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, null);
	            this.markDirty();
	            return itemstack;
	        } else {
	            itemstack = this.getStackInSlot(index).splitStack(count);

	            if (this.getStackInSlot(index).stackSize <= 0) {
	                this.setInventorySlotContents(index, null);
	            } else {
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }

	            this.markDirty();
	            return itemstack;
	        }
	    } else {
	        return null;
	    }
	}
	public int resettime = 20;
	
	 @Override
	 public Packet getDescriptionPacket()
	 {
		 NBTTagCompound syncData = new NBTTagCompound();
		 this.writeToNBT(syncData);
	     return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	 }
	

	@Override
	 public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	 {
	     readFromNBT(pkt.func_148857_g());
	 }
	
	 

	@Override
	public void updateEntity() {
		
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return;

	    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        stack.stackSize = this.getInventoryStackLimit();
	        
	    if (stack != null && stack.stackSize == 0)
	        stack = null;

	    this.inventory[index] = stack;
	    this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "ShardHolder";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord, yCoord, zCoord) <= 64;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(stack.getItem() == ItemApi.getItem("itemShard", 0).getItem() && stack.getItemDamage() < 4)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);
	    

	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

}
