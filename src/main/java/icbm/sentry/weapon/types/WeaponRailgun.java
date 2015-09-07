package icbm.sentry.weapon.types;

import icbm.sentry.interfaces.IEnergyWeapon;
import icbm.sentry.interfaces.ITurret;
import icbm.sentry.weapon.WeaponInaccuracy;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import resonant.api.explosion.IEntityExplosion;
import resonant.api.weapon.IAmmunition;
import resonant.api.weapon.ProjectileType;
import resonant.lib.prefab.vector.Cuboid;
import universalelectricity.api.vector.IVector3;
import universalelectricity.api.vector.Vector3;

/** High powered electro magnetic cannon designed to throw a small metal object up to sonic speeds
 * 
 * @author Darkguardsman */
public class WeaponRailgun extends WeaponInaccuracy implements IEnergyWeapon
{
    private long energy = 100000;

    public WeaponRailgun(ITurret sentry)
    {
        this(sentry, 100);
    }

    public WeaponRailgun(ITurret sentry, float damage)
    {
        super(sentry, 1, damage);
    }

    public WeaponRailgun(ITurret sentry, float damage, long energy)
    {
        this(sentry, damage);
        this.energy = energy;
    }

    @Override
    public void onHitEntity(Entity entity)
    {
        super.onHitEntity(entity);
        onHitBlock(Vector3.fromCenter(entity));
    }

    @SuppressWarnings("unused")
    @Override
    public void onHitBlock(Vector3 hit)
    {
        int size = 10;

        /** Kill all active explosives with antimatter. */
        if (false)
        {
            AxisAlignedBB bounds = new Cuboid().expand(50).translate(hit).toAABB();
            List<IEntityExplosion> entities = world().getEntitiesWithinAABB(IEntityExplosion.class, bounds);

            for (IEntityExplosion entity : entities)
            {
                entity.endExplosion();
            }
            size = 20;
        }

        // TODO: Fix this null.
        world().newExplosion((Entity) null, hit.x, hit.y, hit.z, size, true, true);

        Block block = Block.blocksList[world().getBlockId(hit.intX(), hit.intY(), hit.intZ())];
        if (block != null)
        {
            if (block.getBlockHardness(world(), hit.intX(), hit.intY(), hit.intZ()) >= 0)
            {
                return;
            }
            else if (block.getBlockHardness(world(), hit.intX(), hit.intY(), hit.intZ()) < 100000)
            {
                world().setBlockToAir(hit.intX(), hit.intY(), hit.intZ());
            }
        }
    }

    @Override
    public boolean isAmmo(ItemStack stack)
    {
        return super.isAmmo(stack) && ((IAmmunition) stack.getItem()).getType(stack) == ProjectileType.RAILGUN;
    }

    @Override
    public void fire(IVector3 t)
    {
        Vector3 target = new Vector3(t);
        double d = target.distance(turret().fromCenter());
        Vector3 normalized = target.clone().subtract(turret().fromCenter()).normalize();
        target.translate(normalized.scale(8));

        //Loops several times to allow it to punch threw several blocks
        for (int i = 0; i < 5; i++)
        {
            doFire(target.clone().translate(getInaccuracy(d), getInaccuracy(d), getInaccuracy(d)));
        }

        consumeAmmo(itemsConsumedPerShot, true);
    }

    @Override
    public long getEnergyPerShot()
    {
        return this.energy;
    }
}
