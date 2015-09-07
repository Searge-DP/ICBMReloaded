package icbmrl.core.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.items.BaseElectricItem;
import icbmrl.core.common.ICBMCore;

public class ItemSignalDisrupter extends BaseElectricItem
{
	public ItemSignalDisrupter(int maxEnergy, int maxReceive, int maxSend)
	{
		super(maxEnergy, maxReceive, maxSend);
	}

	/**
	 * Allows items to add custom lines of information to the mouseover
	 * description
	 */
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		super.addInformation(itemStack, par2EntityPlayer, par3List, par4);
		par3List.add("Frequency: " + this.getFrequency(itemStack));
	}

	public int getFrequency(ItemStack itemStack)
	{
		if (itemStack.stackTagCompound == null)
		{
			return 0;
		}

		return itemStack.stackTagCompound.getInteger("frequency");
	}

	public void setFrequency(int frequency, ItemStack itemStack)
	{
		if (itemStack.stackTagCompound == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}

		itemStack.stackTagCompound.setInteger("frequency", frequency);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.openGui(ICBMCore.INSTANCE, 0, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY,
				(int) par3EntityPlayer.posZ);
		return par1ItemStack;
	}
}
