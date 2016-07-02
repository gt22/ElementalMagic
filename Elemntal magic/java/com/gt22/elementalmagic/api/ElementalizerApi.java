package com.gt22.elementalmagic.api;

import com.gt22.elementalmagic.tiles.TileElementalizer;

import net.minecraft.item.ItemStack;

public class ElementalizerApi
{
	
	/**
	 * WARNING recipies ignore stack NBT
	 * Return recepie with given input
	 * @param input
	 * @return
	 */
	public static ElementalizerRecepie getRecepie(ItemStack input)
	{
		for(ElementalizerRecepie r : TileElementalizer.recepies)
		{
			if(r.input.getItem() == input.getItem() && r.input.getItemDamage() == input.getItemDamage())
			{
				return r;
			}
		}
		return null;
	}
	
	/**
	 * WARNING recipies ignore stack NBT
	 * Creating elementalizer recepie and adding it
	 * @param intput
	 * @param output
	 * @param aer
	 * @param ignis
	 * @param aqua
	 * @param terra
	 * @return Created recepie
	 */
	public static ElementalizerRecepie addRecepie(ItemStack intput, ItemStack output, int aer, int ignis, int aqua, int terra)
	{
		ElementalizerRecepie recepie = new ElementalizerRecepie(intput, output, aer, ignis, aqua, terra);
		addRecepie(recepie);
		return recepie;
	}
	
	/**
	 * WARNING recipies ignore stack NBT
	 * Register recepie into elementalizer
	 * @param recepie
	 */
	public static void addRecepie(ElementalizerRecepie recepie)
	{
		TileElementalizer.recepies.add(recepie);
	}
}
