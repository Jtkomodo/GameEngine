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
	@Override
	public void setValue(Vector4f value) {
		this.value=value;
		
	}
	public static DATA_HANDLE<Vector4f,PASSABLE_VEC4F> getHandle(){
    	return new DATA_HANDLE<Vector4f,PASSABLE_VEC4F>(new PASSABLE_VEC4F());
    }







	@Override
	public <S extends PassableData<Vector4f>> S getNewType() {
		return (S)new PASSABLE_VEC4F();
	}







	@Override
	public String printValue(String indent) {
		return ""+this.value;
	}
	

}
