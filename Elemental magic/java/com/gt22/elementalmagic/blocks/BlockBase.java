package com.gt22.elementalmagic.blocks;

import com.gt22.elementalmagic.core.Core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{

	public BlockBase(String unlocName, Material mat, float hardness, float resistance, String harvesttool, int harvestlevel)
	{
		super(mat);
		setBlockName(unlocName);
		setBlockTextureName(Core.modid + ":" + unlocName);
		setCreativeTab(Core.tab);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(harvesttool, harvestlevel);
	}
	
	public BlockBase(String unlocName, Material mat, float hardness, float resistance)
	{
		super(mat);
		setBlockName(unlocName);
		setBlockTextureName(Core.modid + ":" + unlocName);
		setCreativeTab(Core.tab);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	public BlockBase(String unlocName, Material mat)
	{
		this(unlocName, mat, 10F, 5F);
	}
	
	public BlockBase(String unlocName)
	{
		this(unlocName, Material.rock);
	}

}
