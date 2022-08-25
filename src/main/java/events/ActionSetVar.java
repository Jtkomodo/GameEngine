package main.java.events;

import main.java.core.Entity;
import main.java.core.PassableData;
import main.java.core.VAR_RW;
import main.java.core.VAR_W;

public class ActionSetVar<K,T extends PassableData<K>> implements EventAction {

	
	public Entity e;
	public VAR_W<T> var;
	public K value;
	
	
	public ActionSetVar(Entity e,VAR_RW<T> var,K value) {
		this.e=e;
		this.var=var.getAsWriteOnly();
		this.value=value;
	}
	public ActionSetVar(Entity e,VAR_W<T> var,K value) {
		this.e=e;
		this.var=var;
		this.value=value;
	}
	
	@Override
	public void invoke() {
        e.setVar(var, value);

	}

}
