package views;

import bullet.EnemyBullet;
import bullet.PlayerBullet;
import enemy.BasicEnemy;
import enemy.Enemy;
import gamestate.ChooseState;
import gamestate.PlayState;
import item.Bullet;
import item.Health;
import item.Item;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ship.Ship;

/**
 * 
 * @author Kevin
 * View for play state. Transitions back to a clean play view or start menu.
 * Displays static view on game end.
 */
public class PlayView extends View {
	private Ship user;
	private SimpleIntegerProperty health; //health property binded to health bar
	private SimpleStringProperty score; //score property binded to score text

	public PlayView(Scene scene, Pane pane, Ship ship, int difficulty) {
		super(scene, pane);
		state = new PlayState(this, ship, difficulty);
		user = ship;
		user.translate(350, 600);
		pane.getChildren().addAll(user.getImage());
	}

	//initializes listeners, creates health bar and score
	public void start() {
		pane.getChildren().remove(0, pane.getChildren().size());
		pane.setPadding(new Insets(0,0,0,0));
		scene.setOnKeyPressed(null);
		initialize();
		createHealthBar();
		createScore();
	}

	//creates score row on the top right. Binded to score property
	private void createScore() {
		// TODO Auto-generated method stub
		Text text = new Text(500, 25, "Score: ");
		Text text2 = new Text(550, 25, "0");
		setWhite(text, text2);
		text2.textProperty().bind(score);
		pane.getChildren().addAll(text, text2);
	}

	//creates health bar on top left. Width binded to health property
	private void createHealthBar() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(25, 25, 100, 10); 
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.WHITE);
		
		Rectangle rect2 = new Rectangle(25,25,100,10);
		rect2.setFill(Color.BLACK);
		rect2.setStroke(Color.WHITE);
		
		rect.widthProperty().bind(health.divide(10));
		pane.getChildren().addAll(rect2, rect);
	}

	//keypressed events for smooth ship movement, initializes properties
	private void initialize() {
		// TODO Auto-generated method stub
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.SPACE) {
				((PlayState) state).setShoot(true);
			} else {
				((PlayState) state).setMove(true, event);
			}
		});
		scene.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.SPACE) {
				((PlayState) state).setShoot(false);
			} else {
				((PlayState) state).setMove(false, null);
			}
		});
		health = new SimpleIntegerProperty(1000);
		score = new SimpleStringProperty("0");
	}

	//adds enemy to pane
	public void setEnemy(Enemy enemy) {
		pane.getChildren().addAll(enemy.getImage());
	}

	//transition for deleting enemy
	public void deleteEnemy(Node n) {
		// TODO Auto-generated method stub
		FadeTransition ft = new FadeTransition(Duration.millis(500), n);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
		ft.setOnFinished(event -> {
			pane.getChildren().remove(n);
		});
	}

	//removes node from pane
	public void remove(Node n) {
		// TODO Auto-generated method stub
		pane.getChildren().remove(n);
	}

	//adds enemy bullets to pane
	public void addEnemyBullet(EnemyBullet... bullets) {
		// TODO Auto-generated method stub
		for(int i = 0; i < bullets.length; i++){
			pane.getChildren().add(bullets[i].getImage());
		}
	}

	//sound for bullet shooting, creates user bullet image and adds to pane
	public void shoot() {
		// TODO Auto-generated method stub
		Media media = new Media(getClass().getClassLoader().getResource("laser.aiff").toString());
		MediaPlayer player = new MediaPlayer(media);
		player.play();
		PlayerBullet bullet = new PlayerBullet(user.getX(), user.getY() - 25, user.getBulletStrength(),
				user.getBulletSpeed());
		((PlayState) state).addBullet(bullet);
		pane.getChildren().add(bullet.getImage());
	}
	
	//updates health bar
	public void hit(int health){
		this.health.set(this.health.get()-health);
		if(this.health.get() <= 0){
			this.health.set(0);
			((PlayState) state).setDead();
			endScreen();
		}
	}

	//end screen for when ship dies
	private void endScreen() {
		// TODO Auto-generated method stub
		scene.setOnKeyPressed(null);
		VBox col = new VBox(10);
		col.setAlignment(Pos.CENTER);
		col.setPadding(new Insets(250, 100, 100, 250));
		
		createScoreRow(col);
		createButtons(col);
		pane.getChildren().add(col);
	}

	//creates restart and menu buttons at end of game
	private void createButtons(VBox col) {
		// TODO Auto-generated method stub
		HBox buttons = new HBox(10);
		Button restart = new Button("Restart");
		restart.setOnMouseClicked(event -> {
			((PlayState) state).setState("PLAY");
			state.setNextState();
		});
		
		Button menu = new Button("Main Menu");
		menu.setOnMouseClicked(event -> {
			((PlayState) state).setState("MENU");
			state.setNextState();
		});

		setButton(restart, menu);
		
		buttons.getChildren().addAll(restart, menu);
		buttons.setAlignment(Pos.CENTER);
		col.getChildren().add(buttons);
	}

	//creates score row at end of game
	private void createScoreRow(VBox col) {
		// TODO Auto-generated method stub
		HBox scorerow = new HBox();
		Text text = new Text("Score: ");
		Text text2 = new Text("");
		text2.textProperty().bind(score);
		text.setStroke(Color.WHITE);
		text.setFill(Color.WHITE);
		text2.setStroke(Color.WHITE);
		text2.setFill(Color.WHITE);
		scorerow.getChildren().addAll(text, text2);
		scorerow.setAlignment(Pos.CENTER);
		col.getChildren().add(scorerow);
	}

	//adds score to score property
	public void addScore(int health2) {
		// TODO Auto-generated method stub
		score.set(Integer.toString((Integer.parseInt(score.get())+health2)));
	}

	//adds item to pane
	public void setItem(Item item) {
		// TODO Auto-generated method stub
		pane.getChildren().addAll(item.getImage());
	}

	//creates buff text that shows up when buff received
	public void buff(Item temp) {
		// TODO Auto-generated method stub
		Text buff = null;
		if(temp instanceof Bullet){
			buff = new Text(350, 500, "+ " + temp.getBullet() + " bullet strength");			
		}
		else if(temp instanceof Health){
			buff = new Text(350, 500, "+ " + temp.getHealth() + " health");	
			this.health.set(this.health.get() + temp.getHealth());
		}
		else{
			buff = new Text(350, 500, "+ " + temp.getSpeed() + " speed");	
		}
		final Text temp2 = buff;
		setWhite(temp2);
		pane.getChildren().add(temp2);
		transitionBuff(temp2);
	}

	//buff text transition
	private void transitionBuff(Text temp2) {
		// TODO Auto-generated method stub
		FadeTransition ft = new FadeTransition(Duration.millis(1000), temp2);
		ft.setFromValue(0);
		ft.setToValue(1.0);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
		ft.setOnFinished(event -> {
			pane.getChildren().remove(temp2);
		});
	}
}
