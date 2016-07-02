package com.gt22.elementalmagic.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gt22.elementalmagic.core.Core;

public class CraftItem extends ItemBase {
	
	public IIcon[] icons;
	public static String modid = Core.modid;
	private String textureName;
	private int maxmeta = 0;
	ArrayList<String> names = new ArrayList();
	public CraftItem()
	{
		super("CraftItem", true);
		this.setHasSubtypes(true);
	}
	
	public void addCraftItem(String unlocName)
	{
		maxmeta++;
		names.add(unlocName);
	}
	
	public void addCraftItems(String[] names)
	{
		for (int i = 0; i < names.length; i++)
		{
			addCraftItem(names[i]);
		}
	}
	
	/**
	 * Used to see meta of all craft items in debug.
	 */
	public void printMetaWithNames()
	{
		for(int i = 0; i < maxmeta; i++)
		{
			System.out.println(i + " - " + names.get(i));
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < maxmeta; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
		@Override
		public void registerIcons(IIconRegister reg) {
			icons = new IIcon[maxmeta];
			for(int i = 0; i < maxmeta; i++)
			{
				icons[i] = reg.registerIcon(modid + ":" + names.get(i));
			}
		}
		
		@Override
		public IIcon getIconFromDamage(int meta) {
		    if (meta > maxmeta)
		        meta = 0;

		    return icons[meta];
		}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if(meta > maxmeta)
		{
			meta = maxmeta;
		}
	    return "item." + names.get(meta);
	}
	
	
}
