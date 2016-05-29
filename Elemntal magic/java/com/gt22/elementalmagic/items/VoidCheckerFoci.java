package com.gt22.elementalmagic.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.api.MatrixType;
import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.core.ElementalMagic;

public class VoidCheckerFoci extends ItemFocusBasic {
	public VoidCheckerFoci(String unlocName) {
		super();
		setCreativeTab(ElementalMagic.tab);
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
	}
	
	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		return ret;
	}
	
	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 4597855;
	}
	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {
		if(!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("Stable: " + VoidMatrixApi.getMatrix(MatrixType.STABLE, player)));
			player.addChatMessage(new ChatComponentText("Unstable: " + VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, player)));
		}
		return wandstack;
	}

}
