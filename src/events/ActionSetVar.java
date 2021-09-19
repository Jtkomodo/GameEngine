package events;

import core.Entity;
import core.PassableData;
import core.VAR_RW;

public class ActionSetVar<K,T extends PassableData<K>> implements EventAction {

	
	public Entity e;
	public VAR_RW<T> var;
	public K value;
	
	
	public ActionSetVar(Entity e,VAR_RW<T> var,K value) {
		this.e=e;
		this.var=var;
		this.value=value;
	}
	
	
	
	
	@Override
	public void invoke() {
        e.setVar(var, value);

	}

}
