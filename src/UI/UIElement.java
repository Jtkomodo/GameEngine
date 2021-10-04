package UI;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import core.CoreEngine;

import static events.Operation.*;

import events.Condition;
import events.Events;
import events.Flag;
import input.InputPoller;
import physics.AABB;


public abstract class UIElement {

	protected float width,height;
	protected AABB collision_box;
	
	
	protected Flag MOUSE_STATE_CHANGED=new Flag(false);
	protected ActionSystemUIElementCheckInput action;
	protected Vector2f position_in_box=new Vector2f();
	protected int leftState=0,rightState=0;
	
    protected UIElement(float width,float height) {
    	this.width=width;
    	this.height=height;
    	this.collision_box=new AABB(width,height,0);
    	action=new ActionSystemUIElementCheckInput(this);
    	Events event=new Events(new Condition(this.MOUSE_STATE_CHANGED,EQUALS,true),action);
    	
    	event.ActivateFlags();
    	
    	
    }
    
    
    
    protected void checkIfInBounds(Vector2f mouse_position,Vector2f boxPosition) {
    	Vector2f position_in_box=new Vector2f();
    	this.position_in_box.add(boxPosition,position_in_box);


        int leftState=InputPoller.checkState(GLFW.GLFW_MOUSE_BUTTON_1);
        int rightState=InputPoller.checkState(GLFW.GLFW_MOUSE_BUTTON_2);
        
    	if(this.collision_box.vsPoint(mouse_position,position_in_box) && (this.leftState!=leftState || this.rightState!=rightState)) {
    		this.action.setMouseState(mouse_position,leftState,rightState);
			this.leftState=leftState;
			this.rightState=rightState;
    		this.MOUSE_STATE_CHANGED.setState(true);
    	

    		


    	}
        
    }
	public abstract void leftButtonJustPressed();
	public abstract void lefttButtonJustRealesed();
	public abstract void LeftButtonHeld();
	
	
	public abstract void rightButtonJustPressed();
	public abstract void rightButtonJustRealesed();
	public abstract void rightButtonHeld();
	
	protected abstract void renderUpdate(Vector2f box_Position);
	public void setMouseStateChanged(Boolean state) {
		this.MOUSE_STATE_CHANGED.setState(state);
	}
	
	
}
