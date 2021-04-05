package core;

public class PASSABLE_BOOL implements PassableData {

	
	
	public boolean value;
	
	
	
	public PASSABLE_BOOL(boolean value) {
		this.value = value;
	}



	@Override
	public PASSABLE_DATA_TYPE getType() {
		// TODO Auto-generated method stub
		return PASSABLE_DATA_TYPE.BOOL;
	}

}
