package icbm.sentry.interfaces;

import icbm.sentry.turret.EntityMountableDummy;
import universalelectricity.api.vector.IVector3;
import net.minecraft.entity.Entity;

/** @author DarkGuardsman */
public interface IMountedTurret
{
    /** Can the entity mount the sentry */
    public boolean canMount(Entity entity);

    public EntityMountableDummy getFakeEntity();
}
