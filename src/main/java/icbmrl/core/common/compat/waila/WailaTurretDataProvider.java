package icbmrl.core.common.compat.waila;

import java.util.List;

import net.minecraft.item.ItemStack;

/** @author tgame14
 * @since 12/04/14 */
public class WailaTurretDataProvider implements IWailaDataProvider
{
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        // TODO: Add data here
        IProfileContainer container = (IProfileContainer) accessor.getTileEntity();

        if (!container.canAccess(accessor.getPlayer().username))
        {
            currenttip.add(LanguageUtility.getLocal("info.turretdenied.waila").replaceAll("%u", accessor.getPlayer().username));
            return currenttip;
        }
        else
        {
            currenttip.add("User:  " + accessor.getPlayer().username);
            if (container.getAccessProfile() != null)
            {
                AccessUser access = container.getAccessProfile().getUserAccess(accessor.getPlayer().username);
                currenttip.add("Group: " + access.getGroup().getName());
            }
            else
            {
                currenttip.add("Error access profile is empty");
            }

        }

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }
}
