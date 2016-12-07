package views;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bullet.EnemyBullet;
import bullet.PlayerBullet;
import enemy.BasicEnemy;
import enemy.BigEnemy;
import enemy.Enemy;
import enemy.QuickEnemy;
import entities.Entity;
import game.GameController;
import gamestate.GameState;
import gamestate.PlayState;
import item.Bullet;
import item.Health;
import item.Speed;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ship.BasicShip;
import ship.BigShip;
import ship.QuickShip;
import ship.Ship;

/**
 * 
 * @author Kevin
 * Base for all gui-related visuals. 
 */
public class GUI extends Application{
	private GameController game;
	private BorderPane borderpane;
	private Group root;
	private Pane flow;
	private Scene scene;
	public View v;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	     root = new Group(); //make the root of the scene graph
		 scene = new Scene(root, 700, 650, Color.BLACK);
		 primaryStage.setTitle("Space Invaders");
		 createPane(root); // creates borderpane with sections
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 root.requestFocus();

		game = new GameController(this);
		game.startGame();
	}
	
	private void createPane(Group root) {
		flow = new FlowPane();
		root.getChildren().addAll(flow);
		 
	}

	public BorderPane getBorderPane(){
		return borderpane;
	}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}
	
	public Pane getPane(){
		return flow;
	}

	public void changePane(Pane pane) {
		// TODO Auto-generated method stub
		root.getChildren().remove(flow);
		root.getChildren().add(pane);
		flow = pane;
	}
}
