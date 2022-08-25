package main.java.core;

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
	@Override
	public void setValue(Vector2f value) {
		 this.value=value;
		
	}
	public static DATA_HANDLE<Vector2f,PASSABLE_VEC2F> getHandle(){
    	return new DATA_HANDLE<Vector2f,PASSABLE_VEC2F>(new PASSABLE_VEC2F());
    }




	@Override
	public <S extends PassableData<Vector2f>> S getNewType() {
		return (S) new PASSABLE_VEC2F();
	}




	@Override
	public String printValue(String indent) {
		return ""+this.value;
	}
	


	

}
