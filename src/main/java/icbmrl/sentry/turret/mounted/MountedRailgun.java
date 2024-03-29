package icbmrl.sentry.turret.mounted;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.turret.block.TileTurret;
import icbmrl.sentry.weapon.types.WeaponRailgun;

/** Railgun
 * 
 * @author Calclavia */
public class MountedRailgun extends TurretMounted implements IMultiBlock
{
    private int powerUpTicks = -1;

    public MountedRailgun(TileTurret turretProvider)
    {
        super(turretProvider);
        riderOffset = new Vector3(0, 0.2, 0);
        weaponSystem = new WeaponRailgun(this);
        setTrait(ITurret.ENERGY_STORAGE_TRAIT, 10000000L);
        setTrait(ITurret.AMMO_RELOAD_TIME_TRAIT, 100);
    }

    @Override
    public void update()
    {
        super.update();

        if (!world().isRemote && powerUpTicks >= 0)
        {
            powerUpTicks++;

            if (powerUpTicks++ >= 70)
                fire();
        }
    }

    @Override
    public void fire()
    {
        super.fire();
        powerUpTicks = -1;
    }

    @Override
    public void onRedstone()
    {
        if (powerUpTicks == -1)
        {
            powerUpTicks = 0;
            world().playSoundEffect(x(), y(), z(), ModInfo.PREFIX + "railgun", 5F, 0.9f + world().rand.nextFloat() * 0.2f);
        }
    }

    @Override
    public Vector3[] getMultiBlockVectors()
    {
        return new Vector3[] { new Vector3(0, 1, 0) };
    }
}
