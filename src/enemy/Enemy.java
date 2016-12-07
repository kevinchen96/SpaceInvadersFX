package enemy;
import ship.Ship;

/**
 * 
 * @author Kevin
 * Abstract enemy class for all enemy AI. Defines additional variables such as bullet number
 * and move state
 */
public abstract class Enemy extends Ship {
	protected int bullet_number;
	protected long shotStart;
	protected String moveState;
	
	public Enemy(double x, double y, long time, String move) {
		super(x, y);
		shotStart = time;
		moveState = move;
	}
	
	public void setTime(long time){
		shotStart = time;
	}
	
	//Determines when enemy should shoot. Predetermined 2 seconds between each shot.
	public boolean checkShotTime(long newTime){
		if((newTime - shotStart)/2 >= 1000000000){
			return true;
		}
		return false;
	}

	public int getBulletNumber() {
		// TODO Auto-generated method stub
		return bullet_number;
	}
	
	//Based on initial movestate, determines whether enemy moves down or in diagonal pattern.
	public void moveDirection(){
		switch(moveState){
			case "DOWN":
				moveDown();
				break;
			case "DIAGONAL":
				moveDiagonal();
				break;
		}
	}
}
