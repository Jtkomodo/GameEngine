package main.groovy.testScripts;

import java.util.UUID;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import main.groovy.core.GroovyScript
import main.java.animation.ComponentAnimation
import main.java.core.ComponentRenderModel
import main.java.core.ComponentScript
import main.java.core.ComponentTags
import main.java.core.Constants
import main.java.core.CoreEngine
import main.java.core.Entity
import main.java.core.EntityComponent
import main.java.core.Script
import main.java.core.VAR_RW
import main.java.events.EventHandle
import main.java.events.EventTypeTest
import main.java.input.InputPoller
import main.java.physics.AABB
import main.java.physics.ComponentColision
import main.java.test.ComponentTest
import main.java.events.EventDispatcher;
import main.java.events.EventTypeCollisionEnter;
import main.java.events.EventTypeCollisionExit;
import main.java.events.*;

protected class Player extends Script {

	public static final UUID ID=UUID.randomUUID();

	private Entity player;
    public float Walkingspeed=2;
	private float RunningSpeed=5;
	private EventHandle<DATA_AABB> handleOnEnter,handleOnExit;
   
    
	
	
	
	
	@Override
	public void Start(Entity entity) {

		this.player=entity;
		
	
	
		handleOnEnter=EventDispatcher.subscribe(new EventTypeCollisionEnter(entity.ID),{DATA_AABB box->ON_COLLISON_ENTER(box)});
		handleOnExit=EventDispatcher.subscribe(new EventTypeCollisionExit(entity.ID),{DATA_AABB box->ON_COLLISON_EXIT(box)});
			
	}
	
	public void ON_COLLISON_EXIT(DATA_AABB box) {
		
	   UUID entityID=box.A.getID();
	
	   if(entityID!=null) {
		   Entity entity=CoreEngine.getEntity(entityID);
		   
		   if(entity.ListContains(ComponentTags.get_VAR_TAGS(),"Enemy")) {
			   CoreEngine.DebugPrint("exited collision with Enemy");
		   }

		}
	   
		
		
	}
	public void ON_COLLISON_ENTER(DATA_AABB box) {
		UUID entityID=box.A.getID();
		if(entityID!=null) {
			Entity entity=CoreEngine.getEntity(entityID);

			if(entity.ListContains(ComponentTags.get_VAR_TAGS(),"Enemy")) {
				CoreEngine.DebugPrint("collided with Enemy");


			}else if(entity.ListContains(ComponentTags.get_VAR_TAGS(),"Interactable")) {
				CoreEngine.DebugPrint("collided with Interactable");

			}


		}



	}



	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS() {
		if(player.hasVAR(Entity.VAR_POSITION) && player.hasVAR(Entity.VAR_MIRROR)){
			
			boolean mirror=player.getVar(Entity.VAR_MIRROR);
			Vector2f movement=new Vector2f();
			Vector2f direction=new Vector2f();




		    float speed=this.Walkingspeed;




        

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
				speed=this.RunningSpeed;
				if(InputPoller.JustPushed(GLFW.GLFW_KEY_W))
					EventDispatcher.post(new EventTypeTest("function"),new DATA_Float(speed));
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
		EventDispatcher.unsubscribe(handleOnEnter);
		EventDispatcher.unsubscribe(handleOnExit);
		
		return true;
	}

	@Override
	public UUID getSCRIPTID() {
		return ID;
	}


}
new Player();
