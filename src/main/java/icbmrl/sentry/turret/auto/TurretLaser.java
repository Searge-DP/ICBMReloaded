package icbmrl.sentry.turret.auto;

import net.minecraft.util.MathHelper;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.turret.block.TileTurret;
import icbmrl.sentry.weapon.types.WeaponTwinLaser;

/** @author Darkguardsman */
public class TurretLaser extends TurretAuto
{
    /** Laser turret spins its barrels every shot. */
    public float barrelRotation;
    public float barrelRotationVelocity;

	@Config(category = "Turrets", comment = "this is calculated in half hearts, so whatever you put / 2 = the amount of heart damage")
	private static float laserTurretDamage = 4;

    public TurretLaser(TileTurret host)
    {
        super(host);
        weaponSystem = new WeaponTwinLaser(this, laserTurretDamage, 100000);
        weaponSystem.soundEffect = ModInfo.PREFIX + "lasershot";
        barrelLength = 1.2f;
        setTrait(ITurret.ENERGY_STORAGE_TRAIT, 1000000L);
        setTrait(ITurret.AMMO_RELOAD_TIME_TRAIT, 50);
        setTrait(ITurret.SEARCH_RANGE_TRAIT, 70.0);
    }

    @Override
    public void update()
    {
        super.update();

        if (this.world().isRemote)
        {
            this.barrelRotation = MathHelper.wrapAngleTo180_float(this.barrelRotation + this.barrelRotationVelocity);
            this.barrelRotationVelocity = Math.max(this.barrelRotationVelocity - 0.1f, 0);
        }
    }
}
