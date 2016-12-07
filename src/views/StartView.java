package views;

import java.util.ArrayList;

import gamestate.PlayState;
import gamestate.StartState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Kevin
 * Start view for the start state. Play and Instructions buttons leads to
 * choose and instructions states
 */
public class StartView extends View {
	
	public StartView(Scene scene, Pane pane){
		super(scene, pane);
		state = new StartState(this);
	}
	
	//creates menu vbox, with title and start and instructions button
	@Override
	public void start() {
		// TODO Auto-generated method stub

		pane.getChildren().remove(0, pane.getChildren().size());
		scene.setOnKeyPressed(null);
		((FlowPane) pane).setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(200, 50, 50, 150));

		VBox menu = new VBox(20);
		setUpTitle(menu);
		createInstructions(menu);
		createStart(menu);
		
		menu.setAlignment(Pos.CENTER);
		pane.getChildren().add(menu);
	}

	//create instructions button
	private void createInstructions(VBox menu) {
		// TODO Auto-generated method stub

		Button instructions = new Button("Instructions");
		setButton(instructions);
		instructions.setOnMouseClicked(event -> {
			((StartState) state).setState("INSTRUCTION");
			state.setNextState();
		});
		menu.getChildren().add(instructions);
	}

	//create start button
	private void createStart(VBox menu) {
		// TODO Auto-generated method stub
		Button start = new Button("Start");
		setButton(start);
		
		start.setOnMouseClicked(event -> {
			((StartState) state).setState("CHOOSE");
			state.setNextState();
		});
		menu.getChildren().add(start);
	}

	//creates title
	private void setUpTitle(VBox menu) {
		// TODO Auto-generated method stub
		Text text = new Text("Welcome to Space Invaders FX!");
		setWhite(text);
		text.setFont(new Font(java.awt.Font.SANS_SERIF, 20));
		menu.getChildren().add(text);
	}
	

}
