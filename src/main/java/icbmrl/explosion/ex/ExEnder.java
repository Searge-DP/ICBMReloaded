package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.entities.EntityMissile;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.TileExplosive;
import icbmrl.explosion.explosive.blast.BlastEnderman;

public class ExEnder extends Explosion
{
    public ExEnder()
    {
        super("ender", 3);
        this.modelName = "missile_ender.tcn";
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
    {

        if (entityPlayer.inventory.getCurrentItem() != null)
        {
            if (entityPlayer.inventory.getCurrentItem().getItem() instanceof ICoordLink)
            {
                Vector3 link = ((ICoordLink) entityPlayer.inventory.getCurrentItem().getItem()).getLink(entityPlayer.inventory.getCurrentItem());

                if (link != null)
                {
                    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

                    if (tileEntity instanceof TileExplosive)
                    {
                        link.writeToNBT(((TileExplosive) tileEntity).nbtData);

                        if (!world.isRemote)
                        {
                            entityPlayer.addChatMessage("Synced coordinate with " + this.getExplosiveName());
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean onInteract(EntityMissile missileObj, EntityPlayer entityPlayer)
    {
        if (entityPlayer.inventory.getCurrentItem() != null)
        {
            if (entityPlayer.inventory.getCurrentItem().getItem() instanceof ICoordLink)
            {
                Vector3 link = ((ICoordLink) entityPlayer.inventory.getCurrentItem().getItem()).getLink(entityPlayer.inventory.getCurrentItem());

                if (link != null)
                {
                    link.writeToNBT(missileObj.nbtData);
                    if (!missileObj.worldObj.isRemote)
                    {
                        entityPlayer.addChatMessage("Synced coordinate with " + this.getMissileName());
                    }
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "PPP", "PTP", "PPP", 'P', Item.enderPearl, 'T', Explosive.attractive.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        Vector3 teleportTarget = null;

        if (entity instanceof IExplosiveContainer)
        {
            if (((IExplosiveContainer) entity).getTagCompound().hasKey("x") && ((IExplosiveContainer) entity).getTagCompound().hasKey("y") && ((IExplosiveContainer) entity).getTagCompound().hasKey("z"))
            {
                teleportTarget = new Vector3(((IExplosiveContainer) entity).getTagCompound());
            }
        }

        new BlastEnderman(world, entity, x, y, z, 30, teleportTarget).explode();
    }
}
