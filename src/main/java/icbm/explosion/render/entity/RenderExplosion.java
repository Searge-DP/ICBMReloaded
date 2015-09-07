package icbm.explosion.render.entity;

import icbm.Reference;
import icbm.explosion.entities.EntityExplosion;
import icbm.explosion.explosive.blast.BlastRedmatter;

import java.util.Random;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import resonant.lib.render.RenderUtility;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderExplosion extends Render
{
    public static final ResourceLocation TEXTURE_FILE = new ResourceLocation(Reference.DOMAIN, Reference.TEXTURE_PATH + "blackhole.png");

    public Random random = new Random();

    @Override
    public void doRender(Entity entity, double x, double y, double z, float par8, float par9)
    {
        EntityExplosion eZhaPin = (EntityExplosion) entity;

        if (eZhaPin.blast != null)
        {
            // RedM atter Render
            if (eZhaPin.blast instanceof BlastRedmatter)
            {
                Tessellator tessellator = Tessellator.instance;

                /** Draw Sphere */
                GL11.glPushMatrix();
                GL11.glTranslatef((float) x, (float) y, (float) z);

                RenderUtility.enableBlending();
                RenderUtility.disableLighting();

                GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.9f);

                Sphere sphere = new Sphere();
                sphere.draw(5, 32, 32);

                // Enable Lighting/Glow Off
                RenderUtility.enableLighting();

                // Disable Blending
                RenderUtility.disableBlending();
                GL11.glPopMatrix();

                /** Draw Vortex
                 * 
                 * GL11.glPushMatrix(); GL11.glDepthMask(false);
                 * 
                 * CalclaviaRenderHelper.enableBlending(); CalclaviaRenderHelper.disableLighting();
                 * 
                 * GL11.glTranslated(x, y, z); GL11.glRotatef(-entity.ticksExisted, 0, 1, 0);
                 * 
                 * float size = 10; float f10 = 1.0F;
                 * 
                 * int textureSize = 50; float size4 = size * 5; float float_sizeMinus0_01 =
                 * textureSize - 0.01F;
                 * 
                 * float x0 = (textureSize + 0.0F) / size4; float x1 = (textureSize +
                 * float_sizeMinus0_01) / size4; float x2 = (textureSize + 0.0F) / size4; float x3 =
                 * (textureSize + float_sizeMinus0_01) / size4;
                 * 
                 * float renderX = (float) x; float renderY = (float) y; float renderZ = (float) z;
                 * 
                 * this.bindTexture(TEXTURE_FILE); tessellator.startDrawingQuads();
                 * tessellator.setBrightness(240); tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1F);
                 * tessellator.addVertexWithUV(-size, 0, -size, x1, x3);
                 * tessellator.addVertexWithUV(-size, 0, +size, x1, x2);
                 * tessellator.addVertexWithUV(+size, 0, +size, x0, x2);
                 * tessellator.addVertexWithUV(+size, 0, -size, x0, x3); tessellator.draw();
                 * 
                 * // Enable Lighting/Glow Off CalclaviaRenderHelper.enableLighting();
                 * 
                 * // Disable Blending CalclaviaRenderHelper.disableBlending();
                 * 
                 * GL11.glDepthMask(true); GL11.glPopMatrix(); */

                /** Enderdragon Light */
                float par2 = (entity.ticksExisted);

                while (par2 > 200)
                    par2 -= 100;

                RenderHelper.disableStandardItemLighting();
                float var41 = (5 + par2) / 200.0F;
                float var51 = 0.0F;

                if (var41 > 0.8F)
                {
                    var51 = (var41 - 0.8F) / 0.2F;
                }

                Random rand = new Random(432L);

                GL11.glPushMatrix();
                GL11.glTranslatef((float) x, (float) y, (float) z);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glDepthMask(false);
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0F, -1.0F, -2.0F);

                for (int i1 = 0; i1 < (var41 + var41 * var41) / 2.0F * 60.0F; ++i1)
                {
                    GL11.glRotatef(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(rand.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(rand.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(rand.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(rand.nextFloat() * 360.0F + var41 * 90.0F, 0.0F, 0.0F, 1.0F);
                    tessellator.startDrawing(6);
                    float var81 = rand.nextFloat() * 20.0F + 5.0F + var51 * 10.0F;
                    float var91 = rand.nextFloat() * 2.0F + 1.0F + var51 * 2.0F;
                    tessellator.setColorRGBA_I(16777215, (int) (255.0F * (1.0F - var51)));
                    tessellator.addVertex(0.0D, 0.0D, 0.0D);
                    tessellator.setColorRGBA_I(0, 0);
                    tessellator.addVertex(-0.866D * var91, var81, -0.5F * var91);
                    tessellator.addVertex(0.866D * var91, var81, -0.5F * var91);
                    tessellator.addVertex(0.0D, var81, 1.0F * var91);
                    tessellator.addVertex(-0.866D * var91, var81, -0.5F * var91);
                    tessellator.draw();
                }

                GL11.glPopMatrix();
                GL11.glDepthMask(true);
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glShadeModel(GL11.GL_FLAT);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
            else
            {
                if (eZhaPin.blast.getRenderModel() != null && eZhaPin.blast.getRenderResource() != null)
                {
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float) x, (float) y + 1F, (float) z);
                    GL11.glRotatef(eZhaPin.rotationPitch, 0.0F, 0.0F, 1.0F);
                    this.bindTexture(eZhaPin.blast.getRenderResource());
                    eZhaPin.blast.getRenderModel().render(eZhaPin, (float) x, (float) y, (float) z, par8, par9, 0.0625F);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    public void drawCircle(double x, double y, double radius, double accuracy)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        double da = Math.min((2.0 * Math.asin(1.0 / radius) / accuracy), 10000);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(x, y);

        for (double a = 0.0; a <= 2 * Math.PI; a += da)
        {
            GL11.glVertex2d(x + Math.cos(a) * radius, y + Math.sin(a) * radius);
        }

        GL11.glVertex2d(x + radius, y);
        GL11.glEnd();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }

}
