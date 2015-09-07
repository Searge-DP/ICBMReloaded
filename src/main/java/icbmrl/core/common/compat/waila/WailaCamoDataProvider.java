package icbmrl.core.common.compat.waila;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import icbmrl.core.common.blocks.TileCamouflage;

/**
 * @author tgame14
 * @since 24/04/14
 */
public class WailaCamoDataProvider implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileCamouflage tile = (TileCamouflage) accessor.getTileEntity();
		return tile.getMimicBlockID() != 0 && config.getConfig(WailaRegistrar.wailaCamoBlockHide) ? new ItemStack(Block.blocksList[tile.getMimicBlockID()], 0, tile.getMimicBlockMeta()) : null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}
}
