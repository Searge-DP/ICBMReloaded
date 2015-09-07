package icbmrl.core.common.items;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.core.common.prefab.item.ItemICBMBase;

public class ItemComputer extends ItemICBMBase
{
    public ItemComputer(int id)
    {
        super(id, "hackingComputer");
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        super.registerIcons(iconRegister);
        
        // Icon for base item.
        this.itemIcon = iconRegister.registerIcon(ModInfo.PREFIX + "hackingComputer");
    }
}
