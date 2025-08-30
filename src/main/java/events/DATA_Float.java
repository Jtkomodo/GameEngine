package main.java.events;

public class DATA_Float implements EVENT_DATA {

	private Float f;
	public DATA_Float(float f) {
		this.f=f;
	}
	public float get_f() {
		return f;
	}
	
	
	@Override
	public String debug_no_sub(String event_type) {
		// TODO Auto-generated method stub
		return "EVENT:"+event_type+"has no sub";
	}
	@Override
	public String debug_data_sent(String event_type) {
		return "EVENT:"+event_type+" recieved Float "+this.f;
	}

}
