package main.java.events;

import java.util.UUID;

public class EventTypeTest implements EventType<Float> {
     
	private static final String ID="TypeTest";
	
	public static String TYPE_ID() {
	    return ID;	
	}
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
