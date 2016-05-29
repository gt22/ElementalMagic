package com.gt22.elementalmagic.registry;




import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;

import com.gt22.elementalmagic.core.ElementalMagic;
import com.gt22.elementalmagic.items.CraftItem;
import com.gt22.elementalmagic.items.CurrentFocus;
import com.gt22.elementalmagic.items.ElementalCap;
import com.gt22.elementalmagic.items.ElementalRod;
import com.gt22.elementalmagic.items.EmberFocus;
import com.gt22.elementalmagic.items.NatureFocus;
import com.gt22.elementalmagic.items.VoidAspecterFoci;
import com.gt22.elementalmagic.items.VoidCheckerFoci;
import com.gt22.elementalmagic.items.VoidTrasformerFoci;
import com.gt22.elementalmagic.items.WindFocus;
import com.gt22.elementalmagic.wands.ElementalRodOnUpd;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item windFocus;
	public static Item emberFocus;
	public static Item currentFocus;
	public static Item natureFocus;
	public static CraftItem craftItem;
	public static Item elemRod;
	public static WandRod elementalRod;
	public static Item elemCap;
	public static WandCap elementalCap;
	public static Item voidTransformerFocus;
	public static Item voidCheckerFocus;
	public static Item voidAspecterFocus;
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static final void init()
	{
		register(windFocus = new WindFocus("WindFocus"));
		register(emberFocus = new EmberFocus("EmberFocus"));
		register(currentFocus = new CurrentFocus("CurrentFocus"));
		register(natureFocus = new NatureFocus("NatureFocus"));
		craftItem = new CraftItem();
		craftItem.addCraftItem("InertFocus");
		register(craftItem);
		elemRod = new ElementalRod("ElementalRod");
		elementalRod = new WandRod("ELEM", 75, new ItemStack(elemRod, 1, 0), 20, new ElementalRodOnUpd(), new ResourceLocation(ElementalMagic.modid, "other/elem_rod.png"));
		register(elemRod);
		elemCap = new ElementalCap("ElementalCap");
		ArrayList<Aspect> specasp = new ArrayList<Aspect>();
		specasp.add(Aspect.ORDER);
		specasp.add(Aspect.ENTROPY);
		elementalCap = new WandCap("ELEMCAP", 0.8F, specasp, 1.2F, new ItemStack(elemCap), 3);
		elementalCap.setTexture(new ResourceLocation("elemmagic", "other/elemCap.png"));
		register(elemCap);
		register(voidTransformerFocus = new VoidTrasformerFoci("VoidTransformerFocus"));
		register(voidCheckerFocus = new VoidCheckerFoci("VoidCheckerFocus"));
		register(voidAspecterFocus = new VoidAspecterFoci("VoidAspecterFocus"));
	}
	
}
