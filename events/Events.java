package events;

import gameEngine.Start;

public class Events {


	private Condition[] conditions;
	private EventAction action;
	private boolean Activated=false;

	public Events(Condition[] conditions,EventAction action) {
		this.conditions=conditions;
		this.action=action;
	}

	public void ActivateFlags() {
		for(int i=0;i<this.conditions.length;i++) {
			Start.DebugPrint("flag added");
		
			Condition condition=this.conditions[i];
			FlagHandler.addFlag(condition.getFlag());
			condition.getFlag().addEvent(this);
		}
		this.Activated=true;
	}
	public void deactivateFlags() {
		for(int i=0;i<this.conditions.length;i++) {
			Condition condition=this.conditions[i];
			condition.getFlag().removeEvent(this);
		}
		this.Activated=false;
	}
	
	
	
	

	public void Invoke() {

		this.action.invoke();


	}

	public boolean Condition() {
		boolean return_true=true;
		for(int i=0;i<this.conditions.length;i++) {
			Condition condition=this.conditions[i];
			if(!condition.isTrue()) {
				return_true=false;
				break;
			}
		}


		return return_true;

	}

	public boolean isActivated() {
		return Activated;
	}




}
