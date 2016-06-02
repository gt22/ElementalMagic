package com.gt22.elementalmagic.registry;

import com.gt22.elementalmagic.api.AdvThaumApi;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeRegistry {

	public static CrucibleRecipe elemIngot;
	public static void init()
	{
		elemIngot = ThaumcraftApi.addCrucibleRecipe("elemingot", new ItemStack(ItemRegistry.craftItem, 1, 1), ItemApi.getItem("itemResource", 2), AdvThaumApi.getElementas(15));
	}
}
