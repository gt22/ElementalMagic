package com.gt22.elementalmagic.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.tiles.TileElementalizer;

public class Elementalizer extends BlockContainer
{
	
	public Elementalizer(String unlocName) {
		super(Material.iron);
		setBlockName(unlocName);
		setBlockTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileElementalizer();
	}

}
