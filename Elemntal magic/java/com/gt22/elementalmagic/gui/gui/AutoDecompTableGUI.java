package com.gt22.elementalmagic.gui.gui;

import java.util.Arrays;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.gui.container.AutoDecompTableContainer;
import com.gt22.elementalmagic.tiles.TileAutoDecompTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AutoDecompTableGUI extends GuiContainer
{
  private TileAutoDecompTable te;
  private static AutoDecompTableContainer container;
  public static final ResourceLocation texture = new ResourceLocation(ElementalMagic.modid, "textures/gui/AutoDecompTable.png");
  public AutoDecompTableGUI(InventoryPlayer inventory, TileAutoDecompTable te)
  {
    super(container = new AutoDecompTableContainer(inventory, te));
    this.te = te;
    this.xSize = 176;
    this.ySize = 166;
  }

  
  @Override
  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    mc.getTextureManager().bindTexture(texture);
    int k = (this.width - this.xSize) / 2;
    int l = (this.height - this.ySize) / 2;
    GL11.glEnable(3042);
    drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    GL11.glDisable(3042);
    if (this.te.breaktime > 0)
	{
    	int i1 = this.te.getBreakTimeScaled(46);
    	drawTexturedModalRect(k + 93, l + 61 - i1, 176, 46 - i1, 9, i1);
	}
  }
}
