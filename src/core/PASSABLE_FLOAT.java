package core;

import java.util.UUID;

public class PASSABLE_FLOAT implements PassableData<Float> {

	public static UUID ID=UUID.randomUUID();
	private float value=0;
	
	
	private PASSABLE_FLOAT() {
		
	}
	protected PASSABLE_FLOAT(float value) {
		this.value=value;
	}
	
	
	
	public static DATA_HANDLE<Float,PASSABLE_FLOAT> getHandle(){
		return new DATA_HANDLE<Float,PASSABLE_FLOAT>(new PASSABLE_FLOAT());
	}
	
	

	@Override
	public <S extends PassableData<Float>> S getNewType() {
		return (S)new PASSABLE_FLOAT();
	}

	@Override
	public void setValue(Float value) {
	   this.value=value;
		
	}

	@Override
	public String getType() {
		return "float";
	}

	@Override
	public Float getValue() {
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}

	@Override
	public String printValue(String indent) {
		return ""+this.value+"("+Math.round(value)+")";
	}
	
	
	
	
	
	

}
