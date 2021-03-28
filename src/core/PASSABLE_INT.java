package core;

public class PASSABLE_INT implements PassableData {

	
	int Value;
	
	
	
	public PASSABLE_INT(int value) {
		Value = value;
	}



	@Override
	public PASSBLE_DATA_TYPE getID() {
		// TODO Auto-generated method stub
		return PASSBLE_DATA_TYPE.INT;
	}


	

}
