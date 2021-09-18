package physics;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector3f;

import core.Constants;
import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.Game;
import core.PASSABLE_AABB;
import core.PASSABLE_VEC2F;
import core.VAR_R;
import core.VAR_RW;
import events.Flag;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.RenderingEngine;

public class ComponentColision extends EntityComponent {

	protected final static VAR_RW<PASSABLE_VEC2F> VAR_BEFORE_POSITION=createNewVAR("BEFORE_POSITION",PASSABLE_VEC2F.getHandle());	
	protected final static VAR_RW<PASSABLE_AABB> VAR_AABB=createNewVAR("AABB",PASSABLE_AABB.getHandle());
	private static final VAR_RW<PASSABLE_VEC2F> var_nextPosition=createNewVAR("NEXT_POSITION",PASSABLE_VEC2F.getHandle());
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
		
		this.currentEntity.setVar(VAR_AABB,this.aabb);
		this.currentEntity.INITVar(Entity.VAR_POSITION,new Vector2f());
		this.currentEntity.INITVar(VAR_BEFORE_POSITION,new Vector2f());
		this.currentEntity.INITVar(Entity.VAR_VELOCITY,new Vector2f());
		
		PhysicsEngine.addEntity(entity);
		RenderingEngine.addEntity(currentEntity);
		
	}
	
	

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
		if(this.currentEntity.hasAllVars(new VAR_RW<?>[]{Entity.VAR_POSITION,Entity.VAR_VELOCITY})) {
			
		  this.currentEntity.setVar(var_nextPosition,this.currentEntity.getVar(Entity.VAR_POSITION).add(this.currentEntity.getVar(Entity.VAR_VELOCITY).mul((float) (100*CoreEngine.deltaT)),new Vector2f()));
		}
		}

	@Override
	protected void RENDER_TICK() {
	   if(this.currentEntity.DEBUG && CoreEngine.Debugdraw) {
			if(this.currentEntity.hasAllVars(new VAR_RW<?>[]{Entity.VAR_POSITION,Entity.VAR_VELOCITY})) {
				
		        MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(this.currentEntity.getVar(Entity.VAR_POSITION),100),0, 1,Game.DEFAULT_TEXTURE,Constants.COL_COLOR_BLUE));
			}
			}

	}

	@Override
	protected boolean DISABLE() {
		this.currentEntity.removeVAR(VAR_AABB);
	    PhysicsEngine.removeEntity(currentEntity);
		return false;
	}

   


	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}


   public static VAR_R<PASSABLE_AABB> READ_VAR_AABB(){
	   return createNewVAR_R(VAR_AABB);
   }
   protected static VAR_R<PASSABLE_VEC2F> READ_VAR_NEXT_POS(){
	   return createNewVAR_R(var_nextPosition);
   }
	
	
	
	

}
