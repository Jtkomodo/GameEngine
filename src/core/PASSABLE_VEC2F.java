package core;

import org.joml.Vector2f;

public class PASSABLE_VEC2F implements PassableData {

	public Vector2f value;
	
	
	
	
	public PASSABLE_VEC2F(Vector2f value) {
		this.value = value;
	}




	@Override
	public PASSABLE_DATA_TYPE getType() {
		// TODO Auto-generated method stub
		return PASSABLE_DATA_TYPE.VEC2F;
	}

}
