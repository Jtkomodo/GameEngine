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
	private final static VAR_RW<PASSABLE_MODEL> VAR_MODEL=VAR_RW.makeNewVar("MODEL",PASSABLE_MODEL.getHandle());
	public final static VAR_RW<PASSABLE_TEXTURE> VAR_TEXTURE=VAR_RW.makeNewVar("TEXTURE",PASSABLE_TEXTURE.getHandle());
	public final static VAR_RW<PASSABLE_INT> VAR_LAYER=VAR_RW.makeNewVar("TEXTURE",PASSABLE_INT.getHandle());


	protected Model model;
	protected Texture texture;
	

	public ComponentRenderModel(Model model,Texture texture) {
		this.model=new Model(model.getVertices(),model.getUv_coords());
		this.texture=texture;
	}




	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		entity.setVar(VAR_TEXTURE,this.texture);
		entity.INITVar(Entity.VAR_POSITION,new Vector2f());
		entity.INITVar(Entity.VAR_MIRROR,false);
		entity.INITVar(Entity.VAR_COLOR,Constants.DEFAULT_COLOR);
		entity.INITVar(VAR_LAYER,10);
		entity.setVar(VAR_MODEL,this.model);
		RenderingEngine.addEntity(entity);

	}

	

	@Override
	protected void RENDER_TICK() {
		
         
		
		if(this.currentEntity.hasAllVars(new VAR_RW<?>[] {Entity.VAR_COLOR,Entity.VAR_POSITION,Entity.VAR_MIRROR,VAR_TEXTURE,VAR_LAYER})) {
			Vector4f color=this.currentEntity.getVar(Entity.VAR_COLOR);
			Vector2f position=this.currentEntity.getVar(Entity.VAR_POSITION);
			boolean mirror=this.currentEntity.getVar(Entity.VAR_MIRROR);
			Texture  texture=this.currentEntity.getVar(VAR_TEXTURE);
			
			MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(position,this.currentEntity.getVar(VAR_LAYER)),0,1,texture,color,mirror));
		}
	}

	@Override
	protected boolean DISABLE() {
		RenderingEngine.removeEntity(this.currentEntity);
		return true;
	}






	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public static  VAR_R<PASSABLE_MODEL> READ_VAR_MODEL() {
		return   createNewVAR_R(VAR_MODEL);
	}

	

}
