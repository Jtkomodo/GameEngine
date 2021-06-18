package events;

import core.CoreEngine;

public class ActionDebugPrint implements EventAction {

	
	private String message;
	
	
	
	public ActionDebugPrint(String message) {
		this.message=message;
	}
	
	
	
	
	
	
	@Override
	public void invoke() {
	     CoreEngine.DebugPrint(message);
	}

}
