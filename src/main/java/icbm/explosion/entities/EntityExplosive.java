package icbm.explosion.entities;

import icbm.explosion.ICBMExplosion;
import icbm.explosion.explosive.ExplosiveRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import resonant.api.IRotatable;
import resonant.api.explosion.ExplosiveType;
import resonant.api.explosion.IExplosive;
import resonant.api.explosion.IExplosiveContainer;
import resonant.api.explosion.ExplosionEvent.ExplosivePreDetonationEvent;
import universalelectricity.api.vector.Vector3;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityExplosive extends Entity implements IRotatable, IEntityAdditionalSpawnData, IExplosiveContainer
{
    // How long the fuse is (in ticks)
    public int fuse = 90;

    // The ID of the explosive
    public int explosiveID = 0;

    private byte orientation = 3;

    public NBTTagCompound nbtData = new NBTTagCompound();

    public EntityExplosive(World par1World)
    {
        super(par1World);
        this.fuse = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityExplosive(World par1World, Vector3 position, byte orientation, int explosiveID)
    {
        this(par1World);
        this.setPosition(position.x, position.y, position.z);
        float var8 = (float) (Math.random() * Math.PI * 2.0D);
        this.motionX = (-((float) Math.sin(var8)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (-((float) Math.cos(var8)) * 0.02F);
        this.prevPosX = position.x;
        this.prevPosY = position.y;
        this.prevPosZ = position.z;
        this.explosiveID = explosiveID;
        this.fuse = ExplosiveRegistry.get(explosiveID).getYinXin();
        this.orientation = orientation;

        ExplosiveRegistry.get(explosiveID).yinZhaQian(par1World, this);
    }

    public EntityExplosive(World par1World, Vector3 position, int explosiveID, byte orientation, NBTTagCompound nbtData)
    {
        this(par1World, position, orientation, explosiveID);
        this.nbtData = nbtData;
    }

    @Override
    public String getEntityName()
    {
        return "Explosives";
    }

    /** Called to update the entity's position/logic. */
    @Override
    public void onUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            ExplosivePreDetonationEvent evt = new ExplosivePreDetonationEvent(worldObj, posX, posY, posZ, ExplosiveType.BLOCK, ExplosiveRegistry.get(explosiveID));
            MinecraftForge.EVENT_BUS.post(evt);

            if (evt.isCanceled())
            {
                ICBMExplosion.blockExplosive.dropBlockAsItem(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, this.explosiveID, 0);
                this.setDead();
                return;
            }
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionX *= 0.95;
        this.motionY -= 0.045D;
        this.motionZ *= 0.95;

        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.fuse < 1)
        {
            this.explode();
        }
        else
        {
            ExplosiveRegistry.get(this.explosiveID).onYinZha(this.worldObj, new Vector3(this.posX, this.posY, this.posZ), this.fuse);
        }

        this.fuse--;

        super.onUpdate();
    }

    public void explode()
    {
        this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.getExplosiveType().createExplosion(this.worldObj, this.posX, this.posY, this.posZ, this);
        this.setDead();
    }

    /** (abstract) Protected helper method to read subclass entity data from NBT. */
    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        this.fuse = nbt.getByte("Fuse");
        this.explosiveID = nbt.getInteger("explosiveID");
        this.nbtData = nbt.getCompoundTag("data");
    }

    /** (abstract) Protected helper method to write subclass entity data to NBT. */
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setByte("Fuse", (byte) this.fuse);
        nbt.setInteger("explosiveID", this.explosiveID);
        nbt.setTag("data", this.nbtData);
    }

    @Override
    public float getShadowSize()
    {
        return 0.5F;
    }

    @Override
    protected void entityInit()
    {
    }

    /** returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for
     * spiders and wolves to prevent them from trampling crops */
    @Override
    protected boolean canTriggerWalking()
    {
        return true;
    }

    /** Returns true if other Entities should be prevented from moving through this Entity. */
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /** Returns true if this entity should push and be pushed by other entities when colliding. */
    @Override
    public boolean canBePushed()
    {
        return true;
    }

    @Override
    public ForgeDirection getDirection()
    {
        return ForgeDirection.getOrientation(this.orientation);
    }

    @Override
    public void setDirection(ForgeDirection facingDirection)
    {
        this.orientation = (byte) facingDirection.ordinal();
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data)
    {
        data.writeInt(this.explosiveID);
        data.writeInt(this.fuse);
        data.writeByte(this.orientation);
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data)
    {
        this.explosiveID = data.readInt();
        this.fuse = data.readInt();
        this.orientation = data.readByte();
    }

    @Override
    public IExplosive getExplosiveType()
    {
        return ExplosiveRegistry.get(this.explosiveID);
    }

    @Override
    public NBTTagCompound getTagCompound()
    {
        return this.nbtData;
    }
}
