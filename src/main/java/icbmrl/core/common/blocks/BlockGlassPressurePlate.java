package icbmrl.core.common.blocks;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import icbmrl.core.common.lib.TabICBM;

public class BlockGlassPressurePlate extends BlockPressurePlate
{
	public BlockGlassPressurePlate()
	{
		super("blockGlassPressurePlate", Material.glass, BlockPressurePlate.Sensitivity.everything);
		this.setTickRandomly(true);
		this.setResistance(1F);
		this.setHardness(0.3F);
		this.setStepSound(soundTypeGlass);
		this.setCreativeTab(TabICBM.INSTANCE);
	}

	@Override
	public int tickRate(World world)
	{
		return 10;
	}

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1
	 * for alpha
	 */
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getMobilityFlag()
	{
		return 1;
	}
}
