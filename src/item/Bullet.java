package item;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class Bullet extends Item{

	public Bullet(double x, double y) {
		super(x, y);
		bullet_buff = 15;
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Circle circ = new Circle(0, 0, 20);
		circ.setFill(Color.WHITE);
		circ.setStrokeType(StrokeType.CENTERED);
		circ.setStroke(Color.BLACK);
		Line line1 = new Line(-5, -15, -5, -8);
		Line line2 = new Line(-5, -3, -5, 4);
		Line line3 = new Line(-5, 9, -5, 15);

		Line line4 = new Line(5, -15, 5, -8);
		Line line5 = new Line(5, -3, 5, 4);
		Line line6 = new Line(5, 9, 5, 15);
		
		circ.setFill(Color.BLACK);
		circ.setStrokeType(StrokeType.CENTERED);
		circ.setStroke(Color.WHITE);
		
		line1.setStroke(Color.WHITE);
		line2.setStroke(Color.WHITE);
		line3.setStroke(Color.WHITE);
		line4.setStroke(Color.WHITE);
		line5.setStroke(Color.WHITE);
		line6.setStroke(Color.WHITE);

		image.getChildren().addAll(circ, line1, line2, line3, line4, line5, line6);
	}

}
