package main.java.events;

import main.java.core.CoreEngine;


public class Event {


	private Condition condition;
	private EventAction action;
	private boolean Activated=false;

	public Event(Condition condition,EventAction action) {
		this.condition=condition;
		this.action=action;
	}
	

	public void ActivateFlags() {
		
		condition.activate(this);
		this.Activated=true;
	}
	public void deactivateFlags() {
		condition.deactivate(this);
		this.Activated=false;
	}
	
	
	
	

	public void Invoke() {

		this.action.invoke();


	}

	public boolean Condition() {

		return this.condition.check();

	}

	public boolean isActivated() {
		return Activated;
	}




}
