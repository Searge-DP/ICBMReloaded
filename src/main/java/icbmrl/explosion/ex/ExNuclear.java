package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastNuclear;

public class ExNuclear extends Explosion
{
    public ExNuclear(String mingZi, int tier)
    {
        super(mingZi, tier);
        if (this.getTier() == 3)
        {
            this.modelName = "missile_nuclear.tcn";
        }
        else
        {
            this.modelName = "missile_conflag.tcn";
        }
    }

    @Override
    public void init()
    {
        if (this.getTier() == 3)
        {
            if (OreDictionary.getOres("ingotUranium").size() > 0)
            {
                RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "UUU", "UEU", "UUU", 'E', thermobaric.getItemStack(), 'U', "ingotUranium" }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
            }
            else
            {
                RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "EEE", "EEE", "EEE", 'E', thermobaric.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);

            }
        }
        else
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "CIC", "IRI", "CIC", 'R', Explosive.replsive.getItemStack(), 'C', Explosive.chemical.getItemStack(), 'I', Explosive.incendiary.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);

        }
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        if (this.getTier() == 3)
        {
            new BlastNuclear(world, entity, x, y, z, 50, 80).setNuclear().explode();
        }
        else
        {
            new BlastNuclear(world, entity, x, y, z, 30, 45).explode();
        }
    }
}
