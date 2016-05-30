package com.gt22.elementalmagic.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class AdvThaumApi {
	
	private static final String wandpath = "thaumcraft.common.items.wands.ItemWandCasting";
	
	public static void setVis(ItemStack wand, AspectList aspects)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Method set = null;
		try {
			Class[] params = new Class[2];
			params[0] = ItemStack.class;
			params[1] = AspectList.class;
			set = wandclass.getMethod("storeAllVis", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find storeAllVis method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		try {
			set.invoke(wandclass.newInstance(), wand, aspects);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			System.out.println("Unable to invoke storeAllVis method from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
	}
	
	public static int getVis(ItemStack wand, Aspect aspect)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Method get = null;
		try {
			Class[] params = new Class[2];
			params[0] = ItemStack.class;
			params[1] = Aspect.class;
			get = wandclass.getMethod("getVis", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find getVis method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		try {
			return (int) get.invoke(wandclass.newInstance(), wand, aspect);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			System.out.println("Unable to invoke getVis method from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		return 0;
	}
	
	public static int insertVis(ItemStack wand, Aspect aspect, int amount, boolean insertit)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Method insert = null;
		try {
			Class[] params = new Class[4];
			params[0] = ItemStack.class;
			params[1] = Aspect.class;
			params[2] = int.class;
			params[3] = boolean.class;
			insert = wandclass.getMethod("addVis", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find addVis method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		try {
			return (int) insert.invoke(wandclass.newInstance(), wand, aspect, amount, insertit);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			System.out.println("Unable to invoke addVis method from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		return 0;
	}
	
	/**
	 * Used for getting foci from wand
	 * @param wand
	 * @return ItemStack of the foci
	 */
	public static ItemStack getFocusStack(ItemStack wand)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Class param = ItemStack.class;
		Method foci = null;
		try {
			foci = wandclass.getMethod("getFocusItem", param);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find getFocusItem method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		
		try {
			return (ItemStack) foci.invoke(wandclass.newInstance(), wand);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			System.out.println("Unable to invoke getFocusItem method from thaumcraft");
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getMaxVis(ItemStack wand)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Class param = ItemStack.class;
		Method get = null;
		try {
			get = wandclass.getMethod("getMaxVis", param);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find getMaxVis method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		
		try {
			return (int) get.invoke(wandclass.newInstance(), wand);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			System.out.println("Unable to invoke getMaxVis method from thaumcraft");
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Allowing to get item of the foci. I don't know for what
	 * @param wand
	 * @return Item of the foci
	 */
	public static ItemFocusBasic getFoci(ItemStack wand)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		Method get = null;
		Class params = ItemStack.class;
		try {
			get = wandclass.getMethod("getFocus", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find getFocus method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
			try {
				return (ItemFocusBasic) get.invoke(wandclass.newInstance(), wand);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				System.out.println("Cannot invoke getFocus method from thaumcraft");
				e.printStackTrace();
				Minecraft.getMinecraft().shutdown();
			}
		return null;
	}
	
	/**
	 * Used for focies that can work with holding
	 * @param entity Player that use foci
	 * @param cd Cooldown before use
	 */
	public static void setCooldown(EntityLivingBase entity, int cd)
	{
		Class manage = null;
		try {
			manage = Class.forName("thaumcraft.common.items.wands.WandManager");
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand manager class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		
		Method setcd = null;
		try {
			Class[] params = new Class[2];
			params[0] = EntityLivingBase.class;
			params[1] = int.class;
			setcd = manage.getMethod("setCooldown", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find setCooldown method in wand manger class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		if(setcd != null)
		{
			try {
				setcd.invoke(manage.newInstance(), entity, cd);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				System.out.println("Unable to invoke setCooldown method from thaumcraft");
				e.printStackTrace();
				Minecraft.getMinecraft().shutdown();
			}
		}
	}

	public static void breakFurthestBlock(World worldObj, int x, int y, int z,
			Block bi, EntityPlayer player, boolean b, int i) {
		
		Class utils = null;
		try {
			utils = Class.forName("thaumcraft.common.lib.utils.BlockUtils");
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find BlockUtils class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		
		Method breakblock = null;
		try {
			Class[] params = new Class[8];
			params[0] = World.class;
			params[1] = int.class;
			params[2] = int.class;
			params[3] = int.class;
			params[4] = Block.class;
			params[5] = EntityPlayer.class;
			params[6] = boolean.class;
			params[7] = int.class;
			breakblock = utils.getMethod("breakFurthestBlock", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find breakFurthestBlock method in wand manger class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		if(breakblock != null)
		{
			try {
				breakblock.invoke(utils.newInstance(), worldObj, x, y, z, bi, player, b, i);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				System.out.println("Unable to invoke setCooldown method from thaumcraft");
				e.printStackTrace();
				Minecraft.getMinecraft().shutdown();
			}
		}
	}
}
