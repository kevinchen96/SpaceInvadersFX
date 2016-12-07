package gamestate;

import views.View;

/**
 * 
 * @author Kevin
 * Abstract Game State class, each game state is associated with a view, and
 * a transition variable to track when a state is ready to change.
 */
public abstract class GameState {
	protected View v;
	protected Boolean nextState;
	public GameState(View v){
		this.v = v;
		nextState = false;
	}
	public abstract void action();
	
	public abstract View nextView();
	public void setNextState() {
		nextState = true;		
	}
	public boolean transition() {
		// TODO Auto-generated method stub
		return nextState;
	}

}
