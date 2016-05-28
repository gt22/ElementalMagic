package com.gt22.elementalmagic.registry;

import com.gt22.elementalmagic.items.CurrentFocus;
import com.gt22.elementalmagic.items.EmberFocus;
import com.gt22.elementalmagic.items.NatureFocus;
import com.gt22.elementalmagic.items.WindFocus;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item thunderFocus;
	public static Item emberFocus;
	public static Item currentFocus;
	public static Item natureFocus;
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static final void init()
	{
		register(thunderFocus = new WindFocus("WindFocus"));
		register(emberFocus = new EmberFocus("EmberFocus"));
		register(currentFocus = new CurrentFocus("CurrentFocus"));
		register(natureFocus = new NatureFocus("NatureFocus"));
	}
	
}
