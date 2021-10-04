package core;

 public class VAR_RW<T extends PassableData<?>> {

	
	
	private String MangledName;
	private String name;
    private T handle;
    private VAR_R<T> readOnly;
    private VAR_W<T> writeOnly;
    
	
	private VAR_RW(String name,T handle){
	    this.name=name;
		this.MangledName=name+"*"+handle.getType();
	    this.handle=handle;
		this.readOnly=new VAR_R<T>(this);
		this.writeOnly=new VAR_W<T>(this);
	}
	
	
	
	
	public String getMangledName() {
		return this.MangledName;
	}
    public VAR_R<T> getAsReadOnly(){
        return this.readOnly;
    }
    public VAR_W<T> getAsWriteOnly(){
        return this.writeOnly;
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
