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
public class RenderRailgun extends TurretRenderer
{
    public static final IModelCustom MODEL = AdvancedModelLoader.loadModel(ModInfo.MODEL_DIRECTORY + "turret_railgun.tcn");

    public RenderRailgun()
    {
        super(new ResourceLocation(ModInfo.DOMAIN, ModInfo.MODEL_PREFIX + "turret_railgun.png"));
    }

    @Override
    public void render(ForgeDirection side, TileTurret tile, double yaw, double pitch)
    {
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        MODEL.renderOnly("BASE", "NECK");
        GL11.glRotated(yaw, 0, 1, 0);
        MODEL.renderOnly("SUPPORT 1 (ROTATES)", "SUPPORT 2 (ROTATES)", "SUPPORT PLATFORM (ROTATES)");
        GL11.glRotated(pitch, 1, 0, 0);
        MODEL.renderOnly("MAIN TURRET (ROTATES)", "BATTERY PACK (ROTATES)", "MAIN CANNON (ROTATES)", "NOZZLE (ROTATES)");
    }

    @Override
    public void renderInventoryItem(ItemStack itemStack)
    {
        RenderUtility.bind(textureNeutral);
        GL11.glTranslatef(0.5f, 0.3f, 0.7f);
        MODEL.renderAll();
    }
}