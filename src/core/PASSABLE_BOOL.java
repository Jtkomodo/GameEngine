package core;

import java.util.UUID;

public class PASSABLE_BOOL implements PassableData<Boolean> {

	
	public static final UUID ID=UUID.randomUUID();
	
	
	private boolean value=false;
	
	
    private PASSABLE_BOOL() {
    	
    }
	
	public PASSABLE_BOOL(boolean value) {
		this.value = value;
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
    
    public static DATA_HANDLE<PASSABLE_BOOL> getHandle() {
    	     return new DATA_HANDLE<PASSABLE_BOOL>(new PASSABLE_BOOL());
    }

	

}
