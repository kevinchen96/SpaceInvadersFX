package ship;

import entities.Entity;
import item.Item;
import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * 
 * @author Kevin
 * Abstract ship class, contains movement information, bounds, and qualities of ship
 */
public abstract class Ship extends Entity {
	protected int health;
	protected int bullet_speed;
	protected int bullet_strength;
	protected int fullHealth;
	
	public Ship(double x, double y){
		super(x, y);
	}
	
	//move if within bounds
	public void moveShip(KeyEvent event){
		switch (event.getCode()){
			case RIGHT:
				if(image.getBoundsInParent().getMaxX() < 680){
					moveAnimation(speed, 0);
				}
				break;
			case LEFT:
				if(image.getBoundsInParent().getMinX() > 20){
					moveAnimation(-1*speed, 0);
				}
				break;
			case UP:
				if(image.getBoundsInParent().getMinY() > 20){
					moveAnimation(0, -1*speed);
				}
				break;
			case DOWN:
				if(image.getBoundsInParent().getMaxY() < 630){
					moveAnimation(0, speed);
				}
				break;
			default:
				break;
		}
	}
	
	//move animation performed to make smooth transitions
	public void moveAnimation(int x, int y) {
		// TODO Auto-generated method stub
		TranslateTransition tt = new
		TranslateTransition(Duration.millis(100), image);
		tt.setByX(x);
		tt.setByY(y);
		
		tt.play();
		xCoordinate += x;
		yCoordinate += y;
	}
	
	public double getX(){
		return image.getBoundsInParent().getMinX() + (image.getBoundsInParent().getMaxX() - image.getBoundsInParent().getMinX())/2;
	}
	
	public double getY(){
		return image.getBoundsInParent().getMinY() + (image.getBoundsInParent().getMaxY() - image.getBoundsInParent().getMinY())/2;
	}
	
	public int getBulletStrength(){
		return bullet_strength;
	}
	
	public int getHealth(){
		return health;
	}
	public int getBulletSpeed() {
		// TODO Auto-generated method stub
		return bullet_speed;
	}
	public void translate(int i, int j) {
		// TODO Auto-generated method stub
		image.setLayoutX(i);
		image.setLayoutY(j);
	}

	//update health when hit by bullet
	public void hit(int health2) {
		// TODO Auto-generated method stub
		health -= health2;
	}
	
	//applies buff to ship
	public void buff(Item temp) {
		// TODO Auto-generated method stub
		health+=temp.getHealth();
		if(health > fullHealth){
			health = fullHealth;
		}
		speed+=temp.getSpeed();
		bullet_speed+=temp.getSpeed();
		bullet_strength+=temp.getBullet();	
	}	
	public int getFullHealth(){
		return fullHealth;
	}
	public int getSpeed(){
		return speed;
	}
}
