package TestScprits;

import java.util.UUID;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import animation.ComponentAnimation;
import core.CoreEngine;
import core.Entity;
import core.VAR_RW;
import input.InputPoller;
import scripting.Script;
import test.ComponentTest;

public class PlayerScript extends Script {

	public static final UUID ID=UUID.randomUUID();
	
	private Entity player;
	
	
	
	
	@Override
	public void Start(Entity entity) {
		
           this.player=entity;
	}
	
	
	
	
	
	
	
    @Override
    public void GAMELOOP_TICK_BEFORE_PHYSICS() {
    	if(player.hasAllVars(new VAR_RW<?>[] {Entity.VAR_POSITION,Entity.VAR_MIRROR})){
    	    boolean mirror=player.getVar(Entity.VAR_MIRROR);
    		Vector2f position=player.getVar(Entity.VAR_POSITION);
    	    Vector2f movement=new Vector2f();
    	    Vector2f direction=new Vector2f();
    	 
    	    
    	  
    	    
    	  	float speed=2;
    		
    		
    		
    		
    		
    		
    		if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_LEFT)) {
    			movement.x=-1;
    			mirror=false;
    		}
            if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_RIGHT)) {
            	movement.x=1;
            	mirror=true;
            }
            if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_UP)) {
    			movement.y=1;
    			
    		}
            if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_DOWN)) {
            	movement.y=-1;
            }
            if(InputPoller.NOT_REALESED(GLFW.GLFW_KEY_W)) {
            	speed=5;
            }
            
           if(movement.length()!=0) {
        	   
           movement.normalize(direction);
           direction.mul((float)(speed),movement);
           player.setVar(Entity.VAR_VELOCITY,movement);
           PlayAnimation(player);
           }else {
             stopAnimation(player);
             player.setVar(Entity.VAR_VELOCITY,new Vector2f());
              
             
             
           }
           
    	   player.setVar(Entity.VAR_MIRROR,mirror);
    	   
    	   
    	   
    		}
    	
    	
    }
 
    private  void PlayAnimation(Entity e) {
		 e.setVar(ComponentAnimation.VAR_ANAIMATION_PAUSE,false);
	}
	
	
	
	private  void stopAnimation(Entity e) {
		  e.setVar(ComponentAnimation.VAR_ANAIMATION_RESET,true);
	      e.setVar(ComponentAnimation.VAR_ANAIMATION_PAUSE,true);
	}

	

	@Override
	public boolean DISABLE() {
		return true;
	}

	@Override
	public UUID getSCRIPTID() {
		return ID;
	}
  

}
