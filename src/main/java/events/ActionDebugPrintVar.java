package main.java.events;

import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.PassableData;
import main.java.core.VAR_RW;

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
