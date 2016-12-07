package ship;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;

public class BigShip extends Ship{

	public BigShip(double x, double y) {
		super(x, y);
		health = 1250;
		speed = 10;
		bullet_speed = 10;
		bullet_strength = 75;
		fullHealth = health;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			-5.0, -30.0,
			5.0, -30.0,
			5.0, -10.0,
			-5.0, -10.0,
			-5.0, -30.0
		});
		
        Circle circ = new Circle(0, 10, 25);
        circ.setFill(Color.WHITE);
        circ.setStrokeType(StrokeType.CENTERED);
		circ.setStroke(Color.WHITE);
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(polyline, circ);
	}

}
