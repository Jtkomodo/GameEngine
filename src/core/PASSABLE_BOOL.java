package core;

import java.util.UUID;

public class PASSABLE_BOOL implements PassableData<Boolean> {

	
	public static final UUID ID=UUID.randomUUID();
	
	
	private boolean value=false;
	
	
    private PASSABLE_BOOL() {
    	
    }
	
	

	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public String getType() {
		return "BOOL";
	}
    
	@Override
	public void setValue(Boolean value) {
		this.value=value;
		
	}
	
	
	
    public static DATA_HANDLE<Boolean,PASSABLE_BOOL> getHandle() {
    	     return new DATA_HANDLE<Boolean,PASSABLE_BOOL>(new PASSABLE_BOOL());
    }

	@Override
	public <S extends PassableData<Boolean>> S getNewType() {
		return (S) new PASSABLE_BOOL();
	}



	@Override
	public String printValue(String indent) {
		return ""+value;
	}

	

}
