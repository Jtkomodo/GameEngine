package UI;

import org.joml.Vector2f;

import events.EventAction;
import input.InputPoller;
import textrendering.TextBuilder;

public class ActionSystemUITextChanged implements EventAction {

	private TextChangedAble e;
	public ActionSystemUITextChanged(TextChangedAble e) {
		this.e=e;
	}
	
	
	
	@Override
	public void invoke() {
         
        e.TextChanged();
   
    		 
    		 

	}

}
