package com.gt22.elementalmagic.blocks;

import java.util.ArrayList;
import java.util.List;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.wands.IWandable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.registry.BlockRegistry;
import com.gt22.elementalmagic.tiles.TileShardHolder;

import cpw.mods.fml.common.registry.GameRegistry;


public class MetalBlock extends Block implements IWandable
{
	public static IIcon[] icons;
	public static int maxmeta = 0;
	public static ArrayList <String> names = new ArrayList();
	public static ArrayList<ItemStack> ingots = new ArrayList<ItemStack>();
	public static ShapedRecipes[] recepies;
	public MetalBlock() {
		super(Material.iron);
		setCreativeTab(ElementalMagic.tab);
	}

	public static void registerMetalBlocksRecipes()
	{
		recepies = new ShapedRecipes[maxmeta];
		for(int i = 0; i < maxmeta; i++)
		{
			recepies[i] = (ShapedRecipes) GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.metalBlocks, 1, i), 
			"###",
			"###",
			"###",
			'#', ingots.get(i));
			GameRegistry.addShapelessRecipe(new ItemStack(ingots.get(i).getItem(), 9, ingots.get(i).getItemDamage()), new ItemStack(BlockRegistry.metalBlocks, 1, i));
		}
	}
	
	/**
	 * Used to see meta of all metal blocks in debug.
	 */
	public void printMetaWithNames()
	{
		for(int i = 0; i < maxmeta; i++)
		{
			System.out.println(i + " - " + names.get(i));
		}
	}
	
	
	public void addMetalBlock(String unlocName, ItemStack ingot)
	{
		maxmeta++;
		names.add(unlocName);
		ingots.add(ingot);
	}
	
	public void addMetalBlocks(String[] unlocNames, ItemStack ingots)
	{
		for(int i = 0; i < unlocNames.length; i++)
		{
			addMetalBlock(unlocNames[i], ingots);
		}
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile.MetalBlock";
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[maxmeta];
		for(int i = 0; i < maxmeta; i++)
		{
			icons[i] = reg.registerIcon(ElementalMagic.modid + ":" + names.get(i));
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta > maxmeta)
		{
			meta = 0;
		}
		return icons[meta];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxmeta; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack,
			EntityPlayer player, int x, int y, int z, int side, int md) {
		if(md == 0 && checkHolders(world, x, y, z) != null && ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, AdvThaumApi.getElementas(50), false, false))
		{
			ThaumcraftApiHelper.consumeVisFromWand(wandstack, player, AdvThaumApi.getElementas(5000), true, false);
			world.setBlock(x, y, z, BlockRegistry.elementalizer);
			return 0;
		}
		return -1;
	}
	
	public static TileShardHolder[] checkHolders(World world, int x, int y, int z)
	{
		TileEntity[] holders = new TileShardHolder[4];
		if(world.getTileEntity(x - 3, y, z) != null)
			holders[0] = world.getTileEntity(x - 3, y, z);
		if(world.getTileEntity(x + 3, y, z) != null)	
			holders[1] = world.getTileEntity(x + 3, y, z);
		if(world.getTileEntity(x, y, z - 3) != null)	
			holders[2] = world.getTileEntity(x, y, z - 3);
		if(world.getTileEntity(x, y, z + 3) != null)
			holders[3] = world.getTileEntity(x, y, z + 3);
		for(int i  = 0; i < holders.length; i++)
		{
			if(holders == null || !(holders[i] instanceof TileShardHolder))
			{
				return null;
			}
		}
		return (TileShardHolder[]) holders;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack,
			EntityPlayer player) {
		return wandstack;
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player,
			int count) {
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world,
			EntityPlayer player, int count) {
		
	}
}
