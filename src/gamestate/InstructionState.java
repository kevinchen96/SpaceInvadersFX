package gamestate;

import views.StartView;
import views.View;

/**
 * 
 * @author Kevin
 * Instructions state. Transitions back to start state
 */
public class InstructionState extends GameState {

	public InstructionState(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public View nextView() {
		// TODO Auto-generated method stub
		return new StartView(v.getScene(), v.getPane());
	}

}
