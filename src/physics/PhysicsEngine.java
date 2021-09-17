package physics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import org.joml.Vector2f;

import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.PASSABLE_VEC2F;
import core.VAR_RW;
import events.Flag;

public class PhysicsEngine {

	private static LinkedList<Collision> collisionsColided=new LinkedList<Collision>();
	private static LinkedList<UUID> entities=new LinkedList<UUID>();
	
	private static HashMap<Collision,Flag> collisionsWatched=new HashMap<Collision,Flag>();  
	
	
	
	/**
	 * This will add a collision(two AABBs colliding with each other) to a watch list when the collision changes it will update the flag
	 * @param colision the two collisions that will be checked
	 * @param flagEffected the flag that will be set if the collision happens
	 */
	public static void WatchForCollision(Collision colision,Flag flagEffected) {
		collisionsWatched.put(colision, flagEffected);
	
	}
	
	
	
	public static void addEntity(Entity e) {
		if(!entities.contains(e.ID)) {
			entities.add(e.ID);
		}
	}
	public static boolean removeEntity(Entity e) {
		return entities.remove(e.ID);
	}
	
	public static void update() {
		//update the simulation by calling the GAMELOOP_TICK method
		updateSim();
		//now detect all collisions
		DetectionUpdate();
		//now do the responses for each collision
		ResponseUpdate();
		
	}
	
	
	private static void ResponseUpdate() {
		while(!collisionsColided.isEmpty()) {
			Collision c=collisionsColided.pop();
		    UUID A_ID=c.aabb1.getID();
		    UUID B_ID=c.aabb2.getID();
		    Entity A=CoreEngine.getEntity(A_ID);
		    Entity B=CoreEngine.getEntity(B_ID);
		    if(A!=null && B!=null) {
		    	if(A.hasAllVars(new VAR_RW<?>[] {ComponentColision.VAR_AABB,Entity.VAR_POSITION,Entity.VAR_VELOCITY})) {
		    	
		    	
		    	
		    	
		    	AABB a=A.getVar(ComponentColision.VAR_AABB);
			    AABB b=B.getVar(ComponentColision.VAR_AABB);
			    Vector2f velocity=A.getVar(Entity.VAR_VELOCITY);
		    	Vector2f position=A.getVar(Entity.VAR_POSITION);
		    	
		    	
		    	Vector2f direction=new Vector2f();
		 
		    	
		    
		    	if(velocity.length()!=0) {
		    		velocity.normalize(direction);
		    	}else {
		    		direction=new Vector2f();
		    	}
		    	
		    	
		    	Vector2f newMovement=a.findVector(position,velocity,direction, b);
		         if(newMovement!=null) {
		        	 A.setVar(Entity.VAR_POSITION,newMovement);
		         }
		           
		    	
		    	}
		    	
		    }
		    
		}
		
	}


	private static void DetectionUpdate() {

		for(int i_1=0;i_1<entities.size();i_1++) {
			boolean collision=false;
			for(int i_2=0;i_2<entities.size();i_2++) {
				if(i_1 !=i_2) {


					UUID ID_1=entities.get(i_1);
				    UUID ID_2=entities.get(i_2);
					

				
						if(CoreEngine.HasVar(ID_1,ComponentColision.VAR_AABB) && CoreEngine.HasVar(ID_2, ComponentColision.VAR_AABB)) {
							AABB A=CoreEngine.RecieveData(ID_1,ComponentColision.VAR_AABB);
							AABB B=CoreEngine.RecieveData(ID_2,ComponentColision.VAR_AABB);
							
							//if there is a collision then add that collision to the list
						
							
							Collision col=new Collision(A,B);
							
							if(A.vsAABB(B)) {
								collision=true;
							
								if(collisionsWatched.containsKey(col)) {
									collisionsWatched.get(col).setState(true);
								}
								if(!collisionsColided.contains(col)) {
									collisionsColided.add(col);
								}
                               A.setFlagState(true);
							}else {
								if(collisionsWatched.containsKey(col)) {
									collisionsWatched.get(col).setState(false);
								}
							}

						}
					
				}
			}
			//if there was no collision at all with that box then set the before collision position to the position now and set the state of the flag to false
			if(!collision) {
				UUID ID=entities.get(i_1);
			
                if(CoreEngine.HasALLVars(ID,new VAR_RW<?>[] {ComponentColision.VAR_AABB,Entity.VAR_POSITION})) {
                	CoreEngine.RecieveData(ID,ComponentColision.VAR_AABB).setFlagState(false);
                    Vector2f position=CoreEngine.RecieveData(ID,Entity.VAR_POSITION);
    				CoreEngine.sendData(ID,ComponentColision.VAR_BEFORE_POSITION,position);
    				  
                }
			
			}
		}
	}


	public static void updateSim() {
	        for(int i=0;i<entities.size();i++) {
	        	UUID ID=entities.get(i);
	        	Entity e=CoreEngine.getEntity(ID);
	        	if(e!=null && e.hasVAR(ComponentColision.READ_VAR_NEXT_POS())) {
	        		Vector2f new_position=e.getVar(ComponentColision.READ_VAR_NEXT_POS());
	        		e.setVar(Entity.VAR_POSITION,new_position);
	        		
	        	}
	        }
		
	}




	


	
}
