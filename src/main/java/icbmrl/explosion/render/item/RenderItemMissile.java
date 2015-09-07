package icbmrl.explosion.render.item;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.IItemRenderer;

import icbmrl.core.common.lib.ModelICBM;
import icbmrl.explosion.ex.Explosion;
import icbmrl.explosion.explosive.ExplosiveRegistry;
import icbmrl.explosion.items.ItemMissile;
import icbmrl.explosion.render.entity.RenderMissile;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderItemMissile implements IItemRenderer
{
    HashMap<Explosion, ModelICBM> cache = new HashMap<Explosion, ModelICBM>();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return this.shouldUseRenderHelper(type, item, null);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return item.getItem() instanceof ItemMissile;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (this.shouldUseRenderHelper(type, item, null))
        {
            Explosion missile = (Explosion) ExplosiveRegistry.get(item.getItemDamage());

            float scale = 0.7f;
            float right = 0f;

            if (type == ItemRenderType.INVENTORY)
            {
                scale = 0.4f;
                right = -0.5f;

                if (missile.getTier() == 2 || !missile.hasBlockForm())
                {
                    scale = scale / 1.5f;
                }
                else if (missile.getTier() == 3)
                {
                    scale = scale / 1.7f;
                    right = -0.65f;
                }
                else if (missile.getTier() == 4)
                {
                    scale = scale / 1.4f;
                    right = -0.45f;
                }

                GL11.glTranslatef(right, 0f, 0f);
            }

            if (type == ItemRenderType.EQUIPPED)
            {
                GL11.glTranslatef(1f, 0.3f, 0.5f);
                GL11.glRotatef(0, 0, 0, 1f);
            }
            else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
            {
                GL11.glTranslatef(1.15f, -1f, 0.5f);
                GL11.glRotatef(0, 0, 0, 1f);
            }
            else
            {
                GL11.glRotatef(-90, 0, 0, 1f);
            }

            if (type == ItemRenderType.ENTITY)
            {
                scale = scale / 1.5f;
            }

            GL11.glScalef(scale, scale, scale);

            FMLClientHandler.instance().getClient().renderEngine.bindTexture(missile.getMissileResource());

            synchronized (RenderMissile.cache)
            {
                if (!RenderMissile.cache.containsKey(missile))
                {
                    RenderMissile.cache.put(missile, missile.getMissileModel());
                }

                RenderMissile.cache.get(missile).renderAll();
            }
        }
    }
}
