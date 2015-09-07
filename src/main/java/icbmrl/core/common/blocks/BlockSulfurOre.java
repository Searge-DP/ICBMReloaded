package icbmrl.core.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import icbmrl.core.common.ICBMCore;
import icbmrl.core.common.lib.ModInfo;

/**
 * World generated block
 * 
 * @author Calcalvia
 */
public class BlockSulfurOre extends Block
{
	public BlockSulfurOre()
	{
		super(Material.rock);
		this.setUnlocalizedName(ModInfo.PREFIX + "oreSulfur");
		this.setTextureName(ModInfo.PREFIX + "oreSulfur");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(3.0f);
	}

	@Override
	public int idDropped(int id, Random rand, int meta)
	{
		return ICBMCore.itemSulfurDust.itemID;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 3 + rand.nextInt(3);
	}

	@Override
	public int quantityDroppedWithBonus(int drop, Random rand)
	{
		return this.quantityDropped(rand) + rand.nextInt(drop + 1);
	}
}
