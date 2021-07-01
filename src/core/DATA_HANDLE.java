package core;

import java.util.UUID;

public class DATA_HANDLE<T extends PassableData<?>> {

	
	
	  private String TypeName;
	  private UUID TypeID;
	 
	
	  public  DATA_HANDLE(T type) {
	      this.TypeName=type.getType();
	      this.TypeID=type.getDATAID();   
	          
	  }


	public String getTypeName() {
		return TypeName;
	}


	public UUID getTypeID() {
		return TypeID;
	}
	
	
	

	
}
