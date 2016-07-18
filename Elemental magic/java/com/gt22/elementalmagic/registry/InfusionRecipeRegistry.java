package com.gt22.elementalmagic.registry;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.InfusionRecipe;

import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class InfusionRecipeRegistry {
	public static InfusionRecipe elementalWand;
	public static InfusionRecipe autoDecomTable;
	public static void init()
	{
		elementalWand  = ThaumcraftApi.addInfusionCraftingRecipe("ROD_ELEM",
		new ItemStack(ItemRegistry.elemRod),
		4,
		AdvThaumApi.getElementas(32).add(Aspect.MAGIC, 32),
		ItemApi.getItem("itemWandRod", 0),
		new ItemStack[] 
		{ItemApi.getItem("itemShard", 0),
		ItemApi.getItem("itemShard", 2),
		ItemApi.getItem("itemShard", 3),
		ItemApi.getItem("itemShard", 1)});
		
		
		autoDecomTable = ThaumcraftApi.addInfusionCraftingRecipe("autodecomp",
		new ItemStack(BlockRegistry.autoDecompTable),
		5,
		AdvThaumApi.getElementas(64).add(Aspect.CRAFT, 64).add(Aspect.SOUL, 32), ItemApi.getBlock("blockTable", 14),
		new ItemStack[] {new ItemStack(ItemRegistry.craftItem, 1, 1),
		new ItemStack(ItemRegistry.craftItem, 1, 1),
		new ItemStack(ItemRegistry.craftItem, 1, 1),
		new ItemStack(ItemRegistry.craftItem, 1, 1),
		new ItemStack(ItemRegistry.elemPick),
		new ItemStack(ItemRegistry.elemAxe),
		ItemApi.getBlock("blockCosmeticSolid", 4)});
	}
	
	
}
