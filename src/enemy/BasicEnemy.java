package enemy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 * 
 * @author Kevin
 * Basic Enemy, fires 2 bullets
 */
public class BasicEnemy extends Enemy{

	public BasicEnemy(double x, double y, long time, String move) {
		super(x, y, time, move);
		health = 100;
		speed = 5;
		bullet_speed = 20;
		bullet_strength = 50;
		bullet_number = 2;
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
		    10.0, 25.0,
		    0.0, 15.0,
		    -10.0, 25.0,
		    -25.0, -5.0,
		    -25.0, -25.0
		    });
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(polyline);
	}

}
