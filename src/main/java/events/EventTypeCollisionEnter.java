package main.java.events;

import java.util.UUID;

import main.java.physics.AABB;
import main.java.physics.Collision;

public class EventTypeCollisionEnter implements EventType<DATA_AABB> {
	
	private UUID ID;
	private boolean state=false;
	private boolean stateChanged=false;
	
	public EventTypeCollisionEnter(UUID entity) {
		this.ID=entity;
	}
	

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "TYPE_COLLISION_ENTER"+ID;
	}

	
}
