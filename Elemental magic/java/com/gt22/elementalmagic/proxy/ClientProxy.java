package com.gt22.elementalmagic.proxy;

import com.gt22.elementalmagic.render.tiles.ShardHolderRender;
import com.gt22.elementalmagic.tiles.TileShardHolder;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	public void preInit(FMLPreInitializationEvent e)
	{
		initRender();
		super.preInit(e);
	}
	
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
		super.postInit(e);
	}
	
	private void initRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileShardHolder.class, new ShardHolderRender());
	}
}
