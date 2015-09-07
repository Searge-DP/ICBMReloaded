package icbmrl.explosion;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

import icbmrl.explosion.container.ContainerCruiseLauncher;
import icbmrl.explosion.container.ContainerMissileCoordinator;
import icbmrl.explosion.container.ContainerMissileTable;
import icbmrl.explosion.entities.EntityMissile;
import icbmrl.explosion.explosive.TileExplosive;
import icbmrl.explosion.machines.TileCruiseLauncher;
import icbmrl.explosion.machines.TileEMPTower;
import icbmrl.explosion.machines.TileMissileAssembler;
import icbmrl.explosion.machines.TileMissileCoordinator;
import icbmrl.explosion.machines.TileRadarStation;
import icbmrl.explosion.machines.launcher.TileLauncherBase;
import icbmrl.explosion.machines.launcher.TileLauncherFrame;
import icbmrl.explosion.machines.launcher.TileLauncherScreen;

/** ICBM Explosion Module Common Proxy
 * 
 * @author Calclavia */
public class CommonProxy implements IGuiHandler
{
    public void preInit()
    {
    }

    public void init()
    {
        GameRegistry.registerTileEntity(TileCruiseLauncher.class, "ICBMXiaoFaSheQi");
        GameRegistry.registerTileEntity(TileLauncherBase.class, "ICBMFaSheDi");
        GameRegistry.registerTileEntity(TileLauncherScreen.class, "ICBMFaSheShiMuo");
        GameRegistry.registerTileEntity(TileLauncherFrame.class, "ICBMFaSheJia");
        GameRegistry.registerTileEntity(TileRadarStation.class, "ICBMLeiDaTai");
        GameRegistry.registerTileEntity(TileEMPTower.class, "ICBMDianCiQi");
        GameRegistry.registerTileEntity(TileMissileCoordinator.class, "ICBMYinDaoQi");
        GameRegistry.registerTileEntity(TileExplosive.class, "ICBMZhaDan");
        GameRegistry.registerTileEntity(TileMissileAssembler.class, "ICBMMissileTable");
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileCruiseLauncher)
        {
            return new ContainerCruiseLauncher(player.inventory, (TileCruiseLauncher) tileEntity);
        }
        else if (tileEntity instanceof TileMissileCoordinator)
        {
            return new ContainerMissileCoordinator(player.inventory, (TileMissileCoordinator) tileEntity);
        }
        else if (tileEntity instanceof TileMissileAssembler)
        {
            return new ContainerMissileTable(player.inventory, (TileMissileAssembler) tileEntity);
        }
        else if (tileEntity instanceof TileLauncherScreen || tileEntity instanceof TileRadarStation || tileEntity instanceof TileEMPTower || tileEntity instanceof TileLauncherBase || tileEntity instanceof TileMissileCoordinator || tileEntity instanceof TileMissileAssembler)
        {
            return new ContainerDummy(player, tileEntity);
        }

        return null;
    }

    public boolean isGaoQing()
    {
        return false;
    }

    public void spawnParticle(String name, World world, Vector3 position, float scale, double distance)
    {
        this.spawnParticle(name, world, position, 0, 0, 0, scale, distance);
    }

    public void spawnParticle(String name, World world, Vector3 position, double motionX, double motionY, double motionZ, float scale, double distance)
    {
        this.spawnParticle(name, world, position, motionX, motionY, motionZ, 1, 1, 1, scale, distance);
    }

    public void spawnParticle(String name, World world, Vector3 position, double motionX, double motionY, double motionZ, float red, float green, float blue, float scale, double distance)
    {

    }

    public IUpdatePlayerListBox getDaoDanShengYin(EntityMissile eDaoDan)
    {
        return null;
    }

    public int getParticleSetting()
    {
        return -1;
    }

    public List<Entity> getEntityFXs()
    {
        return null;
    }

    public void spawnShock(World world, Vector3 position, Vector3 target)
    {

    }

    public void spawnShock(World world, Vector3 startVec, Vector3 targetVec, int duration)
    {
        // TODO Auto-generated method stub

    }

}
