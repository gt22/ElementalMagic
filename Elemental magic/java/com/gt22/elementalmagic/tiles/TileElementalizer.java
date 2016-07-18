package com.gt22.elementalmagic.tiles;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;

import com.gt22.elementalmagic.api.ElementalizerApi;
import com.gt22.elementalmagic.api.ElementalizerRecepie;
import com.gt22.elementalmagic.blocks.Elementalizer;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.registry.ItemRegistry;
import com.gt22.gt22core.baseclasses.tileEntity.TileWithInventory;
import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileElementalizer extends TileWithInventory {
	public static ArrayList<ElementalizerRecepie> recepies = new ArrayList<ElementalizerRecepie>();
	private int checktime;
	boolean shards = false;
	
	public boolean isWorking()
	{
		return reqvis != null;
	}
	
	@Override
	public void updateEntity() {
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
					worldObj.setBlock(xCoord, yCoord, zCoord, BlockRegistry.elementalizerBase);
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
	
	private int[] reqvis;
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
			ElementalizerRecepie recepie = ElementalizerApi.getRecepie(input);
			if(recepie != null)
			{
				
					
				output = recepie.output;
				if(reqvis == null)
				{
					reqvis = recepie.cost;
				}
				
				TileShardHolder[] holders = Elementalizer.getHolders(worldObj, xCoord, yCoord, zCoord);
				for(int i = 0; i < reqvis.length; i++)
				{
					if(shards && holders[i] != null)
					{
						int shardmeta = holders[i].getStackInSlot(0).getItemDamage();
						if(reqvis[shardmeta] != 0)
						reqvis[shardmeta] -= holders[i].drawVis(AdvThaumApi.aspects[shardmeta], reqvis[i]);
					}
					else
					{
						reqvis = null;
						return;
					}
				}
				System.out.println("Aer: " + reqvis[0]);
				System.out.println("Ignis: " + reqvis[1]);
				System.out.println("Aqua: " + reqvis[2]);
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
		super(2);
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

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setIntArray("reqvis", reqvis);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		reqvis = nbt.getIntArray("reqvis");
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
	
	@SideOnly(Side.CLIENT)
	public int getScaledVis(int height, Aspect aspect)
	{
		if(reqvis == null || !aspect.isPrimal() || aspect == Aspect.ENTROPY || aspect == Aspect.ORDER || getStackInSlot(0) == null || ElementalizerApi.getRecepie(getStackInSlot(0)) == null)
		{
			return height;
		}
		ElementalizerRecepie r = ElementalizerApi.getRecepie(getStackInSlot(0));
		if(aspect == Aspect.AIR)
		{
			int cost = r.cost[0];
			return /*Becouse elementalizer doesn't store aspects inside*/ cost == 0 ? height : (cost - reqvis[0]) * height / cost;
		}
		if(aspect == Aspect.FIRE)
		{
			int cost = r.cost[1];
			return /*Becouse elementalizer doesn't store aspects inside*/ cost == 0 ? height : (cost - reqvis[1]) * height / cost;
		}
		if(aspect == Aspect.WATER)
		{
			int cost = r.cost[2];
			return /*Becouse elementalizer doesn't store aspects inside*/ cost == 0 ? height : (cost - reqvis[2]) * height / cost;
		}
		if(aspect == Aspect.EARTH)
		{
			int cost = r.cost[3];
			return /*Becouse elementalizer doesn't store aspects inside*/ cost == 0 ? height : (cost - reqvis[3]) * height / cost;
		}
		return height;
	}
}
