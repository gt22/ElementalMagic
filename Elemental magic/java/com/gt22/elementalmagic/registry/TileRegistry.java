package com.gt22.elementalmagic.registry;

import net.minecraft.tileentity.TileEntity;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;
import com.gt22.elementalmagic.tiles.TileElementalizer;
import com.gt22.elementalmagic.tiles.TileShardHolder;
import com.gt22.elementalmagic.tiles.TileSuperNode;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry
{
	public static void register(Class<? extends TileEntity> te)
	{
		GameRegistry.registerTileEntity(te, Core.modid + "." + te.getName());
	}
	
	public static final void init()
	{
		register(TileAutoDecompTable.class);
		register(TileShardHolder.class);
		register(TileElementalizer.class);
		register(TileSuperNode.class);
	}
	
}
