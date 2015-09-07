package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastRepulsive;

public class ExRepulsive extends Explosion
{
    public ExRepulsive(String name, int tier)
    {
        super(name, tier);
        this.setYinXin(120);
        if (name.equalsIgnoreCase("attractive"))
        {
            this.modelName = "missile_attractive.tcn";
        }
        else
        {
            this.modelName = "missile_repulsion .tcn";
        }
    }

    @Override
    public void init()
    {
        if (this.getID() == Explosive.attractive.getID())
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "YY", 'Y', Explosive.condensed.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
        }
        else
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "Y", "Y", 'Y', Explosive.condensed.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
        }
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        if (this.getID() == Explosive.attractive.getID())
        {
            new BlastRepulsive(world, entity, x, y, z, 2f).setDestroyItems().setPushType(1).explode();
        }
        else
        {
            new BlastRepulsive(world, entity, x, y, z, 2f).setDestroyItems().setPushType(2).explode();

        }
    }
}
