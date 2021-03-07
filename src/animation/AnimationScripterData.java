package animation;

import java.util.UUID;

import org.joml.Vector2f;

public class AnimationScripterData {

	

	protected int frame;
	protected UUID entity;
	protected SpriteSheet sheet;
	protected Vector2f position=null;
	protected AnimationScripterData(UUID entity,SpriteSheet sheet,int frame) {
		this.frame = frame;
		this.entity = entity;
		this.sheet = sheet;
	}
	
	protected AnimationScripterData(UUID entity,SpriteSheet sheet,int frame,Vector2f position) {
		this.frame = frame;
		this.entity = entity;
		this.sheet = sheet;
		this.position = position;
	}
	public int getFrame() {
		return frame;
	}
	public UUID getEntity() {
		return entity;
	}
	public SpriteSheet getSheet() {
		return sheet;
	}
	public Vector2f getPosition() {
		return position;
	}

	
	
	
}
