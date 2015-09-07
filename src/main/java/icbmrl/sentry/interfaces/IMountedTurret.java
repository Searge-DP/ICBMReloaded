package icbmrl.sentry.interfaces;

import net.minecraft.entity.Entity;

import icbmrl.sentry.turret.EntityMountableDummy;

/** @author DarkGuardsman */
public interface IMountedTurret
{
    /** Can the entity mount the sentry */
    public boolean canMount(Entity entity);

    public EntityMountableDummy getFakeEntity();
}
