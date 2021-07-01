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
		entity.TakeInData(Entity.VAR_TEXTURE,new PASSABLE_TEXTURE(this.texture));
		entity.INITData(Entity.VAR_POSITION,new PASSABLE_VEC2F(new Vector2f()));
		entity.INITData(Entity.VAR_MIRROR,new PASSABLE_BOOL(false));
		entity.INITData(Entity.VAR_COLOR,new PASSABLE_VEC4F(Constants.DEFAULT_COLOR));
		entity.TakeInData(Entity.VAR_MODEL,new PASSABLE_MODEL(this.model));
		RenderingEngine.addEntity(entity);

	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		PASSABLE_VEC4F color=this.currentEntity.getData(Entity.VAR_COLOR);
		PASSABLE_VEC2F position=this.currentEntity.getData(Entity.VAR_POSITION);
		PASSABLE_BOOL mirror=this.currentEntity.getData(Entity.VAR_MIRROR);
		PASSABLE_TEXTURE texture=this.currentEntity.getData(Entity.VAR_TEXTURE);
		
         
		
		if(color!=null && texture!=null && position!=null && mirror!=null) {
			MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(position.getValue(),10),0,1,texture.getValue(),color.getValue(),mirror.getValue()));
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
