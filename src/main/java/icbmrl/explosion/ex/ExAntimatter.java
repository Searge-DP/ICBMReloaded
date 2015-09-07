package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.core.common.lib.ModInfo;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastAntimatter;

public class ExAntimatter extends Explosion
{
    public ExAntimatter()
    {
        super("antimatter", 4);
        this.setYinXin(300);
        this.modelName = "missile_antimatter.tcn";
    }

    /** Called when the explosive is on fuse and going to explode. Called only when the explosive is
     * in it's TNT form.
     * 
     * @param fuseTicks - The amount of ticks this explosive is on fuse */
    @Override
    public void onYinZha(World worldObj, Vector3 position, int fuseTicks)
    {
        super.onYinZha(worldObj, position, fuseTicks);

        if (fuseTicks % 25 == 0)
        {
            worldObj.playSoundEffect(position.x, position.y, position.z, ModInfo.PREFIX + "alarm", 4F, 1F);
        }
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "AAA", "AEA", "AAA", 'E', Explosive.nuclear.getItemStack(), 'A', "antimatterGram" }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastAntimatter(world, entity, x, y, z, InitModMetadata.ANTIMATTER_SIZE, InitModMetadata.DESTROY_BEDROCK).explode();
    }
}
