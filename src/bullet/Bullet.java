package bullet;

import entities.Entity;

/***
 * 
 * @author Kevin Chen
 * Abstract bullet class for enemy and user bullets produced by ships
 */
public abstract class Bullet extends Entity{
	protected int strength;
	
	public Bullet(double x, double y, int bullet_strength, int bullet_speed) {
		super(x, y);
		speed = bullet_speed;
		strength = bullet_strength;
	}
	public int getStrength(){
		return strength;
	}
	
}
