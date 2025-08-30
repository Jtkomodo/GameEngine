package main.java.UI;

import java.util.UUID;

import main.java.core.Entity;
import main.java.events.EVENT_DATA;
import main.java.events.EventDispatcher;
import main.java.events.EventHandle;
import main.java.events.EventType;
import main.java.events.OnEvent;

public class UICEvent<T extends EVENT_DATA> extends UIComponent {

	
	private UUID ID=UUID.randomUUID();
	private EventType<T> type;
	private OnEvent<T> action;
	private EventHandle<T> eventHandle;
	
	
	public UICEvent(EventType<T> type,OnEvent<T> action) {
		this.type=type;
		this.action=action;
	}
	
	
	
	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		this.eventHandle=EventDispatcher.subscribe(type, action);
		
		
	}

	@Override
	protected boolean DISABLE() {
		EventDispatcher.unsubscribe(this.eventHandle);
		return false;
	}

	@Override
	public UUID getCOMPONENTID() {
		
		return this.ID;
	}

	@Override
	protected void enable() {
		EventDispatcher.subscribe(type, action);
		
	}

}
