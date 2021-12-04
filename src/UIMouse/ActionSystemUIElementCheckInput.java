package UIMouse;

import org.joml.Vector2f;

import core.CoreEngine;
import core.Game;
import events.EventAction;
import input.InputPoller;

public class ActionSystemUIElementCheckInput implements EventAction {


    private UIElement element;
    private int mouseLeftState=InputPoller.STILL_REALEASED;
    private int mouseRightState=InputPoller.STILL_REALEASED;
    
	private Vector2f cursorOffset=new Vector2f();
	protected ActionSystemUIElementCheckInput(UIElement element) {
		this.element=element;
	}
   
	protected void setMouseState(Vector2f cursorOffset,int mouseLeftState,int mouseRightState) {
		this.mouseLeftState=mouseLeftState;
		this.mouseRightState=mouseRightState;
		this.cursorOffset=cursorOffset;
	}
	
	@Override
	public void invoke() {
           switch(mouseLeftState) {
              case InputPoller.JUST_PUSHED:
            	  this.element.leftButtonJustPressed(this.cursorOffset);
            	  break;
              case InputPoller.JUST_REALEASED:
            	  this.element.lefttButtonJustRealesed(this.cursorOffset);
            	  break;
              case InputPoller.STILL_PUSHED:
            	  this.element.LeftButtonHeld(this.cursorOffset);
            	  break;  
           
              default:
            	  break;
           }
           
           switch(mouseRightState) {
           case InputPoller.JUST_PUSHED:
         	  this.element.rightButtonJustPressed(this.cursorOffset);
         	  break;
           case InputPoller.JUST_REALEASED:
         	  this.element.rightButtonJustRealesed(this.cursorOffset);
         	  break;
           case InputPoller.STILL_PUSHED:
         	  this.element.rightButtonHeld(this.cursorOffset);
         	  break;  
        
           default:
         	  break;
        }
	        this.element.setMouseStateChanged(false);
	}

}
