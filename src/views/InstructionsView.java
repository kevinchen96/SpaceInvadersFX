package views;

import gamestate.InstructionState;
import item.Bullet;
import item.Health;
import item.Speed;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * @author Kevin
 * Instructions view
 */
public class InstructionsView extends View {

	public InstructionsView(Scene scene, Pane pane) {
		super(scene, pane);
		// TODO Auto-generated constructor stub
		state = new InstructionState(this);
	}

	//creates back button, instructions, item images, and descriptions of items
	@Override
	public void start() {
		// TODO Auto-generated method stub

		pane.getChildren().remove(0, pane.getChildren().size());
		pane.setPadding(new Insets(50,0,0,50));
		scene.setOnKeyPressed(null);
		
		VBox instructions = new VBox(20);
		createBack(instructions);
		createInstructions(instructions);
		createItems(instructions);
		createDescriptions(instructions);
			
		pane.getChildren().addAll(instructions);
	}

	//Creates description of items in hbox
	private void createDescriptions(VBox instructions) {
		// TODO Auto-generated method stub
		HBox descriptions = new HBox(50);
		Text description1 = new Text("Increases bullet strength");
		Text description2 = new Text("Increases health");
		Text description3 = new Text("Increases bullet and ship speed");
		setWhite(description1, description2, description3);
		
		descriptions.getChildren().addAll(description1, description2, description3);
		descriptions.setAlignment(Pos.CENTER);
		
		instructions.getChildren().add(descriptions);
	}

	//creates items in hbox
	private void createItems(VBox instructions) {
		// TODO Auto-generated method stub
		HBox items = new HBox(150);
		Bullet bullet = new Bullet(0,0);
		Health health = new Health(0,0);
		Speed speed = new Speed(0,0);
		
		items.getChildren().addAll(bullet.getImage(), health.getImage(), speed.getImage());
		items.setAlignment(Pos.CENTER);
		
		instructions.getChildren().add(items);
	}

	//create instructions and adds to existing vbox for vertical structure
	private void createInstructions(VBox instructions) {
		// TODO Auto-generated method stub
		Text text = new Text("Space Invaders FX brings newer elements of modern space invader "
				+ "games to older classic versions.");
		Text text2 = new Text("The goal of the game is to reach the highest score. Score "
				+ "accumulates the longer you survive, and the \nmore enemies you shoot down. "
				+ "If you fail to shoot down enemies, or get hit by enemy bullets, your health \n"
				+ "will decrease. The game ends when your health reaches 0. ");
		Text text3 = new Text("To move your character, use the arrow keys. To attack, "
				+ "press space bar. ");
		Text text4 = new Text("Items will fall that will increase the stats of your ship.");
		setWhite(text, text2, text3, text4);
		instructions.getChildren().addAll(text, text2, text3, text4);
	}

	//creates back button which transitions to start
	private void createBack(VBox instructions) {
		// TODO Auto-generated method stub
		Button back = new Button("Back");
		back.setOnMouseClicked(event -> {
			state.setNextState();
		});
		setButton(back);
		instructions.getChildren().add(back);
		instructions.setMargin(back, new Insets(0,0,100,0));
	}

}
