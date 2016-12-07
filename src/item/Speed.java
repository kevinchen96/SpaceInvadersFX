package item;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;

public class Speed extends Item{

	public Speed(double x, double y) {
		super(x, y);
		speed_buff = 5;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Circle circ = new Circle(0, 0, 20);
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			10.0, -17.0,
			-10.0, 5.0,
			-3.0, 5.0,
			-10.0, 17.0,
			10.0, 0.0,
			3.0, 0.0,
			10.0, -17.0
			
		});
		circ.setFill(Color.BLACK);
		circ.setStrokeType(StrokeType.CENTERED);
		circ.setStroke(Color.WHITE);
		polyline.setFill(Color.WHITE);
		image.getChildren().addAll(circ, polyline);
	}

}
