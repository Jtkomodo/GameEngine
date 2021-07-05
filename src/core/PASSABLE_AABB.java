package core;

import java.util.UUID;

import physics.AABB;

public class PASSABLE_AABB implements PassableData<AABB> {

	public static final UUID ID=UUID.randomUUID();

	private AABB value;
	
    protected  PASSABLE_AABB() {
    	
    }
	
	
	protected PASSABLE_AABB(AABB value) {
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
	@Override
	public void setValue(AABB value) {
		 this.value=value;
		
	}

	public static   DATA_HANDLE<AABB,PASSABLE_AABB> getHandle() {
		return new DATA_HANDLE<AABB,PASSABLE_AABB>(new PASSABLE_AABB());
	}


	@Override
	public <S extends PassableData<AABB>> S getNewType() {
		return (S) new PASSABLE_AABB();
	}


	@Override
	public String printValue(String indent) {
		if(value!=null) {
		return      "AABB{"+"\n"+
					indent+"			Widht:"+value.getwidth()+"\n"+
					indent+"			Height:"+value.getHeight()+"\n"+
					indent+"			Resistance:"+value.getResistance()+"\n"+
					indent+"			ID:"+value.getID()+"\n"+
					indent+"		}";
		}else {		
			return "null";
		}
	}




  


    



	

}
