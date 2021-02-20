package core;

import java.util.HashMap;
import java.util.UUID;

import input.InputPoller;
import rendering.MainBatchRender;
import rendering.MainRenderHandler;
import rendering.OneTextureBatchedModel;

public class CoreEngine {
    public static boolean DebugPrint=true;
    
    private static HashMap<UUID,Entity> entites=new HashMap<UUID,Entity>();
    
    protected static void updateEngine() {
    	UpdatePhysicsEngine();
    	UpdateAnimationEngine();
    	UpdateRenderEngine();
    }
    
    
    
    private static void UpdateAnimationEngine() {
		
	}



	public static void UpdateInput() {
    	InputPoller.POll();
    }
    
	private static void UpdatePhysicsEngine() {
		
	}
	private static void UpdateRenderEngine() {
		 MainRenderHandler.SortEntities();
		 MainRenderHandler.addToBatchedRender();
		 MainBatchRender.draw();
		 MainBatchRender.flushModel();
	}
	
    public static void AddEntity(Entity e) {
    	
    }
	
	
	
	
	
	public static void DebugPrint(String s) {
		if(DebugPrint) {
			System.out.println(s);
		}
	}



	public static void DebugPrint(String string,Class calledFrom) {
		if(DebugPrint)	
		System.out.println("["+calledFrom.getName()+"]---"+string);}	
				

}
