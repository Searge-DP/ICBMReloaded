package icbmrl.sentry.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.turret.block.TileTurret;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCrossBowTurret extends TurretRenderer
{
    public static final IModelCustom MODEL = AdvancedModelLoader.loadModel(ModInfo.MODEL_DIRECTORY + "turret_bow.tcn");
    public static final String[] yawOnly = { "leg1", "leg2", "leg3", "legbase" };

    public RenderCrossBowTurret()
    {
        super(new ResourceLocation(ModInfo.DOMAIN, ModInfo.MODEL_TEXTURE_PATH + "turret_bow.png"));
    }

    @Override
    public void render(ForgeDirection side, TileTurret tile, double yaw, double pitch)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5f, 0.6f, 0.5f);
        GL11.glScalef(1.2f, 1.2f, 1.2f);
        // Render base yaw rotation
        GL11.glRotated(yaw, 0, 1, 0);
        MODEL.renderOnly(yawOnly);
        // Render gun pitch rotation
        GL11.glRotated(pitch, 1, 0, 0);
        MODEL.renderAllExcept(yawOnly);
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryItem(ItemStack itemStack)
    {
        RenderUtility.bind(textureNeutral);
        GL11.glTranslatef(0.5f, 0.5f, 0.6f);
        MODEL.renderAll();
    }
}