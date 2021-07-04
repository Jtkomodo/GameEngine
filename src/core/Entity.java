package core;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	
	
	public final static VAR<PASSABLE_LINKED_LIST<Boolean>> VAR_TESTLIST=VAR.makeNewVar("TESTLIST",PASSABLE_LINKED_LIST.getHandle(PASSABLE_BOOL.getHandle()));
	public final static VAR<PASSABLE_HASH_MAP<String,Integer>> VAR_TESTHASH=VAR.makeNewVar("TESTHASH",PASSABLE_HASH_MAP.getHandle(PASSABLE_STRING.getHandle(), PASSABLE_INT.getHandle()));
	
	
	
	
	public final UUID ID=UUID.randomUUID();
	
	
	
	
	
	
	public boolean DEBUG=false;
	private HashMap<String, PassableData<?>> Entity_Data=new HashMap<String, PassableData<?>>();
	private HashMap<UUID,EntityComponent> components=new HashMap<UUID,EntityComponent>(); 

	public Entity(EntityComponent[] components){

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
	
	
	public boolean hasAllVars(VAR<?>[] vars){
		int i=0;
		while(i<vars.length) {
			String varName=vars[i].getMangledName();
			if(!Entity_Data.containsKey(varName) || (Entity_Data.get(varName)==null)) {
				return false;
			}else if(Entity_Data.get(varName).getValue()==null) {
				return false;
			}
				 
			i++;	 
			 }
				
				return true;
			}	
		
		
		
	
	
	
	

	private <ST,T extends PassableData<ST>>  T getData(VAR<T> var){
	
		   if(Entity_Data.containsKey(var.getMangledName())) {
	     	 return (T)Entity_Data.get(var.getMangledName());	
		   }else {
			  return null;
		   }
		}
	
	
   public  <ST,T extends PassableData<ST>> boolean hasVAR(VAR<T> var){
	
		   return getVar(var)!=null;
	 
   }
	
	
	
	public <ST,T extends PassableData<ST>> ST getVar(VAR<T> var) {
		T data=getData(var);
		
		if(data!=null) {
		  return data.getValue();
		}else {
			return null;
		}
	}
	
	
	
	public  <ST,T extends PassableData<ST>> void setVar(VAR<T> var,ST value) {
		    
		         VAR<T> newVar=VAR.makeNewVar(var.getRealName(),var.getHandle());
		         
		         T data=newVar.getType();
		         
		    	
		    	 data.setValue(value);
		         TakeInData(newVar,data); 
	}
	
	public <K,V> void HashMapPut(VAR<PASSABLE_HASH_MAP<K,V>> var,K key,V value) {
	         PASSABLE_HASH_MAP<K,V> data=getData(var);
		
				if(data!=null) {
				   data.put(key, value);
				}else {
				    VAR<PASSABLE_HASH_MAP<K,V>> newVar=VAR.makeNewVar(var.getRealName(),var.getHandle());
			         
			          data=newVar.getType();
			         
			    	
			    	 data.put(key, value);
			         TakeInData(newVar,data); 
					
				}
	}
	public <K,V> V HashMapGet(VAR<PASSABLE_HASH_MAP<K,V>> var,K key) {
        PASSABLE_HASH_MAP<K,V> data=getData(var);
	
			if(data!=null) {
			   return data.get(key);
			}else {
				return null;
			}
}
	public <K,V> V HashMapGetOrDefault(VAR<PASSABLE_HASH_MAP<K,V>> var,K key,V defaultValue) {
        PASSABLE_HASH_MAP<K,V> data=getData(var);
	
			if(data!=null) {
			   return data.getOrDefault(key, defaultValue);
			}else {
				return defaultValue;
			}
}	
	public <K,V> boolean HashMapIsEmpty(VAR<PASSABLE_HASH_MAP<K,V>> var) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.isEmpty();
		  }else {
			  return false;
		  }
		
	}
	public <K,V> boolean HashMapContainsKey(VAR<PASSABLE_HASH_MAP<K,V>> var,K key) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.containsKey(key);
		  }else {
			  return false;
		  }
		
	}
	public <K,V> boolean HashMapContainsValue(VAR<PASSABLE_HASH_MAP<K,V>> var,V value) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.containsValue(value);
		  }else {
			  return false;
		  }
		
	}
	
	public <K,V> void HashMapClear(VAR<PASSABLE_HASH_MAP<K,V>> var) {
		      PASSABLE_HASH_MAP<K,V> data=getData(var);
			
			  if(data!=null) {
				   data.clearMap();
			  }
		
	}
	
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void ListSet(VAR<T> var,ST[] values) {
	      	 
          VAR<T> newVar=VAR.makeNewVar(var.getRealName(),var.getHandle());
        
          T data=newVar.getType();
          data.setListFromArray(values);
          TakeInData(newVar,data); 
           
		
    }
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST[]  ListGetAsArray(VAR<T> var,ST[] array) {
	      T data=getData(var);
		
		if(data!=null) {
		  return data.getListAsArray(array);
		}else {
			return null;
		}
		
		
  }
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> boolean  ListIsEmptyy(VAR<T> var) { 
		   T data=getData(var);
			
			if(data!=null) {
			  return data.isListEmpty();
			}else {
				return false;
			} 


}
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListClear(VAR<T> var) { 
	        
		  T data=getData(var);
			
			if(data!=null) {
			  data.clearList();;
			}
	}
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> int  ListSize(VAR<T> var) {
		  T data=getData(var);
			
			if(data!=null) {
			  return data.getListSize();
			}else {
				return 0;
			}
		
		
	}
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListSetAtIndex(VAR<T> var,int index,ST value) {
		    T data=getData(var);
			
			if(data!=null) {
			   data.setValueAtIndex(index, value);;
			}
		
	}
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST  ListGetValueAtIndex(VAR<T> var,int index) {
	    T data=getData(var);
		
		if(data!=null) {
		   return data.getValueAtIndex(index);
		}else {
			return null;
		}
	
}
	
	
	
	
	private  <ST,T extends PassableData<ST>>  void TakeInData(VAR<T> var,T data) {
	  
			this.Entity_Data.put(var.getMangledName(),data);
	 
	}
	
	public <ST,T extends PassableData<ST>> void INITVar(VAR<T> var,ST defaultValue){
		if(!hasVAR(var)) {
			setVar(var,defaultValue);
		}
	}
	
	

   	public <ST,T extends PassableData<ST>> void removeVAR(VAR<T> var) {
   	         this.Entity_Data.remove(var.getMangledName());
   	}
    
	
	public EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}

	
}
