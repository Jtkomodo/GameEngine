package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import UI.UIManager;

import java.util.UUID;

import animation.AnimationEngine;
import events.FlagHandler;
import input.InputPoller;
import physics.PhysicsEngine;
import rendering.MainBatchRender;
import rendering.MainRenderHandler;
import rendering.RenderingEngine;

public class CoreEngine {
    public static boolean DebugPrint=true;
    
    private static HashMap<UUID,Entity> entities=new HashMap<UUID,Entity>();
    private static double last_frame=Timer.getTIme();
    public static double deltaT;

	public static boolean Debugdraw=false;
    /**
     * main game loop this updates everything in the game
     */
    protected static void updateEngine() {
    	double time1=core.Timer.getTIme();
    	deltaT=time1-last_frame;
    	FlagHandler.updateFlags();
    	UIManager.CollisionUpdate();
        UpdateGameLoop_BEFORE();
    	UpdatePhysicsEngine();
    	UpdateGameLoop_AFTER();
    	UpdateAnimationEngine();
    	UIManager.RenderUpdate();
    	UpdateRenderEngine();
    	
    	
    	
    	last_frame=Timer.getTIme();
    }
   
    
    
    private static void UpdateAnimationEngine() {
		
    	AnimationEngine.update();
    	
	}



	public static void UpdateInput() {
    	InputPoller.POll();
    	
    }
    
	private static void UpdatePhysicsEngine() {
		UIManager.CollisionUpdate();
		
		PhysicsEngine.update();
	}
	private static void UpdateRenderEngine() {
		
	     RenderingEngine.update();    
	      
	      
	
	}
	
	private static void UpdateGameLoop_BEFORE() {
	    Iterator<Entry<UUID,Entity>>	i=entities.entrySet().iterator();
	    
	    
	    while(i.hasNext()) {
	    	Entry<UUID,Entity> entry=i.next();
	    	UUID ID=entry.getKey();
	    	Entity E=entry.getValue();
	    	
	    	E.GAMELOOP_TICK_BEFORE_PHYSICS();
	    }
	}
	private static void UpdateGameLoop_AFTER() {
	    Iterator<Entry<UUID,Entity>>	i=entities.entrySet().iterator();
	    
	    
	    while(i.hasNext()) {
	    	Entry<UUID,Entity> entry=i.next();
	    	UUID ID=entry.getKey();
	    	Entity E=entry.getValue();
	    	
	    	E.GAMELOOP_TICK_AFTER_PHYSICS();
	    }
	}
	
	public static void RemoveAllEntites() {
		  Iterator<Entry<UUID,Entity>>  I=entities.entrySet().iterator();
		   while(I.hasNext()) {
                Entry<UUID,Entity> e=I.next();
			    removeEntity(e.getValue());
		   }
	}
	
	public static void RemoveEntities(Entity[] es) {
		for(int i=0;i<es.length;i++) {
			removeEntity(es[i]);
		}
	}
	
	
	public static void AddEntities(Entity[] es) {
	   Iterator<Entry<UUID,Entity>>  I=entities.entrySet().iterator();
	   while(I.hasNext()) {
		   Entry<UUID,Entity> e=I.next();
		   AddEntity(e.getValue());
	   }
	   
	}

	
	
	public static void AddEntity(Entity e) {
		if(!entities.containsKey(e.ID)) {
			entities.put(e.ID, e);
			e.Init();
		}
	}
	public static boolean removeEntity(Entity e) {
		if(entities.containsKey(e.ID)) {
		  boolean success=e.DISABLE();
		  entities.remove(e.ID);
		  PhysicsEngine.removeEntity(e);
		  RenderingEngine.removeEntity(e);
		  AnimationEngine.removeEntityAnimation(e.ID);
		  
		  
		  
	      return success;
			
			
		}else {
			return false;
		}
	    
		
	}
	
	
	
	
	public static boolean hasEntity(Entity e) {
		return entities.containsKey(e.ID);
	}
	
	
	
	public static void DebugPrint(String s) {
		if(DebugPrint) {
			System.out.println(s);
		}
	}
    public static Entity getEntity(UUID ID) {
    	return  entities.get(ID);
    			
    }
    
    
    
    
    public static<T extends PassableData<?>> boolean HasALLVars(UUID ENTITY_ID,VAR_RW<T>[] vars) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.hasAllVars(vars);
        }else {
        	return false;
        }
    	
    }
    
    
    
    
    public static<ST,T extends PassableData<ST>> boolean HasVar(UUID ENTITY_ID,VAR_RW<T> var) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.hasVAR(var);
        }else {
        	return false;
        }
    	
    }
    
    
    
    public static<ST,T extends PassableData<ST>> ST RecieveData(UUID ENTITY_ID,VAR_RW<T> var) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.getVar(var);
        }else {
        	return null;
        }
    	
    }
    
    
    
    
    
    public static<ST,T extends PassableData<ST>> void sendData(UUID ENTITY_ID,VAR_RW<T> var,ST data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.setVar(var,data);
        }
    	
    }
    public static<ST,T extends PassableData<ST>> void InitData(UUID ENTITY_ID,VAR_RW<T> var,ST data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.INITVar(var,data);
        }
    	
    }
    
    
    public static<T extends PassableData<?>> boolean HasALLVars(UUID ENTITY_ID,VAR_R<T>[] vars) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.hasAllVars(vars);
        }else {
        	return false;
        }
    	
    }
    
    
    
    
    public static<ST,T extends PassableData<ST>> boolean HasVar(UUID ENTITY_ID,VAR_R<T> var) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.hasVAR(var);
        }else {
        	return false;
        }
    	
    }
    
    
    
    public static<ST,T extends PassableData<ST>> ST RecieveData(UUID ENTITY_ID,VAR_R<T> var) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	return e.getVar(var);
        }else {
        	return null;
        }
    	
    }
    
    
    
    
    
    public static<ST,T extends PassableData<ST>> void sendData(UUID ENTITY_ID,VAR_W<T> var,ST data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.setVar(var,data);
        }
    	
    }
    
    
    
    public static<ST,T extends PassableData<ST>> void InitData(UUID ENTITY_ID,VAR_W<T> var,ST data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.INITVar(var,data);
        }
    	
    }


	public static void DebugPrint(String string,Class calledFrom) {
		if(DebugPrint)	
		System.out.println("["+calledFrom.getName()+"]---"+string);}	
				

}
