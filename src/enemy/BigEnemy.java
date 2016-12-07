package enemy;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
/**
 * 
 * @author Kevin
 * Big Enemy fires 3 bullets, and is tankier, stronger, and slower than Basic Enemy
 */
public class BigEnemy extends Enemy{

	public BigEnemy(double x, double y, long time, String move) {
		super(x, y, time, move);
		health = 150;
		speed = 2;
		bullet_speed = 15;
		bullet_strength = 75;
		bullet_number = 3;
		fullHealth = health;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
				-25.0, -25.0,
			    0.0, -15.0,
			    25.0, -25.0,
			    25.0, -5.0,
			    40.0, 15.0,
			    10.0, 0.0,
			    0.0, 25.0,
			    -10.0, 0.0,
			    -40.0, 15.0,
			    -25.0, -5.0,
			    -25.0, -25.0
		    });
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(polyline);
	}

}
