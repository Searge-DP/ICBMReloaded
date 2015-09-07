package icbm.sentry.turret.auto;

import icbm.Reference;
import icbm.sentry.interfaces.ITurret;
import icbm.sentry.turret.ai.TurretAntiAirSelector;
import icbm.sentry.turret.block.TileTurret;
import icbm.sentry.weapon.types.WeaponConventional;

/** AA Turret, shoots down missiles and planes.
 * 
 * @author DarkGaurdsman */
public class TurretAntiAir extends TurretAuto
{
    public TurretAntiAir(TileTurret host)
    {
        super(host);
        weaponSystem = new WeaponConventional(this, 10);
        weaponSystem.soundEffect = Reference.PREFIX + "aagun";
        centerOffset.y = 0.75;
        selector = new TurretAntiAirSelector(this);
        setTrait(ITurret.SEARCH_RANGE_TRAIT, 200.0);
        setTrait(ITurret.MAX_HEALTH_TRAIT, 70.0);
        setTrait(ITurret.ROTATION_SPEED_WITH_TARGET_TRAIT, 20.0);
        setTrait(ITurret.AMMO_RELOAD_TIME_TRAIT, 5);
    }
}
