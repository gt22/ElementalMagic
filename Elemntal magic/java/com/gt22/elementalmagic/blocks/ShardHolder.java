package com.gt22.elementalmagic.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.gui.GuiHandler;
import com.gt22.elementalmagic.tiles.TileShardHolder;

public class ShardHolder extends BlockContainer {

	public ShardHolder(String unlocName) {
		super(Material.rock);
		setBlockName(unlocName);
		setCreativeTab(Core.tab);
		setBlockTextureName(Core.modid + ":" + unlocName);
		setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 1F, 0.75F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileShardHolder();
	}

	
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean onBlockActivated(World worldObj, int x,
			int y, int z, EntityPlayer player,
			int p_149727_6_, float p_149727_7_, float p_149727_8_,
			float p_149727_9_) {
		player.openGui(Core.instance, GuiHandler.HolderID, worldObj, x, y, z);
		return true;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_,
			int p_149747_3_, int p_149747_4_, int p_149747_5_) {
		return false;
	}
	
	
}
