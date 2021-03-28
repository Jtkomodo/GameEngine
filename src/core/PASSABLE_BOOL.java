package core;

public class PASSABLE_BOOL implements PassableData {

	
	
	public boolean value;
	
	
	
	public PASSABLE_BOOL(boolean value) {
		this.value = value;
	}



	@Override
	public PASSBLE_DATA_TYPE getID() {
		// TODO Auto-generated method stub
		return PASSBLE_DATA_TYPE.BOOL;
	}

}
