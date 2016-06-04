package com.gt22.elementalmagic.tiles;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;

import com.gt22.elementalmagic.blocks.MetalBlock;
import com.gt22.elementalmagic.enums.EnumElementalizerInputs;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileElementalizer extends TileEntity implements IInventory {

	
	private int checktime;
	ItemStack[] shards = null;
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			
			if(checktime == 0)
			{
				if(MetalBlock.checkHolders(worldObj, xCoord, yCoord, zCoord) == null)
				{
					worldObj.setBlock(xCoord, yCoord, zCoord, BlockRegistry.metalBlocks);
					worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				}
				checktime = 20;
				shards = getShards();
			}
			else
			{
				checktime--;
				if(shards != null)
				{
					processItem();
				}
			}
		}
	}
	private int[] reqvis = new int[4];
	private void processItem()
	{
		ItemStack input, output;
		EnumElementalizerInputs recepie;
		input = getStackInSlot(0);
		if(input != null)
		{
				recepie = EnumElementalizerInputs.getByInputStack(input);
				if(recepie == null)
				{
					System.out.println("invalid input");
					return;
				}
				output = recepie.output;
			if(reqvis == null)
			{
				reqvis = recepie.cost;
			}
			reqvis[0] -= VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.AIR, reqvis[0]);
			reqvis[1] -= VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.WATER, reqvis[1]);
			reqvis[2] -= VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.FIRE, reqvis[2]);
			reqvis[3] -= VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.EARTH, reqvis[3]);
			System.out.println("Air: " + reqvis[0]);
			System.out.println("Aqua: " + reqvis[1]);
			System.out.println("Ignis: " + reqvis[2]);
			System.out.println("Terra: " + reqvis[3]);
			boolean empty = true;
			for(int i = 0; i < 4; i++)
			{
				if(reqvis[i] != 0)
				{
					empty = false;
				}
			}
			if(empty)
			{
				reqvis = null;
				setInventorySlotContents(0, output);
			}
		}
		else
		{
			reqvis = null;
		}
	}
	
	public ItemStack[] getShards()
	{
		TileShardHolder[] holders = MetalBlock.checkHolders(worldObj, xCoord, yCoord, zCoord);
		if(holders != null)
		{
			ItemStack[] shards = new ItemStack[4];
			for(int i = 0; i < 4; i++)
			{
				shards[i] = holders[i].getStackInSlot(0);
			}
			ItemStack[] ret = new ItemStack[4];
			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					if(shards[j] == null)
					{
						return null;
					}
					if(shards[j].getItemDamage() == i)
					{
						ret[i] = shards[j];
					}
				}
			}
			for(int i = 0; i < 4; i++)
			{
				if(shards[i] == null || shards[i].getItemDamage() != i)
				{
					System.out.println("duplication: i: " + i + " meta: " + shards[i].getItemDamage());
					return null;
				}
			}
			return ret;
		}
		return null;
	}
	
	public TileElementalizer() {
		inventory = new ItemStack[getSizeInventory()];
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
		return "AutoDecompTable";
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
		if(index == 0)
		{
			AspectList al = new AspectList(stack);
			if(al != null && al.size() > 0)
			{
				return true;
			}
		}
		else if(index == 1)
		{
			return stack.getItem() == ItemRegistry.boundMatrix;
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
	    nbt.setIntArray("vis", reqvis);
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
	    nbt.getIntArray("vis");
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

}
