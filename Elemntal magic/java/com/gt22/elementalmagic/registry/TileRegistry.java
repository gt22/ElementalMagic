package com.gt22.elementalmagic.registry;

import scala.tools.nsc.transform.patmat.MatchOptimization.SwitchEmission.RegularSwitchMaker;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry
{
	public static void registerTile(Class<? extends TileEntity> te)
	{
		GameRegistry.registerTileEntity(te, ElementalMagic.modid + "." + te.getName());
	}
	
	public static final void init()
	{
		registerTile(TileAutoDecompTable.class);
	}
	
}
