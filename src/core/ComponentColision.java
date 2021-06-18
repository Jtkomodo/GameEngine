package core;

import org.joml.Vector2f;
import org.joml.Vector3f;

import events.Flag;
import physics.AABB;
import physics.PhysicsEngine;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;

public class ComponentColision extends EntityComponent {

	
	private float width,height,r;
    private Model m;
	public boolean debug=false;
	
	public ComponentColision(float width,float height,float r) {
		this.width=width;
		this.height=height;
		this.r=r;
	}
	

	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		
		this.currentEntity.TakeInData(Entity.VAR_AABB,new AABB(this.currentEntity.ID, width, height, r));
		PhysicsEngine.addEntity(entity);
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
			int[] ind= {
					0,1,2,
					2,3,0	
						
				};
			m=new Model(Vert,uvBg);
			
		
	}
	
	

	@Override
	protected void GAMELOOP_TICK() {
		if(this.currentEntity.hasAllVars(new String[] {Entity.VAR_POSITION.name,Entity.VAR_VELOCITY.name})) {
		this.currentEntity.TakeInData(Entity.VAR_POSITION,new PASSABLE_VEC2F(this.currentEntity.getData(Entity.VAR_POSITION).value.add(this.currentEntity.getData(Entity.VAR_VELOCITY).value,new Vector2f())));
		}
		}

	@Override
	protected void RENDER_TICK() {
	   if(this.currentEntity.DEBUG && CoreEngine.Debugdraw) {
		   
		  MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(this.currentEntity.getData(Entity.VAR_POSITION).value,100),0, 1,Game.DEFAULT_TEXTURE,Constants.COL_COLOR_BLUE));
		  //MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(this.currentEntity.getData(Entity.VAR_BEFORE_POSITION).value,100),0, 1,Game.DEFAULT_TEXTURE,Constants.COL_COLOR_RED));
	   }

	}

	@Override
	protected boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public COMPONENT_TYPE getID() {
		
		return COMPONENT_TYPE.COLLISION;
		
	}

}
