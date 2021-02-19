package animation;

import java.util.UUID;

import rendering.Texture;

public class AnimationData {

	
	public UUID entityID;
	public float u,v;
	public Texture texture;
	public AnimationData(UUID entityID,Texture texture,float u,float v) {
		this.entityID = entityID;
		this.u = u;
		this.v = v;
		this.texture = texture;
	}
	
	
}
