package com.gt22.elementalmagic.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.gt22.elementalmagic.blocks.AutoDecompTable;
import com.gt22.elementalmagic.blocks.Elementalizer;
import com.gt22.elementalmagic.blocks.ElementalizerBase;
import com.gt22.elementalmagic.blocks.ItemBlockMetaBlock;
import com.gt22.elementalmagic.blocks.ShardHolder;
import com.gt22.elementalmagic.core.Core;
import com.gt22.gt22core.baseclasses.block.MetalBlock;
import com.gt22.gt22core.utils.ToolClass;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRegistry {

	public static MetalBlock metalBlocks;
	public static Block autoDecompTable;
	public static Block shardHolder;
	public static Block elementalizerBase;
	public static Block elementalizer;
	public static Block superNode;
	private static void register(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	private static void register(Block block, Class <? extends ItemBlock> itemBlock) {
		GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName());
	}
	
	public static void init()
	{
		metalBlocks = new MetalBlock(Material.iron, 10F, 10F, "Elem", Core.instance, ToolClass.pickaxe, 2);
		metalBlocks.addMetalBlock("ElemMetalBlock", new ItemStack(ItemRegistry.craftItem, 1, 1));
		register(metalBlocks, ItemBlockMetaBlock.class);
		register(autoDecompTable = new AutoDecompTable("AutoDecompTable"));
		register(shardHolder = new ShardHolder("ShardHolder"), ItemBlock.class);
		register(elementalizer = new Elementalizer("Elementalizer"));
		register(elementalizerBase = new ElementalizerBase());
	}
}
