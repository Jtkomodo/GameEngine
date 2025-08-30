package main.java.events;

import java.util.UUID;

public class EventTypeTest implements EventType<DATA_Float> {
     
	private static final String ID="TypeTest";
	private String ID_instance;
	
	
	public EventTypeTest(String EventName) {
		this.ID_instance=ID+":"+EventName;
	}
	
	public static String TYPE_ID() {
	    return ID;	
	}
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.ID_instance;
	}
}
