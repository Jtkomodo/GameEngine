package scripting;

import java.util.UUID;

import core.Entity;

public abstract class Script {

	
	
	/**
	 * This is where any initailization code will go it will pass in the current Entity
	 * 
	 * @param entity the current Entity
	 */
	public abstract void Start(Entity entity);
	/**
	 * GAMELOOP_TICK_BEFORE_PHYSICS:
	 * This is where we will put any code that happens before rendering
	 * and before physics engine has done detection
	 *
	 */
	public void GAMELOOP_TICK_BEFORE_PHYSICS(){
		//we put stuff here for things that need to happen before rendering
	}
	/**
	 * GAMELOOP_TICK_AFTER_PHYSICS:
	 * This is where we will put any code that happens before rendering
	 * and after physics engine has done detection and resolution 
	 *
	 */
	public void GAMELOOP_TICK_AFTER_PHYSICS() {
		
	}

	
	
	/**
	 * RENDER_TICK
	 * This is where we will put any code that needs to happen during rendering
	 */
	public  void RENDER_TICK(){
		//we put stuff here for things that happen during rendering
	}
	/**
	 * Put any component specific cleanup code here this will be called when we remove the component from the entity.
	 * @return returns if disable succeeded 
	 */
	public abstract boolean DISABLE();

	  public abstract UUID getSCRIPTID();
	
	
	
	
	
	
}
