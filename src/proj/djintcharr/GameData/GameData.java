package proj.djintcharr.GameData;

import com.threed.jpct.Camera;
import com.threed.jpct.Light;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;


public class GameData {

	public String _introScreenTexture;
	public String _cubeTexture;
	public String _grassTexture;
	
	public Object3D _ship1 = null;
	public Object3D _model2 = null;
	public Object3D _model3 = null;
	
	public Object3D _gameSurface;
	
	public GameEntity _player1 = null;
	public GameEntity _player2 = null;
	public GameEntity _player3 = null;
	
	public World _world = null;
	public Light _sun = null;
	public Camera _cam = null;
	
	public GameData()
	{
		
	}
	
	public void DoStuff()
	{
		int ert=0;
		ert++;
	}
	
	public int GetStuff()
	{
		return 0;
	}


	
}
