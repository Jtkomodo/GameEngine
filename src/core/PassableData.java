package core;

import java.util.UUID;

public interface PassableData<T> {

	
    public String getType();
	public T getValue();
	public UUID getDATAID(); 
	
	
	
}
