package icbmrl.core.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.MinecraftForge;

import icbmrl.core.common.CommonProxy;
import icbmrl.core.common.blocks.TileProximityDetector;
import icbmrl.core.common.entity.EntityFlyingBlock;
import icbmrl.core.common.entity.EntityFragments;
import icbmrl.core.common.entity.RenderEntityBlock;
import icbmrl.core.common.entity.RenderShrapnel;
import icbmrl.core.common.gui.GuiFrequency;
import icbmrl.core.common.gui.GuiProximityDetector;
import icbmrl.core.common.lib.SoundHandler;
import icbmrl.core.common.tiles.GuiBox;
import icbmrl.core.common.tiles.TileBox;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();
		MinecraftForge.EVENT_BUS.register(SoundHandler.INSTANCE);
	}

	@Override
	public void init()
	{
		super.init();
		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBlock.class, new RenderEntityBlock());
		RenderingRegistry.registerEntityRenderingHandler(EntityFragments.class, new RenderShrapnel());
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (tileEntity instanceof TileBox)
		{
			return new GuiBox(entityPlayer, (TileBox) tileEntity);
		}
		else if (tileEntity instanceof TileProximityDetector)
		{
			return new GuiProximityDetector((TileProximityDetector) tileEntity);
		}
		else if (entityPlayer.inventory.getCurrentItem() != null && entityPlayer.inventory.getCurrentItem().getItem() instanceof IItemFrequency)
		{
			return new GuiFrequency(entityPlayer, entityPlayer.inventory.getCurrentItem());
		}

		return null;
	}
}
