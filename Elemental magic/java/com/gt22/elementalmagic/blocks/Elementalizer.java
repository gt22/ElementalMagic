package com.gt22.elementalmagic.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.gui.GuiHandler;
import com.gt22.elementalmagic.tiles.TileElementalizer;
import com.gt22.elementalmagic.tiles.TileShardHolder;
import com.gt22.gt22core.baseclasses.block.BlockWithTile;
import com.gt22.gt22core.utils.ToolClass;

public class Elementalizer extends BlockWithTile
{
	
	public Elementalizer(String unlocName) {
		super(Material.iron, 7F, 7F, unlocName, Core.instance, ToolClass.pickaxe, 2, TileElementalizer.class);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileElementalizer();
	}
	
	public static TileShardHolder[] getHolders(World world, int x, int y, int z)
	{
		TileShardHolder[] holders = new TileShardHolder[4];
		if(world.getTileEntity(x - 1, y, z) instanceof TileShardHolder)
			holders[0] = (TileShardHolder) world.getTileEntity(x - 1, y, z);
		if(world.getTileEntity(x + 1, y, z) instanceof TileShardHolder)	
			holders[1] = (TileShardHolder) world.getTileEntity(x + 1, y, z);
		if(world.getTileEntity(x, y, z - 1) instanceof TileShardHolder)	
			holders[2] = (TileShardHolder) world.getTileEntity(x, y, z - 1);
		if(world.getTileEntity(x, y, z + 1) instanceof TileShardHolder)
			holders[3] = (TileShardHolder) world.getTileEntity(x, y, z + 1);
		for(int i  = 0; i < holders.length; i++)
		{
			if(holders == null || !(holders[i] instanceof TileShardHolder))
			{
				return null;
			}
		}
		return (TileShardHolder[]) holders;
	}
	
	@Override
	public void onBlockHarvested(World worldObj, int xCoord,
			int yCoord, int zCoord, int meta,
			EntityPlayer player)
	{
		TileElementalizer te = (TileElementalizer) worldObj.getTileEntity(xCoord, yCoord, zCoord);
		for(int i = 0; i < te.getSizeInventory(); i++)
		{
			if(te.getStackInSlot(i) !=  null)
			{
				ItemStack stack = te.getStackInSlot(i);
				EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord, stack);
				worldObj.spawnEntityInWorld(item);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer p,
			int meta, float hitx, float hity,
			float hitz) {
		p.openGui(Core.instance, GuiHandler.ElementalizerID, world, x, y, z);
		return true;
	}

}
