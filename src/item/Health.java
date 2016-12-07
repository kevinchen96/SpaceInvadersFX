package item;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;

public class Health extends Item{

	public Health(double x, double y) {
		super(x, y);
		health_buff = 50;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Circle circ = new Circle(0, 0, 20);
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			-2.0, -17.0,
			2.0, -17.0,
			2.0, -2.0,
			17.0, -2.0,
			17.0, 2.0,
			2.0, 2.0,
			2.0, 17.0,
			-2.0, 17.0,
			-2.0, 2.0,
			-17.0, 2.0,
			-17.0, -2.0,
			-2.0, -2.0,
			-2.0, -17.0
		});
      circ.setFill(Color.BLACK);
      circ.setStrokeType(StrokeType.CENTERED);
      circ.setStroke(Color.WHITE);
      polyline.setFill(Color.WHITE);
      image.getChildren().addAll(circ, polyline);
	}
	
}
