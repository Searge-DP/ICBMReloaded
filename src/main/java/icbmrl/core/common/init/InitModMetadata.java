package icbmrl.core.common.init;

import java.util.Arrays;

import cpw.mods.fml.common.ModMetadata;

import icbmrl.core.common.lib.ModInfo;

/**
 * Settings class for various configuration settings.
 * 
 * @author Calclavia
 */
public class InitModMetadata
{
	public static void setModMetadata(String id, String name, ModMetadata metadata)
	{
		metadata.modId = id;
		metadata.name = name;
		metadata.description = "ICBM is a Minecraft Mod that introduces intercontinental ballistic missiles to Minecraft. But the fun doesn't end there! This mod also features many different explosives, missiles and machines classified in three different tiers. If strategic warfare, carefully coordinated airstrikes, messing with matter and general destruction are up your alley, then this mod is for you!";
		metadata.url = "";
		metadata.logoFile = "";
		metadata.version = ModInfo.VERSION;
		metadata.authorList = Arrays.asList(new String[] { "Calclavia", "warlordjones" });
		metadata.credits = "Please visit the website.";
		metadata.autogenerated = false;
	}

}
