package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastChemical;

public class ExDebilitation extends Explosion
{
    public ExDebilitation(String mingZi, int tier)
    {
        super(mingZi, tier);
        this.modelName = "missle_deblitation.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(3), new Object[] { "SSS", "WRW", "SSS", 'R', Explosive.replsive.getItemStack(), 'W', Item.bucketWater, 'S', "dustSulfur" }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastChemical(world, entity, x, y, z, 20, 20 * 30, false).setConfuse().explode();
    }
}
