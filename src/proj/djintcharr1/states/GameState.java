package proj.djintcharr1.states;

import proj.djintcharr1.GameEngine;

public abstract class GameState {

	GameEngine _gameEngine;
	
	long _timeSinceStateEntered;

	public GameState(GameEngine gameEngine)
	{
		this._gameEngine = gameEngine;
		_timeSinceStateEntered = 0;
	}

	public boolean enter()
	{		
		return true;
	}

	public boolean exit()
	{
		return true;
	}

	public int update()
	{
		return GameEngine.STATE_RESUME;
	}

}
