package views;

import gamestate.GameState;
import item.Bullet;
import item.Health;
import item.Item;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * @author Kevin
 * Abstract view class. Associated with gamestates. Each view contains a game state, and
 * the scene and pane for the GUI. 
 */
public abstract class View {
	protected Scene scene;
	protected Pane pane;
	protected GameState state;
	
	public View(Scene scene, Pane pane){
		this.scene = scene;
		this.pane = pane;
		start();
	}
	
	//Sets up visuals and registers listeners
	public abstract void start();
	
	public GameState getState(){
		return state;
	}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}
	
	public Pane getPane(){
		return pane;
	}
	
	//Sets white style for text nodes
	public void setWhite(Text ... n){
		for(Text node: n){
			node.setStroke(Color.WHITE);
			node.setFill(Color.WHITE);
		}
	}
	
	//Sets style for buttons
	public void setButton(Button ... buttons){
		for(Button b: buttons){
			b.setStyle("-fx-font: 22 arial; -fx-base: #000; -fx-border-width: 2px; "
					+ "-fx-border-color: #FFF; -fx-border-radius: 5px; -fx-cursor: hand");
		}
	}
	
}
