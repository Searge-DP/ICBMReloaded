package icbmrl.api;

public interface IAmmoType
{
	/**
	 * Primary group the ammo counts as for example "Rocket", "Missile",
	 * "Bullet", "Shell"
	 * 
	 * @return valid string value
	 */
	String getCategory();

	/**
	 * Gets the type of the ammo for example "9mm", "12Gauge", "120mm"
	 * 
	 * @return valid string value
	 */
	String getType();

	/** Name to use for translation */
	String getUnlocalizedName();
}
