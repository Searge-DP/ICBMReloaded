package icbmrl.explosion.ex;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastShrapnel;

public class ExShrapnel extends Explosion
{
    public ExShrapnel(String name, int tier)
    {
        super(name, tier);
        if (name.equalsIgnoreCase("shrapnel"))
        {
            this.modelName = "missile_shrapnel.tcn";
        }
        else if (name.equalsIgnoreCase("anvil"))
        {
            this.modelName = "missile_anvil.tcn";
        }
        else if (name.equalsIgnoreCase("fragmentation"))
        {
            this.modelName = "missile_fragment.tcn";
        }
    }

    @Override
    public void init()
    {
        if (this.getID() == Explosive.shrapnel.getID())
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "???", "?@?", "???", '@', replsive.getItemStack(), '?', Item.arrow }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
        }
        else if (this.getID() == Explosive.anvil.getID())
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(10), new Object[] { "SSS", "SAS", "SSS", 'A', Block.anvil, 'S', Explosive.shrapnel.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
        }
        else if (this.getID() == Explosive.fragmentation.getID())
        {
            RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { " @ ", "@?@", " @ ", '?', incendiary.getItemStack(), '@', shrapnel.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
        }
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        if (this.getTier() == 2)
        {
            new BlastShrapnel(world, entity, x, y, z, 15, true, true, false).explode();
        }
        else if (this.getID() == Explosive.anvil.getID())
        {
            new BlastShrapnel(world, entity, x, y, z, 25, false, false, true).explode();
        }
        else
        {
            new BlastShrapnel(world, entity, x, y, z, 30, true, false, false).explode();
        }
    }
}
