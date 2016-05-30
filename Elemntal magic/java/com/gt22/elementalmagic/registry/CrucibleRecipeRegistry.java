package com.gt22.elementalmagic.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeRegistry {

	public static CrucibleRecipe elemIngot;
	public static CrucibleRecipe elemStick;
	public static CrucibleRecipe[] elemItems;
	public static void init()
	{
		elemIngot = ThaumcraftApi.addCrucibleRecipe("elemingot", new ItemStack(ItemRegistry.craftItem, 1 ,1), ItemApi.getItem("itemResource", 2), new AspectList().add(ResearchRegistry.elems));
		elemStick = ThaumcraftApi.addCrucibleRecipe("elemingot", new ItemStack(ItemRegistry.craftItem, 1 ,2), new ItemStack(Items.stick), new AspectList().add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.EARTH, 2).add(Aspect.FIRE, 2));
		elemItems = new CrucibleRecipe[2];
		elemItems[0] = elemIngot;
		elemItems[1] = elemStick;
	}
}
