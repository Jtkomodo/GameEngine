package main.java.core;

import java.util.UUID;

public class DATA_HANDLE<K,T extends PassableData<K>> {

	
	
	  private String TypeName;
	  private UUID TypeID;
	  private T type;
	
	  public  DATA_HANDLE(T type) {
		  this.type=type.getNewType();
	      this.TypeName=type.getType();
	      this.TypeID=type.getDATAID();   
	          
	  }
    protected T getType() {
    	return this.type;
    }

	public String getTypeName() {
		return TypeName;
	}


	public UUID getTypeID() {
		return TypeID;
	}
	
	


	
}
