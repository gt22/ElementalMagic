package com.gt22.elementalmagic.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.gui.GuiHandler;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;
import com.gt22.gt22core.baseclasses.block.BlockWithTile;
import com.gt22.gt22core.utils.ToolClass;

public class AutoDecompTable extends BlockWithTile
{

	private static IIcon[] icons = new IIcon[2];
	public AutoDecompTable(String unlocName)
	{
		super(Material.iron, 7F, 7F, unlocName, Core.instance, ToolClass.pickaxe, 2, TileAutoDecompTable.class);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileAutoDecompTable();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		icons[0] = reg.registerIcon(Core.modid + ":AutoDecomTableSide");
		icons[1] = reg.registerIcon(Core.modid + ":AutoDecomTableTop");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		return dir == ForgeDirection.DOWN || dir == ForgeDirection.UP ? icons[1] : icons[0];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if(!player.isSneaking())
		{
			player.openGui(Core.instance, GuiHandler.AutoDecompTableID, world, x, y, z);
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
