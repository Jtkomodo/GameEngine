package core;

import org.joml.Vector2f;
import org.joml.Vector3f;

import animation.SpriteSheet;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.Texture;

public class ComponentRenderModel extends EntityComponent {

	
	
	
    
	protected Model model;
	protected Texture texture;
	
	
	public ComponentRenderModel(Model model,Texture texture) {
		this.model=new Model(model.getVertices(),model.getUv_coords());
	    this.texture=texture;
	}

	
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
	     this.currentEntity=entity;
	     entity.TakeInData(Entity.VAR_TEXTURE,this.texture);
	     entity.INITData(Entity.VAR_POSITION,new PASSABLE_VEC2F(new Vector2f()));
	     entity.TakeInData(Entity.VAR_MODEL,this.model);     
	
	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		PASSABLE_VEC2F position=this.currentEntity.getData(Entity.VAR_POSITION);
		Texture texture=this.currentEntity.getData(Entity.VAR_TEXTURE);
		if(texture!=null && position!=null) {
		MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(position.value,10),0,1,texture));
		}
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
