package events;

import gameEngine.Start;

public class EventActionDebugPrint implements EventAction {

	
	
	private String string;
	
	public EventActionDebugPrint(String string) {
		this.string=string;
	}
	
	
	
	@Override
	public void invoke() {
	   Start.DebugPrint(string);
	}

}
