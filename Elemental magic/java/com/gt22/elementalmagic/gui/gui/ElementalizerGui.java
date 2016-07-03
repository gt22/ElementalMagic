package com.gt22.elementalmagic.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.api.aspects.Aspect;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.gui.container.ElementalizerContainer;
import com.gt22.elementalmagic.tiles.TileElementalizer;

public class ElementalizerGui extends GuiContainer
{
	  private TileElementalizer te;
	  private static ElementalizerContainer container;
	  public static final ResourceLocation texture = new ResourceLocation(Core.modid, "textures/gui/ElementalizerGUI.png");
	  public ElementalizerGui(InventoryPlayer inventory, TileElementalizer te)
	  {
	    super(container = new ElementalizerContainer(inventory, te));
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
		    ItemStack[] shards = te.getShards();
		    for(ItemStack shard : shards)
		    {
		    	if(shard != null)
		    	switch(shard.getItemDamage())
		           {
			           case(0)://Air
			           {
			        	   drawTexturedModalRect(k + 8, l + 67, 176, 0, 16, 16);
			        	   break;
			           }
			           case(1)://Fire
			           {
			        	   drawTexturedModalRect(k + 44, l + 67, 176, 17, 16, 16);
			        	   break;
			           }
			           case(2)://Water
			           {
			        	   drawTexturedModalRect(k + 116, l + 67, 176, 34, 16, 16);
			        	   break;
			           }
			           case(3)://Earth
			           {
			        	   drawTexturedModalRect(k + 152, l + 67, 176, 51, 16, 16);
			        	   break;
			           }
		           }
		    }
		    if(te.isWorking())
		    {
		    	int aer = te.getScaledVis(48, Aspect.AIR);
		    	int ignis = te.getScaledVis(48, Aspect.FIRE);
		    	int aqua = te.getScaledVis(48, Aspect.WATER);
		    	int terra = te.getScaledVis(48, Aspect.EARTH);
		    	drawTexturedModalRect(k + 11, l + 18 + aer, 7, 168 + aer, 16, 48 - aer);
		    	drawTexturedModalRect(k + 47, l + 18 + ignis, 24, 168 + ignis, 16, 48 - ignis);
		    	drawTexturedModalRect(k + 119, l + 18 + aqua, 41, 168 + aqua, 16, 48 - aqua);
		    	drawTexturedModalRect(k + 155, l + 18 + terra, 58, 168 + terra, 16, 48 - terra);
		    	//System.out.println(te.getScaledVis(48, Aspect.AIR));
		    }
	  }
}
