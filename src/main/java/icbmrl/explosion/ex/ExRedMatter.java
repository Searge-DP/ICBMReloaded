package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastRedmatter;

public class ExRedMatter extends Explosion
{
    public ExRedMatter()
    {
        super("redMatter", 4);
        this.modelName = "missile_redmatter.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "AAA", "AEA", "AAA", 'E', antimatter.getItemStack(), 'A', "strangeMatter" }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastRedmatter(world, entity, x, y, z, 35).explode();
    }

}
