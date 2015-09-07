package icbmrl.explosion.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.explosion.machines.launcher.TileLauncherFrame;
import icbmrl.explosion.model.tiles.MFaSheJia;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderLauncherFrame extends TileEntitySpecialRenderer
{
    public static final MFaSheJia MODEL = new MFaSheJia();
    public static final ResourceLocation TEXTURE_FILE = new ResourceLocation(ModInfo.DOMAIN, ModInfo.MODEL_TEXTURE_PATH + "launcher_0.png");

    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
        TileLauncherFrame tileEntity = (TileLauncherFrame) var1;

        if (tileEntity != null && tileEntity.worldObj != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.25F, (float) z + 0.5F);
            GL11.glScalef(1f, 0.85f, 1f);

            this.bindTexture(TEXTURE_FILE);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

            if (tileEntity.getDirection() != ForgeDirection.NORTH && tileEntity.getDirection() != ForgeDirection.SOUTH)
            {
                GL11.glRotatef(90F, 0.0F, 180F, 1.0F);
            }

            MODEL.render(0.0625F);

            GL11.glPopMatrix();
        }
    }

}
