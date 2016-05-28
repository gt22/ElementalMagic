package com.gt22.elementalmagic.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

public class ArcaneRecipeRegistry {
	public static final ShapedArcaneRecipe windFoci = new ShapedArcaneRecipe(
	"windfocus",
	new ItemStack(ItemRegistry.windFocus, 1, 0),
	new AspectList().add(Aspect.AIR, 30),
	"zxz",
	"xcx",
	"zxz",
	'z', ItemApi.getItem("itemShard", 0),
	'x', Items.sugar,
	'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
	public static final ShapedArcaneRecipe emberFoci = new ShapedArcaneRecipe(
	"emberfocus",
	new ItemStack(ItemRegistry.windFocus, 1, 0),
	new AspectList().add(Aspect.FIRE, 30),
	"zxz",
	"xcx",
	"zxz",
	'z', ItemApi.getItem("itemShard", 1),
	'x', Items.coal,
	'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
	public static final ShapedArcaneRecipe currentFoci = new ShapedArcaneRecipe(
	"currentfocus",
	new ItemStack(ItemRegistry.windFocus, 1, 0),
	new AspectList().add(Aspect.WATER, 30),
	"zxz",
	"xcx",
	"zxz",
	'z', ItemApi.getItem("itemShard", 2),
	'x', new ItemStack(Items.potionitem, 0, 0),
	'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
	public static final ShapedArcaneRecipe natureFoci = new ShapedArcaneRecipe(
	"naturefocus",
	new ItemStack(ItemRegistry.windFocus, 1, 0),
	new AspectList().add(Aspect.EARTH, 30),
	"zxz",
	"xcx",
	"zxz",
	'z', ItemApi.getItem("itemShard", 3),
	'x', Items.wheat_seeds,
	'c', new ItemStack(ItemRegistry.craftItem, 1, 0));
	public static final ShapedArcaneRecipe inertFoci = new ShapedArcaneRecipe(
	"elemfocies",
	new ItemStack(ItemRegistry.craftItem, 1, 0),
	new AspectList().add(Aspect.FIRE, 10).add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10),
	"zxz",
	"xcx",
	"zxz",
	'z', Blocks.glass,
	'x', Items.quartz,
	'c', Items.diamond);
}
