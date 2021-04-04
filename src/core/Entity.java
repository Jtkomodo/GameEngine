package core;


import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.joml.Vector2f;
import rendering.Texture;

public class Entity {

	
	public final static String VAR_SPRITE_SHEET="SPRITE_SHEET"; 
	public final static String VAR_FRAME="ANIMATION_FRAME"; 
	public final static String VAR_ANIMATION_UPDATED="ANIMATION_UPDATED";
	public final static String VAR_MODEL="MODEL";
	public final static String VAR_MODEL_UPDATED="MODEL_UPDATED";
	public final static String VAR_TEXTURE="TEXTURE";
	public final static String VAR_POSITION="POSITION";
	public final static String VAR_VELOCITY="VELOCITY";	
	
	public final UUID ID;
	public boolean PHYSICS=true;
	public boolean ANIMATION=true;
	public boolean RENDERING=true;
	
	
	
	
	
	
	protected HashMap<String, PassableData> Entity_Data=new HashMap<String, PassableData>();
	private HashMap<COMPONENT_TYPE,EntityComponent> components=new HashMap<COMPONENT_TYPE,EntityComponent>(); 

	
	public Entity(EntityComponent[] components){
		this.ID=UUID.randomUUID();
		addComponents(components);
		
	}
	
	public void addComponents(EntityComponent[] components) {
		for(int i=0;i<components.length;i++) {
			addComponent(components[i]);
		}
	}
	
	protected void Init() {
		Iterator<EntityComponent> i=this.components.values().iterator();
		
		
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.INIT(this);
		}
	}
	
	
	public void addComponent(EntityComponent c) {
		this.components.put(c.getID(), c);
	}
	
	public boolean hasComponent(COMPONENT_TYPE type) {
		if(components.containsKey(type)) {
			return true;
		}else {
			return false;
		}
	}
	public <T extends EntityComponent> T getComponent(COMPONENT_TYPE id){
	   if(components.containsKey(id)) {
		return(T)components.get(id);	
	   }else {
		   return null;
	   }
	}
	public <T extends PassableData> T getData(String name){
		   if(Entity_Data.containsKey(name)) {
			return(T)Entity_Data.get(name);	
		   }else {
			   return null;
		   }
		}
	
	public void TakeInData(String NAME,PassableData data) {
		this.Entity_Data.put(NAME,data);
	}
	public void INITData(String NAME,PassableData data) {
		if(!this.Entity_Data.containsKey(NAME)) {
		   this.Entity_Data.put(NAME,data);
		}
	}
	
	
	
	protected EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	
}
