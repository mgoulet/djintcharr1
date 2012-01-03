package proj.djintcharr1.states;

import proj.djintcharr.GameData.GameEntity;
import proj.djintcharr1.GameEngine;

import com.threed.jpct.Camera;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import com.threed.jpct.util.MemoryHelper;

public class PlayingState extends GameState {

	public PlayingState(GameEngine gameEngine) {
		super(gameEngine);
		// TODO Auto-generated constructor stub
	}

	public boolean enter()
	{
		_timeSinceStateEntered = System.nanoTime();

		//build player 1
		_gameEngine.getGameData()._player1 = new GameEntity();
		_gameEngine.getGameData()._player1.setPos(new SimpleVector(0,0,0));
		_gameEngine.getGameData()._player1.setDir(new SimpleVector(0,0,1));
		_gameEngine.getGameData()._player1.setNor(new SimpleVector(0,1,0));
		_gameEngine.getGameData()._player1.setModel(_gameEngine.getGameData()._ship1.cloneObject());
		_gameEngine.getGameData()._player1.getModel().setOrigin(_gameEngine.getGameData()._player1.getPos());
		_gameEngine.getGameData()._player1.getModel().setOrientation(_gameEngine.getGameData()._player1.getDir(), _gameEngine.getGameData()._player1.getNor());

		_gameEngine.getGameData()._world.addObject(_gameEngine.getGameData()._player1.getModel());

		//build player 2
		_gameEngine.getGameData()._player2 = new GameEntity();
		_gameEngine.getGameData()._player2.setPos(new SimpleVector(0,0,50));
		_gameEngine.getGameData()._player2.setDir(new SimpleVector(0,0,1));
		_gameEngine.getGameData()._player2.setNor(new SimpleVector(0,1,0));
		_gameEngine.getGameData()._player2.setModel(_gameEngine.getGameData()._ship1.cloneObject());
		_gameEngine.getGameData()._player2.getModel().setOrigin(_gameEngine.getGameData()._player2.getPos());
		_gameEngine.getGameData()._player2.getModel().setOrientation(_gameEngine.getGameData()._player2.getDir(), _gameEngine.getGameData()._player2.getNor());

		_gameEngine.getGameData()._world.addObject(_gameEngine.getGameData()._player2.getModel());	
			
		//build player 3
		_gameEngine.getGameData()._player3 = new GameEntity();
		_gameEngine.getGameData()._player3.setPos(new SimpleVector(0,0,-50));
		_gameEngine.getGameData()._player3.setDir(new SimpleVector(0,0,1));
		_gameEngine.getGameData()._player3.setNor(new SimpleVector(0,1,0));
		_gameEngine.getGameData()._player3.setModel(_gameEngine.getGameData()._ship1.cloneObject());
		_gameEngine.getGameData()._player3.getModel().setOrigin(_gameEngine.getGameData()._player3.getPos());
		_gameEngine.getGameData()._player3.getModel().setOrientation(_gameEngine.getGameData()._player3.getDir(), _gameEngine.getGameData()._player3.getNor());

		_gameEngine.getGameData()._world.addObject(_gameEngine.getGameData()._player3.getModel());	
		
		//init game plane
		_gameEngine.getGameData()._gameSurface = Primitives.getPlane(1, 1000);
		_gameEngine.getGameData()._gameSurface.setTexture(_gameEngine.getGameData()._grassTexture);
		_gameEngine.getGameData()._gameSurface.rotateX((float)Math.PI / 2.0f);
		//_gameEngine.getGameData()._gameSurface.translate(0, 10, 0);
		_gameEngine.getGameData()._world.addObject(_gameEngine.getGameData()._gameSurface);

		//init camera
		Camera cam = _gameEngine.getGameData()._world.getCamera();
		cam.setPosition(_gameEngine.getGameData()._player1.getPos());
		cam.setOrientation(_gameEngine.getGameData()._player1.getDir(), _gameEngine.getGameData()._player1.getNor());
			
		cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
		cam.lookAt(_gameEngine.getGameData()._player1.getModel().getTransformedCenter());
		_gameEngine.getGameData()._world.setCameraTo(cam);
		
		return true;
	}
	
	public boolean exit()
	{
		//cleanup
		_gameEngine.getGameData()._world.dispose();
		
		return true;
	}
	
	public int update()
	{		
		_gameEngine.getGameData()._player1.getModel().rotateY(0.01f);
		_gameEngine.getGameData()._player2.getModel().rotateY(-0.01f);
		_gameEngine.getGameData()._player3.getModel().rotateY(-0.01f);
		
		
		//Camera cam = new Camera();// = _gameEngine.getGameData()._world.getCamera();
		//_gameEngine.getGameData()._world.getCamera().setPosition(_gameEngine.getGameData()._player1.getPos());
		//_gameEngine.getGameData()._world.getCamera().setOrientation(_gameEngine.getGameData()._player1.getDir(), _gameEngine.getGameData()._player1.getNor());
		//_gameEngine.getGameData()._world.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, 50);
		//_gameEngine.getGameData()._world.getCamera().moveCamera(Camera.CAMERA_MOVEUP, 30);
		SimpleVector svPos = _gameEngine.getGameData()._player1.getModel().getOrigin();
		SimpleVector svDir = _gameEngine.getGameData()._player1.getModel().getXAxis();
		SimpleVector svNor = _gameEngine.getGameData()._player1.getModel().getYAxis();
		
		svDir.add(svPos);
		
		Camera cam = new Camera();
		cam.setPosition(svPos);
		cam.setOrientation(svDir, new SimpleVector(0,1,0));
		
		//_gameEngine.getGameData()._world.getCamera().moveCamera(Camera.CAMERA_MOVEOUT, 50);
		cam.moveCamera(Camera.CAMERA_MOVEUP, -25);
		cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
		
		cam.lookAt(svPos);
		
		
		_gameEngine.getGameData()._world.setCameraTo(cam);
		
		//_gameEngine.getGameData()._player1.getModel().setVisibility(false);
		
		return GameEngine.STATE_RESUME;
			
	}
	
}
