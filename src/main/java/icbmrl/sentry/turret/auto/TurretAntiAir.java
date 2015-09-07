package icbmrl.sentry.turret.auto;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.turret.ai.TurretAntiAirSelector;
import icbmrl.sentry.turret.block.TileTurret;
import icbmrl.sentry.weapon.types.WeaponConventional;

/** AA Turret, shoots down missiles and planes.
 * 
 * @author DarkGaurdsman */
public class TurretAntiAir extends TurretAuto
{
    public TurretAntiAir(TileTurret host)
    {
        super(host);
        weaponSystem = new WeaponConventional(this, 10);
        weaponSystem.soundEffect = ModInfo.PREFIX + "aagun";
        centerOffset.y = 0.75;
        selector = new TurretAntiAirSelector(this);
        setTrait(ITurret.SEARCH_RANGE_TRAIT, 200.0);
        setTrait(ITurret.MAX_HEALTH_TRAIT, 70.0);
        setTrait(ITurret.ROTATION_SPEED_WITH_TARGET_TRAIT, 20.0);
        setTrait(ITurret.AMMO_RELOAD_TIME_TRAIT, 5);
    }
}
