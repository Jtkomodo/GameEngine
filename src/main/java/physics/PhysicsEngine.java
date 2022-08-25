package main.java.physics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import org.joml.Vector2f;

import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.EntityComponent;
import main.java.core.PASSABLE_VEC2F;
import main.java.core.VAR_R;
import main.java.core.VAR_RW;
import main.java.events.EventDispatcher;
import main.java.events.EventTypeCollisionEnter;
import main.java.events.EventTypeCollisionExit;
import main.java.events.Flag;

public class PhysicsEngine {

	private static LinkedList<Collision> collisionsColided=new LinkedList<Collision>();
	private static LinkedList<UUID> entities=new LinkedList<UUID>();
	private static LinkedList<UUID> entitiesMoved=new LinkedList<UUID>();
	private static HashMap<Collision,Boolean> stateOfCollisions=new HashMap<Collision,Boolean>();	
	
	

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
	//	CoreEngine.DebugPrint("collisions"+collisionsColided.size());
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

		for(int i_1=0;i_1<entitiesMoved.size();i_1++) {
			boolean collision=false;
			for(int i_2=0;i_2<entities.size();i_2++) {


					Entity e=CoreEngine.getEntity(entitiesMoved.get(i_1));
				    Entity e2=CoreEngine.getEntity(entities.get(i_2));

				   
				    

				
						if(e.hasVAR(ComponentColision.VAR_AABB) && e2.hasVAR(ComponentColision.VAR_AABB) && !e.equals(e2)) {
							AABB A=e.getVar(ComponentColision.VAR_AABB);
							AABB B=e2.getVar(ComponentColision.VAR_AABB);
							//if there is a collision then add that collision to the list
						
							
							Collision col=new Collision(A,B);
							Collision col2=new Collision(B,A);
							
							
							//get the last state of the collisions so we can fire a event on enter
							boolean stateLastAB;
							boolean stateLastBA;
							
							if(stateOfCollisions.containsKey(col)) {
							   stateLastAB=stateOfCollisions.get(col);
							}else {
							   stateLastAB=false;
							}
							
							if(stateOfCollisions.containsKey(col2)) {
								   stateLastBA=stateOfCollisions.get(col2);
								}else {
								   stateLastBA=false;
							}								
							
							
							if(A.vsAABB(B)) {
								collision=true;
								
								if(!collisionsColided.contains(col)) {
									collisionsColided.add(col);
								}
//								
								//if the last time through the collision was false then now we fire the event on enter
								if(stateLastAB==false) {
									EventDispatcher.post(new EventTypeCollisionEnter(e.ID),B);
								}
								
								if(stateLastBA==false) {
									EventDispatcher.post(new EventTypeCollisionEnter(e2.ID),A);
								}
								
								
                               stateOfCollisions.put(col,true);
                               stateOfCollisions.put(col2,true);
                               
							}else {
								if(stateLastAB==true) {
									EventDispatcher.post(new EventTypeCollisionExit(e.ID),B);
								}

								if(stateLastBA==true) {	
									EventDispatcher.post(new EventTypeCollisionExit(e2.ID),A);
								}
								stateOfCollisions.put(col,false);
								stateOfCollisions.put(col2,false);


							}

						    
					
				}
			}
			
			//if there was no collision at all with that box then set the before collision position to the position now and set the state of the flag to false
			if(!collision) {
				UUID ID=entitiesMoved.get(i_1);
			
                if(CoreEngine.HasALLVars(ID,new VAR_RW<?>[] {ComponentColision.VAR_AABB,Entity.VAR_POSITION})) {
                	//CoreEngine.RecieveData(ID,ComponentColision.VAR_AABB).setFlagState(false);
                	
                    Vector2f position=CoreEngine.RecieveData(ID,Entity.VAR_POSITION);
    				CoreEngine.sendData(ID,ComponentColision.VAR_BEFORE_POSITION,position);
    				  
                }
			
			}
		}
	
	}


	public static void updateSim() {
		entitiesMoved.clear();
		for(int i=0;i<entities.size();i++) {
			UUID ID=entities.get(i);
			Entity e=CoreEngine.getEntity(ID);
			if(e!=null && e.hasAllVars(new VAR_R[] {ComponentColision.READ_VAR_NEXT_POS(),ComponentColision.VAR_MOVED.getAsReadOnly()})) {
				if(e.getVar(ComponentColision.VAR_MOVED)) {
					Vector2f new_position=e.getVar(ComponentColision.READ_VAR_NEXT_POS());
					e.setVar(Entity.VAR_POSITION,new_position);
					entitiesMoved.add(e.ID);

			       //    CoreEngine.DebugPrint("why isn't it working");

				}else {
					 if(CoreEngine.HasALLVars(ID,new VAR_RW<?>[] {ComponentColision.VAR_AABB,Entity.VAR_POSITION})) {
		                	//CoreEngine.RecieveData(ID,ComponentColision.VAR_AABB).setFlagState(false);
		                	
		                    Vector2f position=CoreEngine.RecieveData(ID,Entity.VAR_POSITION);
		    				CoreEngine.sendData(ID,ComponentColision.VAR_BEFORE_POSITION,position);
		    				
		                }
					
				}
			}
		}

	}




	


	
}
