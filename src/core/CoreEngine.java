package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import animation.AnimationEngine;
import input.InputPoller;
import physics.PhysicsEngine;
import rendering.MainBatchRender;
import rendering.MainRenderHandler;

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
    	UpdatePhysicsEngine();
    	UpdateAnimationEngine();
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
		
		PhysicsEngine.update();
	}
	private static void UpdateRenderEngine() {
	     Iterator<Entity> I=entities.values().iterator();		
		 while(I.hasNext()) {
			 
            EntityComponent[] components=I.next().getComponents();	 
		    for(int i=0;i<components.length;i++) {
		    	components[i].RENDER_TICK();
		    }
		 }
	      
	      
		 MainRenderHandler.SortEntities();
		 MainRenderHandler.addToBatchedRender();
		 MainBatchRender.draw();
		 MainBatchRender.flushModel();
	}
	
    public static void AddEntity(Entity e) {
    	 entities.put(e.ID, e);
         e.Init();
    }
	
	
	
	
	
	public static void DebugPrint(String s) {
		if(DebugPrint) {
			System.out.println(s);
		}
	}
    public static Entity getEntity(UUID ID) {
    	return  entities.get(ID);
    			
    }
    public static<T extends PassableData> void sendData(UUID ENTITY_ID,VAR<T> var,T data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.TakeInData(var,data);
        }
    	
    }
    public static<T extends PassableData> void InitData(UUID ENTITY_ID,VAR<T> var,T data) {
        Entity e=getEntity(ENTITY_ID);
        if(e!=null) {
        	e.INITData(var,data);
        }
    	
    }


	public static void DebugPrint(String string,Class calledFrom) {
		if(DebugPrint)	
		System.out.println("["+calledFrom.getName()+"]---"+string);}	
				

}
