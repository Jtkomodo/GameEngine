package main.groovy.testScripts;

import java.util.Random;
import java.util.UUID;

import org.joml.Vector2f;

import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.Script;
import main.java.core.Timer;
import main.java.physics.AABB
import main.java.physics.ComponentColision;

public class Enemy extends Script {

	public static final UUID ID=UUID.randomUUID();
	private static final int idle=0,chase=1;
	private boolean timeToSwitch=true;
	private float speed=0.5f;
	private Entity e,player;
	private Random r=new Random();
	private int direction=1;
	private int state=idle;
	
	public Enemy(Entity player) {
		this.player=player;
	}
	
	
	
	@Override
	public void Start(Entity entity) {
	    this.e=entity;
		//AABB a=e.getVar(ComponentColision.VAR_AABB);
		
	}


	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS() {
		switch(state) {
			case idle:
			idle();
			break;
			case chase:
			ChasePlayer();
			break
			default:
			idle();
			break;
			
		}
		
		
		
	}
	private void idle() {
		if(this.player.hasVAR(Entity.VAR_POSITION) && this.e.hasVAR(Entity.VAR_POSITION) && this.e.hasVAR(ComponentColision.VAR_AABB)) {
			Vector2f target=player.getVar(Entity.VAR_POSITION);
			Vector2f pos=e.getVar(Entity.VAR_POSITION);
			AABB a=e.getVar(ComponentColision.VAR_AABB);
			Vector2f Direction=new Vector2f();
			target.sub(pos,Direction);
			float distance=Direction.length();
			if(distance <= 500) {
				state=chase;
			}
			
		}
		
	}
	private void ChasePlayer() {
		
		  if(this.player.hasVAR(Entity.VAR_POSITION) && this.e.hasVAR(Entity.VAR_POSITION) && this.e.hasVAR(ComponentColision.VAR_AABB)) {
	        	Vector2f target=player.getVar(Entity.VAR_POSITION);
	        	Vector2f pos=e.getVar(Entity.VAR_POSITION);
	        	AABB a=e.getVar(ComponentColision.VAR_AABB);
	        	Vector2f Direction=new Vector2f();
	        	Vector2f velocity=new Vector2f();
	        	target.sub(pos,Direction);
				if(Direction.length() >= 300) {
					state=idle;
				}
	        	Direction.normalize();
	        	float speed=this.speed;
	        	Direction.mul(speed,velocity);
				

				a.DEBUG_SHOW_DATA();
	        	
	        	this.e.setVar(Entity.VAR_VELOCITY,velocity);
				
	        	
	        }
		
	}
	
	
	
	
	
	
	
	@Override
	public boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
	@Override
	public UUID getSCRIPTID() {
		return ID;
	}

}

new Enemy(player);
