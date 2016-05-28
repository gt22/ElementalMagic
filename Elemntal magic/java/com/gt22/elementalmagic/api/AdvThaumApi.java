package com.gt22.elementalmagic.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AdvThaumApi {
	
	private static final String wandpath = "thaumcraft.common.items.wands.ItemWandCasting";
	public static boolean drawVis(ItemStack wand, EntityPlayer player, AspectList aspects, boolean consume)
	{
		Class wandclass = null;
		try {
			wandclass = Class.forName(wandpath);
		} catch (ClassNotFoundException e1) {
			System.out.println("Unable to find wand class from thaumcraft");
			e1.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
				Method draw = null;
		try {
			Class[] params = new Class[5];
			params[0] = ItemStack.class;
			params[1] = EntityPlayer.class;
			params[2] = AspectList.class;
			params[3] = boolean.class;
			params[4] = boolean.class;
			draw = wandclass.getMethod("consumeAllVis", params);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Unable to find consumeAllVis method from wand class from thaumcraft");
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
		if(draw != null)
		{
			try {
				return (boolean) draw.invoke(wandclass.newInstance(), wand, player, aspects, consume, false);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				System.out.println("Unable to invoke consumeAllVis method from thaumcraft");
				e.printStackTrace();
				Minecraft.getMinecraft().shutdown();
			}
		}
		return false;
	}
	
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
}
