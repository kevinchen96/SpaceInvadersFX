package bullet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/**
 * Player Bullet produced from user
 */
public class PlayerBullet extends Bullet{

	public PlayerBullet(double x, double y, int bullet_strength, int bullet_speed) {
		super(x, y, bullet_strength, bullet_speed);
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Line line1 = new Line(0.0, -5.0, 0.0, 5.0);
		line1.setStroke(Color.BLUE);
		line1.setStrokeWidth(2.0);
		image.getChildren().addAll(line1);
	}

}
