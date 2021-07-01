package core;

import java.util.UUID;

import physics.AABB;

public class PASSABLE_AABB implements PassableData<AABB> {

	public static final UUID ID=UUID.randomUUID();

	private AABB value;
	
    private  PASSABLE_AABB() {
    	
    }
	
	
	public PASSABLE_AABB(AABB value) {
		this.value=value;
	}
	
	
	
	
	
	@Override
	public AABB getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}





	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "AABB";
	}


	public static DATA_HANDLE<PASSABLE_AABB> getHandle() {
		return new DATA_HANDLE<PASSABLE_AABB>(new PASSABLE_AABB());
	}

    



	

}
