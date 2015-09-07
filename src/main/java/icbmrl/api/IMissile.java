package icbmrl.api;

import net.minecraft.entity.Entity;

import com.sun.javafx.geom.Vec3d;

public interface IMissile
{
	/** Tells the missile to start moving */
	void setIntoMotion();

	// TODO
	void setTarget(Vec3d location, boolean ark);

	void setTarget(Entity entity, boolean track_towards);
}
