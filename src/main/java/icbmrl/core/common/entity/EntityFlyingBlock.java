package icbmrl.core.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

import io.netty.buffer.ByteBuf;

/**
 * @author Calclavia
 */
public class EntityFlyingBlock extends Entity implements IEntityAdditionalSpawnData
{
	public Block block = null;
	public int metadata = 0;

	public float yawChange = 0;
	public float pitchChange = 0;

	public float gravity = 0.045f;

	public EntityFlyingBlock(World world)
	{
		super(world);
		this.ticksExisted = 0;
		this.preventEntitySpawning = true;
		this.isImmuneToFire = true;
		this.setSize(1F, 1F);
	}

	public EntityFlyingBlock(World world, Vec3 position, Block block, int metadata)
	{
		super(world);
		this.isImmuneToFire = true;
		this.ticksExisted = 0;
		setSize(0.98F, 0.98F);
		yOffset = height / 2.0F;
		setPosition(position.xCoord + 0.5, position.yCoord, position.zCoord + 0.5);
		motionX = 0D;
		motionY = 0D;
		motionZ = 0D;
		this.block = block;
		this.metadata = metadata;
	}

	public EntityFlyingBlock(World world, Vec3 position, Block block, int metadata, float gravity)
	{
		this(world, position, block, metadata);
		this.gravity = gravity;
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	public void onUpdate()
	{
		if (this.block == null || this.posY > 400 || this.block == Blocks.piston_extension || this.block == Blocks.flowing_water
				|| this.block == Blocks.flowing_lava)
		{
			this.setDead();
			return;
		}

		this.motionY -= gravity;

		if (this.isCollided)
		{
			// this.pushOutOfBlocks(this.posX, (this.boundingBox.minY +
			// this.boundingBox.maxY) / 2.0D, this.posZ);
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.yawChange > 0)
		{
			this.rotationYaw += this.yawChange;
			this.yawChange -= 2;
		}

		if (this.pitchChange > 0)
		{
			this.rotationPitch += this.pitchChange;
			this.pitchChange -= 2;
		}

		if ((this.onGround && this.ticksExisted > 20) || this.ticksExisted > 20 * 120)
		{
			this.setBlock();
			return;
		}

		this.ticksExisted++;
	}

	public void setBlock()
	{
		if (!this.worldObj.isRemote)
		{
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(posY);
			int k = MathHelper.floor_double(posZ);

			this.worldObj.setBlock(i, j, k, block, this.metadata, 2);
		}

		this.setDead();
	}

	/** Checks to see if and entity is touching the missile. If so, blow up! */

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		// Make sure the entity is not an item
		if (par1Entity instanceof EntityLiving)
		{
			if (this.block != null)
			{
				if (!(block instanceof BlockLiquid) && (this.motionX > 2 || this.motionY > 2 || this.motionZ > 2))
				{
					int damage = (int) (1.2 * (Math.abs(this.motionX) + Math.abs(this.motionY) + Math.abs(this.motionZ)));
					((EntityLiving) par1Entity).attackEntityFrom(DamageSource.fallingBlock, damage);
				}
			}
		}

		return null;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("metadata", this.metadata);
		nbttagcompound.setInteger("blockID", Block.getIdFromBlock(block));
		nbttagcompound.setFloat("gravity", this.gravity);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.metadata = nbttagcompound.getInteger("metadata");
		this.block = Block.getBlockById(nbttagcompound.getInteger("blockID"));
		this.gravity = nbttagcompound.getFloat("gravity");
	}

	@Override
	public float getShadowSize()
	{
		return 0.5F;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public void writeSpawnData(ByteBuf data)
	{
		data.writeInt(Block.getIdFromBlock(this.block));
		data.writeInt(this.metadata);
		data.writeFloat(this.gravity);
		data.writeFloat(yawChange);
		data.writeFloat(pitchChange);

	}

	@Override
	public void readSpawnData(ByteBuf data)
	{
		block = Block.getBlockById(data.readInt());
		metadata = data.readInt();
		gravity = data.readFloat();
		yawChange = data.readFloat();
		pitchChange = data.readFloat();
	}
}