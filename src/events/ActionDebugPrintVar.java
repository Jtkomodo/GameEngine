package events;

import core.CoreEngine;
import core.Entity;
import core.PassableData;
import core.VAR_RW;

public class ActionDebugPrintVar<K,T extends PassableData<K>> implements EventAction{

	private VAR_RW<T> var;
	private Entity e;
	
	
	public ActionDebugPrintVar(Entity e,VAR_RW<T> var) {
		this.e=e;
		this.var=var;
		
	}
	
	@Override
	public void invoke() {
		CoreEngine.DebugPrint(var.getMangledName()+"="+e.getVar(var));
		
	}

}
