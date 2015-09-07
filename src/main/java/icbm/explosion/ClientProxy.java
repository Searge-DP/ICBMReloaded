package icbm.explosion;

import icbm.core.ICBMCore;
import icbm.core.SoundHandler;
import icbm.explosion.entities.EntityBombCart;
import icbm.explosion.entities.EntityExplosion;
import icbm.explosion.entities.EntityExplosive;
import icbm.explosion.entities.EntityGrenade;
import icbm.explosion.entities.EntityLightBeam;
import icbm.explosion.entities.EntityMissile;
import icbm.explosion.ex.missiles.MissilePlayerHandler;
import icbm.explosion.explosive.TileExplosive;
import icbm.explosion.fx.FXAntimatterPartical;
import icbm.explosion.gui.GuiCruiseLauncher;
import icbm.explosion.gui.GuiEMPTower;
import icbm.explosion.gui.GuiLauncherScreen;
import icbm.explosion.gui.GuiMissileCoordinator;
import icbm.explosion.gui.GuiMissileTable;
import icbm.explosion.gui.GuiRadarStation;
import icbm.explosion.machines.TileCruiseLauncher;
import icbm.explosion.machines.TileEMPTower;
import icbm.explosion.machines.TileMissileAssembler;
import icbm.explosion.machines.TileMissileCoordinator;
import icbm.explosion.machines.TileRadarStation;
import icbm.explosion.machines.launcher.TileLauncherBase;
import icbm.explosion.machines.launcher.TileLauncherFrame;
import icbm.explosion.machines.launcher.TileLauncherScreen;
import icbm.explosion.potion.PoisonFrostBite;
import icbm.explosion.render.entity.RenderEntityExplosive;
import icbm.explosion.render.entity.RenderExplosion;
import icbm.explosion.render.entity.RenderGrenade;
import icbm.explosion.render.entity.RenderLightBeam;
import icbm.explosion.render.entity.RenderMissile;
import icbm.explosion.render.item.RenderItemMissile;
import icbm.explosion.render.item.RenderRocketLauncher;
import icbm.explosion.render.tile.BlockRenderHandler;
import icbm.explosion.render.tile.RenderBombBlock;
import icbm.explosion.render.tile.RenderCruiseLauncher;
import icbm.explosion.render.tile.RenderEmpTower;
import icbm.explosion.render.tile.RenderLauncherBase;
import icbm.explosion.render.tile.RenderLauncherFrame;
import icbm.explosion.render.tile.RenderLauncherScreen;
import icbm.explosion.render.tile.RenderMissileAssembler;
import icbm.explosion.render.tile.RenderMissileCoordinator;
import icbm.explosion.render.tile.RenderRadarStation;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import resonant.lib.render.RenderUtility;
import resonant.lib.render.fx.FXElectricBolt;
import resonant.lib.render.fx.FXElectricBoltSpawner;
import resonant.lib.render.fx.FXEnderPortalPartical;
import resonant.lib.render.fx.FXShockWave;
import resonant.lib.render.fx.FXSmoke;
import universalelectricity.api.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private boolean disableReflectionFX = false;

    @Override
    public void preInit()
    {
        TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(SoundHandler.INSTANCE);
    }

    @Override
    public void init()
    {
        super.init();

        MinecraftForgeClient.registerItemRenderer(ICBMExplosion.itemRocketLauncher.itemID, new RenderRocketLauncher());
        MinecraftForgeClient.registerItemRenderer(ICBMExplosion.itemMissile.itemID, new RenderItemMissile());

        RenderingRegistry.registerBlockHandler(new RenderBombBlock());
        RenderingRegistry.registerBlockHandler(new BlockRenderHandler());

        RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderEntityExplosive());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile(0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosion.class, new RenderExplosion());
        RenderingRegistry.registerEntityRenderingHandler(EntityLightBeam.class, new RenderLightBeam());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
        RenderingRegistry.registerEntityRenderingHandler(EntityBombCart.class, new RenderMinecart());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCruiseLauncher.class, new RenderCruiseLauncher());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLauncherBase.class, new RenderLauncherBase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLauncherScreen.class, new RenderLauncherScreen());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLauncherFrame.class, new RenderLauncherFrame());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRadarStation.class, new RenderRadarStation());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEMPTower.class, new RenderEmpTower());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMissileCoordinator.class, new RenderMissileCoordinator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileExplosive.class, new RenderBombBlock());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMissileAssembler.class, new RenderMissileAssembler());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileCruiseLauncher)
        {
            return new GuiCruiseLauncher(entityPlayer.inventory, (TileCruiseLauncher) tileEntity);
        }
        else if (tileEntity instanceof TileLauncherScreen)
        {
            return new GuiLauncherScreen(((TileLauncherScreen) tileEntity));
        }
        else if (tileEntity instanceof TileRadarStation)
        {
            return new GuiRadarStation(((TileRadarStation) tileEntity));
        }
        else if (tileEntity instanceof TileEMPTower)
        {
            return new GuiEMPTower((TileEMPTower) tileEntity);
        }
        else if (tileEntity instanceof TileMissileCoordinator)
        {
            return new GuiMissileCoordinator(entityPlayer.inventory, (TileMissileCoordinator) tileEntity);
        }
        else if (tileEntity instanceof TileMissileAssembler)
        {
            return new GuiMissileTable(entityPlayer.inventory, ((TileMissileAssembler) tileEntity));
        }

        return null;
    }

    @Override
    public boolean isGaoQing()
    {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    @Override
    public int getParticleSetting()
    {
        return Minecraft.getMinecraft().gameSettings.particleSetting;
    }

    @Override
    public void spawnParticle(String name, World world, Vector3 position, double motionX, double motionY, double motionZ, float red, float green, float blue, float scale, double distance)
    {
        EntityFX fx = null;

        if (name.equals("smoke"))
        {
            fx = new FXSmoke(world, position, red, green, blue, scale, distance);
        }
        else if (name.equals("missile_smoke"))
        {
            fx = (new FXSmoke(world, position, red, green, blue, scale, distance)).setAge(100);
        }
        else if (name.equals("portal"))
        {
            fx = new FXEnderPortalPartical(world, position, red, green, blue, scale, distance);
        }
        else if (name.equals("antimatter"))
        {
            fx = new FXAntimatterPartical(world, position, red, green, blue, scale, distance);
        }
        else if (name.equals("digging"))
        {
            fx = new EntityDiggingFX(world, position.x, position.y, position.z, motionX, motionY, motionZ, Block.blocksList[(int) red], 0, (int) green);
            fx.multipleParticleScaleBy(blue);
        }
        else if (name.equals("shockwave"))
        {
            fx = new FXShockWave(world, position, red, green, blue, scale, distance);
        }

        if (fx != null)
        {
            fx.motionX = motionX;
            fx.motionY = motionY;
            fx.motionZ = motionZ;
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
        }
    }

    @Override
    public void spawnShock(World world, Vector3 startVec, Vector3 targetVec)
    {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXElectricBolt(world, startVec, targetVec, 0));
    }

    @Override
    public void spawnShock(World world, Vector3 startVec, Vector3 targetVec, int duration)
    {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXElectricBoltSpawner(world, startVec, targetVec, 0, duration));
    }

    @Override
    public IUpdatePlayerListBox getDaoDanShengYin(EntityMissile eDaoDan)
    {
        return new MissilePlayerHandler(Minecraft.getMinecraft().sndManager, eDaoDan, Minecraft.getMinecraft().thePlayer);
    }

    @Override
    public List<Entity> getEntityFXs()
    {
        if (!this.disableReflectionFX)
        {
            try
            {
                EffectRenderer renderer = Minecraft.getMinecraft().effectRenderer;
                List[] fxLayers = (List[]) ReflectionHelper.getPrivateValue(EffectRenderer.class, renderer, 2);

                return fxLayers[0];
            }
            catch (Exception e)
            {
                ICBMCore.LOGGER.severe("Failed to use relfection on entity effects.");
                e.printStackTrace();
                this.disableReflectionFX = true;
            }
        }
        return null;
    }

    // TODO: Work on this!
    // @ForgeSubscribe
    public void renderingLivingEvent(Pre evt)
    {
        if (evt.entity instanceof EntityLivingBase)
        {
            if (evt.entity.getActivePotionEffect(PoisonFrostBite.INSTANCE) != null)
            {
                try
                {
                    ModelBase modelBase = (ModelBase) ReflectionHelper.getPrivateValue(RendererLivingEntity.class, evt.renderer, 2);

                    if (modelBase != null)
                    {
                        if (evt.entity.isInvisible())
                        {
                            GL11.glDepthMask(false);
                        }
                        else
                        {
                            GL11.glDepthMask(true);
                        }

                        float f1 = evt.entity.ticksExisted;
                        // this.bindTexture(evt.renderer.func_110829_a);
                        RenderUtility.setTerrainTexture();
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glLoadIdentity();
                        float f2 = f1 * 0.01F;
                        float f3 = f1 * 0.01F;
                        GL11.glTranslatef(f2, f3, 0.0F);
                        GL11.glScalef(2, 2, 2);
                        evt.renderer.setRenderPassModel(modelBase);
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_BLEND);
                        float f4 = 0.5F;
                        GL11.glColor4f(f4, f4, f4, 1.0F);
                        GL11.glDisable(GL11.GL_LIGHTING);
                        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                        modelBase.render(evt.entity, (float) evt.entity.posX, (float) evt.entity.posY, (float) evt.entity.posZ, evt.entity.rotationPitch, evt.entity.rotationYaw, 0.0625F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                    }
                }
                catch (Exception e)
                {
                    ICBMCore.LOGGER.severe("Failed to render entity layer object");
                    e.printStackTrace();
                }
            }
        }
    }
}
