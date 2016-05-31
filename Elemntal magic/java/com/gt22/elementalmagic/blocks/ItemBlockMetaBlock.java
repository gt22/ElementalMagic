package com.gt22.elementalmagic.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMetaBlock extends ItemBlockWithMetadata {
	public ItemBlockMetaBlock(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return this.getUnlocalizedName() + "-" + item.getItemDamage();
	}

}
