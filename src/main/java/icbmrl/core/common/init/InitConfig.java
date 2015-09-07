package icbmrl.core.common.init;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.config.Configuration;

public class InitConfig
{
	public static boolean USE_FUEL;

	public static boolean LOAD_CHUNKS;

	public static int MAX_MISSILE_DISTANCE;

	public static int ANTIMATTER_SIZE;

	public static boolean DESTROY_BEDROCK;

	public static int MAX_ROCKETLAUNCHER_TIER;

	public static void initConfig(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		USE_FUEL = config.get(Configuration.CATEGORY_GENERAL, "Use Fuel", true).getBoolean(true);

		LOAD_CHUNKS = config.get(Configuration.CATEGORY_GENERAL, "Allow Chunk Loading", true).getBoolean(true);

		MAX_MISSILE_DISTANCE = config.get(Configuration.CATEGORY_GENERAL, "Max Missile Distance", 10000).getInt();

		ANTIMATTER_SIZE = config.get(Configuration.CATEGORY_GENERAL, "Antimatter Explosion Size", 55).getInt();

		DESTROY_BEDROCK = config.get(Configuration.CATEGORY_GENERAL, "Antimatter Destroy Bedrock", true).getBoolean(true);

		MAX_ROCKETLAUNCHER_TIER = config.get(Configuration.CATEGORY_GENERAL, "Limits the max missile tier for rocket launcher item", 2).getInt();
		config.save();
	}
}
