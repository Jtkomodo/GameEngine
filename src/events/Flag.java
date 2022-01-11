package events;

import java.util.LinkedList;

import core.CoreEngine;



public class Flag {

	
	private boolean state=false;
	private LinkedList<Event> events=new LinkedList<Event>();
	private boolean stateChanged=false;
	
	public Flag() {
		this.state=false;
	}
	
	public Flag(boolean state) {
		this.state=state;
	}
	
	protected void addEvent(Event event) {
		
		if(!this.events.contains(event)) {
			this.events.add(event);
			CoreEngine.DebugPrint("Event added to FLAG");
		}	
	}
	protected void removeEvent(Event event) {
		
		if(this.events.remove(event)) {
			CoreEngine.DebugPrint("Event removed from FLAG");
		}
		if(this.events.isEmpty()) {
		    FlagHandler.removeFlag(this);
		}
		
	}

	public void TriggerEvents() {
	
		for(int i=0;i<this.events.size();i++) {
			Event event=events.get(i);
			if(event.Condition()) {
				event.Invoke();
				
			}
		}
		this.stateChanged=false;
	}
	
	
	
	public boolean setState(boolean state) {
	     if(this.state!=state) {
			this.state=state;
			this.stateChanged=true;
			FlagHandler.Flag_Changed(this);
		}else{
			this.stateChanged=false;
		}
		return this.stateChanged;
	}

	public boolean State() {
		return state;
	}

	public boolean StateChanged() {
		return stateChanged;
	}

	public void StateChanged_handled() {
		this.stateChanged = false;
	}
	public void SetStateChanged(boolean state) {
		this.stateChanged=state;
	}

	public void toggleState() {
	     setState(!this.state);
		
	}
	
}
