package icbmrl.core.common.blocks;

import net.minecraft.block.BlockButton;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import icbmrl.core.common.lib.TabICBM;

public class BlockGlassButton extends BlockButton
{
	public BlockGlassButton()
	{
		super(true);
		this.setTickRandomly(true);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(TabICBM.INSTANCE);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return Blocks.glass.getIcon(p_149691_1_, p_149691_2_);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
