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

	
	
	
	
	
	public final static VAR<PASSABLE_SPRITESHEET> VAR_SPRITE_SHEET=VAR.makeNewVar("SPRITESHEET",PASSABLE_SPRITESHEET.getHandle());
	public final static VAR<PASSABLE_INT> VAR_FRAME=VAR.makeNewVar("FRAME",PASSABLE_INT.getHandle());
	public final static VAR<PASSABLE_MODEL> VAR_MODEL=VAR.makeNewVar("MODEL",PASSABLE_MODEL.getHandle());
	public final static VAR<PASSABLE_TEXTURE> VAR_TEXTURE=VAR.makeNewVar("TEXTURE",PASSABLE_TEXTURE.getHandle());
    public final static VAR<PASSABLE_AABB> VAR_AABB=VAR.makeNewVar("AABB",PASSABLE_AABB.getHandle());
    public final static VAR<PASSABLE_VEC2F> VAR_BEFORE_POSITION=VAR.makeNewVar("BEFORE_POSITION",PASSABLE_VEC2F.getHandle());
	public final static VAR<PASSABLE_VEC2F> VAR_POSITION=VAR.makeNewVar("POSITION",PASSABLE_VEC2F.getHandle());
	public final static VAR<PASSABLE_VEC2F> VAR_VELOCITY=VAR.makeNewVar("Velocity",PASSABLE_VEC2F.getHandle());
	public final static VAR<PASSABLE_BOOL> VAR_MIRROR=VAR.makeNewVar("BEFORE_POSITION",PASSABLE_BOOL.getHandle());
    public final static VAR<PASSABLE_BOOL> VAR_ANAIMATION_PAUSE=VAR.makeNewVar("ANIMATION_PAUSE",PASSABLE_BOOL.getHandle());
    public final static VAR<PASSABLE_BOOL> VAR_ANAIMATION_RESET=VAR.makeNewVar("ANIMATION_RESET",PASSABLE_BOOL.getHandle());
    public final static VAR<PASSABLE_BOOL> VAR_ANIMATION_UPDATED=VAR.makeNewVar("ANIMATION_UPDATED",PASSABLE_BOOL.getHandle());
	public final static VAR<PASSABLE_BOOL> VAR_MODEL_UPDATED=VAR.makeNewVar("MODEL_UPDATED",PASSABLE_BOOL.getHandle());
	public final static VAR<PASSABLE_VEC4F> VAR_COLOR=VAR.makeNewVar("COLOR",PASSABLE_VEC4F.getHandle());
	
	
	
	
	
	
	public final UUID ID;
	
	
	
	
	
	
	public boolean DEBUG=false;
	private HashMap<String, PassableData<?>> Entity_Data=new HashMap<String, PassableData<?>>();
	private HashMap<UUID,EntityComponent> components=new HashMap<UUID,EntityComponent>(); 

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
	public void RENDER_TICK() {
		Iterator<EntityComponent> i=components.values().iterator();
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.RENDER_TICK();
		}
	}
	
	
	
	
	
	public void addComponents(EntityComponent[] components) {
		for(int i=0;i<components.length;i++) {
			addComponent(components[i]);
		}
	}
	
	protected boolean DISABLE() {
		Iterator<EntityComponent> i=this.components.values().iterator();
		while(i.hasNext()) {
			EntityComponent c=i.next();
		  if(!c.DISABLE()) {
			  return false;
		  }
		  
		}
		return true;
		
	}
	
	
	protected void Init() {
		Iterator<EntityComponent> i=this.components.values().iterator();
		
		
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.INIT(this);
		}
	}
	
	
	public void addComponent(EntityComponent c) {
		this.components.put(c.getCOMPONENTID(), c);
	}
	
	public boolean hasComponent(UUID type) {
		if(components.containsKey(type)) {
			return true;
		}else {
			return false;
		}
	}
	public <T extends EntityComponent> T getComponent(UUID id){
	   if(components.containsKey(id)) {
		return(T)components.get(id);	
	   }else {
		   return null;
	   }
	}
	public <ST,T extends PassableData<ST>>  boolean hasVAR(VAR<T> var) {
		return Entity_Data.containsKey(var.getName()) && (Entity_Data.get(var.getName())!=null);
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
	

	public <ST,T extends PassableData<ST>>  T getData(VAR<T> var){
	
		   if(Entity_Data.containsKey(var.getName())) {
	     	 return (T)Entity_Data.get(var.getName());	
		   }else {
			  return null;
		   }
		}
	
	
	
	public  <ST,T extends PassableData<ST>>  void TakeInData(VAR<T> var,T data) {
	  
			this.Entity_Data.put(var.getName(),data);
	 
	}
	
	
	
	public <ST,T extends PassableData<ST>> void INITData(VAR<T> var,T data) {
		if(!this.Entity_Data.containsKey(var.getName())) {
		   TakeInData(var,data);
		}
	}
   	public <ST,T extends PassableData<ST>> void removeVAR(VAR<T> var) {
   	         this.Entity_Data.remove(var.getName());
   	}
    
	
	public EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	
}
