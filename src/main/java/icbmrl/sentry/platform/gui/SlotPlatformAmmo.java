package icbmrl.sentry.platform.gui;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.util.ForgeDirection;

import icbmrl.sentry.interfaces.IWeaponProvider;
import icbmrl.sentry.platform.TileTurretPlatform;
import icbmrl.sentry.turret.block.TileTurret;

/** Slot that only accept upgrades for sentries
 * 
 * @author DarkGuardsman */
public class SlotPlatformAmmo extends Slot
{
    private TileTurretPlatform platform;

    public SlotPlatformAmmo(TileTurretPlatform tile, int par2, int par3, int par4)
    {
        super(tile, par2, par3, par4);
        this.platform = tile;
    }

    @Override
    public boolean isItemValid(ItemStack compareStack)
    {
        if (compareStack != null)
        {
            if(CompatibilityModule.isHandler(compareStack.getItem()))
            {
                return true;
            }
            if (platform != null)
            {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
                {
                    TileTurret tileTurret = platform.getTurret(direction);
                    if (tileTurret != null && tileTurret.getTurret() instanceof IWeaponProvider)
                    {
                        if (((IWeaponProvider) tileTurret.getTurret()).getWeaponSystem() != null)
                        {
                            return ((IWeaponProvider) tileTurret.getTurret()).getWeaponSystem().isAmmo(compareStack);
                        }
                    }
                }
            }
        }
        return false;
    }
}
