package com.gt22.elementalmagic.registry;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Materials {
	public static ArmorMaterial armorMatVoidMatrixArmor = EnumHelper.addArmorMaterial("VOIDMATRIXARMOR", 33, new int[] { 4, 9, 7, 4 }, 30);
	public static ToolMaterial toolMatElem = EnumHelper.addToolMaterial("ELEM", 2, 1000, 7F, 3F, 25);
}
