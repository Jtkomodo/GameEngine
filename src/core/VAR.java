package core;

 public class VAR<T extends PassableData<?>> {

	
	
	private String name;
    private DATA_HANDLE<T> handle;
	
	private VAR(String name,DATA_HANDLE<T> handle){
	    this.name=name+"*"+handle.getTypeName();
	    this.handle=handle;
		
	}
	
	
	
	
	public String getName() {
		return this.name;
	}




	public DATA_HANDLE<T> getHandle() {
		return handle;
	}
   
	 protected static <K,T extends PassableData<K>> VAR<T> makeNewVar(String name,DATA_HANDLE<T> handle){
		 return new VAR<T>(name,handle);
	 }


	
	
	
}
