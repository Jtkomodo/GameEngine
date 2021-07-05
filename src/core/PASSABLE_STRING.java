package core;

import java.util.UUID;

public class PASSABLE_STRING implements PassableData<String> {
	public static final UUID ID=UUID.randomUUID();
	
	private String value;
	
	
	
	private PASSABLE_STRING() {
		
	}
	
	
	
	
	
	
	@Override
	public <S extends PassableData<String>> S getNewType() {
		return (S)new PASSABLE_STRING();
	}

	@Override
	public void setValue(String value) {
		this.value=value;
	}

	@Override
	public String getType() {
		return "STRING";
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public UUID getDATAID() {
		return ID;
	}
	
	public static DATA_HANDLE<String,PASSABLE_STRING> getHandle(){
		return new DATA_HANDLE<String,PASSABLE_STRING>(new PASSABLE_STRING());
	}






	@Override
	public String printValue(String indent) {
		return this.value;
	}
	

}
