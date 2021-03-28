package core;
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
public abstract class  EntityComponent {
	
   
	
    
	protected Entity currentEntity;
	/**
	 * When a comonent type is created we make sure to pass in it's COMPID so we can retrieve the component later
	 * @param ID COMPNENT_ID of the component
	 */
	
    /**
     * INIT this is what is called when we add the component all the init code goes here
     * @param entity
     */
	protected abstract void INIT(Entity entity);
	/**
	 * GAMELOOP_TICK:
	 * This is where we will put any code that happens before rendering
	 *
	 */
	protected abstract void GAMELOOP_TICK();//we put stuff here for things that need to happen before rendering
	/**
	 * RENDER_TICK
	 * This is where we will put any code that needs to happen during rendering
	 */
	protected abstract void RENDER_TICK();//we put stuff here for things that happen during rendering
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
    public abstract COMPONENT_TYPE getID();



}
