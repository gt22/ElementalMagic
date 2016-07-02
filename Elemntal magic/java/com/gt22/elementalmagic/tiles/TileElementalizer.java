package com.gt22.elementalmagic.tiles;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;

import com.gt22.elementalmagic.api.ElementalizerApi;
import com.gt22.elementalmagic.api.ElementalizerRecepie;
import com.gt22.elementalmagic.blocks.Elementalizer;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;

public class TileElementalizer extends TileEntity implements IInventory {
	public static ArrayList<ElementalizerRecepie> recepies = new ArrayList<ElementalizerRecepie>();
	private int checktime;
	boolean shards = false;
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			if(checktime == 0)
			{
				if(Elementalizer.getHolders(worldObj, xCoord, yCoord, zCoord) == null)
				{
					for(int i = 0; i < getSizeInventory(); i++)
					{
						if(getStackInSlot(i) !=  null)
						{
							ItemStack stack = getStackInSlot(i);
							EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord, stack);
							worldObj.spawnEntityInWorld(item);
						}
					}
					worldObj.setBlock(xCoord, yCoord, zCoord, BlockRegistry.metalBlocks);
					worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				}
				checktime = 20;
				shards = checkShards();
			}
			else
			{
				checktime--;
				if(shards)
				{
					processItem();
				}
				else
				{
					reqvis = null;
				}
			}
		}
	}
	
	private int[] reqvis = new int[4];
	private void processItem()
	{
		if(shards)
		{
			ItemStack input, output;
			input = getStackInSlot(0);
			if(input == null)
			{
				reqvis = null;
				return;
			}
			if(ElementalizerApi.getRecepie(input) != null)
			{
				ElementalizerRecepie recepie = ElementalizerApi.getRecepie(input);
					
					output = recepie.output;
				if(reqvis == null)
				{
					reqvis = recepie.cost;
				}
				Aspect[] aspects = {
					Aspect.AIR,
					Aspect.FIRE,
					Aspect.WATER,
					Aspect.EARTH
				};
				TileShardHolder[] holders = Elementalizer.getHolders(worldObj, xCoord, yCoord, zCoord);
				for(int i = 0; i < reqvis.length; i++)
				{
					if(shards && holders[i] != null)
					{
						reqvis[i] -= holders[i].drawVis(aspects[holders[i].getStackInSlot(0).getItemDamage()], reqvis[i]);
					}
					else
					{
						reqvis = null;
						return;
					}
				}
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
					setInventorySlotContents(0, null);
					setInventorySlotContents(1, output);
				}
			}
			else
			{
				reqvis = null;
			}
		}
		else
		{
			reqvis = null;
		}
	}
	
	public boolean checkShards()
	{
		TileShardHolder[] holders = Elementalizer.getHolders(worldObj, xCoord, yCoord, zCoord);
		if(holders != null)
		{
			ItemStack[] shards = new ItemStack[4];
			for(int i = 0; i < 4; i++)
			{
				shards[i] = holders[i].getStackInSlot(0);
			}
			ArrayList<ItemStack> shardlist = new ArrayList<ItemStack>();
			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < shardlist.size(); j++)
				{
					if(shards[i] == null || shards[i].getItemDamage() == shardlist.get(j).getItemDamage())
					{
						return false;
					}
				}
				shardlist.add(shards[i]);
			}
			return shardlist.size() == 4;
		}
		return false;
	}
	
	public ItemStack[] getShards()
	{
		TileShardHolder[] holders = Elementalizer.getHolders(worldObj, xCoord, yCoord, zCoord);
		if(holders != null)
		{
			ItemStack[] shards = new ItemStack[4];
			shardcheck: for(int i = 0; i < 4; i++)
			{
				
				ItemStack shard = holders[i].getStackInSlot(0);
				for(ItemStack s : shards)
				{
					if(s != null && shard != null && s.getItemDamage() == shard.getItemDamage())
					{
						continue shardcheck;
					}
				}
				shards[i] = shard;
			}
			return shards;
		}
		return null;
	}
	
	
	public TileElementalizer() {
		inventory = new ItemStack[getSizeInventory()];
		if(ElementalizerApi.getRecepie(ItemApi.getItem("itemResource", 2)) == null) //If thaumium recepie not registered we need to register standar recepies
		{
			registerStandartRecepies();
		}
	}
	
	private static void registerStandartRecepies()
	{
		ElementalizerApi.addRecepie(new ElementalizerRecepie(ItemApi.getItem("itemResource", 2), new ItemStack(ItemRegistry.craftItem, 1, 1), 10000, 10000, 10000, 10000));
		ElementalizerApi.addRecepie(new ElementalizerRecepie(ItemApi.getItem("itemShard", 0), new ItemStack(ItemRegistry.craftItem, 1, 2), 0, 10000, 10000, 10000));
		ElementalizerApi.addRecepie(new ElementalizerRecepie(ItemApi.getItem("itemShard", 1), new ItemStack(ItemRegistry.craftItem, 1, 2), 10000, 0, 10000, 10000));
		ElementalizerApi.addRecepie(new ElementalizerRecepie(ItemApi.getItem("itemShard", 2), new ItemStack(ItemRegistry.craftItem, 1, 2), 10000, 10000, 0, 10000));
		ElementalizerApi.addRecepie(new ElementalizerRecepie(ItemApi.getItem("itemShard", 3), new ItemStack(ItemRegistry.craftItem, 1, 2), 10000, 10000, 10000, 0));	
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
		return "Elementalizer";
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
		return index == 0;
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
	    reqvis = nbt.getIntArray("vis");
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

}
