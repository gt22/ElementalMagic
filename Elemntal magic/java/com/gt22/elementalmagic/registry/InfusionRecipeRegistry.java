package com.gt22.elementalmagic.registry;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionRecipeRegistry {
	private static ItemStack[] elemwandrecipe = {ItemApi.getItem("itemShard", 0), ItemApi.getItem("itemShard", 2), ItemApi.getItem("itemShard", 3), ItemApi.getItem("itemShard", 1)};
	private static ItemStack[] autodecomprecepie = {new ItemStack(ItemRegistry.craftItem, 1, 1), new ItemStack(ItemRegistry.craftItem, 1, 1), new ItemStack(ItemRegistry.craftItem, 1, 1), new ItemStack(ItemRegistry.craftItem, 1, 1), new ItemStack(ItemRegistry.elemPick), new ItemStack(ItemRegistry.elemAxe), ItemApi.getBlock("blockCosmeticSolid", 4)};
	public static InfusionRecipe elementalwand;
	public static InfusionRecipe autodecomp;
	public static void init()
	{
		elementalwand  = ThaumcraftApi.addInfusionCraftingRecipe("ROD_ELEM", new ItemStack(ItemRegistry.elemRod), 4, new AspectList().add(Aspect.AIR, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32) .add(Aspect.FIRE, 32).add(Aspect.MAGIC, 32), ItemApi.getItem("itemWandRod", 0), elemwandrecipe);
		autodecomp = ThaumcraftApi.addInfusionCraftingRecipe("autodecomp", new ItemStack(BlockRegistry.autoDecompTable), 5, new AspectList().add(Aspect.AIR, 64).add(Aspect.WATER, 64).add(Aspect.EARTH, 64).add(Aspect.FIRE, 64).add(Aspect.CRAFT, 64).add(Aspect.SOUL, 32), ItemApi.getBlock("blockTable", 14), autodecomprecepie);
	}
	
	
}
