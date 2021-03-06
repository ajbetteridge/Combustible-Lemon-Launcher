package ibuilder99.mods.cll.util;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigLoader {
	
	public static int LemonID;
	public static int LemonExplosiveID;
	public static int LemonLauncherID;
	
	public static int LemonLeavesID;
	public static int LemonLeavesHarvestedID;
	public static int LemonSapling;
	
	public static boolean useKeyToFire;
	
	public static void loadConfig(File cfg){
		Configuration config = new Configuration(cfg);
		config.load();
		
		LemonID = config.get(Configuration.CATEGORY_ITEM, "Lemon", 3000).getInt();
		LemonExplosiveID = config.get(Configuration.CATEGORY_ITEM, "LemonExplosive", 3001).getInt();
		LemonLauncherID = config.get(Configuration.CATEGORY_ITEM, "LemonLauncher", 3002).getInt();
		
		LemonLeavesID = config.get(Configuration.CATEGORY_BLOCK, "LemonLeaves", 3003).getInt();
		LemonLeavesHarvestedID = config.get(Configuration.CATEGORY_BLOCK, "LemonLeavesHarvested", 3004).getInt();
		LemonSapling = config.get(Configuration.CATEGORY_BLOCK, "LemonSapling", 3005).getInt();
		useKeyToFire = config.get("general", "UseKeyToFire", false).getBoolean(false);
		
		config.save();
	}
}
