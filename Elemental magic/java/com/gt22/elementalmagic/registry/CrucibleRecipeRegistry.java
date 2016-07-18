package com.gt22.elementalmagic.registry;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.CrucibleRecipe;

import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class CrucibleRecipeRegistry {

	public static CrucibleRecipe elemIngot;
	public static CrucibleRecipe[] elemShard = new CrucibleRecipe[4];
	public static Aspect[] elementals = new Aspect[4];
	public static CrucibleRecipe[] elemComponents= new CrucibleRecipe[5];
	public static ArrayList<ItemStack> shards = new ArrayList<ItemStack>();
	public static void init()
	{
		elemIngot = ThaumcraftApi.addCrucibleRecipe("elemingot", new ItemStack(ItemRegistry.craftItem, 1, 1), ItemApi.getItem("itemResource", 2), AdvThaumApi.getElementas(15));
		shards.add(ItemApi.getItem("itemShard", 0));
		shards.add(ItemApi.getItem("itemShard", 1));
		shards.add(ItemApi.getItem("itemShard", 2));
		shards.add(ItemApi.getItem("itemShard", 3));
		elementals[0] = Aspect.AIR;
		elementals[1] = Aspect.FIRE;
		elementals[2] = Aspect.WATER;
		elementals[3] = Aspect.EARTH;
		elemComponents[0] = elemIngot;
		for(int i = 0; i < 4; i++)
		{
			elemShard[i] = ThaumcraftApi.addCrucibleRecipe("elemingot", new ItemStack(ItemRegistry.craftItem, 1, 2), shards.get(i), AdvThaumApi.getElementas(3).remove(elementals[i]));
			elemComponents[i + 1] = elemShard[i];
		}
		
	}
}
