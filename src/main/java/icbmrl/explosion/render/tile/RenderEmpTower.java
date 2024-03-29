package icbmrl.explosion.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.explosion.machines.TileEMPTower;
import icbmrl.explosion.model.tiles.ModelEmpTower;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEmpTower extends TileEntitySpecialRenderer
{
    public static final ResourceLocation TEXTURE_FILE = new ResourceLocation(ModInfo.DOMAIN, ModInfo.MODEL_TEXTURE_PATH + "emp_tower.png");

    public static final ModelEmpTower MODEL = new ModelEmpTower();

    @Override
    public void renderTileEntityAt(TileEntity t, double x, double y, double z, float f)
    {
        TileEMPTower tileEntity = (TileEMPTower) t;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        this.bindTexture(TEXTURE_FILE);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        MODEL.render(tileEntity.rotation, 0.0625F);
        GL11.glPopMatrix();
    }
}