package com.gt22.elementalmagic.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;

public class ArcaneRecipeRegistry
{
	public static ShapedArcaneRecipe windFoci, emberFoci, currentFoci,
			natureFoci, inertFoci, elemCap, elemPcik, elemSword, elemAxe,
			elemShovel, elemHoe, aerCharm, aquaCharm, terraCharm, ignisCharm, neutralCharm;
	public static ShapedArcaneRecipe boundMatrix;
	public static ShapedArcaneRecipe primalCharm;
	public static ShapedArcaneRecipe salisMundis;

	/**
	 * @formatter:off
	 */
	public static void init()
	{
		windFoci = ThaumcraftApi.addArcaneCraftingRecipe("windfocus",
		new ItemStack(ItemRegistry.windFocus, 1, 0),
		new AspectList().add(Aspect.AIR, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 0),
		'x', Items.sugar,
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		emberFoci = ThaumcraftApi.addArcaneCraftingRecipe("emberfocus",
		new ItemStack(ItemRegistry.emberFocus, 1, 0),
		new AspectList().add(Aspect.FIRE, 30), 
		"zxz", 
		"xcx", 
		"zxz", 
		'z', ItemApi.getItem("itemShard", 1), 
		'x', Items.coal, 
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		currentFoci = ThaumcraftApi.addArcaneCraftingRecipe("currentfocus",
		new ItemStack(ItemRegistry.currentFocus, 1, 0),
		new AspectList().add(Aspect.WATER, 30),
		"zxz", 
		"xcx", 
		"zxz", 
		'z', ItemApi.getItem("itemShard", 2),
		'x', new ItemStack(Items.potionitem, 0, 0),
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		natureFoci = ThaumcraftApi.addArcaneCraftingRecipe("naturefocus",
		new ItemStack(ItemRegistry.natureFocus, 1, 0),
		new AspectList().add(Aspect.EARTH, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 3),
		'x', Items.wheat_seeds,
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		inertFoci = ThaumcraftApi.addArcaneCraftingRecipe("elemfocies",
		new ItemStack(ItemRegistry.craftItem, 1, 0),
		AdvThaumApi.getElementas(10),
		"zxz",
		"xcx",
		"zxz",
		'z', Blocks.glass,
		'x', Items.quartz,
		'c', Items.diamond);
		elemCap = ThaumcraftApi.addArcaneCraftingRecipe("CAP_ELEMCAP",
		new ItemStack(ItemRegistry.elemCap),
		AdvThaumApi.getElementas(25),
		"z1z",
		"4c2",
		"z3z",
		'z', ItemApi.getItem("itemResource", 2),
		'1', ItemApi.getItem("itemShard", 0),
		'2', ItemApi.getItem("itemShard", 2),
		'3', ItemApi.getItem("itemShard", 3),
		'4', ItemApi.getItem("itemShard", 1),
		'c', ItemApi.getItem("itemWandCap", 1));
		elemPcik = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
		new ItemStack(ItemRegistry.elemPick),
		AdvThaumApi.getElementas(10),
		"zzz",
		" x ",
		" x ", 
		'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
		'x', new ItemStack(Items.stick));
		elemAxe = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
		new ItemStack(ItemRegistry.elemAxe),
		AdvThaumApi.getElementas(10),
		true,
		"zz ",
		"zx ",
		" x ",
		'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
		'x', new ItemStack(Items.stick));
		elemHoe = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
		new ItemStack(ItemRegistry.elemHoe),
		AdvThaumApi.getElementas(10),
		true,
		"zz ",
		" x ",
		" x ",
		'z', new ItemStack(ItemRegistry.craftItem, 1, 1), 
		'x', new ItemStack(Items.stick));
		elemSword = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
		new ItemStack(ItemRegistry.elemSword),
		AdvThaumApi.getElementas(10),
		" z ",
		" z ", 
		" x ", 
		'z', new ItemStack(ItemRegistry.craftItem, 1, 1), 
		'x', new ItemStack(Items.stick));
		elemShovel = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
		new ItemStack(ItemRegistry.elemShovel), 
		AdvThaumApi.getElementas(10),
		true,
		" z ",
		" x ",
		" x ",
		'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
		'x', new ItemStack(Items.stick));
		boundMatrix = ThaumcraftApi.addArcaneCraftingRecipe("autodecomp",
		new ItemStack(ItemRegistry.boundMatrix),
		AdvThaumApi.getElementas(35),
		"#$#",
		"%^&",
		"#*#",
		'#', ItemApi.getItem("itemResource", 2),
		'$', ItemApi.getItem("itemZombieBrain", 0),
		'%', ItemApi.getItem("itemResource", 15),
		'^', new ItemStack(Items.emerald),
		'&', ItemApi.getItem("itemResource", 10),
		'*', new ItemStack(ItemRegistry.craftItem, 1, 1));
		primalCharm = ThaumcraftApi.addArcaneCraftingRecipe("altrsrd",
		ItemApi.getItem("itemResource", 15),
		AdvThaumApi.getPrimals(30),
		" o ",
		"lel",
		" n ",
		'o', ItemApi.getItem("itemShard", 4),
		'l', new ItemStack(ItemRegistry.craftItem, 1, 1),
		'e', new ItemStack(ItemRegistry.craftItem, 1, 2),
		'n', ItemApi.getItem("itemShard", 5));
		salisMundis = ThaumcraftApi.addArcaneCraftingRecipe("altrsrd",
		ItemApi.getItem("itemResource", 14),
		AdvThaumApi.getPrimals(70),
		"e",
		'e', new ItemStack(ItemRegistry.craftItem, 1, 2));
		aerCharm = ThaumcraftApi.addArcaneCraftingRecipe("elementalCharms", new ItemStack(ItemRegistry.elemCharms, 1, 0),
		AdvThaumApi.getElementas(40).add(Aspect.AIR, 20),
		" sa",
		"s s",
		"fs ",
		'a', ItemApi.getItem("itemBaubleBlanks", 0),
		's', new ItemStack(Items.string),
		'f', new ItemStack(ItemRegistry.windFocus));
		aquaCharm = ThaumcraftApi.addArcaneCraftingRecipe("elementalCharms", new ItemStack(ItemRegistry.elemCharms, 1, 1),
		AdvThaumApi.getElementas(40).add(Aspect.WATER, 20),
		" sa",
		"s s",
		"fs ",
		'a', ItemApi.getItem("itemBaubleBlanks", 0),
		's', new ItemStack(Items.string),
		'f', new ItemStack(ItemRegistry.currentFocus));
		terraCharm = ThaumcraftApi.addArcaneCraftingRecipe("elementalCharms", new ItemStack(ItemRegistry.elemCharms, 1, 2),
		AdvThaumApi.getElementas(40).add(Aspect.EARTH, 20),
		" sa",
		"s s",
		"fs ",
		'a', ItemApi.getItem("itemBaubleBlanks", 0),
		's', new ItemStack(Items.string),
		'f', new ItemStack(ItemRegistry.natureFocus));
		ignisCharm = ThaumcraftApi.addArcaneCraftingRecipe("elementalCharms", new ItemStack(ItemRegistry.elemCharms, 1, 3),
		AdvThaumApi.getElementas(40).add(Aspect.FIRE, 20),
		" sa",
		"s s",
		"fs ",
		'a', ItemApi.getItem("itemBaubleBlanks", 0),
		's', new ItemStack(Items.string),
		'f', new ItemStack(ItemRegistry.emberFocus));
		neutralCharm = ThaumcraftApi.addArcaneCraftingRecipe("elementalCharms", new ItemStack(ItemRegistry.elemCharms, 1, 4),
		AdvThaumApi.getElementas(40),
		" sa",
		"s s",
		"fs ",
		'a', ItemApi.getItem("itemBaubleBlanks", 0),
		's', new ItemStack(Items.string),
		'f', new ItemStack(ItemRegistry.craftItem, 1, 0));
	}
	/**
	 * @formatter:on
	 */
}
