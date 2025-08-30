package main.java.UI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import main.java.core.CoreEngine;
import main.java.physics.AABB;
import main.java.rendering.RenderEntity;

public class UIEngine {
	
	
	


	//private static LinkedList<AABB> listOfUITriggers=new LinkedList<AABB>();
	//private static HashMap<AABB,Boolean> stateOfMouseColisions=new HashMap<AABB,Boolean>();
	
	

	
	public static void update() {
		checkMouse();
		checkKeys();
	}

	
	
	private static void checkKeys() {
		
	}

	private static void checkMouse() {
	
	}
	
	
	public static void disableScene(GUIScene scene) {
		scene.disableScene();
	}
	public static void enableScene(GUIScene scene) {
		scene.enableScene();
	}
	
//	public static void addTrigger(AABB trigger) {
//		if(!listOfUITriggers.contains(trigger)) {
//			listOfUITriggers.add(trigger);
//			stateOfMouseColisions.put(trigger, false);
//		}
//	}
//	public static void removeTrigger(AABB trigger) {
//		listOfUITriggers.remove(trigger);
//		stateOfMouseColisions.remove(trigger);
//	}

}
