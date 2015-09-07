package icbmrl.sentry.turret;

import icbmrl.sentry.turret.auto.TurretAntiAir;
import icbmrl.sentry.turret.auto.TurretAutoBow;
import icbmrl.sentry.turret.auto.TurretGun;
import icbmrl.sentry.turret.auto.TurretLaser;
import icbmrl.sentry.turret.mounted.MountedRailgun;

/** Enum of all sentries created by ICBM */
public enum TurretType
{
    GUN_TURRET(TurretGun.class),
    LASER_TURRET(TurretLaser.class),
    ANTI_AIRCRAFT_TURRET(TurretAntiAir.class),
    RAILGUN(MountedRailgun.class),
    AUTO_BOW(TurretAutoBow.class);

    private final Class<? extends Turret> clazz;
    private final String id;

    private TurretType(Class<? extends Turret> clazz)
    {
        this.clazz = clazz;
        this.id = LanguageUtility.toCamelCase(name());
    }

    public final String getId()
    {
        return this.id;
    }

    public static TurretType get(int id)
    {
        if (id >= 0 && id < TurretType.values().length)
            return TurretType.values()[id];

        return null;
    }

    /** get the SentryType for the SaveManager registered ID */
    public static TurretType get(String id)
    {
        for (TurretType type : TurretType.values())
            if (id.endsWith(type.id))
                return type;

        return null;
    }

    /** Called to load sentry types into sentry registry */
    public static void load()
    {
        for (TurretType type : TurretType.values())
        {
            if (type.id != null && type.clazz != null)
            {
                TurretRegistry.registerSentry(type.id, type.clazz);
            }
        }
    }
}
