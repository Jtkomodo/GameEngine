package main.java.events;

import java.util.UUID;

public class EventHandle<T> {
	
	
	private EventType<T> Type;
	private OnEvent<T> action;
	
		
	protected EventHandle(EventType<T> Type,OnEvent<T> action) {
		this.Type=Type;
		this.action=action;
	
		
	}
	protected String getType() {
		return Type.getID();
	}
	
	protected void dispatch(T data) {
		this.action.invoke(data);
	}
	
    
}
