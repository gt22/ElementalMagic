package com.gt22.elementalmagic.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.gui.container.HolderContainer;
import com.gt22.elementalmagic.tiles.TileShardHolder;

public class HolderGUI extends GuiContainer
{
  private TileShardHolder te;
  private static HolderContainer container;
  public static final ResourceLocation texture = new ResourceLocation(ElementalMagic.modid, "textures/gui/HolderGUI.png");
  public HolderGUI(InventoryPlayer inventory, TileShardHolder te)
  {
    super(container = new HolderContainer(inventory, te));
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
    
  }
}
