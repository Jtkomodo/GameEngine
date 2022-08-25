package main.java.core;

import java.util.UUID;

import main.java.events.Flag;

public class PASSABLE_FLAG implements PassableData<Flag> {

	public static final UUID ID=UUID.randomUUID();
	public  Flag value=new Flag();
	
	
	protected PASSABLE_FLAG(boolean value) {
		this.value=new Flag(value);
	}
	
	
	
	 private PASSABLE_FLAG() {
		
	}



	public static DATA_HANDLE<Flag,PASSABLE_FLAG> getHandle() {
	     return new DATA_HANDLE<Flag,PASSABLE_FLAG>(new PASSABLE_FLAG());
}
	@Override
	public <S extends PassableData<Flag>> S getNewType() {
		return (S) new PASSABLE_FLAG(false);
	}

	@Override
	public void setValue(Flag value) {
	        this.value=value;
		
	}

	@Override
	public String getType() {
		return "FLAG";
	}

	@Override
	public Flag getValue() {
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}

	@Override
	public String printValue(String indent) {
	
		return ""+"FLAG("+value.State()+")";
	}

}
