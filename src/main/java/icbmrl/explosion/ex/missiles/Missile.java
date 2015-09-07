package icbmrl.explosion.ex.missiles;

import net.minecraft.item.ItemStack;

import icbmrl.explosion.ICBMExplosion;
import icbmrl.explosion.ex.Explosion;

/** Ex object that are only defined as missiles
 * 
 * @author Calclavia */
public abstract class Missile extends Explosion
{
    public Missile(String name, int tier)
    {
        super(name, tier);
        this.hasBlock = false;
        this.hasGrenade = false;
        this.hasMinecart = false;
    }

    @Override
    public ItemStack getItemStack()
    {
        return new ItemStack(ICBMExplosion.itemMissile, 1, this.getID());
    }
}
