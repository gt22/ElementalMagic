package com.gt22.elementalmagic.blocks;

import com.gt22.elementalmagic.tiles.TileSuperNode;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SuperNode extends BlockBase implements ITileEntityProvider
{
	public SuperNode()
	{
		super("customNode", Material.cactus);
		isBlockContainer = true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileSuperNode();
	}
}
