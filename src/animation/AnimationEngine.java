package animation;

import java.util.LinkedList;
import java.util.UUID;

public class AnimationEngine {

	
	private static LinkedList<AnimationData> AnimationStack=new LinkedList<AnimationData>();
	
	
	public static void update() {
		
		while(!AnimationStack.isEmpty()) {
			AnimationData data=AnimationStack.pop();
			UUID ID=data.entityID;
			
		}
		
	}
	
	public static void takeInAnimationData(AnimationData data) {
		AnimationStack.add(data);
	}
	
	
}
