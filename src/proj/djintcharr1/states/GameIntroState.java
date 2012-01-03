package proj.djintcharr1.states;

import proj.djintcharr1.GameEngine;

import com.threed.jpct.Camera;
import com.threed.jpct.Light;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public class GameIntroState extends GameState {

	public GameIntroState(GameEngine gameEngine) {
		super(gameEngine);

	}

	public boolean enter()
	{		
		_timeSinceStateEntered = System.nanoTime();	
			
		
		return true;
	}
	
	public boolean exit()
	{
		
		return true;
	}
	
	public int update()
	{

		float deltaTimeNormalized = (System.nanoTime() - this._timeSinceStateEntered)/1000000L;
		if (deltaTimeNormalized > 3000)
		{
			return GameEngine.STATE_PLAYING;		
		}	



		//display splash page
		//...

		return GameEngine.STATE_RESUME;

	}
	
}

