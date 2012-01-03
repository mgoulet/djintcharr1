package proj.djintcharr1.states;

import proj.djintcharr1.GameEngine;
import proj.djintcharr1.R;
import android.content.res.Resources;

import com.threed.jpct.Camera;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

public class LoadAssetsState extends GameState {
	
	public LoadAssetsState(GameEngine gameEngine) {
		super(gameEngine);
		// TODO Auto-generated constructor stub

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
		loadSomeTextures();
		
		loadModels();
	
		initGraphics();
		
		return GameEngine.STATE_GAMEINTRO;
			
	}

	public void loadSomeTextures()
	{
		
		Resources res = _gameEngine.getActivity().getResources();
		
		//loading screen texture
		_gameEngine.getGameData()._introScreenTexture = "loadingScreenTexture";
		Texture introScreenTexture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(_gameEngine.getActivity().getResources().getDrawable(R.raw.intro_image)), 64, 64));
		TextureManager.getInstance().addTexture(_gameEngine.getGameData()._introScreenTexture, introScreenTexture);

		_gameEngine.getGameData()._cubeTexture = "cubeTexture";
		Texture cubeTexture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(
				_gameEngine.getActivity().getResources().getDrawable(R.raw.intro_image)), 64, 64));
		TextureManager.getInstance().addTexture(_gameEngine.getGameData()._cubeTexture, cubeTexture);
	
		_gameEngine.getGameData()._grassTexture = "planetex";
		Texture grassTexture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(
				_gameEngine.getActivity().getResources().getDrawable(R.raw.planetex)), 64, 64));
		TextureManager.getInstance().addTexture(_gameEngine.getGameData()._grassTexture, grassTexture);
		
		MemoryHelper.compact();
	}
	
	public void loadModels()
	{
		Resources res = _gameEngine.getActivity().getResources();
		
		_gameEngine.getGameData()._ship1 = Loader.loadMD2(res.openRawResource(R.raw.viper),0.1f);
		
	}
	
	public void initGraphics()
	{
		_gameEngine.getGameData()._world = new World();
		_gameEngine.getGameData()._world.setAmbientLight(20, 20, 20);
		
		_gameEngine.getGameData()._sun = new Light(_gameEngine.getGameData()._world);
		_gameEngine.getGameData()._sun.setIntensity(250, 250, 250);
		
		SimpleVector sv = new SimpleVector();
		sv.set(0, -100,-100);
		_gameEngine.getGameData()._sun.setPosition(sv);

	}
	
}
