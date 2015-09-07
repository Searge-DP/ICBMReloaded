package icbmrl.sentry.turret.block;

import net.minecraft.tileentity.TileEntity;

import icbmrl.sentry.render.RenderGunTurret;
import icbmrl.sentry.render.TurretRenderer;
import icbmrl.sentry.turret.TurretRegistry;
import org.lwjgl.opengl.GL11;

/** Container class for most sentry gun renders of 1^3. Other sentries might need a more custom
 * render approach if they become to complex or large
 * 
 * @author DarkGuardsman */
public class RenderTurret extends RenderTaggedTile
{
    private static final TurretRenderer backup_render = new RenderGunTurret();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
    {
        super.renderTileEntityAt(tile, x, y, z, f);

        if (tile instanceof TileTurret)
        {
            TileTurret tileTurret = (TileTurret) tile;
            TurretRenderer sentryRender = TurretRegistry.getRenderFor(tileTurret.getTurret());

            if (sentryRender == null)
            {
                sentryRender = backup_render;
            }

            if (sentryRender != null)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) x, (float) y, (float) z);
                GL11.glScalef(1f, 1f, 1f);

                RenderUtility.bind(sentryRender.getTexture(getPlayer(), tileTurret));

                if (tileTurret.getTurret() != null)
                    sentryRender.render(tileTurret.getDirection(), tileTurret, tileTurret.getTurret().getServo().yaw, tileTurret.getTurret().getServo().pitch);

                GL11.glPopMatrix();
            }
        }
    }
}
