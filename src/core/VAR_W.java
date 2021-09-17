package core;

public class VAR_W<T extends PassableData<?>> {
	
	
private VAR_RW<T> var;
	
	protected VAR_W(VAR_RW<T> var) {
		this.var=var;
	}
	protected VAR_RW<T> getVar() {
		return var;
	}

}
