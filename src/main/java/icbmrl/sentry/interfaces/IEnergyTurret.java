package icbmrl.sentry.interfaces;

/** Applied to turret that need energy to function
 * 
 * @author DarkGuardsman */
public interface IEnergyTurret extends IEnergyContainer, ITurret
{
    /** Energy to function per tick */
    public long getRunningCost();
}
