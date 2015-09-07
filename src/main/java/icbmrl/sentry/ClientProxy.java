package icbmrl.sentry;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

import icbmrl.core.common.prefab.EmptyRenderer;
import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.platform.TileTurretPlatform;
import icbmrl.sentry.platform.gui.GuiTurretPlatform;
import icbmrl.sentry.platform.gui.user.GuiUserAccess;
import icbmrl.sentry.render.RenderAATurret;
import icbmrl.sentry.render.RenderCrossBowTurret;
import icbmrl.sentry.render.RenderGunTurret;
import icbmrl.sentry.render.RenderLaserTurret;
import icbmrl.sentry.render.RenderRailgun;
import icbmrl.sentry.turret.EntityMountableDummy;
import icbmrl.sentry.turret.TurretRegistry;
import icbmrl.sentry.turret.auto.TurretAntiAir;
import icbmrl.sentry.turret.auto.TurretAutoBow;
import icbmrl.sentry.turret.auto.TurretGun;
import icbmrl.sentry.turret.auto.TurretLaser;
import icbmrl.sentry.turret.block.TileTurret;
import icbmrl.sentry.turret.mounted.MountedRailgun;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
    }

    @Override
    public void init()
    {
        super.init();

        /** TileEntities */
        RenderingRegistry.registerEntityRenderingHandler(EntityMountableDummy.class, new EmptyRenderer());

        // Sentry render registry TODO find a way to automate
        TurretRegistry.registerSentryRenderer(TurretAntiAir.class, new RenderAATurret());
        TurretRegistry.registerSentryRenderer(TurretGun.class, new RenderGunTurret());
        TurretRegistry.registerSentryRenderer(TurretLaser.class, new RenderLaserTurret());
        TurretRegistry.registerSentryRenderer(MountedRailgun.class, new RenderRailgun());
        TurretRegistry.registerSentryRenderer(TurretAutoBow.class, new RenderCrossBowTurret());

        GlobalItemRenderer.register(ICBMSentry.blockTurret.blockID, new ISimpleItemRenderer()
        {
            @Override
            public void renderInventoryItem(ItemStack itemStack)
            {
                Class<? extends ITurret> sentry = TurretRegistry.getSentry(NBTUtility.getNBTTagCompound(itemStack).getString("unlocalizedName"));
                if (sentry != null)
                    TurretRegistry.getRenderFor(sentry).renderInventoryItem(itemStack);
            }
        });
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (tile instanceof TileTurretPlatform)
        {
            if (ID == 0)
                return new GuiTurretPlatform(player, (TileTurretPlatform) tile);
        }
        if (tile instanceof TileTurret)
        {
            if (ID == 1)
                return new GuiUserAccess(player, tile);
        }
        return null;
    }

    public void renderBeam(World world, IVector3 position, IVector3 hit, Color color, int age)
    {
        renderBeam(world, position, hit, color.getRed(), color.getGreen(), color.getBlue(), age);
    }

    @Override
    public void renderBeam(World world, IVector3 position, IVector3 target, float red, float green, float blue, int age)
    {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FxLaser(world, position, target, red, green, blue, age));
    }
}
