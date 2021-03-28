package core;

import org.joml.Vector3f;

import animation.SpriteSheet;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;

public class ComponentRenderModel extends EntityComponent {

	
	
	public final static String VAR_MODEL="MODEL";
	public final static String VAR_MODEL_UPDATED="MODEL_UPDATED";
	protected Model model;
	
	
	
	public ComponentRenderModel(Model model) {
		this.model=new Model(model.getVertices(),model.getUv_coords());
	
	}

	
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
	     this.currentEntity=entity;
	     entity.Entity_Data.put(VAR_MODEL,this.model);     
	
	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(this.currentEntity.position,10),0,1,this.currentEntity.texture));

	}

	@Override
	protected boolean DISABLE() {
	   
		return true;
	}

	@Override
	public COMPONENT_TYPE getID() {
		return COMPONENT_TYPE.RENDER;
	}

}
