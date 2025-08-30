package main.java.physics;

import java.beans.EventHandler;
import java.util.UUID;

import org.joml.Vector2f;

import groovyjarjarantlr.debug.Event;
import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.VAR_RW;
import main.java.events.Condition;
import main.java.events.ConditionalEvent;
import main.java.events.EventDispatcher;
import main.java.events.EventHandle;
import main.java.events.OnEvent;
import main.java.events.Operation;
import main.java.input.InputPoller;
import main.java.rendering.Render;
import main.java.UI.DATA_MOUSE_COLLISION;
import main.java.UI.EventCollisonWithMouse;
import main.java.UI.UIComponent;
import main.java.UI.UIEngine;

public class UICTrigger extends UIComponent {

	private UUID ComponentID=UUID.randomUUID();//this is the unique identifier for the compnent
	
	private AABB colider;
	private EventHandle<DATA_MOUSE_COLLISION> handleJustEntered,handleStillEntered,handleExit;
	private OnEvent<DATA_MOUSE_COLLISION> onJustEntered,onStillEntered,onExit;
	private Vector2f position;
	private Boolean lastState=false;
	
	
	public UICTrigger(Vector2f position,AABB colider,OnEvent<DATA_MOUSE_COLLISION> onStillEntered,OnEvent<DATA_MOUSE_COLLISION> onJustEntered,OnEvent<DATA_MOUSE_COLLISION> onExit) {
		this.position=position;
		this.colider=colider;
		this.onJustEntered=onJustEntered;
		this.onStillEntered=onStillEntered;
		this.onExit=onExit;
		//this.colider.DEBUG_SHOW_DATA();
		
}
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		this.currentEntity.setVar(Entity.VAR_POSITION,this.position);
		this.colider.TIE_ENTITY(this.currentEntity.ID);
		
		//this just sets up for listing to the event
		this.handleExit=EventDispatcher.subscribe(new EventCollisonWithMouse("mouse exit",this.currentEntity.ID),this.onExit);
		this.handleJustEntered=EventDispatcher.subscribe(new EventCollisonWithMouse("mouse just entered",this.currentEntity.ID),this.onJustEntered);
		this.handleStillEntered=EventDispatcher.subscribe(new EventCollisonWithMouse("mouse still entered",this.currentEntity.ID),this.onStillEntered);
		
	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
		//this is run once before the physics are calculated
		
		//just checks the collision with the mouse
		//and reports to the correct event
		
		if(this.currentEntity.hasVAR(Entity.VAR_POSITION)) {
			Vector2f position=this.currentEntity.getData(Entity.VAR_POSITION).value;
			
			position=position.sub(Render.cam.getPosition(),new Vector2f());
			
			Vector2f mousePosition=InputPoller.getWorldMousePosition();
			
			//check the collision
			boolean state=this.colider.vsPoint(position,mousePosition);
			Vector2f offsetInBox=colider.getOffsetinBox(position, mousePosition);
			DATA_MOUSE_COLLISION data=new DATA_MOUSE_COLLISION(colider,offsetInBox);
			
			
			
			//simple checking to see state of button
			if(state) {
				if(!this.lastState) {
					EventDispatcher.post(this.handleJustEntered.getEventType(),data);
				}else{
					EventDispatcher.post(this.handleStillEntered.getEventType(),data);
				}		
			}else{
				if(this.lastState) {
					EventDispatcher.post(this.handleExit.getEventType(),data);
				}
			}
			
			lastState=state;
		
		
		}
		
		
		
		
		
	}
	
	
	
	@Override
	protected void RENDER_TICK() {
		//used to debug draw
		if(this.currentEntity.DEBUG && CoreEngine.Debugdraw ) {
			if(this.currentEntity.hasAllVars(new VAR_RW<?>[]{Entity.VAR_POSITION})) {
				
				Vector2f position=this.currentEntity.getData(Entity.VAR_POSITION).value;
				position=position.sub(Render.cam.getPosition(),new Vector2f());
				this.colider.debugDraw(position);
			}
		}

	}
	
	@Override
	protected boolean DISABLE() {
		EventDispatcher.unsubscribe(this.handleJustEntered);
		EventDispatcher.unsubscribe(this.handleStillEntered);
		EventDispatcher.unsubscribe(this.handleExit);
		
		return true;
	}

	@Override
	public UUID getCOMPONENTID() {
		return this.ComponentID;
	}

	@Override
	protected void enable() {
		EventDispatcher.subscribe(this.handleJustEntered);
		EventDispatcher.subscribe(this.handleStillEntered);
		EventDispatcher.subscribe(this.handleExit);
		
	}

}
