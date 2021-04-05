package core;

public class PASSABLE_INT implements PassableData {

	
	int Value;
	
	
	
	public PASSABLE_INT(int value) {
		Value = value;
	}



	@Override
	public PASSABLE_DATA_TYPE getType() {
		// TODO Auto-generated method stub
		return PASSABLE_DATA_TYPE.INT;
	}


	

}
