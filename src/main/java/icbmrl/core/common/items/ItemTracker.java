package icbmrl.core.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseElectricItem;

public class ItemTracker extends BaseElectricItem
{
	private static final long ENERGY_PER_TICK = 1;

	public ItemTracker(int maxEnergy, int maxReceive, int maxSend)
	{
		super(maxEnergy, maxReceive, maxSend);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		if (par1IconRegister instanceof TextureMap)
		{
			((TextureMap) par1IconRegister).setTextureEntry(this.getUnlocalizedName().replace("item.", ""), new TextureTracker());
			this.itemIcon = ((TextureMap) par1IconRegister).getTextureExtry(this.getUnlocalizedName().replace("item.", ""));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		super.addInformation(itemStack, par2EntityPlayer, par3List, par4);

		Entity trackingEntity = getTrackingEntity(FMLClientHandler.instance().getClient().theWorld, itemStack);

		if (trackingEntity != null)
		{
			par3List.add(StatCollector.translateToLocal("info.tracker.tracking") + " " + trackingEntity.getCommandSenderName());
		}

		par3List.add(StatCollector.translateToLocal("info.tracker.tooltip"));
	}

	public void setTrackingEntity(ItemStack itemStack, Entity entity)
	{
		if (itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		if (entity != null)
		{
			itemStack.stackTagCompound.setInteger("trackingEntity", entity.getEntityId());
		}
	}

	public Entity getTrackingEntity(World worldObj, ItemStack itemStack)
	{
		if (worldObj != null)
		{
			if (itemStack.stackTagCompound != null)
			{
				int trackingID = itemStack.stackTagCompound.getInteger("trackingEntity");
				return worldObj.getEntityByID(trackingID);
			}
		}
		return null;
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
		setTrackingEntity(par1ItemStack, par3EntityPlayer);
	}

	@Override
	public void onUpdate(ItemStack itemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		super.onUpdate(itemStack, par2World, par3Entity, par4, par5);

		if (par3Entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par3Entity;

			if (player.inventory.getCurrentItem() != null)
			{
				if (player.inventory.getCurrentItem().getItem() == this && par2World.getWorldTime() % 20 == 0)
				{
					Entity trackingEntity = this.getTrackingEntity(par2World, itemStack);

					if (trackingEntity != null)
					{
						// if (this.discharge(itemStack, ENERGY_PER_TICK, true)
						// < ENERGY_PER_TICK)
						// {
						this.setTrackingEntity(itemStack, null);
						// }
					}
				}
			}
		}
	}
}
