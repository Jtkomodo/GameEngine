package UIMouse;

import java.util.LinkedList;

import org.joml.Vector2f;

import rendering.Model;

public abstract class UIFormat {
	/**
	 * this is all the slots whether visible or not
	 */
	protected LinkedList<Vector2f> slots=new LinkedList<Vector2f>();
	/**
	 * this is the slots that will actually be seen right now so if you have a list 
	 * that scrolls for example this will only contain the slots you can see
	 */
	protected LinkedList<Vector2f> ShownSlots=new LinkedList<Vector2f>();
	protected Vector2f padding;
	protected float width,height;
	protected int nextSlotIndex=0;
	protected int amountOfElments;
	
	
	protected UIFormat(float width,float height,Vector2f padding) {
		this.padding=padding;
		this.width=width;
		this.height=height;
	
	
	}
	protected abstract void addElement();
	protected abstract void removeElement();
	
	

}
