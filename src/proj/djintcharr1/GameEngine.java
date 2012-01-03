package proj.djintcharr1;

import proj.djintcharr.GameData.GameData;
import proj.djintcharr1.states.GameIntroState;
import proj.djintcharr1.states.GameState;
import proj.djintcharr1.states.LoadAssetsState;
import proj.djintcharr1.states.MenuState;
import proj.djintcharr1.states.PlayingState;
import android.app.Activity;

public class GameEngine {

	private Activity _activity;
	
	//state array with current state reference.
	private GameState[] 	_gameStates;
	private GameState		_currentState;
	
	public static final int STATE_STOP			= -2;
	public static final int STATE_RESUME		= -1;
	public static final int STATE_LOADASSETS 	= 0;
	public static final int STATE_GAMEINTRO 	= 1;
	public static final int STATE_MENU 			= 2;
	public static final int STATE_PLAYING		= 3;
	public static final int STATE_MAX			= 4;
	
	private GameRenderer	_gameRenderer;
	private GameData		_gameData;
		
	public GameEngine(Activity activity)
	{
		_activity = activity;
		
		//build our renderer
		this._gameRenderer = new GameRenderer(_activity, this);
		
		this._gameData = new GameData();
		
		//Build and initialize our game states
		_gameStates = new GameState[STATE_MAX];
		_gameStates[STATE_LOADASSETS] = new LoadAssetsState(this);
		_gameStates[STATE_GAMEINTRO] = new GameIntroState(this);
		_gameStates[STATE_MENU] = new MenuState(this);
		_gameStates[STATE_PLAYING] = new PlayingState(this);		
		_currentState = _gameStates[STATE_LOADASSETS];
		_currentState.enter();

	}
	
	public void pause() {

	}

	public void resume() {
		
	}

	public void stop() {
		
	}
	
	public boolean update()
	{
		boolean result = true;
		int stateResult = _currentState.update();
		
		if (stateResult == STATE_STOP)
		{
			_currentState.exit();
			result = true;
		}
		else if (stateResult == STATE_RESUME)
		{
			result =  false;
		}
		else
		{
			result = false;
			_currentState.exit();
			_currentState = _gameStates[stateResult];
			_currentState.enter();		
		}		
		
		//false: game incomplete
		//true: game is complete
		return result;
		
	}

	public GameState getCurrentState()
	{
		return _currentState;
	}

	public Activity getActivity()
	{
		return _activity;
	}
	
	public GameRenderer getGameRenderer()
	{
		return _gameRenderer;
	}
	
	public GameData getGameData()
	{
		return _gameData;
	}
	
}



