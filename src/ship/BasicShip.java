package ship;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class BasicShip extends Ship{

	public BasicShip(double x, double y) {
		super(x, y);
		health = 1000;
		speed = 20;
		bullet_speed = 20;
		bullet_strength = 50;
		fullHealth = health;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			0.0, -25.0,
			-25.0, 25.0,
			0.0, 15.0,
			25.0, 25.0,
			0.0, -25.0
		});
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(polyline);
	}

}
