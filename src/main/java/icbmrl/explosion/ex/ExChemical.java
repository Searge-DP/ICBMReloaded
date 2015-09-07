package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.ICBMCore;
import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastChemical;

public class ExChemical extends Explosion
{
    public ExChemical(String mingZi, int tier)
    {
        super(mingZi, tier);
        //chemical
        if (this.getTier() == 1)
        {
            this.modelName = "missile_chemical.tcn";
        }//contagious
        else if (this.getTier() == 2)
        {
            this.modelName = "missile_contagious.tcn";
        }
    }

    @Override
    public void init()
    {
        if (this.getTier() == 1)
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "@@@", "@?@", "@@@", '@', ICBMCore.itemPoisonPowder, '?', debilitation.getItemStack() }), "Chemical", InitModMetadata.CONFIGURATION, true);
        }
        else if (this.getTier() == 2)
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(2), new Object[] { " @ ", "@?@", " @ ", '?', Item.rottenFlesh, '@', chemical.getItemStack() }), "Contagious", InitModMetadata.CONFIGURATION, true);
        }
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        if (this.getTier() == 1)
        {
            new BlastChemical(world, entity, x, y, z, 20, 20 * 30, false).setPoison().setRGB(0.8f, 0.8f, 0).explode();
        }
        else if (this.getTier() == 2)
        {
            new BlastChemical(world, entity, x, y, z, 20, 20 * 30, false).setContagious().setRGB(0.3f, 0.8f, 0).explode();
        }

    }

}
