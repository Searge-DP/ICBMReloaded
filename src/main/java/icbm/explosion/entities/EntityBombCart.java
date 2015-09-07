package icbm.explosion.entities;

import icbm.explosion.ICBMExplosion;
import icbm.explosion.explosive.ExplosiveRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import resonant.api.explosion.IExplosive;
import resonant.api.explosion.IExplosiveContainer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBombCart extends EntityMinecartTNT implements IExplosiveContainer, IEntityAdditionalSpawnData
{
    public int explosiveID = 0;
    public NBTTagCompound nbtData = new NBTTagCompound();

    public EntityBombCart(World par1World)
    {
        super(par1World);
    }

    public EntityBombCart(World par1World, double x, double y, double z, int explosiveID)
    {
        super(par1World, x, y, z);
        this.explosiveID = explosiveID;
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data)
    {
        data.writeInt(this.explosiveID);
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data)
    {
        this.explosiveID = data.readInt();
    }

    @Override
    protected void explodeCart(double par1)
    {
        // TODO add event
        this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.getExplosiveType().createExplosion(this.worldObj, this.posX, this.posY, this.posZ, this);
        this.setDead();
    }

    public boolean interact(EntityPlayer player)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            if (player.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
            {
                this.ignite();
                return true;
            }
        }
        return false;
    }

    @Override
    public void killMinecart(DamageSource par1DamageSource)
    {
        this.setDead();
        ItemStack itemstack = new ItemStack(Item.minecartEmpty, 1);

        if (this.entityName != null)
        {
            itemstack.setItemName(this.entityName);
        }

        this.entityDropItem(itemstack, 0.0F);

        double d0 = this.motionX * this.motionX + this.motionZ * this.motionZ;

        if (!par1DamageSource.isExplosion())
        {
            this.entityDropItem(new ItemStack(ICBMExplosion.blockExplosive, 1, this.explosiveID), 0.0F);
        }

        if (par1DamageSource.isFireDamage() || par1DamageSource.isExplosion() || d0 >= 0.009999999776482582D)
        {
            this.explodeCart(d0);
        }
    }

    @Override
    public ItemStack getCartItem()
    {
        return new ItemStack(ICBMExplosion.itemBombCart, 1, this.explosiveID);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("haoMa", this.explosiveID);
        this.nbtData = nbt.getCompoundTag("data");

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.explosiveID = nbt.getInteger("haoMa");
        nbt.setTag("data", this.nbtData);

    }

    @Override
    public IExplosive getExplosiveType()
    {
        return ExplosiveRegistry.get(this.explosiveID);
    }

    @Override
    public Block getDefaultDisplayTile()
    {
        return ICBMExplosion.blockExplosive;
    }

    @Override
    public int getDefaultDisplayTileData()
    {
        return this.explosiveID;
    }

    @Override
    public NBTTagCompound getTagCompound()
    {
        return this.nbtData;
    }

}
