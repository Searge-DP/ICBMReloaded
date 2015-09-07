package icbmrl.core.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import icbmrl.core.common.CommonProxy;
import icbmrl.core.common.entity.EntityFlyingBlock;
import icbmrl.core.common.entity.EntityFragments;
import icbmrl.core.common.entity.RenderEntityBlock;
import icbmrl.core.common.entity.RenderShrapnel;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		super.init();
		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBlock.class, new RenderEntityBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityFragments.class, new RenderShrapnel());
	}
}
