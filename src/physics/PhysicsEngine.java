package physics;

import java.util.LinkedList;
import java.util.UUID;

import org.joml.Vector2f;

import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.PASSABLE_VEC2F;

public class PhysicsEngine {

	public static LinkedList<Collision> collisionsColided=new LinkedList<Collision>();
	public static LinkedList<UUID> entities=new LinkedList<UUID>();
	
	
	
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
		    	if(A.hasAllVars(new String[] {Entity.VAR_AABB.name,Entity.VAR_POSITION.name,Entity.VAR_VELOCITY.name})) {
		    	AABB a=A.getData(Entity.VAR_AABB);
		    	AABB b=B.getData(Entity.VAR_AABB);
		    	
		    	
		    	
		    	
		    	
		    	Vector2f velocity=A.getData(Entity.VAR_VELOCITY).value;
		    	Vector2f position=A.getData(Entity.VAR_POSITION).value;
		    	
		    	
		    	Vector2f direction=new Vector2f();
		 
		    	
		    
		    	if(velocity.length()!=0) {
		    		velocity.normalize(direction);
		    	}else {
		    		direction=new Vector2f();
		    	}
		    	
		    	
		    	Vector2f newMovement=a.findVector(position,velocity,direction, b);
		         if(newMovement!=null) {
		        	 A.TakeInData(Entity.VAR_POSITION,new PASSABLE_VEC2F(newMovement));
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
					Entity e_1=CoreEngine.getEntity(ID_1);
					UUID ID_2=entities.get(i_2);
					Entity e_2=CoreEngine.getEntity(ID_2);


					if(e_1!=null && e_2!=null) {
						if(e_1.hasVAR(Entity.VAR_AABB) && e_2.hasVAR(Entity.VAR_AABB)) {
							//if there is a collision then add that collision to the list
							if(e_1.getData(Entity.VAR_AABB).vsAABB(e_2.getData(Entity.VAR_AABB))) {
								collision=true;
								Collision col=new Collision(e_1.getData(Entity.VAR_AABB),e_2.getData(Entity.VAR_AABB));
								if(!collisionsColided.contains(col)) {
									collisionsColided.add(col);
								}

							}

						}
					}
				}
			}
			//if there was no collision at all with that box then set the before collision position to the position now
			if(!collision) {
				UUID ID=entities.get(i_1);
				Entity e=CoreEngine.getEntity(ID);

				if(e!=null) {
					PASSABLE_VEC2F position=e.getData(Entity.VAR_POSITION);
					e.TakeInData(Entity.VAR_BEFORE_POSITION,position);
				}
			}
		}
	}


	public static void updateSim() {
	        for(int i=0;i<entities.size();i++) {
	        	UUID ID=entities.get(i);
	        	Entity e=CoreEngine.getEntity(ID);
	        	if(e!=null) {
	        		e.GAMELOOP_TICK();
	        		
	        	}
	        }
		
	}




	


	
}
