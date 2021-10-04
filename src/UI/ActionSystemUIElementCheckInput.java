package UI;

import org.joml.Vector2f;

import core.CoreEngine;
import core.Game;
import events.EventAction;
import input.InputPoller;

public class ActionSystemUIElementCheckInput implements EventAction {


    private UIElement element;
    private int mouseLeftState=InputPoller.STILL_REALEASED;
    private int mouseRightState=InputPoller.STILL_REALEASED;
    
	private Vector2f cursorPosition=new Vector2f();
	protected ActionSystemUIElementCheckInput(UIElement element) {
		this.element=element;
	}
   
	protected void setMouseState(Vector2f cursorPosition,int mouseLeftState,int mouseRightState) {
		this.mouseLeftState=mouseLeftState;
		this.mouseRightState=mouseRightState;
		this.cursorPosition=cursorPosition;
	}
	
	@Override
	public void invoke() {
           switch(mouseLeftState) {
              case InputPoller.JUST_PUSHED:
            	  this.element.leftButtonJustPressed();
            	  break;
              case InputPoller.JUST_REALEASED:
            	  this.element.lefttButtonJustRealesed();
            	  break;
              case InputPoller.STILL_PUSHED:
            	  this.element.LeftButtonHeld();
            	  break;  
           
              default:
            	  break;
           }
           
           switch(mouseRightState) {
           case InputPoller.JUST_PUSHED:
         	  this.element.rightButtonJustPressed();
         	  break;
           case InputPoller.JUST_REALEASED:
         	  this.element.rightButtonJustRealesed();
         	  break;
           case InputPoller.STILL_PUSHED:
         	  this.element.rightButtonHeld();
         	  break;  
        
           default:
         	  break;
        }
	        this.element.setMouseStateChanged(false);
	}

}
