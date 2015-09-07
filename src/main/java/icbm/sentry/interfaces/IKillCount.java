package icbm.sentry.interfaces;

import net.minecraft.entity.Entity;

/** Used to track kills by an object
 * 
 * @author Darkguardsman */
public interface IKillCount
{
    /** Gets total kill count */
    public int getKillCount();

    /** Gets count of a set type */
    public int getKillCount(String type);

    /** Called when the object kills something. Must be triggered by an event */
    public void onKillOfEntity(Entity entity);
}
