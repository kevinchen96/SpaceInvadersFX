package gamestate;

import javafx.scene.layout.Pane;
import ship.Ship;
import views.ChooseView;
import views.InstructionsView;
import views.PlayView;
import views.StartView;
import views.View;
/**
 * 
 * @author Kevin
 * Choose State for choosing characters and difficulty. Transitions back to start state or
 * play state
 */
public class ChooseState extends GameState {
	private String next;
	private Ship ship;
	private int difficulty;
	public ChooseState(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	public void setState(String input){
		next = input;
	}
	
	@Override
	public View nextView() {
		// TODO Auto-generated method stub
		switch(next){
			case "START":
				return new StartView(v.getScene(), v.getPane());
			case "PLAY":
				return new PlayView(v.getScene(), new Pane(), ship, difficulty);		
			default:
				return null;
		}
	}

	//Sets ship to use for play state
	public void setShip(Ship ship) {
		// TODO Auto-generated method stub
		this.ship = ship;
	}

	//Sets difficulty to use for play state
	public void setDifficulty(int currDiff) {
		// TODO Auto-generated method stub
		this.difficulty = currDiff;
	}

}
