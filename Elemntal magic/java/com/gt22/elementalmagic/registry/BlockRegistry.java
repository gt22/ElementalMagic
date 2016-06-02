package com.gt22.elementalmagic.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.gt22.elementalmagic.blocks.AutoDecompTable;
import com.gt22.elementalmagic.blocks.ItemBlockMetaBlock;
import com.gt22.elementalmagic.blocks.MetalBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {

	public static MetalBlock metalBlocks;
	public static Block autoDecompTable;
	private static void register(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	private static void register(Block block, Class <? extends ItemBlock> itemBlock) {
		GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName());
	}
	
	public static void init()
	{
		metalBlocks = new MetalBlock();
		metalBlocks.addMetalBlock("ElemMetalBlock", new ItemStack(ItemRegistry.craftItem, 1, 1));
		register(metalBlocks, ItemBlockMetaBlock.class);
		register(autoDecompTable = new AutoDecompTable("AutoDecompTable"));
	}
}
