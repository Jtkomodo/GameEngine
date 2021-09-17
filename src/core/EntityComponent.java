package core;

import java.util.UUID;

/**
 * This is a abstract class that all our components will inherit from
 * each component type will have a ID specific to that type. You can only have
 * one of each component types this is so that we can just store the components in
 * a hashmap with the component's type ID as the key and the actual component as the value.
 * This way in order to tell if a entity has a Render component for a example it just has to check
 * if the component's ID exist in the hashmap.
 * @author Jesse Talbot
 *
 */
public abstract class  EntityComponent  {
	
   
	
    
	protected Entity currentEntity;
	
	
	protected static  <ST,T extends PassableData<ST>> VAR_W<T> createNewVAR_W(VAR_RW<T> var){
		  return new VAR_W<T>(var);	
		 
	 }
	
	
	 protected static  <ST,T extends PassableData<ST>> VAR_R<T> createNewVAR_R(VAR_RW<T> var){
		  return new VAR_R<T>(var);	
		 
	 }
	
	 protected static <ST,T extends PassableData<ST>> VAR_RW<T> createNewVAR(String name,DATA_HANDLE<ST,T> handle){
		  return VAR_RW.makeNewVar(name,handle);
	 }
	
	
	
	

    /**
     * INIT this is what is called when we add the component all the init code goes here
     * @param entity
     */
	protected abstract void INIT(Entity entity);
	/**
	 * GAMELOOP_TICK_BEFORE_PHYSICS:
	 * This is where we will put any code that happens before rendering
	 * and before physics engine has done detection
	 *
	 */
	protected void GAMELOOP_TICK_BEFORE_PHYSICS(){
		//we put stuff here for things that need to happen before rendering
	}
	/**
	 * GAMELOOP_TICK_AFTER_PHYSICS:
	 * This is where we will put any code that happens before rendering
	 * and after physics engine has done detection and resolution 
	 *
	 */
	protected void GAMELOOP_TICK_AFTER_PHYSICS() {
		
	}

	
	
	/**
	 * RENDER_TICK
	 * This is where we will put any code that needs to happen during rendering
	 */
	protected  void RENDER_TICK(){
		//we put stuff here for things that happen during rendering
	}
	/**
	 * Put any component specific cleanup code here this will be called when we remove the component from the entity.
	 * @return returns if disable succeeded 
	 */
	protected abstract boolean DISABLE();
	/**
	 * get the component specific ID each component will have a id so that we can easily
	 *  tell if a entity has this component type
	 * @return
	 */
    public abstract UUID getCOMPONENTID();



}
