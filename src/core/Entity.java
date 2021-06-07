package core;


import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.joml.Vector2f;

import animation.SpriteSheet;
import physics.AABB;
import rendering.Model;
import rendering.Texture;

public class Entity {

	
	
	
	
	
	public final static VAR<SpriteSheet> VAR_SPRITE_SHEET=new VAR<SpriteSheet>("SPRITE_SHEET"); 
	public final static VAR<PASSABLE_INT> VAR_FRAME=new VAR<PASSABLE_INT>("ANIMATION_FRAME");
    public final static VAR<Model> VAR_MODEL=new VAR<Model>("MODEL");
	public final static VAR<Texture> VAR_TEXTURE=new VAR<Texture>("TEXTURE");
    public final static VAR<AABB> VAR_AABB=new VAR<AABB>("AABB");
    public final static VAR<PASSABLE_VEC2F> VAR_BEFORE_POSITION=new VAR<PASSABLE_VEC2F>("BEFORE_POSITION");
	public final static VAR<PASSABLE_VEC2F> VAR_POSITION=new VAR<PASSABLE_VEC2F>("POSITION");
	public final static VAR<PASSABLE_VEC2F> VAR_VELOCITY=new VAR<PASSABLE_VEC2F>("VELOCITY");		
	public final static VAR<PASSABLE_BOOL> VAR_MIRROR=new VAR<PASSABLE_BOOL>("MIRROR");	
    public final static VAR<PASSABLE_BOOL> VAR_ANAIMATION_PAUSE=new VAR<PASSABLE_BOOL>("ANAIMATION_PAUSE");
    public final static VAR<PASSABLE_BOOL> VAR_ANAIMATION_RESET=new VAR<PASSABLE_BOOL>("ANAIMATION_RESET");
    public final static VAR<PASSABLE_BOOL> VAR_ANIMATION_UPDATED=new VAR<PASSABLE_BOOL>("ANIMATION_UPDATED");
	public final static VAR<PASSABLE_BOOL> VAR_MODEL_UPDATED=new VAR<PASSABLE_BOOL>("MODEL_UPDATED");
	
	
	
	
	
	
	
	public final UUID ID;
	
	
	
	
	
	
	public boolean DEBUG=false;
	protected HashMap<String, PassableData> Entity_Data=new HashMap<String, PassableData>();
	private HashMap<COMPONENT_TYPE,EntityComponent> components=new HashMap<COMPONENT_TYPE,EntityComponent>(); 

	public Entity(EntityComponent[] components){
		this.ID=UUID.randomUUID();
		addComponents(components);
		
	}
	
	
	public void GAMELOOP_TICK() {
		Iterator<EntityComponent> i=components.values().iterator();
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.GAMELOOP_TICK();
		}
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
	public<T extends PassableData> boolean hasVAR(VAR<T> var) {
		return Entity_Data.containsKey(var.name) && (Entity_Data.get(var.name)!=null);
	}
	public boolean hasAllVars(String[] varNames) {
	 int i=0;
	while(i<varNames.length) {
     String varName=varNames[i];
	if(!Entity_Data.containsKey(varName) || (Entity_Data.get(varName)==null)) {
		return false;
	}
		 
	i++;	 
	 }
		
		return true;
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
	
	
	
	public EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	
}
