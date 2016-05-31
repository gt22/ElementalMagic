package com.gt22.elementalmagic.blocks;

import java.util.ArrayList;
import java.util.List;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.registry.BlockRegistry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class MetalBlock extends Block{
	public static IIcon[] icons;
	public static int maxmeta = 0;
	public static ArrayList <String> names = new ArrayList();
	public static ArrayList<ItemStack> ingots = new ArrayList<ItemStack>();
	public MetalBlock() {
		super(Material.iron);
		setCreativeTab(ElementalMagic.tab);
	}

	public static void registerMetalBlocksRecipes()
	{
		for(int i = 0; i < maxmeta; i++)
		{
			GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.metalBlocks, 1, i), 
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
}
