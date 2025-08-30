package main.java.events;

import java.util.UUID;

public class EventHandle<T extends EVENT_DATA> {
	
	
	private EventType<T> Type;
	private OnEvent<T> action;
	
		
	protected EventHandle(EventType<T> Type,OnEvent<T> action) {
		this.Type=Type;
		this.action=action;
	
		
	}
	protected String getType() {
		return Type.getID();
	}
	public EventType<T> getEventType() {
		return this.Type;
	}
	public OnEvent<T> getAction(){
		return this.action; 
	}
	
	protected void dispatch(T data) {
		this.action.invoke(data);
	}
	
    
}
