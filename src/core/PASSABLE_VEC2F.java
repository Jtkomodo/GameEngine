package core;

import java.util.UUID;

import org.joml.Vector2f;

public class PASSABLE_VEC2F implements PassableData<Vector2f> {
	public static final UUID ID=UUID.randomUUID();
	public Vector2f value;
	
	
	
	
	
    public PASSABLE_VEC2F(Vector2f value) {
		this.value = value;
	}




	private PASSABLE_VEC2F() {
	
	}




	@Override
	public UUID getDATAID() {
		return ID;
	}




	@Override
	public Vector2f getValue() {
		return this.value;
	}




	@Override
	public String getType() {
		return "VEC2F";
	}

	public static DATA_HANDLE<PASSABLE_VEC2F> getHandle(){
    	return new DATA_HANDLE<PASSABLE_VEC2F>(new PASSABLE_VEC2F());
    }
	


	

}
