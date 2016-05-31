package com.gt22.elementalmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import com.gt22.elementalmagic.api.VoidMatrixApi;
import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.enums.ElemCoreType;
import com.gt22.elementalmagic.enums.MatrixType;
import com.gt22.elementalmagic.items.nbt.ItemNbt;
import com.gt22.elementalmagic.models.ModelFortressArmor;
import com.gt22.elementalmagic.registry.ItemRegistry;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;

public class VoidMatrixArmor
  extends ItemArmor
  implements IRepairable, ISpecialArmor, IGoggles, IRevealer, IWarpingGear, IVisDiscountGear
{
  public IIcon iconHelm;
  public IIcon iconChest;
  public IIcon iconLegs;
  public static final String coreNBTTag = ElementalMagic.modid + ".ELEMCORETYPE";
  
  public VoidMatrixArmor(String unlocName, ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
  {
    super(enumarmormaterial, j, k);
    setUnlocalizedName(unlocName);
    setCreativeTab(ElementalMagic.tab);
  }
  
  public static void setCoreType(ElemCoreType type, ItemStack stack)
  {
	  if(stack != null && stack.getItem() == ItemRegistry.voidMatrixChest)
	  {
		  ItemNbt.setString(stack, coreNBTTag, type.toString());;
	  }
  }
  
  public static ElemCoreType getCoreType(ItemStack stack)
  {
	  if(stack != null && stack.getItem() == ItemRegistry.voidMatrixChest)
	  {
		  String type = ItemNbt.getString(stack, coreNBTTag, "NEUTRAL");
		  return ElemCoreType.valueOf(type);
	  }
	  return null;
   }
  
  @Override
	public ItemStack onItemRightClick(ItemStack stack, World worldObj,
			EntityPlayer player) {
		if(stack.getItem() == ItemRegistry.voidMatrixChest)
		{
			VoidMatrixArmor chest = (VoidMatrixArmor) stack.getItem();
			switch(chest.getCoreType(stack))
			{
			case NEUTRAL:
			{
				chest.setCoreType(ElemCoreType.AIR, stack);
				System.out.println("air");
				break;
			}
			case AIR:
			{
				chest.setCoreType(ElemCoreType.WATER, stack);
				System.out.println("water");
				break;
			}
			case WATER:
			{
				chest.setCoreType(ElemCoreType.EARTH, stack);
				System.out.println("earth");
				break;
			}
			case EARTH:
			{
				chest.setCoreType(ElemCoreType.FIRE, stack);
				System.out.println("fire");
				break;
			}
			case FIRE:
			{
				chest.setCoreType(ElemCoreType.NEUTRAL, stack);
				System.out.println("neutral");
				break;
			}
			}
		}
	  return stack;
	}
  
 
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister ir)
  {
    this.iconHelm = ir.registerIcon(ElementalMagic.modid + ":VoidMatrixHelm");
    this.iconChest = ir.registerIcon(ElementalMagic.modid + ":VoidMatrixChest");
    this.iconLegs = ir.registerIcon(ElementalMagic.modid + ":VoidMatrixLegs");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1)
  {
    return this.armorType == 1 ? this.iconChest : this.armorType == 0 ? this.iconHelm : this.iconLegs;
  }
  
  ModelBiped model1 = null;
  ModelBiped model2 = null;
  ModelBiped model = null;
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
  {
    int type = ((ItemArmor)itemStack.getItem()).armorType;
    if (this.model1 == null) {
      this.model1 = new ModelFortressArmor(1.0F);
    }
    if (this.model2 == null) {
      this.model2 = new ModelFortressArmor(0.5F);
    }
    if ((type == 1) || (type == 3)) {
      this.model = this.model1;
    } else {
      this.model = this.model2;
    }
    if (this.model != null)
    {
      this.model.bipedHead.showModel = (armorSlot == 0);
      this.model.bipedHeadwear.showModel = (armorSlot == 0);
      this.model.bipedBody.showModel = ((armorSlot == 1) || (armorSlot == 2));
      this.model.bipedRightArm.showModel = (armorSlot == 1);
      this.model.bipedLeftArm.showModel = (armorSlot == 1);
      this.model.bipedRightLeg.showModel = (armorSlot == 2);
      this.model.bipedLeftLeg.showModel = (armorSlot == 2);
      this.model.isSneak = entityLiving.isSneaking();
      
      this.model.isRiding = entityLiving.isRiding();
      this.model.isChild = entityLiving.isChild();
      this.model.aimedBow = false;
      this.model.heldItemRight = (entityLiving.getHeldItem() != null ? 1 : 0);
      if ((entityLiving instanceof EntityPlayer)) {
        if (((EntityPlayer)entityLiving).getItemInUseDuration() > 0)
        {
          EnumAction enumaction = ((EntityPlayer)entityLiving).getItemInUse().getItemUseAction();
          if (enumaction == EnumAction.block) {
            this.model.heldItemRight = 3;
          } else if (enumaction == EnumAction.bow) {
            this.model.aimedBow = true;
          }
        }
      }
    }
    return this.model;
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return ElementalMagic.modid + ":textures/armor/VoidMatrixArmor.png";
  }
  
  public EnumRarity getRarity(ItemStack itemstack)
  {
    return EnumRarity.epic;
  }
  
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
  {
	list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + getVisDiscount(stack, player, null) + "%");
    super.addInformation(stack, player, list, par4);
  }
  
  public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
  {
    return par2ItemStack.isItemEqual(ItemApi.getItem("itemResource", 16)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
  }

  @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	  if(player.getCurrentArmor(2) == null || player.getCurrentArmor(2).getItem() != ItemRegistry.voidMatrixChest)
	  {
		  return;
	  }
	  ItemStack stack = player.getCurrentArmor(2);
	  if(player.ticksExisted % 20 == 0 && player.isInWater() && getCoreType(stack) == ElemCoreType.WATER && VoidMatrixApi.getMatrix(MatrixType.STABLE, player) >= 2)
	  {
		  VoidMatrixApi.drawMatrix(MatrixType.STABLE, 2, player);
		  player.addPotionEffect(new PotionEffect(13, 21, 2));
	  }
	  if(player.ticksExisted % 20 == 0 && player.isBurning() && getCoreType(stack) == ElemCoreType.FIRE && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, player) >= 2)
	  {
		  VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, 2, player);
		  player.addPotionEffect(new PotionEffect(12, 21, 0));
	  }
	  if(player.ticksExisted % 200 == 0 && itemStack.getItemDamage() != 0 && getCoreType(stack) != ElemCoreType.NEUTRAL && VoidMatrixApi.getMatrix(VoidMatrixApi.getMatrixTypeFromCoreType(getCoreType(stack)), player) >= 2)
	  {
		  VoidMatrixApi.drawMatrix(VoidMatrixApi.getMatrixTypeFromCoreType(getCoreType(stack)), 2, player);
		  itemStack.setItemDamage(itemStack.getItemDamage() - 1);
		  
	  }
	}

  
  
  public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
  {
    int priority = 0;
    double ratio = this.damageReduceAmount / 25.0D;
    if(!(player instanceof EntityPlayer))
    {
    	return new ArmorProperties(0, 0, 10);
    }
    EntityPlayer p = (EntityPlayer) player;
    if(p.getCurrentArmor(2) != null && !(p.getCurrentArmor(2).getItem() instanceof VoidMatrixArmor))
    {
    	return new ArmorProperties(0, 0, 10);
    }
    ItemStack stack = p.getCurrentArmor(2);
    if(source.isProjectile())
    {
    	if(getCoreType(stack) == ElemCoreType.AIR && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, p) >= (int) Math.ceil(damage))
    	{
    		priority = 1;
    		ratio = 0.40D;
    		VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, (int) Math.ceil(damage), p);
    	}
    	else
    	{
	      priority = 1;
	      ratio = this.damageReduceAmount / 35.0D;
	    }
    }
    else if (source.isMagicDamage() == true)
    {
    	
    	if(getCoreType(stack) == ElemCoreType.AIR && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, p) >= (int) Math.ceil(damage) * 2)
    	{
    		priority = 1;
    		ratio = 0.40D;
    		VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, (int) Math.ceil(damage) * 2, p);
    	}
    	else
    	{
	      priority = 1;
	      ratio = this.damageReduceAmount / 35.0D;
	    }
    }
    else if ((source.isFireDamage() == true) || (source.isExplosion()))
    {
    	if(getCoreType(stack) == ElemCoreType.FIRE && VoidMatrixApi.getMatrix(MatrixType.UNSTABLE, p) >= Math.ceil(damage))
    	{
    		priority = 1;
    		ratio = 0.40D;
    		if(armor.getItem() == ItemRegistry.voidMatrixChest)
    		{
    			VoidMatrixApi.drawMatrix(MatrixType.UNSTABLE, (int) Math.ceil(damage), p);
    		}
    	}
      priority = 1;
      ratio = 0;
    } else if(source == DamageSource.fall)
    {
    	if(getCoreType(stack) == ElemCoreType.EARTH && VoidMatrixApi.getMatrix(MatrixType.STABLE, p) >= Math.ceil(damage))
    	{
    		priority = 1;
    		ratio = 0.40D;
    		VoidMatrixApi.drawMatrix(MatrixType.STABLE, (int) Math.ceil(damage), p);
    	}
    } else if (source.isUnblockable())
    {
      priority = 0;
      ratio = 0.0D;
    }
    
      double set = 0.875D;
      for (int a = 1; a < 4; a++)
      {
        ItemStack piece = ((EntityPlayer)player).inventory.armorInventory[a];
        if ((piece != null) && ((piece.getItem() instanceof VoidMatrixArmor)))
        {
          set += 0.125D;
        }
      }
      ratio *= set;
    return new ISpecialArmor.ArmorProperties(priority, ratio, armor.getMaxDamage() + 1 - armor.getItemDamage());
  }
  
  public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
  {
    return this.damageReduceAmount;
  }
  
  public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
  {
	  if(entity instanceof EntityPlayer)
	  {
		  EntityPlayer player = (EntityPlayer) entity;
		  if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == ItemRegistry.voidMatrixChest)
		  {
			  ItemStack chest = player.getCurrentArmor(2);
			  switch(getCoreType(chest))
			  {
				case AIR:
				{
					if(source.isMagicDamage() || source.isProjectile())
					{
						return;
					}
					break;
				}
				case EARTH:
				{
					if(source == DamageSource.fall)
					{
						return;
					}
					break;
				}
				case FIRE:
				{
					if(source.isFireDamage() || source.isExplosion())
					{
						return;
					}
					break;
				}
				case NEUTRAL:
				{
					break;
				}
				case WATER:
				{
					if(source == DamageSource.drown)
					{
						return;
					}
					break;
				}  
			  }
		  }
	  }
	  
	 stack.damageItem(damage, entity);
  }
  
  public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
  {
    return itemstack.getItem() == ItemRegistry.voidMatrixHelm;
  }
  
  public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
  {
	  return itemstack.getItem() == ItemRegistry.voidMatrixHelm;
  }

@Override
public int getWarp(ItemStack itemstack, EntityPlayer player) {
	return 2;
}

@Override
public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
	return 5;
}
}
