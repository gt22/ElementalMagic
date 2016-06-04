package com.gt22.elementalmagic.enums;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;

import com.gt22.elementalmagic.registry.ItemRegistry;

public enum EnumElementalizerInputs {
	THAUMIUM(ItemApi.getItem("itemResource", 2), new ItemStack(ItemRegistry.craftItem, 1, 1), new int[] {10000, 10000, 10000, 10000}),
	AIRSHARD(ItemApi.getItem("itemShard", 0), new ItemStack(ItemRegistry.craftItem, 1, 2), new int[] {0, 3000, 3000, 3000}),
	FIRESHARD(ItemApi.getItem("itemShard", 1), new ItemStack(ItemRegistry.craftItem, 1, 2), new int[] {3000, 3000, 0, 3000}),
	WATERSHARD(ItemApi.getItem("itemShard", 2), new ItemStack(ItemRegistry.craftItem, 1, 2), new int[] {3000, 0, 3000, 3000}),
	EARTHSHARD(ItemApi.getItem("itemShard", 3), new ItemStack(ItemRegistry.craftItem, 1, 2), new int[] {3000, 3000, 3000, 0});
	public final ItemStack input;
	public final ItemStack output;
	public final int[] cost;
	/**
	 * 
	 * @param input itemstack
	 * @param output itemstack
	 * @param cost vis cost int type AIR, WATER, FIRE, EARTH
	 */
	private EnumElementalizerInputs(ItemStack input, ItemStack output, int[] cost) {
		this.input = input;
		this.output = output;
		this.cost = cost;
	}
	
	public static EnumElementalizerInputs getByInputStack(ItemStack stack)
	{
		EnumElementalizerInputs[] values = EnumElementalizerInputs.values();
		for(int i = 0; i < values.length; i++)
		{
			if(values[i].input.getItem().equals(stack.getItem()) && values[i].input.getItemDamage() == stack.getItemDamage())
			{
				return values[i];
			}
		}
		return null;
	}
}
