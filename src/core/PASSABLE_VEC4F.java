package core;
import java.util.UUID;

import org.joml.Vector4f;
public class PASSABLE_VEC4F implements PassableData<Vector4f> {
	public static final UUID ID=UUID.randomUUID();
	public Vector4f value;
	
	
	
	
	
	
	public PASSABLE_VEC4F(Vector4f value) {
		this.value = value;
	}



  



	private  PASSABLE_VEC4F() {
		// TODO Auto-generated constructor stub
	}







	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}







	@Override
	public Vector4f getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}







	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "VEC4F";
	}
	
	public static DATA_HANDLE<PASSABLE_VEC4F> getHandle(){
    	return new DATA_HANDLE<PASSABLE_VEC4F>(new PASSABLE_VEC4F());
    }
	

}
