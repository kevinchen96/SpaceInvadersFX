package views;

import java.util.ArrayList;
import java.util.Arrays;

import gamestate.ChooseState;
import item.Bullet;
import item.Health;
import item.Speed;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ship.BasicShip;
import ship.BigShip;
import ship.QuickShip;
import ship.Ship;

/**
 * 
 * @author Kevin
 * Choose view. This view represents the state of choosing ship and difficulty of the game
 */

public class ChooseView extends View {
	private VBox selection; //main vbox of the view
	private int curr = 0; //current ship choice, numerical index in list of ships
	private ArrayList<Group> groups; //list of groups containing ship images
	private ArrayList<Ship> ships; //list of ship objects
	private ArrayList<String> difficulties; //list of difficulties
	private ArrayList<Button> buttons; //list of buttons containing difficulties
	private int currDiff = 0; //current difficulty choice
	
	public ChooseView(Scene scene, Pane pane) {
		super(scene, pane);
		// TODO Auto-generated constructor stub
		state = new ChooseState(this);
	}

	//initializes lists, registers listeners, creates back button, ship images/descriptions,
	//difficulty choice, and start button
	@Override
	public void start() {
		// TODO Auto-generated method stub
		initialize();
		pane.getChildren().remove(0, pane.getChildren().size());
		pane.setPadding(new Insets(50,0,0,50));
		scene.setOnKeyPressed(null);
		scene.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.RIGHT){
				changeSelection(1);
			}
			else if(event.getCode() == KeyCode.LEFT){
				changeSelection(-1);
			}
		});
		createBack();
		createShipHeading();
		createShipImages();
		createDescriptions();
		createDifficulty();
		createStart();
		
		pane.getChildren().add(selection);
	}

	//creates start button, leads to play state with chosen ship and difficulty
	private void createStart() {
		// TODO Auto-generated method stub
		Button start = new Button("Start");
		start.setOnMouseClicked(event -> {
			((ChooseState) state).setState("PLAY");
			((ChooseState) state).setShip(ships.get(curr));
			((ChooseState) state).setDifficulty(currDiff);
			state.setNextState();
		});
		setButton(start);
		selection.setMargin(start, new Insets(50, 0, 0, 250));
		selection.getChildren().add(start);
	}

	//creates ship images, adds images to group, preselects first ship
	private void createShipImages() {
		// TODO Auto-generated method stub
		HBox items = new HBox(150);

		Rectangle rect = new Rectangle(-25, -25, 50, 50);    
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.WHITE);
		
		groups.get(0).getChildren().addAll(ships.get(0).getImage(), rect);
		groups.get(1).getChildren().addAll(ships.get(1).getImage());
		groups.get(2).getChildren().addAll(ships.get(2).getImage());
		items.getChildren().addAll(groups.get(0), groups.get(1), groups.get(2));
		
		items.setAlignment(Pos.CENTER);
		selection.getChildren().add(items);
	}

	//Creates ship choice heading
	private void createShipHeading() {
		// TODO Auto-generated method stub
		Text text = new Text("Choose your ship (Use right/left keys)");
		text.setFont(new Font(java.awt.Font.SANS_SERIF, 20));
		((FlowPane) pane).setAlignment(Pos.CENTER);
		setWhite(text);
		selection.getChildren().add(text);
	}

	//creates back button to start menu
	private void createBack() {
		// TODO Auto-generated method stub
		Button back = new Button("Back");
		back.setOnMouseClicked(event -> {
			((ChooseState) state).setState("START");
			state.setNextState();
		});
		setButton(back);
		selection.getChildren().add(back);
		selection.setMargin(back, new Insets(0,0,50,0));
	}

	//initializes objects and lists
	private void initialize() {
		// TODO Auto-generated method stub
		selection = new VBox(20);
		
		BasicShip basicship = new BasicShip(0,0);
		BigShip bigship = new BigShip(0,0);
		QuickShip quickship = new QuickShip(0,0);
		
		Group basicshipgroup = new Group();
		Group bigshipgroup = new Group();
		Group quickshipgroup = new Group();
		
		difficulties = new ArrayList<String>(Arrays.asList("Easy", "Medium", "Hard"));
		ships = new ArrayList<Ship>(Arrays.asList(basicship, bigship, quickship));
		groups = new ArrayList<Group>(Arrays.asList(basicshipgroup, bigshipgroup, quickshipgroup));

		buttons = new ArrayList<Button>();
	}
	
	//creates Difficulty choice
	private void createDifficulty() {
		// TODO Auto-generated method stub
		HBox difficulty = new HBox(50);
		createDifficultyString(difficulty);
		createDifficultyButtons(difficulty);
		
		selection.getChildren().add(difficulty);
		selection.setMargin(difficulty, new Insets(50, 0, 0, 0));
	}

	//creates Difficulty buttons
	private void createDifficultyButtons(HBox difficulty) {
		// TODO Auto-generated method stub
		for(String s: difficulties){
			Button button = new Button(s);
			button.setStyle("-fx-font: 16 arial; -fx-base: #000; -fx-border-width: 2px; "
					+ "-fx-border-color: #FFF; -fx-border-radius: 5px; -fx-cursor: hand; -fx-text-fill: #FFF;");
	
			button.setOnMouseClicked(event -> {
				buttons.get(currDiff).setStyle("-fx-font: 16 arial; -fx-base: #000; -fx-border-width: 2px; "
						+ "-fx-border-color: #FFF; -fx-border-radius: 5px; -fx-cursor: hand; -fx-text-fill: #FFF;");
				button.setStyle("-fx-font: 16 arial; -fx-base: #FFF; -fx-border-width: 2px; "
						+ "-fx-border-color: #FFF; -fx-border-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000;");
				currDiff = buttons.indexOf(button);
			});
			
			buttons.add(button);
			difficulty.getChildren().add(button);
			difficulty.setAlignment(Pos.CENTER);
		}
		buttons.get(currDiff).setStyle("-fx-font: 16 arial; -fx-base: #FFF; -fx-border-width: 2px; "
				+ "-fx-border-color: #FFF; -fx-border-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000;");
	}

	//creates difficulty choice string
	private void createDifficultyString(HBox difficulty) {
		// TODO Auto-generated method stub
		Text text = new Text("Choose Difficulty: ");
		text.setFont(new Font(java.awt.Font.SANS_SERIF, 20));
		setWhite(text);
		difficulty.getChildren().add(text);
	}

	//creates descriptions of all ship stats
	private void createDescriptions() {
		// TODO Auto-generated method stub
		HBox descriptions = new HBox(100);
		for(Ship ship: ships){
			VBox description = new VBox();
			Text description1 = new Text("Health: " + ship.getHealth());
			Text description2 = new Text("Ship Speed: " + ship.getSpeed());
			Text description3 = new Text("Bullet Speed: " + ship.getBulletSpeed());
			Text description4 = new Text("Bullet Strength: " + ship.getBulletStrength());
			
			setWhite(description1, description2, description3, description4);
			description.getChildren().addAll(description1, description2, 
					description3, description4);
			descriptions.getChildren().add(description);
		}
		descriptions.setAlignment(Pos.CENTER);
		selection.getChildren().add(descriptions);
	}

	//changes ship selection, logic for pressing left on the first ship and right on the last
	private void changeSelection(int i) {
		// TODO Auto-generated method stub
		groups.get(curr).getChildren().remove(1);
		curr += i;
		if(curr == 3){
			curr = 0;
		}
		else if(curr == -1){
			curr = 2;
		}
		Rectangle rect = new Rectangle(-25, -25, 50, 50);    
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.WHITE);
		
		groups.get(curr).getChildren().add(rect);
		
	}

}
