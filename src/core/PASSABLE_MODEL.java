package core;

import java.util.UUID;

import rendering.Model;

public class PASSABLE_MODEL implements PassableData<Model> {

	public static final UUID ID=UUID.randomUUID();
	
	private Model value;
	
	
	
	
	protected PASSABLE_MODEL(Model value) {
		this.value=value;
	}
	
	
	
	
	private PASSABLE_MODEL() {
	
	}




	@Override
	public Model getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}




	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "MODEL";
	}
	@Override
	public void setValue(Model value) {
		this.value=value;
		
	}
	public static DATA_HANDLE<Model,PASSABLE_MODEL> getHandle(){
    	return new DATA_HANDLE<Model,PASSABLE_MODEL>(new PASSABLE_MODEL());
    }




	@Override
	public <S extends PassableData<Model>> S getNewType() {
		return (S)new PASSABLE_MODEL();
	}




	@Override
	public String printValue(String indent) {
		return ""+this.value;
	}
	

}
