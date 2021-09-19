package TestScprits;

import java.util.Random;
import java.util.UUID;

import org.joml.Vector2f;

import core.Entity;
import core.Timer;
import scripting.Script;

public class EnemyScript extends Script {

	public static final UUID ID=UUID.randomUUID();
	private int state;
	private Timer time=new Timer(2);
	private boolean timeToSwitch=true;
	private float speed=0.5f;
	private Entity e,player;
	private Random r=new Random();
	private int direction=1;
	
	public EnemyScript(Entity player) {
		this.player=player;
	}
	
	
	
	@Override
	public void Start(Entity entity) {
	    this.e=entity;
	}


	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS() {


		
			
			ChasePlayer();

		  
		
		
		
	}
	private void ChasePlayer() {
		  if(this.player.hasVAR(Entity.VAR_POSITION) && this.e.hasVAR(Entity.VAR_POSITION)) {
	        	Vector2f target=player.getVar(Entity.VAR_POSITION);
	        	Vector2f pos=e.getVar(Entity.VAR_POSITION);
	        	
	        	Vector2f Direction=new Vector2f();
	        	Vector2f velocity=new Vector2f();
	        	target.sub(pos,Direction);
	        	Direction.normalize();
	        	
	        	Direction.mul(speed,velocity);
	        	
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
