package icbmrl.core.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockICBMDecor extends BaseBlock // implements IAntiPoisonBlock
{
	private final IIcon[] icon = new IIcon[7];

	public BlockICBMDecor(int id)
	{
		super(Material.rock);
		this.setHardness(3.8f);
		this.setResistance(50);
		this.setStepSound(soundTypeMetal);
	}
}
