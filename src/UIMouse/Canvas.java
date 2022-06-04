package UIMouse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.joml.Vector2f;

public class Canvas {
	
	
	protected Vector2f position;
	protected float width,height;
	private HashMap<Vector2f,GUIElement> elements=new HashMap<Vector2f,GUIElement>();
	
	public Canvas(Vector2f position,float width,float height) {
		this.position=position;
		this.width=width;
		this.height=height;
	}
	public void Render() {
	     Iterator<Entry<Vector2f,GUIElement>> I=elements.entrySet().iterator();
	     while(I.hasNext()) {
	    	 Entry<Vector2f,GUIElement> e=I.next();
	    	 Vector2f offset=e.getKey();
	    	 GUIElement element=e.getValue();
	    	 Vector2f elementPosition=new Vector2f();
	    	 this.position.add(offset,elementPosition);
	    	 element.Render(elementPosition);
	     }
	     
		
	}
	public void CollisiionUpdate() {
	     Iterator<Entry<Vector2f,GUIElement>> I=elements.entrySet().iterator();
	     while(I.hasNext()) {
	    	 Entry<Vector2f,GUIElement> e=I.next();
	    	 Vector2f offset=e.getKey();
	    	 GUIElement element=e.getValue();
	    	 Vector2f elementPosition=new Vector2f();
	    	 this.position.add(offset,elementPosition);
	    	 element.CollsitonUpdate(elementPosition);
	     }
	     
		
	}
	
	public void addElement(Vector2f ElementOffset,GUIElement e) {
		this.elements.put(ElementOffset, e);
	}
	
	

	public void setPosition(Vector2f position) {
		this.position=position;
	}
	
	
	public void setWidth(float width) {
		this.width=width;
	}
	
	public void setHeight(float height) {
		this.height=height;
	}
	
}
