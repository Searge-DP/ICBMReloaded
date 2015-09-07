package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastBreech;

public class ExBreaching extends Explosion
{
    public ExBreaching()
    {
        super("breaching", 2);
        this.setYinXin(40);
        this.modelName = "missile_breaching.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(2), new Object[] { "GCG", "GCG", "GCG", 'C', Explosive.condensed.getItemStack(), 'G', Item.gunpowder }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastBreech(world, entity, x, y, z, 2.5f, 7).explode();
    }
}
