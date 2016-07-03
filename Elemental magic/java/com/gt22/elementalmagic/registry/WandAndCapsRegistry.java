package com.gt22.elementalmagic.registry;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

import com.gt22.elementalmagic.core.Core;
import com.gt22.elementalmagic.wands.ElementalRodOnUpd;

public class WandAndCapsRegistry
{
	public static WandCap elementalCap;
	public static WandRod elementalRod;
	public static void init()
	{
		ArrayList<Aspect> specasp = new ArrayList<Aspect>();
		specasp.add(Aspect.ORDER);
		specasp.add(Aspect.ENTROPY);
		elementalRod = new WandRod("ELEMWAND", 75, new ItemStack(ItemRegistry.elemRod, 1, 0), 20, new ElementalRodOnUpd(), new ResourceLocation(Core.modid, "other/elem_rod.png"));
		elementalCap = new WandCap("ELEMCAP", 0.8F, specasp, 1.2F, new ItemStack(ItemRegistry.elemCap), 3);
		elementalCap.setTexture(new ResourceLocation(Core.modid, "other/elemCap.png"));
	}
}
