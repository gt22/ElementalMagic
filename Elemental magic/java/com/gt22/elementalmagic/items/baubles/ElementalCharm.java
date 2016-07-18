package com.gt22.elementalmagic.items.baubles;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import baubles.api.BaubleType;
import com.gt22.elementalmagic.core.Core;
import com.gt22.gt22core.integration.baubles.item.BaubleBase;

/**
 * meta - element<br>
 * 0 - aer<br>
 * 1 - aqua<br>
 * 2 - terra<br>
 * 3 - ignis<br>
 * 4 - neutral
 */
public class ElementalCharm extends BaubleBase implements IVisDiscountGear
{
	public IIcon[] icons = new IIcon[5];

	public ElementalCharm()
	{
		super("ElementalCharm", Core.instance, BaubleType.AMULET);
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player)
	{
		EntityPlayer p = (EntityPlayer) player;
		switch (stack.getItemDamage())
		{
			case (0):
			{
				if (player.ticksExisted % 5 != 0 && player.isSneaking() && ThaumcraftApiHelper.consumeVisFromInventory(p, new AspectList().add(Aspect.AIR, 100)))
				{
					int duration = 10;
					for (Object o : player.getActivePotionEffects())
					{
						PotionEffect pot = (PotionEffect) o;
						if (pot.getPotionID() == 8)
						{
							duration += pot.getDuration();
							break;
						}
					}
					int amp = duration / 800;
					amp = Math.max(0, amp);
					amp = Math.min(10, amp);
					player.addPotionEffect(new PotionEffect(8, duration, amp));
				}
				return;
			}
			case (1):
			{
				if (player.isInWater() && player.ticksExisted % 20 == 0 && ThaumcraftApiHelper.consumeVisFromInventory(p, new AspectList().add(Aspect.WATER, 100)))
				{
					player.addPotionEffect(new PotionEffect(13, 20, 0));
				}
				return;
			}
			case (2):
			{
				
				return;
			}
			case (3):
			{
				if (player.ticksExisted % 20 != 0 && player.isBurning() && ThaumcraftApiHelper.consumeVisFromInventory(p, new AspectList().add(Aspect.FIRE, 100)))
				{
					player.extinguish();
				}
				return;
			}
		}
	}

	@Override
	public void registerIcons(IIconRegister reg)
	{
		for (int i = 0; i < 5; i++)
		{
			reg.registerIcon(Core.modid + ":ElementalCharm-" + i);
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return icons[stack.getItemDamage()];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 5; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String postfix;
		switch (stack.getItemDamage())
		{
			case (0):
			{
				postfix = "Aer";
				break;
			}
			case (1):
			{
				postfix = "Aqua";
				break;
			}
			case (2):
			{
				postfix = "Terra";
				break;
			}
			case (3):
			{
				postfix = "Ignis";
				break;
			}
			case (4):
			{
				postfix = "Neutral";
				break;
			}
			default:
			{
				postfix = "";
				break;
			}
		}
		return "item.ElementalCharm" + postfix;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_)
	{
		if (stack.getItemDamage() == 4)
		{
			info.add(EnumChatFormatting.DARK_PURPLE + "Vis discount: " + getVisDiscount(stack, player, Aspect.AIR) + "%");
		}
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
	{
		int discount = 1;
		if (stack.getItemDamage() != 4)
		{
			return 0;
		}
		for (int i = 1; i < 5; i++)
		{
			if (player.getEquipmentInSlot(i) != null && player.getEquipmentInSlot(i).getItem() instanceof IVisDiscountGear && ((IVisDiscountGear) player.getEquipmentInSlot(i).getItem()).getVisDiscount(stack, player, aspect) != 0)
			{
				discount++;
			}
		}
		return discount;
	}
}
