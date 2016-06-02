package com.gt22.elementalmagic.config;

import net.minecraftforge.common.config.Configuration;

public class CfgValues {
	public static int reverseID;
	public static int seedID;
	public static int grassID;
	public static int podzolID;
	public static int mushroomID;
	public static int lavaID;
	public static boolean doNotReverseThePerl;
	public static void init(Configuration cfg)
	{
		reverseID = cfg.getInt("reverse", "fociUpgrades", 45, 9, 9999, "Reverse upgradeID.");
		seedID = cfg.getInt("seed", "fociUpgrades", 46, 9, 9999, "Seed upgradeID.");
		grassID = cfg.getInt("grass", "fociUpgrades", 47, 9, 9999, "Grass upgradeID.");
		podzolID = cfg.getInt("podzol", "fociUpgrades", 48, 9, 9999, "Podzol upgradeID.");
		mushroomID = cfg.getInt("mycelium", "fociUpgrades", 49, 9, 9999, "Mycelium upgradeID.");
		lavaID = cfg.getInt("lava", "fociUpgrades", 50, 9, 9999, "Lava upgradeID.");
		doNotReverseThePerl = cfg.getBoolean("dnrtp", "events", true, "Enable Do not reverse the perl event");
	}
}
