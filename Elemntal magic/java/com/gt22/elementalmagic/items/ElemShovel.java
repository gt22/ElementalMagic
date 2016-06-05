package com.gt22.elementalmagic.items;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.registry.Materials;

public class ElemShovel extends ItemSpade {

	public ElemShovel(String unlocName) {
		super(Materials.toolMatElem);
		setUnlocalizedName(unlocName);
		setTextureName(ElementalMagic.modid + ":" + unlocName);
		setCreativeTab(ElementalMagic.tab);
	}
	

	private int side;
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z,
			int p_77648_7_, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		boolean ret = false;
		MovingObjectPosition movingobjectposition = getTargetBlock(player.worldObj, player, true);
	    if ((movingobjectposition != null) && 
	      (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)) {
	      this.side = movingobjectposition.sideHit;
	      if (!player.worldObj.isRemote)
		    {
	    	  Block bi = world.getBlock(x, y, z);
		      int md = world.getBlockMetadata(x, y, z);
		      if ((ForgeHooks.isToolEffective(stack, bi, md))) {
		        for (int aa = -1; aa <= 1; aa++) {
		          for (int bb = -1; bb <= 1; bb++)
		          {
		            int xx = 0;
		            int yy = 0;
		            int zz = 0;
		            if (this.side <= 1)
		            {
		              xx = aa;
		              zz = bb;
		            }
		            else if (this.side <= 3)
		            {
		              xx = aa;
		              yy = bb;
		            }
		            else
		            {
		              zz = aa;
		              yy = bb;
		            }
		            if ((!(player instanceof EntityPlayer)) || (world.canMineBlock((EntityPlayer)player, x + xx, y + yy, z + zz)))
		            {
		              Block bl = world.getBlock(x + xx, y + yy, z + zz);
		              md = world.getBlockMetadata(x + xx, y + yy, z + zz);
		              if ((bl.getBlockHardness(world, x + xx, y + yy, z + zz) >= 0.0F) && ((ForgeHooks.isToolEffective(stack, bl, md))))
		              {
		                stack.damageItem(1, player);
		                ArrayList<ItemStack> drops = world.getBlock(x + xx, y + yy, z + zz).getDrops(world, x + xx, y + yy, z + zz, world.getBlockMetadata(x + xx, y + yy, z + zz), 0);
		                world.setBlockToAir(x + xx, y + yy, z + zz);
		                ret = true;
		                for(ItemStack o : drops)
		                {
		                	world.spawnEntityInWorld(new EntityItem(world, x + xx, y + yy, z + zz, o));
		                }
		              }
		              
		            }
		          }
		        }
		      }
		    }
	    }
		return ret;
	}
	  
	
	public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
	  {
	    float var4 = 1.0F;
	    float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;
	    
	    float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;
	    
	    double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;
	    
	    double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;
	    
	    double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;
	    
	    Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
	    float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
	    float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
	    float var16 = -MathHelper.cos(-var5 * 0.017453292F);
	    float var17 = MathHelper.sin(-var5 * 0.017453292F);
	    float var18 = var15 * var16;
	    float var20 = var14 * var16;
	    double var21 = 10.0D;
	    Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
	    
	    return world.func_147447_a(var13, var23, par3, !par3, false);
	  }
}
