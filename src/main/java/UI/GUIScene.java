package main.java.UI;

import java.util.Iterator;
import java.util.LinkedList;

import org.joml.Vector2f;

import main.java.core.CoreEngine;

/*
purpose: this what holds all the UI enities that are on the screen
what is in the buffer directly controls what the user will be able to see and interact with


*/
public class GUIScene {


	
	LinkedList<UIEntity> BuffferOfCurrentEntiies= new LinkedList<UIEntity>();
	
	
	private String name;
	
	
	public GUIScene(String name) {
		this.name=name;
		
	}
	
	protected void disableScene() {
		Iterator<UIEntity> I=this.BuffferOfCurrentEntiies.iterator();
		while(I.hasNext()) {
			UIEntity e=I.next();
			e.disable();
		}
	}
	protected void enableScene() {
		Iterator<UIEntity> I=this.BuffferOfCurrentEntiies.iterator();
		while(I.hasNext()) {
			UIEntity e=I.next();
			e.enable();
		}
	}
    
	public void addEntity(UIEntity e){
		if(!this.BuffferOfCurrentEntiies.contains(e)) {
			this.BuffferOfCurrentEntiies.add(e);
			CoreEngine.AddEntity(e);
			e.enable();
			CoreEngine.DebugPrint("UISchene:"+name+"added entity"+e);
		}
	}
	
	public void removeEntity(UIEntity e){
		this.BuffferOfCurrentEntiies.remove(e);
		CoreEngine.removeEntity(e);
		CoreEngine.DebugPrint("UISchene:"+name+"remove entity"+e);
		
	}
	
	
	

}
