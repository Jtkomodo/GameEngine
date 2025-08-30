package main.java.UI;

import org.joml.Vector2f;

import main.java.events.EVENT_DATA;
import main.java.physics.AABB;

public class DATA_MOUSE_COLLISION implements EVENT_DATA {

	public AABB trigger;
	public Vector2f mouseOffsetInBox;
	
	
	public DATA_MOUSE_COLLISION(AABB trigger,Vector2f mouseOffsetInBox) {
		this.trigger=trigger;	
		this.mouseOffsetInBox=mouseOffsetInBox;
	}
	
	
	
	@Override
	public String debug_no_sub(String string) {
		String message="EVENT_DATA:Mouse position:"+this.mouseOffsetInBox+"ABBB:"+string+" ";

		switch(trigger.getDEBUG_MODE()) {

		case AABB.SHOW_DATA:

			message+="has no sub";
			break;

		default:
			message="";
		}



		return message;
	}

	@Override
	public String debug_data_sent(String string) {
	
		String message="EVENT_DATA:Mouse position:"+"x:"+Math.round(this.mouseOffsetInBox.x)+"y;"+Math.round(this.mouseOffsetInBox.y)+" ABBB:"+string+" sent ";

		switch(trigger.getDEBUG_MODE()) {

		case AABB.SHOW_DATA:

			if(trigger!=null) {
				message+="AABB{"+"\n"+
						"			Widht:"+trigger.getwidth()+"\n"+
						"			Height:"+trigger.getHeight()+"\n"+
						"			Resistance:"+trigger.getResistance()+"\n"+
						"			ID:"+trigger.getID()+"\n"+
						"		}";
			}else {		
				message+="null";
			}
			break;
		case AABB.SHOW_ONLY_ID:
			if(trigger!=null) {
				message+="ID="+trigger.getID();
			}
			
			break;
		case AABB.OFF:	

		default:
			message="";
		}



		return message;
	}
	

}
