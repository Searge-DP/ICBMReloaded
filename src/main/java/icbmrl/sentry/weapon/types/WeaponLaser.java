package icbmrl.sentry.weapon.types;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.ICBMSentry;
import icbmrl.sentry.interfaces.IEnergyWeapon;
import icbmrl.sentry.interfaces.ITurret;
import icbmrl.sentry.weapon.WeaponDamage;

/** @author DarkGuardsman */
public class WeaponLaser extends WeaponDamage implements IEnergyWeapon
{
    private long energy;

    public WeaponLaser(ITurret sentry, float damage, long energy)
    {
        this(sentry, damage);
        this.soundEffect = ModInfo.PREFIX + "lasershot";
        this.energy = energy;
    }

    public WeaponLaser(ITurret sentry, float damage)
    {
        super(sentry, ObjectDamageSource.doLaserDamage(sentry), damage);
    }

    @Override
    public void fire(Entity entity)
    {
        this.onHitEntity(entity);
        this.playFiringAudio();
    }

    @Override
    public void onHitEntity(Entity entity)
    {
        if (entity != null)
        {
            super.onHitEntity(entity);
            entity.setFire(5);
        }
    }

    @Override
    public void fire(IVector3 target)
    {
        //TODO add tile damage effect vs light tiles like grass
        this.playFiringAudio();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void fireClient(IVector3 hit)
    {
        ICBMSentry.proxy.renderBeam(world(), getBarrelEnd(), hit, 1F, 1F, 1F, 10);
    }

    @Override
    public boolean isAmmo(ItemStack stack)
    {
        return stack != null && CompatibilityModule.isHandler(stack.getItem());
    }

    @Override
    public boolean consumeAmmo(int sum, boolean yes)
    {
        return true;
    }

    @Override
    public long getEnergyPerShot()
    {
        return this.energy;
    }
}
