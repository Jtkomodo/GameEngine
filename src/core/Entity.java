package core;


import java.util.HashMap;
import java.util.UUID;

public class Entity {

	
	
	public final UUID ID;
	private HashMap<Integer,EntityComponent> components=new HashMap<Integer,EntityComponent>(); 
	
	
	public Entity(EntityComponent[] components){
		this.ID=UUID.randomUUID();
		addComponents(components);
	}
	
	public void addComponents(EntityComponent[] components) {
		for(int i=0;i<components.length;i++) {
			addComponent(components[i]);
		}
	}
	
	
	public void addComponent(EntityComponent c) {
		this.components.put(c.getID(), c);
	}

	public <T extends EntityComponent> T getComponent(int id){
	   if(components.containsKey(id)) {
		return(T)components.get(id);	
	   }else {
		   return null;
	   }
	}
	
}
