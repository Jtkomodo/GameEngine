package core;

public class VAR_R<T extends PassableData<?>> {

	private VAR_RW<T> var;
	
	protected VAR_R(VAR_RW<T> var) {
		this.var=var;
	}
	protected VAR_RW<T> getVar() {
		return var;
	}
	
}
