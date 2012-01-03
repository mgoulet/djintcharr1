package proj.djintcharr1;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread {

	private GameEngine 		_gameEngine;
	
	private int _currentState;
	
	private long sleepTime;
	
	private long delay = 10;
	
	@Override
	public void run()
	{
		//UPDATE
		while (_currentState == 1)
		{
			long beforeTime = System.nanoTime();
			
			_gameEngine.update();
			
			this.sleepTime = delay - ((System.nanoTime()-beforeTime)/1000000L);
			
			try {

				if (sleepTime>0)
					this.sleep(sleepTime);
				
			} catch (InterruptedException e) {
				Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
			}
		}	
	}	
	
	public GameThread(GameEngine gameEngine)
	{
		this._gameEngine = gameEngine;
		
		this._currentState = 1;
	}
	
}
