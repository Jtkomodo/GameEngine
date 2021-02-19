package animation;

import java.util.LinkedList;

public class AnimationEngine {

	
	private static LinkedList<AnimationData> AnimationStack=new LinkedList<AnimationData>();
	
	
	public static void update() {
		
		
		
	}
	
	public static void takeInAnimationData(AnimationData data) {
		AnimationStack.add(data);
	}
	
	
}
