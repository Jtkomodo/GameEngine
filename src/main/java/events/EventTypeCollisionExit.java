package main.java.events;

import java.util.UUID;

import main.java.physics.AABB;

public class EventTypeCollisionExit implements EventType<DATA_AABB> {

	
	
	private UUID ID;
	public EventTypeCollisionExit(UUID ID) {
		this.ID=ID;
	}
	
	
	
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "TYPE_COLLISION_EXIT"+ID;
	}


}
