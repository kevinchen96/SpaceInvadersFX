package gamestate;

import views.ChooseView;
import views.InstructionsView;
import views.StartView;
import views.View;

/**
 * 
 * @author Kevin
 * Start state for the game. Transitions to Choose state or Instructions state
 */
public class StartState extends GameState {
	private String next;
	public StartState(View v) {
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
			case "INSTRUCTION":
				return new InstructionsView(v.getScene(), v.getPane());
			case "CHOOSE":
				return new ChooseView(v.getScene(), v.getPane());		
			default:
				return null;
		}
		
	}

}
