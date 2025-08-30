package main.java.UI;

import main.java.events.EVENT_DATA;

public class DATA_KEY implements EVENT_DATA {

	
	
	private int keycode;
	private byte state;
	
	public DATA_KEY(int keycode,byte state) {
		this.keycode=keycode;
		this.state=state;
	}
	
	
	
	@Override
	public String debug_no_sub(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String debug_data_sent(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
