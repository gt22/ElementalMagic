package com.gt22.elementalmagic.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.gt22.elementalmagic.gui.container.AutoDecompTableContainer;
import com.gt22.elementalmagic.gui.container.HolderContainer;
import com.gt22.elementalmagic.gui.gui.AutoDecompTableGUI;
import com.gt22.elementalmagic.gui.gui.HolderGUI;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;
import com.gt22.elementalmagic.tiles.TileShardHolder;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	public static final int AutoDecompTableID = 0;
	public static final int HolderID = 1;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (AutoDecompTableID):
			{
				return new AutoDecompTableContainer(player.inventory, (TileAutoDecompTable) world.getTileEntity(x, y, z));
			}
			case(HolderID):
			{
				return new HolderContainer(player.inventory, (TileShardHolder) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case (AutoDecompTableID):
			{
				return new AutoDecompTableGUI(player.inventory, (TileAutoDecompTable) world.getTileEntity(x, y, z));
			}
			case(HolderID):
			{
				return new HolderGUI(player.inventory, (TileShardHolder) world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

}
