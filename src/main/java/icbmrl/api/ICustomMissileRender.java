package icbmrl.api;

import net.minecraft.item.ItemStack;

import net.minecraftforge.client.IItemRenderer;

public interface ICustomMissileRender
{
	/**
	 * Location and rotation is already set based on default missile. So only
	 * make minor adjustments based on your model as needed. Avoid extra effects
	 * as this will slow down the render time in the inventory.
	 *
	 * @param type
	 *            - render type
	 * @param stack
	 *            - missile itemstack
	 * @param data
	 *            - unknown?
	 * @return false to use the default missile render
	 */
	boolean renderMissileItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data);

	/**
	 * Location and rotation is already set based on default missile. So only
	 * make minor adjustments based on your model as needed.
	 * 
	 * @return false to use the default missile render
	 */
	boolean renderMissileInWorld();

}
