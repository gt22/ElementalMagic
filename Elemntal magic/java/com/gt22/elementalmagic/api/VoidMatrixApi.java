package com.gt22.elementalmagic.api;

import thaumcraft.api.aspects.Aspect;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.enums.ElemCoreType;
import com.gt22.elementalmagic.enums.MatrixType;

import net.minecraft.entity.player.EntityPlayer;

public class VoidMatrixApi {
	public static final int maxMatrix = 100;
	public static String getMatrixNBTKey(MatrixType type)
	{
		return type == MatrixType.STABLE ? ElementalMagic.modid + ".matrixStable" : ElementalMagic.modid + ".matrixUnstable";
	}
	
	public static MatrixType getMatrixTypeFromAsper(Aspect aspect)
	{
		if(aspect == Aspect.AIR || aspect == Aspect.FIRE)
		{
			return MatrixType.UNSTABLE;
		}
		if(aspect == Aspect.EARTH || aspect == Aspect.WATER)
		{
			return MatrixType.STABLE;
		}
		return null;
	}
	
	public static MatrixType getMatrixTypeFromCoreType(ElemCoreType type)
	{
		switch(type)
		{
		case FIRE: case AIR:
		{
			return MatrixType.UNSTABLE;
		}
		case EARTH: case WATER:
		{
			return MatrixType.STABLE;
		}
		default:
		{
			return null;
		}
		}
	}
	
	public static int getMatrix(MatrixType type, EntityPlayer player)
	{
		if(!player.getEntityData().hasKey(getMatrixNBTKey(type)))
		{
			player.getEntityData().setInteger(getMatrixNBTKey(type), 0);
		}
		
		return player.getEntityData().getInteger(getMatrixNBTKey(type));
	}
	
	public static void setMatrix(MatrixType type, int amount, EntityPlayer player)
	{
		player.getEntityData().setInteger(getMatrixNBTKey(type), amount);
	}
	
	public static int addMatrix(MatrixType type, int amount, EntityPlayer player)
	{
		int curmatrix = getMatrix(type, player);
		if(amount + curmatrix > maxMatrix)
		{
			amount = maxMatrix - curmatrix;
		}
		setMatrix(type, curmatrix + amount, player);
		return amount;
	}
	
	public static int drawMatrix(MatrixType type, int amount, EntityPlayer player)
	{
		int curmatrix = getMatrix(type, player);
		if(amount > curmatrix)
		{
			amount = curmatrix;
		}
		setMatrix(type, curmatrix - amount, player);
		return amount;
	}
	
	
	
}
