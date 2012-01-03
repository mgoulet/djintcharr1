package proj.djintcharr1;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.RGBColor;
import com.threed.jpct.Texture;
import com.threed.jpct.World;

public class GameRenderer implements GLSurfaceView.Renderer {
	
	private Activity _activity;
	private GameEngine _gameEngine; 
	
	private GLSurfaceView _GLView;
	private FrameBuffer _fb = null;
	private RGBColor _back = new RGBColor(50, 50, 100);
	
	private Texture font = null;
	
	private int _fps;
	private int _lfps;
	private long time = System.currentTimeMillis();
	
	public GameRenderer(Activity activity, GameEngine gameEngine)
	{
		super();
		
		_fps = 0;
		
		_activity = activity;
		_gameEngine = gameEngine;
		
		_GLView = new GLSurfaceView(activity.getApplication());
		
		_GLView.setEGLConfigChooser(new GLSurfaceView.EGLConfigChooser() {
			public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
				// Ensure that we get a 16bit framebuffer. Otherwise, we'll fall
				// back to Pixelflinger on some device (read: Samsung I7500)
				int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
				EGLConfig[] configs = new EGLConfig[1];
				int[] result = new int[1];
				egl.eglChooseConfig(display, attributes, configs, 1, result);
				return configs[0];
			}
		});
		
		_GLView.setRenderer(this);
		activity.setContentView(_GLView);
		
		try
		{
			Resources res = _activity.getResources();
			font = new Texture(res.openRawResource(R.raw.numbers));
			font.setMipmap(false);
		} catch (Resources.NotFoundException e)
		{
			Logger.log("ERROR: Cannot find font resource.");	
		}
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub

		if (_fb != null) {
			_fb.dispose();
		}
		_fb = new FrameBuffer(gl, w, h);
		
	}
	
	@Override
	public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDrawFrame(GL10 gl) {

		//if either of these is false, renderer not ready
		if ( (_fb == null) || (_gameEngine.getGameData()._world == null) )
			return;
		
		calculateFps();
		
		_fb.clear(_back);
		_gameEngine.getGameData()._world.renderScene(_fb);
		_gameEngine.getGameData()._world.draw(_fb);
		blitNumber(_lfps, 5, 5);
		blitNumber(_lfps, 5, _GLView.getHeight() - 10);
		_fb.display();
		
	}	

	public GLSurfaceView getGLSurfaceView()
	{
		return _GLView;
	}

	public RGBColor getRGBColor()
	{
		return _back;
	}
	
	public FrameBuffer getFrameBuffer()
	{
		return _fb;
	}
	
	public void setFrameBuffer(FrameBuffer fb)
	{
		this._fb = fb;
	}
	
	private void calculateFps()
	{		
		if (System.currentTimeMillis() - time >= 1000) {
			_lfps = _fps;
			_fps = 0;
			time = System.currentTimeMillis();
		}
		_fps++;
		
	}
	
	private void blitNumber(int number, int x, int y) {
		if (font != null) {
			String sNum = Integer.toString(number);
			
			for (int i = 0; i < sNum.length(); i++) {
				char cNum = sNum.charAt(i);
				int iNum = cNum - 48;
				_fb.blit(font, iNum * 5, 0, x, y, 5, 9, FrameBuffer.TRANSPARENT_BLITTING);
				x += 5;
			}
		}
	}
	
	
	
}
