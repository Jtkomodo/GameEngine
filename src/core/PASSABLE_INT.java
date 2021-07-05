package core;

import java.util.UUID;

public class PASSABLE_INT implements PassableData<Integer> {

	public static final UUID ID=UUID.randomUUID();
	private int Value;
	
	
	private PASSABLE_INT() {
		
	}
	
	
	
	protected PASSABLE_INT(int value) {
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
	@Override
	public void setValue(Integer value) {
		this.Value=value;
		
	}
    public static DATA_HANDLE<Integer,PASSABLE_INT> getHandle(){
    	return new DATA_HANDLE<Integer,PASSABLE_INT>(new PASSABLE_INT());
    }



	@Override
	public <S extends PassableData<Integer>> S getNewType() {
		return (S)new PASSABLE_INT();
	}



	@Override
	public String printValue(String indent) {
		return ""+this.Value;
	}
	

}
