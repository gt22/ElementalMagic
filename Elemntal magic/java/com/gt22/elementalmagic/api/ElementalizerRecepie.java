package com.gt22.elementalmagic.api;

import net.minecraft.item.ItemStack;

public class ElementalizerRecepie
{
	public ItemStack input, output;
	public int[] cost;
	/**
	 * WARNING recipies ignore stack NBT
	 * @param input
	 * @param output
	 * @param aer
	 * @param ignis
	 * @param aqua
	 * @param terra
	 */
	public ElementalizerRecepie(ItemStack input, ItemStack output, int aer, int ignis, int aqua, int terra)
	{
		this.input = input;
		this.output = output;
		cost = new int[] {aer, ignis, aqua, terra};
	}
}
