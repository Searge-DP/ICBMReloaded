package icbmrl.sentry.turret.traits;

import icbmrl.sentry.interfaces.ITurret;

/** @author Darkguardsman */
public class SentryTraitDouble extends SentryTraitUpgrade<Double>
{
    public SentryTraitDouble(String name, double value)
    {
        super(name, value);
    }

    public SentryTraitDouble(String name, String upgrade, double value)
    {
        this(name, value);
    }

    @Override
    public void updateTrait(ITurret turret)
    {
        if (getUpgradeName() != null)
            setValue(getDefaultValue() + (getDefaultValue() * turret.getUpgradeEffect(getUpgradeName())));
    }
}
