package icbmrl.sentry.turret.auto;

import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.turret.block.TileTurret;
import icbmrl.sentry.weapon.types.WeaponConventional;

/** @author DarkGuardsman */
public class TurretGun extends TurretAuto
{
    public TurretGun(TileTurret host)
    {
        super(host);
        weaponSystem = new WeaponConventional(this, 8);
        centerOffset.y = 0.3;
        barrelOffset.y = 0.3;
        barrelLength = 1f;
        setTrait(ITurret.AMMO_RELOAD_TIME_TRAIT, 40);
    }
}
