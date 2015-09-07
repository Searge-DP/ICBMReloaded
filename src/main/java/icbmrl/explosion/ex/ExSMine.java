package icbmrl.explosion.ex;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.oredict.ShapedOreRecipe;

import icbmrl.core.common.init.InitModMetadata;
import icbmrl.core.common.lib.ModelICBM;
import icbmrl.explosion.explosive.Explosive;
import icbmrl.explosion.explosive.blast.BlastMine;
import icbmrl.explosion.model.tiles.MDiLei;

public class ExSMine extends Explosive
{
    public ExSMine(String mingZi, int tier)
    {
        super(mingZi, tier);
        this.setYinXin(20);
        this.hasGrenade = false;
        this.hasMinecart = false;
        this.hasMissile = false;
    }

    @Override
    public void onYinZha(World worldObj, Vector3 position, int fuseTicks)
    {

    }

    @Override
    public void init()
    {
        RecipeUtility.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "S", "L", "R", 'S', Explosive.fragmentation.getItemStack(), 'L', Explosive.attractive.getItemStack(), 'R', Explosive.replsive.getItemStack() }), this.getUnlocalizedName(), InitModMetadata.CONFIGURATION, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelICBM getBlockModel()
    {
        return MDiLei.INSTANCE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ResourceLocation getBlockResource()
    {
        return MDiLei.TEXTURE;
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
    {
        new BlastMine(world, entity, x, y, z, 5).explode();
    }

}
