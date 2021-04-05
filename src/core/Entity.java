package core;


import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.joml.Vector2f;

import animation.SpriteSheet;
import rendering.Model;
import rendering.Texture;

public class Entity {

	
	
	
	
	
	public final static VAR<SpriteSheet> VAR_SPRITE_SHEET=new VAR<SpriteSheet>("SPRITE_SHEET"); 
	public final static VAR<PASSABLE_INT> VAR_FRAME=new VAR<PASSABLE_INT>("ANIMATION_FRAME");
    public final static VAR<PASSABLE_BOOL> VAR_ANIMATION_UPDATED=new VAR<PASSABLE_BOOL>("ANIMATION_UPDATED");
    public final static VAR<Model> VAR_MODEL=new VAR<Model>("MODEL");
	public final static VAR<PASSABLE_BOOL> VAR_MODEL_UPDATED=new VAR<PASSABLE_BOOL>("MODEL_UPDATED");
	public final static VAR<Texture> VAR_TEXTURE=new VAR<Texture>("TEXTURE");
	public final static VAR<PASSABLE_VEC2F> VAR_POSITION=new VAR<PASSABLE_VEC2F>("POSITION");
	public final static VAR<PASSABLE_VEC2F> VAR_VELOCITY=new VAR<PASSABLE_VEC2F>("VELOCITY");		
	public final static VAR<PASSABLE_BOOL> VAR_MIRROR=new VAR<PASSABLE_BOOL>("MIRROR");	
	
	

	
	
	
	public final UUID ID;
	
	
	
	
	
	
	
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
	public <T extends PassableData> T getData(VAR<T> var){
	
		   if(Entity_Data.containsKey(var.name)) {
	     	 return (T)Entity_Data.get(var.name);	
		   }else {
			  return null;
		   }
		}
	
	public <T extends PassableData> void TakeInData(VAR<T> var,T data) {
	
		this.Entity_Data.put(var.name,data);
	}
	
	public <T extends PassableData> void INITData(VAR<T> var,T data) {
		if(!this.Entity_Data.containsKey(var.name)) {
		   TakeInData(var,data);
		}
	}
	
	
	
	protected EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	
}
