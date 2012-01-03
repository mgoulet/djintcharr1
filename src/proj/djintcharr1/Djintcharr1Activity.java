package proj.djintcharr1;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Djintcharr1Activity extends Activity {
	
	//Epic game classes
	private GameEngine 		_gameEngine;
	private GameThread 		_gameThread;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
		//create our game
		_gameEngine 	= new GameEngine(this);
		_gameThread 	= new GameThread(_gameEngine);
		
		_gameThread.start();
		
    }

	@Override
	protected void onPause() {
		super.onPause();
		
		_gameThread.suspend();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		_gameThread.resume();
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		_gameThread.stop();
		
		//should clean up engine
		//...

	}    

	public boolean onTouchEvent(MotionEvent me) {
		int ert=0;
		ert++;
	
		return true;
	}	
	
	
    
}