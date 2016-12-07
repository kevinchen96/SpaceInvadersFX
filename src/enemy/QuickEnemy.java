package enemy;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 * 
 * @author Kevin
 * QuickEnemy fires a single bullet, and is weaker, but faster than BasicEnemy.
 */
public class QuickEnemy extends Enemy {

	public QuickEnemy(double x, double y, long time, String move) {
		super(x, y, time, move);
		health = 50;
		speed = 10;
		bullet_speed = 30;
		bullet_strength = 25;
		bullet_number = 1;
		fullHealth = health;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			-15.0, -25.0,
		    0.0, -15.0,
		    15.0, -25.0,
		    15.0, -5.0,
		    0.0, 25.0,
		    -15.0, -5.0,
		    -15.0, -25.0
		});
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(polyline);
	}
	
}
