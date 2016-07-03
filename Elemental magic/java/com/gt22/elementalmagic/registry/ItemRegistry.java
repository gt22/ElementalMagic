package com.gt22.elementalmagic.registry;




import net.minecraft.item.Item;

import com.gt22.elementalmagic.items.BoundMatrix;
import com.gt22.elementalmagic.items.CraftItem;
import com.gt22.elementalmagic.items.ElementalCap;
import com.gt22.elementalmagic.items.ElementalRod;
import com.gt22.elementalmagic.items.foci.CurrentFocus;
import com.gt22.elementalmagic.items.foci.EmberFocus;
import com.gt22.elementalmagic.items.foci.NatureFocus;
import com.gt22.elementalmagic.items.foci.WindFocus;
import com.gt22.elementalmagic.items.tools.ElemAxe;
import com.gt22.elementalmagic.items.tools.ElemHoe;
import com.gt22.elementalmagic.items.tools.ElemPick;
import com.gt22.elementalmagic.items.tools.ElemShovel;
import com.gt22.elementalmagic.items.tools.ElemSword;

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
		register(windFocus = new WindFocus());
		register(emberFocus = new EmberFocus());
		register(currentFocus = new CurrentFocus());
		register(natureFocus = new NatureFocus());
		craftItem = new CraftItem();
		craftItem.addCraftItem("InertFocus");
		craftItem.addCraftItem("ElementalIngot");
		craftItem.addCraftItem("ElementalShard");
		register(craftItem);
		register(elemRod = new ElementalRod());
		register(elemCap = new ElementalCap());
		register(elemAxe = new ElemAxe("ElementalAxe"));
		register(elemPick = new ElemPick("ElementalPick"));
		register(elemShovel = new ElemShovel("ElementalShovel"));
		register(elemSword = new ElemSword("ElementalSword"));
		register(elemHoe = new ElemHoe("ElementalHoe"));
		register(boundMatrix = new BoundMatrix());
	}
	
}
