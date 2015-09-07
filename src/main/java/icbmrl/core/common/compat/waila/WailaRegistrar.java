package icbmrl.core.common.compat.waila;

import icbmrl.core.common.blocks.TileCamouflage;
import icbmrl.core.common.init.InitModMetadata;
import icbmrl.sentry.turret.block.TileTurret;

/**
 * @author tgame14
 * @since 12/04/14
 */
public class WailaRegistrar
{
	public static final String wailaCamoBlockHide = "wailaCamoBlockWailaHide";
	public static void wailaCallBack (IWailaRegistrar registrar)
	{
		registrar.registerBodyProvider(new WailaTurretDataProvider(), TileTurret.class);
		registrar.registerStackProvider(new WailaCamoDataProvider(), TileCamouflage.class);
		registrar.addConfig(InitModMetadata.DOMAIN, wailaCamoBlockHide, "Hide Camo block waila tooltip?");
	}
}
