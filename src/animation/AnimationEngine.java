package animation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import org.joml.Vector2f;

import core.CoreEngine;
import core.Entity;
import core.PASSABLE_BOOL;
import core.PASSABLE_INT;
import core.PASSABLE_SPRITESHEET;
import core.PassableData;
import core.Timer;

public class AnimationEngine{

	
	
	
	protected static HashMap<UUID,Animation> animations=new HashMap<UUID,Animation>();
	
	private static double  timepassed=0;
	private static double frametime=0;
	
	
   public static void addEntityAnimation(UUID entity,Animation animation) {
	    animations.put(entity, animation);
   }
	
	
   public static void update() {
	   
	  
	    
	  timepassed=CoreEngine.deltaT;
	  frametime+=timepassed;//same thing but this is to actually tell when a second has passed only for debugging purposes 
    
	  Iterator<UUID> i=animations.keySet().iterator();
	  while(i.hasNext()) {
		  UUID ID=i.next();
		  Entity e=CoreEngine.getEntity(ID);
		  if(e!=null) {
			  boolean paused=false;
			  if(e.hasVAR(ComponentAnimation.VAR_ANAIMATION_PAUSE)) {
			   paused=e.getVar(ComponentAnimation.VAR_ANAIMATION_PAUSE);
			  }
			  boolean reset=false;
			  if(e.hasVAR(ComponentAnimation.VAR_ANAIMATION_RESET)) {
			   reset=e.getVar(ComponentAnimation.VAR_ANAIMATION_RESET);
			  }
			
			  
			  if(reset) {
				resetAnimation(ID,e,animations.get(ID));
			  }
			  if(!paused) {
			  updateAnimation(ID,e,animations.get(ID));
			  }
		  }
	  }
	   
   }
	
	
   private static void resetAnimation(UUID ID, Entity e, Animation A) {

	   A.unp+=timepassed;//this is just the time taken since a animation frame was changed
	  if(A.unp>=(1/A.fps)) {
		A.unp-=1/A.fps;  
	  A.currentFrame=0;
	   AnimationData d=A.data[A.currentFrame];
	
	
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_SPRITE_SHEET,d.sheet);
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_FRAME,d.frame);
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_ANIMATION_UPDATED,true);
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_ANAIMATION_RESET,false);
	 }
		if(frametime>=1.0) {

			frametime=0;
		}
	  }
	   
	



private static void updateAnimation(UUID ID,Entity e,Animation A) {
	 

	   A.unp+=timepassed;//this is just the time taken since a animation frame was changed
	  if(A.unp>=(1/A.fps)) {
		A.unp-=1/A.fps;  
	   int frame=A.currentFrame;
	   if(frame!=A.data.length-1) {
		   A.currentFrame++;
	   }else {
		   A.currentFrame=0;
	   }
	   AnimationData d=A.data[A.currentFrame];
	
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_SPRITE_SHEET,d.sheet);
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_FRAME,d.frame);
	   CoreEngine.sendData(ID,ComponentAnimation.VAR_ANIMATION_UPDATED,true);
	   
	 }
		if(frametime>=1.0) {

			frametime=0;
		}
	  }
	   
	   
   




public static void removeEntityAnimation(UUID iD) {
	 animations.remove(iD);
}
	
	
	
	
	
	
}
