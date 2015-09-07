package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.explosion.explosive.blast.BlastFire;

public class ExIncendiary extends Explosion
{
    public ExIncendiary(String mingZi, int tier)
    {
        super(mingZi, tier);
        this.modelName = "missile_incendiary.tcn";
    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "@@@", "@?@", "@!@", '@', "dustSulfur", '?', replsive.getItemStack(), '!', Item.bucketLava }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @Override
    public void onYinZha(World worldObj, Vector3 position, int fuseTicks)
    {
        super.onYinZha(worldObj, position, fuseTicks);
        worldObj.spawnParticle("lava", position.x, position.y + 0.5D, position.z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastFire(world, entity, x, y, z, 14).explode();
    }
}
