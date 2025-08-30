package main.java.UI;

import java.util.UUID;

import main.java.events.Condition;
import main.java.events.ConditionalEvent;
import main.java.events.EventDispatcher;
import main.java.events.EventHandle;
import main.java.events.EventType;
import main.java.events.OnEvent;
import main.java.physics.AABB;

public class EventCollisonWithMouse implements EventType<DATA_MOUSE_COLLISION> {


	

	
	private String ID;
	
	public EventCollisonWithMouse(String ID,UUID entity) {
		this.ID=entity+"-"+ID;
		
		
	}
	


	@Override
	public String getID() {
		return this.ID;
	}
	
}
