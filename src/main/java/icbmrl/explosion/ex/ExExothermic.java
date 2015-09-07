package icbmrl.explosion.ex;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastExothermic;

public class ExExothermic extends Explosion
{
    public boolean createNetherrack = true;

    public ExExothermic()
    {
        super("exothermic", 3);
        this.createNetherrack = InitModMetadata.CONFIGURATION.get(Configuration.CATEGORY_GENERAL, "Exothermic Create Netherrack", createNetherrack).getBoolean(createNetherrack);
        this.modelName = "missile_endothermic.tcn";
    }

    @Override
    public void onYinZha(World worldObj, Vector3 position, int fuseTicks)
    {
        super.onYinZha(worldObj, position, fuseTicks);
        worldObj.spawnParticle("lava", position.x, position.y + 0.5D, position.z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "!!!", "!@!", "!!!", '@', Block.glass, '!', Explosive.incendiary.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastExothermic(world, entity, x, y, z, 50).explode();
    }
}
