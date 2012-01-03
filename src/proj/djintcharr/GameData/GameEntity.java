package proj.djintcharr.GameData;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

public class GameEntity {

	private Object3D _model;
	private int _modelId;
	private SimpleVector _pos;
	private SimpleVector _dir;
	private SimpleVector _nor;
	
	public GameEntity()
	{
		_pos = new SimpleVector(0,0,0);
		_dir = new SimpleVector(0,0,1);
		_nor = new SimpleVector(0,1,0);
	}
	
	public GameEntity(SimpleVector pos, SimpleVector dir, SimpleVector nor)
	{
		_pos = pos;
		_dir = dir;
		_nor = nor;
		
	}
	
	public SimpleVector getPos()
	{
		return _pos;
	}
	public SimpleVector getDir()
	{
		return _dir;
	}
	public SimpleVector getNor()
	{
		return _nor;
	}
	public void setPos(SimpleVector vec)
	{
		_pos = vec;
	}
	public void setDir(SimpleVector vec)
	{
		_dir = vec;
	}
	public void setNor(SimpleVector vec)
	{
		_nor = vec;
	}
	public void setModel(Object3D model)
	{
		_model = model;
	}
	public Object3D getModel()
	{
		return _model;
	}
	public void setModelId(int modelId)
	{
		_modelId = modelId;
	}
	public int getModelId()
	{
		return _modelId;
	}

}
