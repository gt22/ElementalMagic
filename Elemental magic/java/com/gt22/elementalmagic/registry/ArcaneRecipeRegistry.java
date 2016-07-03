package com.gt22.elementalmagic.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import com.gt22.elementalmagic.api.AdvThaumApi;

public class ArcaneRecipeRegistry {
	public static ShapedArcaneRecipe windFoci, emberFoci, currentFoci, natureFoci, inertFoci, elemCap, elemPcik, elemSword, elemAxe, elemShovel, elemHoe;
	public static ShapedArcaneRecipe[] tools;
	public static ShapedArcaneRecipe boundMatrix;
	public static ShapedArcaneRecipe primalCharm;
	public static ShapedArcaneRecipe salisMundis;
	public static ShapedArcaneRecipe[] altrsrd = new ShapedArcaneRecipe[2];
	public static void init(){
		windFoci = ThaumcraftApi.addArcaneCraftingRecipe(
		"windfocus",
		new ItemStack(ItemRegistry.windFocus, 1, 0),
		new AspectList().add(Aspect.AIR, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 0),
		'x', Items.sugar,
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		emberFoci = ThaumcraftApi.addArcaneCraftingRecipe(
		"emberfocus",
		new ItemStack(ItemRegistry.emberFocus, 1, 0),
		new AspectList().add(Aspect.FIRE, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 1),
		'x', Items.coal,
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		currentFoci = ThaumcraftApi.addArcaneCraftingRecipe(
		"currentfocus",
		new ItemStack(ItemRegistry.currentFocus, 1, 0),
		new AspectList().add(Aspect.WATER, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 2),
		'x', new ItemStack(Items.potionitem, 0, 0),
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		natureFoci = ThaumcraftApi.addArcaneCraftingRecipe(
		"naturefocus",
		new ItemStack(ItemRegistry.natureFocus, 1, 0),
		new AspectList().add(Aspect.EARTH, 30),
		"zxz",
		"xcx",
		"zxz",
		'z', ItemApi.getItem("itemShard", 3),
		'x', Items.wheat_seeds,
		'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
		inertFoci = ThaumcraftApi.addArcaneCraftingRecipe(
		"elemfocies",
		new ItemStack(ItemRegistry.craftItem, 1, 0),
		new AspectList().add(Aspect.FIRE, 10).add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10),
		"zxz",
		"xcx",
		"zxz",
		'z', Blocks.glass,
		'x', Items.quartz,
		'c', Items.diamond);
		
		elemCap = ThaumcraftApi.addArcaneCraftingRecipe(
		"CAP_ELEMCAP",
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
		new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10),
			"zzz",
			" x ",
			" x ",
			'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
			'x', new ItemStack(Items.stick));
		elemAxe = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
				new ItemStack(ItemRegistry.elemAxe),
				new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10),
				true,
				"zz ",
				"zx ",
				" x ",
				'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
				'x', new ItemStack(Items.stick));
		elemHoe = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
				new ItemStack(ItemRegistry.elemHoe),
				new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10),
				true,
				"zz ",
				" x ",
				" x ",
				'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
				'x', new ItemStack(Items.stick));
		elemSword = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
				new ItemStack(ItemRegistry.elemSword),
				new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10),
				" z ",
				" z ",
				" x ",
				'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
				'x', new ItemStack(Items.stick));
		elemShovel = ThaumcraftApi.addArcaneCraftingRecipe("elemingot",
				new ItemStack(ItemRegistry.elemShovel),
				new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10),
				true,
				" z ",
				" x ",
				" x ",
				'z', new ItemStack(ItemRegistry.craftItem, 1, 1),
				'x', new ItemStack(Items.stick));
		tools = new ShapedArcaneRecipe[5];
		tools[0] = elemPcik;
		tools[1] = elemAxe;
		tools[2] = elemShovel;
		tools[3] = elemSword;
		tools[4] = elemHoe;
		
		boundMatrix = ThaumcraftApi.addArcaneCraftingRecipe(
		"autodecomp",
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
		
		primalCharm = ThaumcraftApi.addArcaneCraftingRecipe("altrsrd", ItemApi.getItem("itemResource", 15), AdvThaumApi.getPrimals(30),
		" o ",
		"lel",
		" n ",
		'o', ItemApi.getItem("itemShard", 4),
		'l', new ItemStack(ItemRegistry.craftItem, 1, 1),
		'e', new ItemStack(ItemRegistry.craftItem, 1, 2),
		'n', ItemApi.getItem("itemShard", 5));
		
		salisMundis = ThaumcraftApi.addArcaneCraftingRecipe("altrsrd", ItemApi.getItem("itemResource", 14), AdvThaumApi.getPrimals(70), "e", 'e', new ItemStack(ItemRegistry.craftItem, 1, 2));
		altrsrd[0] = primalCharm;
		altrsrd[1] = salisMundis;
		}
	

}
