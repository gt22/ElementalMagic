package com.gt22.elementalmagic.render.tiles;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.render.models.ShardHolderModel;
import com.gt22.elementalmagic.render.models.ShardModel;
import com.gt22.elementalmagic.tiles.TileShardHolder;

public class ShardHolderRender extends TileEntitySpecialRenderer {

	private ShardHolderModel model;
	private ShardModel shard;
	public ShardHolderRender() {
		model = new ShardHolderModel();
		shard = new ShardModel();
	}
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F); 
		GL11.glPopMatrix(); 
	}
	
	@Override 
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) { 
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F); 
		String texturename = "models/HolderTextrue";
		TileShardHolder holder = (TileShardHolder) te;
		if(holder.getStackInSlot(0) != null)
		{
			switch(holder.getStackInSlot(0).getItemDamage())
	           {
		           case(0)://Air
		           {
		        	   texturename += "Air";
		        	   break;
		           }
		           case(1)://Fire
		           {
		        	   texturename += "Fire";
		        	   break;
		           }
		           case(2)://Water
		           {
		        	   texturename += "Water";
		        	   break;
		           }
		           case(3)://Earth
		           {
		        	   texturename += "Earth";
		        	   break;
		           }
	           }
		}
		ResourceLocation textures = (new ResourceLocation(Core.modid, texturename + ".png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F); 
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPushMatrix();
		
        if (holder.getStackInSlot(0) != null)
        {
        	GL11.glPushMatrix();
        	ResourceLocation texture = null;
           switch(holder.getStackInSlot(0).getItemDamage())
           {
	           case(0)://Air
	           {
	        	   texture = new ResourceLocation(Core.modid, "models/AirShardModel.png");
	        	   break;
	           }
	           case(1)://Fire
	           {
	        	   texture = new ResourceLocation(Core.modid, "models/FireShardModel.png");
	        	   break;
	           }
	           case(2)://Water
	           {
	        	   texture = new ResourceLocation(Core.modid, "models/WaterShardModel.png");
	        	   break;
	           }
	           case(3)://Earth
	           {
	        	   texture = new ResourceLocation(Core.modid, "models/EarthShardModel.png");
	        	   break;
	           }
           }
           
           if(texture != null)
           {
        	   Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        	   shard.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        	   GL11.glPopMatrix();
           }
        }

        GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	
	
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block) 
	{ 
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, i, j, k); 
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier); 
	}
}
