package game;
import java.util.ArrayList;

import bullet.EnemyBullet;
import bullet.PlayerBullet;
import enemy.*;
import entities.Entity;
import gamestate.GameState;
import gamestate.PlayState;
import item.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import ship.BasicShip;
import ship.Ship;
import views.GUI;
import views.PlayView;
import views.StartView;
import views.View;

/**
 * 
 * @author Kevin
 * Base controller behind the GUI, uses animation timer to update views
 */
public class GameController {
	private AnimationTimer timer;
	private GUI gui;
	private GameState state; //Current state and view associated to represent parts of game
	private View view;
	public GameController(GUI g){
		gui = g;
		timer =  new AnimationTimer()
		{
            @Override
            public void handle(long now) {
            	//if the state is ready to change, it'll move on to the next state and view
            	if(state.transition()){
            		View temp = state.nextView();            		
            		setState(temp.getState(), temp);
            	}
            	state.action();
            }
        };
  	}
	
	//Starts the game with the start state and view, and begins the timer
	public void startGame(){
		view = new StartView(gui.getScene(), gui.getPane());
		state = view.getState();
		timer.start(); 
	}
	
	//Sets the state and view of the game, and changes the gui's pane if necessary.
	public void setState(GameState state, View view){
		this.state = state;
		this.view = view;
		gui.changePane(view.getPane());
	}
}
