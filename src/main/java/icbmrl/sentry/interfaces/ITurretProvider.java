package icbmrl.sentry.interfaces;

import net.minecraft.inventory.IInventory;

/** Implement this on any object that hosts a turret.
 * 
 * @author Darkguardsman, Calclavia */
public interface ITurretProvider extends IVectorWorld
{
    /** Gets the sentry hosted by this container */
    public ITurret getTurret();

    public IInventory getInventory();    

    public void sendFireEventToClient(IVector3 target);
}
