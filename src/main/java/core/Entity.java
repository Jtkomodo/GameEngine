package main.java.core;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.joml.Vector2f;

import main.java.animation.SpriteSheet;
import main.java.physics.AABB;
import main.java.rendering.Model;
import main.java.rendering.Texture;

public class Entity {

	
	
	
	
	

	
	

	public final static VAR_RW<PASSABLE_FLAG> VAR_FLAG=VAR_RW.makeNewVar("testFlag",PASSABLE_FLAG.getHandle());
	public final static VAR_RW<PASSABLE_VEC2F> VAR_PROJECTION=VAR_RW.makeNewVar("PROJECTION",PASSABLE_VEC2F.getHandle());
	public final static VAR_RW<PASSABLE_VEC2F> VAR_POSITION=VAR_RW.makeNewVar("POSITION",PASSABLE_VEC2F.getHandle());
	public final static VAR_RW<PASSABLE_VEC2F> VAR_VELOCITY=VAR_RW.makeNewVar("Velocity",PASSABLE_VEC2F.getHandle());
	public final static VAR_RW<PASSABLE_BOOL> VAR_MIRROR=VAR_RW.makeNewVar("BEFORE_POSITION",PASSABLE_BOOL.getHandle());
	public final static VAR_RW<PASSABLE_VEC4F> VAR_COLOR=VAR_RW.makeNewVar("COLOR",PASSABLE_VEC4F.getHandle());
	
	
	public final static VAR_RW<PASSABLE_LINKED_LIST<AABB>> VAR_TESTLIST=VAR_RW.makeNewVar("TESTLIST",PASSABLE_LINKED_LIST.getHandle(PASSABLE_AABB.getHandle()));
	
	public final static VAR_RW<PASSABLE_HASH_MAP<String,Integer>> VAR_TESTHASH=VAR_RW.makeNewVar("TESTHASH",PASSABLE_HASH_MAP.getHandle(PASSABLE_STRING.getHandle(), PASSABLE_INT.getHandle()));
	
	public final static VAR_RW<PASSABLE_HASH_MAP<String,AABB>> VAR_TESTHASH_AABBB=VAR_RW.makeNewVar("TESTHASH",PASSABLE_HASH_MAP.getHandle(PASSABLE_STRING.getHandle(), PASSABLE_AABB.getHandle()));
	
	
	
	public final UUID ID=UUID.randomUUID();
	
	
	
	
	
	
	public boolean DEBUG=false;
	private boolean HIDDDEN=false;
	private HashMap<String, PassableData<?>> Entity_Data=new HashMap<String, PassableData<?>>();
	private HashMap<UUID,EntityComponent> components=new HashMap<UUID,EntityComponent>(); 
	public Entity() {
		
	}

	public Entity(EntityComponent[] components){

		addComponents(components);
		
	}
	
	
	public void GAMELOOP_TICK_BEFORE_PHYSICS() {
		Iterator<EntityComponent> i=components.values().iterator();
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.GAMELOOP_TICK_BEFORE_PHYSICS();
		}
	}
	public void GAMELOOP_TICK_AFTER_PHYSICS() {
		Iterator<EntityComponent> i=components.values().iterator();
		while(i.hasNext()) {
			EntityComponent c=i.next();
			c.GAMELOOP_TICK_AFTER_PHYSICS();
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
		if(CoreEngine.hasEntity(this)) {
		    c.INIT(this);
		}
	}
	public boolean removeComponent(UUID ID) {
		if(hasComponent(ID)) {
			boolean r=this.components.get(ID).DISABLE();
			this.components.remove(ID);
			return r;
		}else {
			return false;
		}
		
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
	
	
	public boolean hasAllVars(VAR_RW<?>[] vars){
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
		
	public boolean hasAllVars(VAR_W<?>[] vars){
		int i=0;
		while(i<vars.length) {
			
			String varName=vars[i].getVar().getMangledName();
			if(!Entity_Data.containsKey(varName) || (Entity_Data.get(varName)==null)) {
				return false;
			}else if(Entity_Data.get(varName).getValue()==null) {
				return false;
			}
				 
			i++;	 
			 }
				
				return true;
			}	
				
	public boolean hasAllVars(VAR_R<?>[] vars){
		int i=0;
		while(i<vars.length) {
			String varName=vars[i].getVar().getMangledName();
			if(!Entity_Data.containsKey(varName) || (Entity_Data.get(varName)==null)) {
				return false;
			}else if(Entity_Data.get(varName).getValue()==null) {
				return false;
			}
				 
			i++;	 
			 }
				
				return true;
			}	
		
	
	
	
	

	public <ST,T extends PassableData<ST>>  T getData(VAR_RW<T> var){
	
		   if(Entity_Data.containsKey(var.getMangledName())) {
	     	 return (T)Entity_Data.get(var.getMangledName());	
		   }else {
			  return null;
		   }
		}
	
   public<ST,T extends PassableData<ST>> boolean hasVAR(VAR_RW<T> var){
			
		   return getVar(var)!=null;
	 
  }
   public  <ST,T extends PassableData<ST>> boolean hasVAR(VAR_R<T> var){
	
		   return getVar(var.getVar())!=null;
	 
   }
   
   public  <ST,T extends PassableData<ST>> boolean hasVAR(VAR_W<T> var){
		
	   return getVar(var.getVar())!=null;
 
}

	
	
	public <ST,T extends PassableData<ST>> ST getVar(VAR_RW<T> var) {
		T data=getData(var);
		
		if(data!=null) {
		  return data.getValue();
		}else {
			return null;
		}
	}
	public <ST,T extends PassableData<ST>> ST getVar(VAR_R<T> var) {
	     return getVar(var.getVar());
	}
	
	
	public  <ST,T extends PassableData<ST>> void setVar(VAR_RW<T> var,ST value) {
		    
		         VAR_RW<T> newVar=VAR_RW.makeNewVar(var.getRealName(),var.getHandle());
		         
		         T data=newVar.getType();
		         
		    	
		    	 data.setValue(value);
		         TakeInData(newVar,data); 
	}
	
	public  <ST,T extends PassableData<ST>> void setVar(VAR_W<T> var_W,ST value) {
	    
		VAR_RW<T> var=var_W.getVar();
        VAR_RW<T> newVar=VAR_RW.makeNewVar(var.getRealName(),var.getHandle());
        
        T data=newVar.getType();
        
   	
   	 data.setValue(value);
        TakeInData(newVar,data); 
}
	
	
	
	
	public <K,V> void HashMapPut(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,K key,V value) {
	         PASSABLE_HASH_MAP<K,V> data=getData(var);
		
				if(data!=null) {
				   data.put(key, value);
				}else {
				    VAR_RW<PASSABLE_HASH_MAP<K,V>> newVar=VAR_RW.makeNewVar(var.getRealName(),var.getHandle());
			         
			          data=newVar.getType();
			         
			    	
			    	 data.put(key, value);
			         TakeInData(newVar,data); 
					
				}
			   
	}
	public <K,V> void HashMapPut(VAR_W<PASSABLE_HASH_MAP<K,V>> var,K key,V value) {
		HashMapPut(var.getVar(), key, value);
		
	}
	public <K,V> V HashMapGet(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,K key) {
        PASSABLE_HASH_MAP<K,V> data=getData(var);
	
			if(data!=null) {
			   return data.get(key);
			}else {
				return null;
			}
    }
	public <K,V> void HashMapRemove(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,K key) {
		PASSABLE_HASH_MAP<K,V> data=getData(var);
		if(data!=null) {
			data.remove(key);
			
		}	
	}
	
	
	
	public <K,V> V HashMapGet(VAR_R<PASSABLE_HASH_MAP<K,V>> var,K key) {
		return HashMapGet(var.getVar(), key);
		
	}
	public <K,V> V HashMapGetOrDefault(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,K key,V defaultValue) {
        PASSABLE_HASH_MAP<K,V> data=getData(var);
	
			if(data!=null) {
			   return data.getOrDefault(key, defaultValue);
			}else {
				return defaultValue;
			}
}	
	public <K,V> V HashMapGetOrDefault(VAR_R<PASSABLE_HASH_MAP<K,V>> var,K key,V defaultValue) {
		return HashMapGetOrDefault(var.getVar(), key, defaultValue);
	}
	
	
	public <K,V> boolean HashMapIsEmpty(VAR_RW<PASSABLE_HASH_MAP<K,V>> var) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.isEmpty();
		  }else {
			  return false;
		  }
		
	}
	public <K,V> boolean HashMapIsEmpty(VAR_R<PASSABLE_HASH_MAP<K,V>> var) {
		return HashMapIsEmpty(var.getVar());
	}
	public <K,V> boolean HashMapContainsKey(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,K key) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.containsKey(key);
		  }else {
			  return false;
		  }
		
	}
	public <K,V> boolean HashMapContainsKey(VAR_R<PASSABLE_HASH_MAP<K,V>> var,K key) {
		return HashMapContainsKey(var.getVar(), key);
		
	}
	public <K,V> boolean HashMapContainsValue(VAR_RW<PASSABLE_HASH_MAP<K,V>> var,V value) {
		   PASSABLE_HASH_MAP<K,V> data=getData(var);
		
		  if(data!=null) {
			  return data.containsValue(value);
		  }else {
			  return false;
		  }
		
	}
	public <K,V> boolean HashMapContainsValue(VAR_R<PASSABLE_HASH_MAP<K,V>> var,V value) {
		return HashMapContainsValue(var.getVar(), value);
		
	}
	
	
	public  <K,V> void HashMapClear(VAR_W<PASSABLE_HASH_MAP<K,V>> var) {
		
		HashMapClear(var.getVar());
		
	}
	
	
	
	public  <K,V> void HashMapClear(VAR_RW<PASSABLE_HASH_MAP<K,V>> var) {
		      PASSABLE_HASH_MAP<K,V> data=getData(var);
			
			  if(data!=null) {
				   data.clearMap();
			  }
		
	}
	
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void ListSet(VAR_RW<T> var,ST[] values) {
	      	 
          VAR_RW<T> newVar=VAR_RW.makeNewVar(var.getRealName(),var.getHandle());
        
          T data=newVar.getType();
          data.setListFromArray(values);
          TakeInData(newVar,data); 
           
		
    }
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void ListSet(VAR_W<T> var,ST[] values) {
		ListSet(var.getVar(), values);
	}
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST[]  ListGetAsArray(VAR_RW<T> var,ST[] array) {
	      T data=getData(var);
		
		if(data!=null) {
		  return data.getListAsArray(array);
		}else {
			return null;
		}
		
		
  }
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST[]  ListGetAsArray(VAR_R<T> var,ST[] array) {
		 return ListGetAsArray(var.getVar(), array);
		
	}
	
	public <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> boolean  ListIsEmptyy(VAR_RW<T> var) { 
		   T data=getData(var);
			
			if(data!=null) {
			  return data.isListEmpty();
			}else {
				return false;
			} 


  }
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> boolean  ListIsEmptyy(VAR_R<T> var) { 
		 return ListIsEmptyy(var.getVar());
	}
	
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListClear(VAR_RW<T> var) { 
	        
		  T data=getData(var);
			
			if(data!=null) {
			  data.clearList();;
			}
	}
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListClear(VAR_W<T> var) { 
	     	ListClear(var.getVar());
		
	}
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> int  ListSize(VAR_RW<T> var) {
		  T data=getData(var);
			
			if(data!=null) {
			  return data.getListSize();
			}else {
				return 0;
			}
		
		
	}
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> int  ListSize(VAR_R<T> var) {
		return ListSize(var.getVar());
		
		
	}
	
	public <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListSetAtIndex(VAR_RW<T> var,int index,ST value) {
		    T data=getData(var);
			
			if(data!=null) {
			   data.setValueAtIndex(index, value);;
			}
		
	}
	public <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> void  ListSetAtIndex(VAR_W<T> var,int index,ST value) {
	      ListSetAtIndex(var.getVar(), index, value); 
    }
	
	
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST  ListGetValueAtIndex(VAR_RW<T> var,int index) {
	    T data=getData(var);
		
		if(data!=null) {
		   return data.getValueAtIndex(index);
		}else {
			return null;
		}
	
}
	public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> ST  ListGetValueAtIndex(VAR_R<T> var,int index) {
		
		return ListGetValueAtIndex(var.getVar(), index);
		
	}
     public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> boolean  ListContains(VAR_RW<T> var,ST value) {
		boolean returnValue=false;
		T data=getData(var);
		if(data!=null) {
			returnValue=data.hasValue(value);
		}
		
		
		return returnValue;
	}
     public  <L extends List<ST>,ST,T extends PassableData<L> & PassableList<ST>> boolean  ListContains(VAR_R<T> var,ST value) {
 	       return ListContains(var.getVar(),value);
 	}
	
		
	private <ST,T extends PassableData<ST>>  void TakeInData(VAR_RW<T> var,T data) {
	  
			this.Entity_Data.put(var.getMangledName(),data);
	 
	}
	
	
	
	
	
	public <ST,T extends PassableData<ST>> void INITVar(VAR_RW<T> var,ST defaultValue){
		if(!hasVAR(var)) {
			setVar(var,defaultValue);
		}
	}
	public <ST,T extends PassableData<ST>> void INITVar(VAR_W<T> var,ST defaultValue){
		INITVar(var.getVar(),defaultValue);
	}
	

   	public <ST,T extends PassableData<ST>> void removeVAR(VAR_RW<T> var) {
   	         this.Entity_Data.remove(var.getMangledName());
   	}
   	public <ST,T extends PassableData<ST>> void removeVAR(VAR_W<T> var) {
	         this.Entity_Data.remove(var.getVar().getMangledName());
	}
    public void DebugPrintAllVars(String entityName) {
    	
    	CoreEngine.DebugPrint(entityName+":[");
        Iterator<Entry<String,PassableData<?>>> I=this.Entity_Data.entrySet().iterator();
    	while(I.hasNext()) {
    		Entry<String,PassableData<?>> e=I.next();
    		String key=e.getKey();
    		PassableData<?> d=e.getValue();
            String s[]=key.split("\\*");
            String name=s[0];
            String type=s[1];
            
    		CoreEngine.DebugPrint("    "+name+":{\n"+"	Type:"+type+"\n"+"	Value:"+d.printValue("")+"\n	}"); 		
    		
    	}
    	
    	
    	
    	CoreEngine.DebugPrint("]");
    }
	
    public void hide() {

		CoreEngine.removeEntity(this);
		
    	this.HIDDDEN=true;
    	
    }
    public void show() {
    	CoreEngine.AddEntity(this);
    	this.HIDDDEN=false;
    }
    
	public EntityComponent[] getComponents() {
		return this.components.values().toArray(new EntityComponent[this.components.size()]);
	}


	public boolean isHIDDDEN() {
		return HIDDDEN;
	}
   
	protected static  <ST,T extends PassableData<ST>> VAR_W<T> createNewVAR_W(VAR_RW<T> var){
		  return new VAR_W<T>(var);	
		 
	 }
	
	
	 protected static  <ST,T extends PassableData<ST>> VAR_R<T> createNewVAR_R(VAR_RW<T> var){
		  return new VAR_R<T>(var);	
		 
	 }
	
	 protected static <ST,T extends PassableData<ST>> VAR_RW<T> createNewVAR(String name,DATA_HANDLE<ST,T> handle){
		  return VAR_RW.makeNewVar(name,handle);
	 }
	
	
}
