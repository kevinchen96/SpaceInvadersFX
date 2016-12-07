package bullet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * Enemy Bullet produced from enemy ships
 */
public class EnemyBullet extends Bullet{

	public EnemyBullet(double x, double y, int bullet_strength, int bullet_speed) {
		super(x, y, bullet_strength, bullet_speed);
	}

	@Override
	public void setUpImage() {
		// TODO Auto-generated method stub
		Circle circ = new Circle(0, 0, 5);
		circ.setFill(Color.RED);
		image.getChildren().addAll(circ);
	}

}
