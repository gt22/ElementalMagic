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
import com.gt22.elementalmagic.config.CfgValues;
import com.gt22.elementalmagic.registry.ItemRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileAutoDecompTable extends TileWithInventory {
	
	public TileAutoDecompTable()
	{
		super(2);
	}
	
	public int breaktime;
	public ItemStack[] inventory;


	@SideOnly(Side.CLIENT)
	public int getBreakTimeScaled(int par1)
	{
		return this.breaktime * par1 / 1000;
	}
	
	private boolean canBreak()
	  {
	    if (
	    getStackInSlot(0) == null|| 
	    getStackInSlot(1) == null || 
	    !getStackInSlot(1).hasTagCompound()|| 
	    !getStackInSlot(1).stackTagCompound.hasKey("playerName") || 
	    worldObj.getPlayerEntityByName(getStackInSlot(1).stackTagCompound.getString("playerName")) == null)
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
	      AspectList al = new AspectList(getStackInSlot(0));
	      AspectList primals = AdvThaumApi.reduceToPrimals(al, false);
	      
	      if (this.worldObj.rand.nextInt(80) < primals.visSize()) {
	          Aspect primal = primals.getAspects()[this.worldObj.rand.nextInt(primals.getAspects().length)];
		      EntityPlayer player = worldObj.getPlayerEntityByName(getStackInSlot(1).stackTagCompound.getString("playerName"));
		      AdvThaumApi api = new AdvThaumApi();
		      if(primal != null && primal != Aspect.ORDER && primal != Aspect.ENTROPY)
		      {
		    	  	if(player.getDistanceSq(xCoord, yCoord, zCoord) > 100)
		    	  	{
		    	  		if(worldObj.rand.nextInt(CfgValues.autoDecompRange) > player.getDistance(xCoord, yCoord, zCoord))
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
	      getStackInSlot(0).stackSize -= 1;
	      	if (getStackInSlot(0).stackSize <= 0) {
	      		setInventorySlotContents(0, null);
	      }
	    }
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
	    nbt.setInteger("breaktime", breaktime);

	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);
	    breaktime = nbt.getInteger("breaktime");
	}

}
