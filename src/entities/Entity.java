package entities;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * 
 * @author Kevin
 * Abstract entity class encapsulates all data used by all entities in the game.
 * Most have to deal with movements, animations, and coordinates.
 */
public abstract class Entity {
	// X and Y center coordinates for the entity
	protected double xCoordinate;
	protected double yCoordinate;
	protected int speed;
	protected String state;

	// Drawing of image
	protected Group image;

	public Entity(double x, double y) {
		xCoordinate = x;
		yCoordinate = y;
		image = new Group();
		setUpImage();
		setOrigin();
		setScale(50);
		state = "left";
	}

	public double getX() {
		return xCoordinate;
	}

	public double getY() {
		return yCoordinate;
	}

	// Draws the image with fx shapes and lines
	public abstract void setUpImage();

	public Group getImage() {
		return image;
	}

	public void setOrigin() {
		image.setLayoutX(xCoordinate);
		image.setLayoutY(yCoordinate);
	}

	// Scales image
	public void setScale(int parseInt) {
		image.setScaleX(parseInt / 100.0);
		image.setScaleY(parseInt / 100.0);
	}

	public void move(double x, double y) {
		image.setLayoutX(xCoordinate + x/10);
		image.setLayoutY(yCoordinate + y/10);
		xCoordinate += x/10;
		yCoordinate += y/10;
	}

	public void moveDown() {
		move(0, speed);
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		move(0, -speed);
	}

	//Diagonal movement pattern, checks between bounds and directional states.
	public void moveDiagonal() {
		// TODO Auto-generated method stub
		if (state.equals("left")) {
			if (image.getBoundsInParent().intersects(0, 0, 0, 500)) {
				move(speed, speed);
				state = "right";
			} else {
				move(-speed, speed);
			}
		} else if (state.equals("right")) {
			if (image.getBoundsInParent().intersects(700, 0, 700, 500)) {
				move(-speed, speed);
				state = "left";
			} else {
				move(speed, speed);
			}
		}

	}
}
