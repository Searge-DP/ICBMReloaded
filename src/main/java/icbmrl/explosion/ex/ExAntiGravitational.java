package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastAntiGravitational;

public class ExAntiGravitational extends Explosion
{
    public ExAntiGravitational()
    {
        super("antiGravitational", 3);
        this.modelName = "missile_antigrav.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "EEE", "ETE", "EEE", 'T', replsive.getItemStack(), 'E', Item.eyeOfEnder }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastAntiGravitational(world, entity, x, y, z, 30).explode();
    }
}
