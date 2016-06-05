package com.gt22.elementalmagic.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.gui.GuiHandler;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;

public class AutoDecompTable extends BlockContainer
{

	public AutoDecompTable(String unlocName)
	{
		super(Material.wood);
		setBlockName(unlocName);
		setBlockTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileAutoDecompTable();
	}

	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if(!player.isSneaking())
		{
			player.openGui(ElementalMagic.instance, GuiHandler.AutoDecompTableID, world, x, y, z);
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
