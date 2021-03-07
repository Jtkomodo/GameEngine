package animation;

import org.joml.Vector2f;

public class InterpolationData {

	
	public Vector2f BeginPosition;
	public Vector2f EndPosition;
	public float time;
	
	public InterpolationData(Vector2f beginPosition, Vector2f endPosition,float time) {
		BeginPosition = beginPosition;
		EndPosition = endPosition;
		this.time=time;
	}
	
	
	
	
}
