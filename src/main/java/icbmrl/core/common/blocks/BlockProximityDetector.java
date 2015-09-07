package icbmrl.core.common.blocks;

import net.minecraft.block.material.Material;

public class BlockProximityDetector extends BaseBlock
{
	public BlockProximityDetector()
	{
		super(Material.anvil);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	/*
	 * @Override public Icon getIcon(int side, int metadata) { return side == 0
	 * ? this.iconBottom : (side == 1 ? this.iconTop : this.iconSide); }
	 * 
	 * @Override public boolean onMachineActivated(World world, int x, int y,
	 * int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	 * { player.openGui(ICBMCore.INSTANCE, 0, world, x, y, z); return true; }
	 * 
	 * @Override public boolean onUseWrench(World world, int x, int y, int z,
	 * EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	 * TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	 * 
	 * if (tileEntity instanceof TileProximityDetector) {
	 * ((TileProximityDetector) tileEntity).isInverted =
	 * !((TileProximityDetector) tileEntity).isInverted; if (!world.isRemote)
	 * player.addChatMessage("Proximity Detector Inversion: " +
	 * ((TileProximityDetector) tileEntity).isInverted); return true; }
	 * 
	 * return false; }
	 * 
	 * @Override public boolean canProvidePower() { return true; }
	 * 
	 * @Override public boolean renderAsNormalBlock() { return false; }
	 * 
	 * @Override public TileEntity createNewTileEntity(World world) { return new
	 * TileProximityDetector(); }
	 */
}
