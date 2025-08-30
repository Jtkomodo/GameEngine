package main.java.UI;

import org.joml.Vector2f;

import main.java.core.Entity;
import main.java.core.*;

public class UIEntity extends Entity{

	
	
	
	
	/**
	 * 
	 * @param position 
	 * this is the position of the top left of the entity
	 * @param width
	 * this is the width of the box 
	 * @param height
	 * this it the height of the box
	 * @param components
	 * these are the components that drive the behavior and look
	 */
	public UIEntity(Vector2f position,float width,float height) {
		
		this.setVar(Entity.VAR_POSITION,position);
		
	
		
	}
	
	public void disable() {
		super.DISABLE();
		
		
		
	}
	public void enable() {
		super.ENABLE();
	}
	
	
	public void addComponents(UIComponent[] components) {
		super.addComponents(components);
		
	}
	
	
	
	
}
