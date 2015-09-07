package icbmrl.core.common.items;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.core.common.prefab.item.ItemICBMBase;

public class ItemPoisonPowder extends ItemICBMBase
{
    public ItemPoisonPowder(int id)
    {
        super(id, "poisonPowder");
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        super.registerIcons(iconRegister);
        
        // Icon for base item.
        this.itemIcon = iconRegister.registerIcon(ModInfo.PREFIX + "poisonPowder");
    }
}
