package core;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector3f;

import events.Flag;
import physics.AABB;
import physics.PhysicsEngine;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.RenderingEngine;

public class ComponentColision extends EntityComponent {

	public static final UUID ID=UUID.randomUUID();
	private float width,height,r;
    private Model m;
    private AABB aabb;
	public boolean debug=false;
	
	public ComponentColision(AABB aabb) {
		this.width=aabb.getwidth();
		this.height=aabb.getHeight();
		this.r=aabb.getResistance();
		float widthR=width;
		float heightR=height;
		float[] Vert= {
				 -widthR,+heightR,
					widthR,heightR,
					widthR,-heightR,
					-widthR,-heightR
				 };
			float[] uvBg={
					0,0,
					1,0,
					1,1,
					0,1
					
					};
			m=new Model(Vert,uvBg);
			this.aabb=aabb;
		
	}
	
	
	
	
	public ComponentColision(float width,float height,float r) {
		this.width=width;
		this.height=height;
		this.r=r;
		float widthR=width;
		float heightR=height;
		float[] Vert= {
				 -widthR,+heightR,
					widthR,heightR,
					widthR,-heightR,
					-widthR,-heightR
				 };
			float[] uvBg={
					0,0,
					1,0,
					1,1,
					0,1
					
					};
			m=new Model(Vert,uvBg);
			this.aabb=new AABB( width, height, r);
	}
	

	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		this.aabb.TIE_ENTITY(entity.ID);
		
		this.currentEntity.TakeInData(Entity.VAR_AABB,new PASSABLE_AABB(this.aabb));
		
		PhysicsEngine.addEntity(entity);
		RenderingEngine.addEntity(currentEntity);
		
	}
	
	

	@Override
	protected void GAMELOOP_TICK() {
		if(this.currentEntity.hasAllVars(new String[] {Entity.VAR_POSITION.getName(),Entity.VAR_VELOCITY.getName()})) {
		this.currentEntity.TakeInData(Entity.VAR_POSITION,new PASSABLE_VEC2F(this.currentEntity.getData(Entity.VAR_POSITION).getValue().add(this.currentEntity.getData(Entity.VAR_VELOCITY).getValue(),new Vector2f())));
		}
		}

	@Override
	protected void RENDER_TICK() {
	   if(this.currentEntity.DEBUG && CoreEngine.Debugdraw) {
		   
		  MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(this.currentEntity.getData(Entity.VAR_POSITION).getValue(),100),0, 1,Game.DEFAULT_TEXTURE,Constants.COL_COLOR_BLUE));
		  //MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(this.currentEntity.getData(Entity.VAR_BEFORE_POSITION).getValue(),100),0, 1,Game.DEFAULT_TEXTURE,Constants.COL_COLOR_RED));
	   }

	}

	@Override
	protected boolean DISABLE() {
		this.currentEntity.removeVAR(Entity.VAR_AABB);
	    PhysicsEngine.removeEntity(currentEntity);
		return false;
	}




	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}




	
	
	
	

}
