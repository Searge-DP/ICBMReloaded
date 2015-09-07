package icbm.sentry.turret.block;

import icbm.Reference;
import icbm.TabICBM;
import icbm.core.prefab.BlockICBM;
import icbm.sentry.ICBMSentry;
import icbm.sentry.interfaces.IMountedTurret;
import icbm.sentry.interfaces.ITurret;
import icbm.sentry.interfaces.ITurretProvider;
import icbm.sentry.turret.Turret;
import icbm.sentry.turret.TurretRegistry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import resonant.lib.access.Nodes;
import resonant.lib.multiblock.IBlockActivate;
import resonant.lib.prefab.block.BlockAdvanced;
import resonant.lib.prefab.item.ItemBlockSaved;
import resonant.lib.utility.inventory.InventoryUtility;
import resonant.lib.utility.nbt.SaveManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** Block turret is a class used by all turrets. Each type of turret will have a different tile
 * entity.
 * 
 * @author Calclavia, tgame14 */
public class BlockTurret extends BlockICBM
{
    //Temp vars only used during harvest process to pass these args to another method
    boolean isHarvesting = false;
    ITurret harvestedTurret = null;

    public BlockTurret(int id)
    {
        super(id, "turret");
        this.setCreativeTab(TabICBM.INSTANCE);
        this.setHardness(8f);
        this.setResistance(50f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntity ent = world.getBlockTileEntity(x, y, z);

        if (ent instanceof TileTurret)
        {
            if (((TileTurret) ent).getTurret() instanceof IMountedTurret)
            {
                Entity fakeEntity = ((IMountedTurret)((TileTurret) ent).getTurret()).getFakeEntity();

                if (fakeEntity != null)
                {
                    this.setBlockBounds(.2f, 0, .2f, .8f, .4f, .8f);
                    return;
                }
            }
        }
        this.setBlockBounds(.2f, 0, .2f, .8f, .8f, .8f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.PREFIX + "machine");
    }

    @Override
    public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof IBlockActivate)
        {
            return ((IBlockActivate) tileEntity).onActivated(entityPlayer);
        }

        return false;
    }

    @Override
    public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (!world.isRemote && tile instanceof TileTurret)
        {
            if (((TileTurret) tile).canUse(Nodes.PROFILE_ADMIN, entityPlayer) || ((TileTurret) tile).canUse(Nodes.PROFILE_OWNER, entityPlayer))
                InventoryUtility.dropBlockAsItem(world, x, y, z, true);
        }
        return true;
    }

    @Override
    public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        /** Checks the TileEntity if it can activate. If not, then try to activate the turret
         * platform below it. */
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof IBlockActivate)
        {
            if (((IBlockActivate) tileEntity).onActivated(entityPlayer))
            {
                return true;
            }
        }

        int id = world.getBlockId(x, y - 1, z);
        Block block = Block.blocksList[id];

        if (block instanceof BlockAdvanced)
        {
            return ((BlockAdvanced) block).onMachineActivated(world, x, y - 1, z, entityPlayer, side, hitX, hitY, hitZ);
        }

        return false;
    }

    @Override
    public boolean onSneakMachineActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
    {
        if (entityPlayer != null)
        {
            entityPlayer.openGui(ICBMSentry.INSTANCE, 1, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int side)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity instanceof TileTurret)
        {
            if (!canBlockStay(world, x, y, z))
            {
                InventoryUtility.dropBlockAsItem(world, x, y, z, true);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileTurret();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return 0;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y - 1, z) == ICBMSentry.blockPlatform.blockID;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubBlocks(int id, CreativeTabs creativeTab, List list)
    {
        for (Class<? extends ITurret> sentry : TurretRegistry.getSentryMap().values())
            list.add(TurretRegistry.getItemStack(sentry));
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        if (!world.isRemote)
        {
            TileEntity te = world.getBlockTileEntity(x, y, z);
            if (te instanceof TileTurret && entity instanceof EntityPlayer)
            {
                ((TileTurret) te).getAccessProfile().setUserAccess(((EntityPlayer) entity).username, ((TileTurret) te).getAccessProfile().getOwnerGroup());
                ((TileTurret) te).onProfileChange();
            }
        }
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int par5, EntityPlayer entityPlayer)
    {
        //Save the tile so that get block dropped can function
        harvestedTurret = getTurret(world, x, y, z);
        isHarvesting = true;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ItemStack itemStack = getItemStack(world, x, y, z);
        if (isHarvesting && itemStack == null)
        {
            itemStack = getItemStack(harvestedTurret);
            harvestedTurret = null;
            isHarvesting = false;
        }
        if (itemStack != null && itemStack.getTagCompound().hasKey(ITurret.SENTRY_TYPE_SAVE_ID))
            ret.add(itemStack);

        return ret;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return getItemStack(world, x, y, z);
    }

    /** Gets the item version of this block */
    public ItemStack getItemStack(World world, int x, int y, int z)
    {
        return getItemStack(world.getBlockTileEntity(x, y, z));
    }

    /** Gets the item version of this block */
    public ItemStack getItemStack(TileEntity tile)
    {
        return getItemStack(getTurret(tile));
    }

    /** Gets the item version of this block */
    public ItemStack getItemStack(ITurret turret)
    {
        return TurretRegistry.getItemStack(turret);
    }

    /** Gets the turret object this block contains */
    public ITurret getTurret(World world, int x, int y, int z)
    {
        return getTurret(world.getBlockTileEntity(x, y, z));
    }

    /** Gets the turret object this block contains */
    public ITurret getTurret(TileEntity tile)
    {
        if (tile instanceof ITurretProvider)
        {
            return ((ITurretProvider) tile).getTurret();
        }
        return null;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
}
