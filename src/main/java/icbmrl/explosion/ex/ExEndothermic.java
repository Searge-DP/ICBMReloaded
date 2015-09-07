package icbmrl.explosion.ex;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastSky;

public class ExEndothermic extends Explosion
{
    public ExEndothermic()
    {
        super("endothermic", 3);
        this.modelName = "missile_endothermic.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "?!?", "!@!", "?!?", '@', attractive.getItemStack(), '?', Block.ice, '!', Block.blockSnow }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastSky(world, entity, x, y, z, 50).explode();
    }
}
