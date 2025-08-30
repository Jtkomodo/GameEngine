package main.java.events;

import main.java.physics.AABB;

public class DATA_AABB implements EVENT_DATA {

	private AABB A;

	public DATA_AABB(AABB A) {
		this.A=A;
	}







	@Override
	public String debug_no_sub(String event_type) {
		String message="EVENT_DATA:"+event_type+" ";

		switch(A.getDEBUG_MODE()) {

		case AABB.SHOW_DATA:

			message+="has no sub";
			break;

		default:
			message="";
		}



		return message;
	}




	@Override
	public String debug_data_sent(String event_type) {
		String message="EVENT_DATA:"+event_type+" sent ";

		switch(A.getDEBUG_MODE()) {

		case AABB.SHOW_DATA:

			if(A!=null) {
				message+="AABB{"+"\n"+
						"			Widht:"+A.getwidth()+"\n"+
						"			Height:"+A.getHeight()+"\n"+
						"			Resistance:"+A.getResistance()+"\n"+
						"			ID:"+A.getID()+"\n"+
						"		}";
			}else {		
				message+="null";
			}
			break;
		case AABB.SHOW_ONLY_ID:
			if(A!=null) {
				message+="ID="+A.getID();
			}
			
			break;
		case AABB.OFF:	

		default:
			message="";
		}



		return message;
	}

}
