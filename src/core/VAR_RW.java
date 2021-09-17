package core;

 public class VAR_RW<T extends PassableData<?>> {

	
	
	private String MangledName;
	private String name;
    private T handle;
	
	private VAR_RW(String name,T handle){
	    this.name=name;
		this.MangledName=name+"*"+handle.getType();
	    this.handle=handle;
		
	}
	
	
	
	
	public String getMangledName() {
		return this.MangledName;
	}


    protected String getRealName() {
    	return this.name;
    }
    protected <ST ,S extends PassableData<ST>> S getType() {
    	return (S)this.handle;
    }
	protected <ST,S extends PassableData<ST>> DATA_HANDLE<ST,S> getHandle() {
		return new DATA_HANDLE<ST,S>((S) handle);
	}
   
	 protected static <K,T extends PassableData<K>> VAR_RW<T> makeNewVar(String name,DATA_HANDLE<K,T> handle){
		 return new VAR_RW<T>(name,handle.getType());
	 }


	
	
	
}
