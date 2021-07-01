package core;

import java.util.UUID;

public class PASSABLE_INT implements PassableData<Integer> {

	public static final UUID ID=UUID.randomUUID();
	private int Value;
	
	
	public PASSABLE_INT() {
		
	}
	
	
	
	public PASSABLE_INT(int value) {
		Value = value;
	}



	


	@Override
	public UUID getDATAID() {
		// TODO Auto-generated method stub
		return ID;
	}






	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return this.Value;
	}






	@Override
	public String getType() {
		return "INT";
	}

    public static DATA_HANDLE<PASSABLE_INT> getHandle(){
    	return new DATA_HANDLE<PASSABLE_INT>(new PASSABLE_INT());
    }
	

}
