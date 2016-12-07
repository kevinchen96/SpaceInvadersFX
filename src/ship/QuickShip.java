package ship;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class QuickShip extends Ship{
	public QuickShip(double x, double y) {
		super(x, y);
		health = 750;
		speed = 30;
		bullet_speed = 30;
		bullet_strength = 25;
		fullHealth = health;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			0.0, -25.0,
			-15.0, 25.0,
			15.0, 25.0,
			0.0, -25.0
		});
		Polyline polyline2 = new Polyline();
		polyline2.getPoints().addAll(new Double[]{
			-15.0, 25.0,
			-30.0, 35.0,
			-10.0, 10.0
		});
		Polyline polyline3 = new Polyline();
		polyline3.getPoints().addAll(new Double[]{
			15.0, 25.0,
			30.0, 35.0,
			10.0, 10.0
		});
		Line line1 = new Line(-5.0, -8.33, 5.0, -8.33);
		polyline.setFill(Color.WHITE);
		polyline.setStroke(Color.WHITE);
		polyline2.setFill(Color.WHITE);
		polyline2.setStroke(Color.WHITE);
		polyline3.setFill(Color.WHITE);
		polyline3.setStroke(Color.WHITE);
		line1.setStroke(Color.WHITE);
		image.getChildren().addAll(polyline, polyline2, polyline3, line1);
	}
}
