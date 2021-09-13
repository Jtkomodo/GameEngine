package core;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import animation.SpriteSheet;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import rendering.RenderingEngine;
import rendering.Texture;

public class ComponentRenderModel extends EntityComponent {


	public static final UUID ID=UUID.randomUUID();


	protected Model model;
	protected Texture texture;
	

	public ComponentRenderModel(Model model,Texture texture) {
		this.model=new Model(model.getVertices(),model.getUv_coords());
		this.texture=texture;
	}




	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		entity.setVar(Entity.VAR_TEXTURE,this.texture);
		entity.INITVar(Entity.VAR_POSITION,new Vector2f());
		entity.INITVar(Entity.VAR_MIRROR,false);
		entity.INITVar(Entity.VAR_COLOR,Constants.DEFAULT_COLOR);
		entity.setVar(Entity.VAR_MODEL,this.model);
		RenderingEngine.addEntity(entity);

	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		
         
		
		if(this.currentEntity.hasAllVars(new VAR<?>[] {Entity.VAR_COLOR,Entity.VAR_POSITION,Entity.VAR_MIRROR,Entity.VAR_TEXTURE})) {
			Vector4f color=this.currentEntity.getVar(Entity.VAR_COLOR);
			Vector2f position=this.currentEntity.getVar(Entity.VAR_POSITION);
			boolean mirror=this.currentEntity.getVar(Entity.VAR_MIRROR);
			Texture  texture=this.currentEntity.getVar(Entity.VAR_TEXTURE);
			
			MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(position,10),0,1,texture,color,mirror));
		}
	}

	@Override
	protected boolean DISABLE() {
		RenderingEngine.removeEntity(this.currentEntity);
		currentEntity.removeVAR(Entity.VAR_MODEL);
		return true;
	}






	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	

	

}
