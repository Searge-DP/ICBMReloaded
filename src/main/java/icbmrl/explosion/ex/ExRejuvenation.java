package icbmrl.explosion.ex;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastRegen;

public class ExRejuvenation extends Explosion
{
    public ExRejuvenation()
    {
        super("rejuvenation", 2);
        this.modelName = "missile_regen.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "ICI", "CDC", "ICI", 'D', Block.blockDiamond, 'C', Item.pocketSundial, 'I', Block.blockIron }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastRegen(world, entity, x, y, z, 16).doExplode();
    }
}
