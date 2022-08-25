package main.java.rendering;

import java.util.LinkedList;
import java.util.UUID;

import main.java.core.CoreEngine;
import main.java.core.Entity;

public class RenderingEngine {

	
	private static LinkedList<UUID> entitiesToRender=new LinkedList<UUID>();
	
	
	
    public static void update() {
      for(int i=0;i<entitiesToRender.size();i++) {
    	  UUID ID=entitiesToRender.get(i);
    	  Entity e=CoreEngine.getEntity(ID);
    	  if(e!=null) {
    		  e.RENDER_TICK();
    	  }
    	  
      }
 	 MainRenderHandler.SortEntities();
	 MainRenderHandler.addToBatchedRender();
	 MainBatchRender.draw();
	 MainBatchRender.flushModel();
    	
    }
	
	public static void addEntity(Entity e) {
		if(!entitiesToRender.contains(e.ID)) {
	    entitiesToRender.add(e.ID);	
	    }
	}
	
	public static void removeEntity(Entity e) {
		entitiesToRender.remove(e.ID);
	}
	
	
	
	
}
