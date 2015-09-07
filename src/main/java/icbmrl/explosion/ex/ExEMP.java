package icbmrl.explosion.ex;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastEMP;

public class ExEMP extends Explosion
{
    public ExEMP()
    {
        super("emp", 3);
        this.modelName = "missile_emp.tcn";
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastEMP(world, entity, x, y, z, 50).setEffectBlocks().setEffectEntities().explode();
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "RBR", "BTB", "RBR", 'T', replsive.getItemStack(), 'R', Block.blockRedstone, 'B', UniversalRecipe.BATTERY.get() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }
}
