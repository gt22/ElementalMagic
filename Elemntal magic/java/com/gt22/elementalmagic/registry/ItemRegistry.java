package com.gt22.elementalmagic.registry;




import net.minecraft.item.Item;

import com.gt22.elementalmagic.items.BoundMatrix;
import com.gt22.elementalmagic.items.CraftItem;
import com.gt22.elementalmagic.items.CurrentFocus;
import com.gt22.elementalmagic.items.ElemAxe;
import com.gt22.elementalmagic.items.ElemHoe;
import com.gt22.elementalmagic.items.ElemPick;
import com.gt22.elementalmagic.items.ElemShovel;
import com.gt22.elementalmagic.items.ElemSword;
import com.gt22.elementalmagic.items.ElementalCap;
import com.gt22.elementalmagic.items.ElementalRod;
import com.gt22.elementalmagic.items.EmberFocus;
import com.gt22.elementalmagic.items.NatureFocus;
import com.gt22.elementalmagic.items.WindFocus;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item windFocus;
	public static Item emberFocus;
	public static Item currentFocus;
	public static Item natureFocus;
	public static CraftItem craftItem;
	public static Item elemRod;
	public static Item elemCap;
	public static Item elemPick;
	public static Item elemSword;
	public static Item elemAxe;
	public static Item elemShovel;
	public static Item elemHoe;
	public static Item boundMatrix;
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
		craftItem.addCraftItem("ElementalIngot");
		craftItem.addCraftItem("ElementalShard");
		register(craftItem);
		register(elemRod = new ElementalRod("ElementalRod"));
		register(elemCap = new ElementalCap("ElementalCap"));
		register(elemAxe = new ElemAxe("ElementalAxe"));
		register(elemPick = new ElemPick("ElementalPick"));
		register(elemShovel = new ElemShovel("ElementalShovel"));
		register(elemSword = new ElemSword("ElementalSword"));
		register(elemHoe = new ElemHoe("ElementalHoe"));
		register(boundMatrix = new BoundMatrix("BoundMatrix"));
	}
	
}
