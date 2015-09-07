package icbmrl.core.common.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.BaseCreativeTab;
import icbmrl.core.common.ICBMCore;

public class TabICBM extends BaseCreativeTab
{
	public static final TabICBM INSTANCE = new TabICBM();

	public static ItemStack itemStack;

	public TabICBM()
	{
		super("ICBM");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(ICBMCore.blockConcrete);
	}
}
