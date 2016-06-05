package com.gt22.elementalmagic.api;

import com.gt22.elementalmagic.tiles.TileElementalizer;

import net.minecraft.item.ItemStack;

public class ElementalizerApi
{
	/**
	 * Register recepie into elementalizer
	 * @param recepie
	 */
	public static void addRecepie(ElementalizerRecepie recepie)
	{
		TileElementalizer.recepies.add(recepie);
	}
	
	/**
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
}
