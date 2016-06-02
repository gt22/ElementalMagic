package com.gt22.elementalmagic.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.registry.ItemRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileAutoDecompTable extends TileEntity implements IInventory {
	
	public int breaktime;
	public ItemStack[] inventory;
	@Override
	public int getSizeInventory() {
		return 2;
	}
	@SideOnly(Side.CLIENT)
	public int getBreakTimeScaled(int par1)
	{
		return this.breaktime * par1 / 1000;
	}
	
	private boolean canBreak()
	  {
	    if (this.inventory[0] == null || this.inventory[1] == null || !this.inventory[1].hasTagCompound()|| !this.inventory[1].stackTagCompound.hasKey("playerName") || worldObj.getPlayerEntityByName(this.inventory[1].stackTagCompound.getString("playerName")) == null)
	    {
	      return false;
	    }
	    AspectList al = new AspectList(inventory[0]);
	    if ((al == null) || (al.size() == 0))
	    {
	      return false;
	    }
	    return true;
	  }
	
	@Override
	public void updateEntity() {
		boolean flag1 = false;
	      if ((this.breaktime == 0) && (canBreak()))
	      {
	    	  this.breaktime = 1000;
	    	  flag1 = true;
	      }
	      if ((this.breaktime > 0) && (canBreak()))
	      {
	        this.breaktime--;
	        if (this.breaktime == 0)
	        {
	        	
	        	this.breaktime = 0;
	        	if(!worldObj.isRemote)
	        	{
	        		breakItem();
	        	}
	        	flag1 = true;
	        }
	      }
	      else
	      {  
	        this.breaktime = 0;
	      }
	    if (flag1)
	    {
	      this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	      markDirty();
	    }
	}
	
	public void breakItem()
	  {
	    if (canBreak())
	    {
	      AspectList al = new AspectList(inventory[0]);
	      AspectList primals = AdvThaumApi.reduceToPrimals(al, false);
	      
	      if (this.worldObj.rand.nextInt(80) < primals.visSize()) {
	          Aspect primal = primals.getAspects()[this.worldObj.rand.nextInt(primals.getAspects().length)];
		      EntityPlayer player = worldObj.getPlayerEntityByName(inventory[1].stackTagCompound.getString("playerName"));
		      AdvThaumApi api = new AdvThaumApi();
		      if(primal != null && primal != Aspect.ORDER && primal != Aspect.ENTROPY)
		      {
		    	  	if(player.getDistanceSq(xCoord, yCoord, zCoord) > 100)
		    	  	{
		    	  		if(worldObj.rand.nextInt(500) > player.getDistance(xCoord, yCoord, zCoord))
		    	  		{
		    	  			api.giveAspect(player, primal, (short)1);
		    	  		}
		    	  		else
		    	  		{
		    	  			api.playWarpEffects(player);
		    	  			ThaumcraftApiHelper.addWarpToPlayer(player, 1, true);
		    	  			player.addChatComponentMessage(new ChatComponentTranslation(EnumChatFormatting.DARK_PURPLE+"Your mind was corrupdet by damaged aspect", new Object[0]));
		    	  		}
		    	  	}
		    	  	else
		    	  	{
		    	  		api.giveAspect(player, primal, (short)1);
		    	  	}  
		      }
		  }
	      	this.inventory[0].stackSize -= 1;
	      	if (this.inventory[0].stackSize <= 0) {
	      		this.inventory[0] = null;
	      }
	    }
	  }
	
	public TileAutoDecompTable() {
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

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
	    nbt.setInteger("breaktime", breaktime);
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
	    breaktime = nbt.getInteger("breaktime");
	}

}
