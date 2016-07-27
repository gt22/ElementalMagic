package com.gt22.elementalmagic.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;

import com.gt22.gt22core.baseclasses.tileEntity.TileWithInventory;



public class TileShardHolder extends TileWithInventory {
	
	public TileShardHolder() {
		super(1);
	}
	
	public int drawVis(Aspect aspect, int amount)
	{
		return VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, aspect, amount);
	}
	
	
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
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(stack.getItem() == ItemApi.getItem("itemShard", 0).getItem() && stack.getItemDamage() < 4)
		{
			return true;
		}
		return false;
	}

}
