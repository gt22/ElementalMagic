package com.gt22.elementalmagic.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class AdvThaumApi {
	
	public static AspectList getPrimals(int amount)
	{
		return new AspectList().add(Aspect.AIR, amount).add(Aspect.EARTH, amount).add(Aspect.FIRE, amount).add(Aspect.WATER, amount).add(Aspect.ORDER, amount).add(Aspect.ENTROPY, amount);
	}
	
	public static AspectList getElementas(int amount)
	{
		return new AspectList().add(Aspect.AIR, amount).add(Aspect.EARTH, amount).add(Aspect.FIRE, amount).add(Aspect.WATER, amount);
	}
	
	public int visSize(AspectList al)
	  {
	    int q = 0;
	    for (Aspect as : al.aspects.keySet()) {
	      q += al.getAmount(as);
	    }
	    return q;
	  }
	
	public static AspectList reduceToPrimals(AspectList al, boolean merge)
	  {
	    AspectList out = new AspectList();
	    for (Aspect aspect : al.getAspects()) {
	      if (aspect != null) {
	        if (aspect.isPrimal())
	        {
	          if (merge) {
	            out.merge(aspect, al.getAmount(aspect));
	          } else {
	            out.add(aspect, al.getAmount(aspect));
	          }
	        }
	        else
	        {
	          AspectList send = new AspectList();
	          send.add(aspect.getComponents()[0], al.getAmount(aspect));
	          send.add(aspect.getComponents()[1], al.getAmount(aspect));
	          send = reduceToPrimals(send, merge);
	          for (Aspect a : send.getAspects()) {
	            if (merge) {
	              out.merge(a, send.getAmount(a));
	            } else {
	              out.add(a, send.getAmount(a));
	            }
	          }
	        }
	      }
	    }
	    return out;
	  }
	
	private static final String wandpath = "thaumcraft.common.items.wands.ItemWandCasting";
	//Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, (short)i);
	//PacketHandler.INSTANCE.sendTo(new PacketAspectPool(this.table.aspect.getTag(), Short.valueOf((short)1), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(p.getCommandSenderName(), this.table.aspect))), (EntityPlayerMP)p);
	public boolean giveAspect(EntityPlayer player, Aspect aspect, short amount)
	{
		try
		{
			SimpleNetworkWrapper instance = (SimpleNetworkWrapper) Class.forName("thaumcraft.common.lib.network.PacketHandler").getField("INSTANCE").get(this);
			Field proxy = Class.forName("thaumcraft.common.Thaumcraft").getField("proxy");
			Class <? extends IMessage> pap = (Class<? extends IMessage>) Class.forName("thaumcraft.common.lib.network.playerdata.PacketAspectPool");
			Constructor <? extends IMessage> conspap = pap.getConstructor(String.class, Short.class, Short.class);
			AspectList al = ThaumcraftApiHelper.getDiscoveredAspects(player.getCommandSenderName());
			short value;
			if(al != null)
			{
				value = (short) al.getAmount(aspect);
			}
			else
			{
				value = 0;
			}
			instance.sendTo(conspap.newInstance(aspect.getTag(), Short.valueOf((short) 1), Short.valueOf(value)), (EntityPlayerMP)player);
			Class core = proxy.getDeclaringClass().getClass();
			Field playerKnowledge = proxy.get(proxy.get(core)).getClass().getField("playerKnowledge");
			Class  playerK = Class.forName("thaumcraft.common.lib.research.PlayerKnowledge");
			Object pk = playerKnowledge.get(proxy.get(core));
			Class[] params = 
			{
				String.class,
				Aspect.class,
				short.class,
			};
			Method add = pk.getClass().getMethod("addAspectPool", params);
			return (boolean) add.invoke(pk, player.getCommandSenderName(), aspect, amount);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void playWarpEffects(EntityPlayer player)
	{
		try
		{
			SimpleNetworkWrapper instance = (SimpleNetworkWrapper) Class.forName("thaumcraft.common.lib.network.PacketHandler").getField("INSTANCE").get(this);
			Class <? extends IMessage> pmc = (Class<? extends IMessage>) Class.forName("thaumcraft.common.lib.network.misc.PacketMiscEvent");
			Constructor <? extends IMessage> conspmc = pmc.getConstructor(short.class);
			instance.sendTo(conspmc.newInstance((short)0), (EntityPlayerMP)player);
		}
		catch (Exception e)
		{
			System.out.println("Unable to play warp effects");
			e.printStackTrace();
		}
	}
	public void compliteResearch(EntityPlayer player, String research)
	{
		try {
				SimpleNetworkWrapper instance = (SimpleNetworkWrapper) Class.forName("thaumcraft.common.lib.network.PacketHandler").getField("INSTANCE").get(this);
				Field proxy = Class.forName("thaumcraft.common.Thaumcraft").getField("proxy");
				Class <? extends IMessage> prc = (Class<? extends IMessage>) Class.forName("thaumcraft.common.lib.network.playerdata.PacketResearchComplete");
				Constructor <? extends IMessage> consprc = prc.getConstructor(String.class);
				instance.sendTo(consprc.newInstance(research), (EntityPlayerMP)player);
				Class clientproxy = Class.forName("thaumcraft.client.ClientProxy");
				Field researchManag = proxy.get(clientproxy).getClass().getField("researchManager");
				Class[] params = new Class[2];
				params[0] = EntityPlayerMP.class;
				params[1] = String.class;
				Class resMan = Class.forName("thaumcraft.common.lib.research.ResearchManager");
				Object resm = researchManag.get(clientproxy);
				resm.getClass().getMethod("completeResearch", params).invoke(resm, (EntityPlayerMP)player, research);
		} catch (Exception e) {
			System.out.println("Unable to give research");
			e.printStackTrace();
			//Minecraft.getMinecraft().shutdown();
		} 
		
		
	}
	
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
