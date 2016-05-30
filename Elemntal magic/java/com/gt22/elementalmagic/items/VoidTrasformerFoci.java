package com.gt22.elementalmagic.items;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

import com.gt22.elementalmagic.api.AdvThaumApi;
import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.enums.MatrixType;

public class VoidTrasformerFoci extends ItemFocusBasic {
	public VoidTrasformerFoci(String unlocName) {
		super();
		setCreativeTab(ElementalMagic.tab);
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
	}
	
	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList ret = new AspectList();
		ret.add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000);
		return ret;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		AspectList al = this.getVisCost(stack);
		if (al!=null && al.size()>0) {
			list.add(StatCollector.translateToLocal(isVisCostPerTick(stack)?"item.Focus.cost2":"item.Focus.cost1"));
			for (Aspect aspect:al.getAspectsSorted()) {
				DecimalFormat myFormatter = new DecimalFormat("#####.##");
				String amount = myFormatter.format(al.getAmount(aspect)/100f);
				list.add(" \u00A7"+aspect.getChatcolor()+aspect.getName()+"\u00A7r x "+ amount + " (no discount)");				
			}
		}
		addFocusInformation(stack,player,list,par4);
	}
	
	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 4597855;
	}
	
	@Override
	public ItemStack onFocusRightClick(ItemStack wandstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {
		if(AdvThaumApi.getVis(wandstack, Aspect.ORDER) >= getVisCost(AdvThaumApi.getFocusStack(wandstack)).getAmount(Aspect.ORDER) && AdvThaumApi.getVis(wandstack, Aspect.ENTROPY) >= getVisCost(AdvThaumApi.getFocusStack(wandstack)).getAmount(Aspect.ENTROPY) && VoidMatrixApi.getMatrix(MatrixType.STABLE, player) <= VoidMatrixApi.maxMatrix - 5 && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, player) <= VoidMatrixApi.maxMatrix - 5)
		{
			AdvThaumApi.setVis(wandstack, new AspectList().add(Aspect.ORDER, AdvThaumApi.getVis(wandstack, Aspect.ORDER) - getVisCost(AdvThaumApi.getFocusStack(wandstack)).getAmount(Aspect.ORDER)).add(Aspect.ENTROPY, AdvThaumApi.getVis(wandstack, Aspect.ENTROPY) - getVisCost(AdvThaumApi.getFocusStack(wandstack)).getAmount(Aspect.ENTROPY)));
			VoidMatrixApi.addMatrix(MatrixType.STABLE, 5, player);
			VoidMatrixApi.addMatrix(MatrixType.UNSTABLE, 5, player);
		}
		return wandstack;
	}
	

}
