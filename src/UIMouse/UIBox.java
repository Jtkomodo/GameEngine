package UIMouse;

import java.util.LinkedList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import core.Constants;
import core.Game;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.Render;
import rendering.RenderEntity;

public class UIBox {

	
	
	private LinkedList<UIElement> allElements=new LinkedList<UIElement>();
	private LinkedList<UIElement> ShownElements=new LinkedList<UIElement>();
	
	
	
	private float width,height;
	private UIElement currentElement;
	protected boolean stopsClockWhenShown;
	private Vector2f next_element_position=new Vector2f();
	private Vector2f position,padding;
	private Model m;
	
	
	
	
	
	public UIBox (Vector2f position,float width,float height,Vector2f padding,boolean stopsClockWhenShown) {
		this.stopsClockWhenShown=stopsClockWhenShown;
		this.position=position;
		this.padding=padding;
		this.width=width;
		this.height=height;
	    this.position.add(padding,this.next_element_position);
		float[] Vert= {
				 -0.5f,+0.5f,
					0.5f,0.5f,
					0.5f,-0.5f,
					-0.5f,-0.5f
				 };
			float[] uvBg={
					0,0,
					1,0,
					1,1,
					0,1
					
					};
			int[] ind= {
					0,1,2,
					2,3,0	
						
				};
			m=new Model(Vert,uvBg);
			this.position.sub(width/2, -height/2,this.next_element_position);
			this.next_element_position.sub(-padding.x,padding.y);
		
		
	}
	protected void updateFlags(Vector2f Cursor_position) {
		  Vector2f camPosition= Render.cam.getPosition();
	         Vector2f newPosition= new Vector2f(); 
	        		 
	        position.sub(camPosition,newPosition);
		for(int i=0;i<this.ShownElements.size();i++) {
			
			this.ShownElements.get(i).checkIfInBounds(Cursor_position,newPosition);
		}
		
		
	}
	
	
	protected void Render_update() {
		
	
        Vector2f camPosition= Render.cam.getPosition();
         Vector2f newPosition= new Vector2f(); 
        		 
        position.sub(camPosition,newPosition);
        
		RenderEntity e=new RenderEntity(m,new Vector3f(newPosition,199),0, new Vector2f(width,height),Game.DEFAULT_TEXTURE,Constants.BLACK);
		RenderEntity e2=new RenderEntity(m,new Vector3f(this.next_element_position.sub(camPosition,new Vector2f()),200),0,5,Game.DEFAULT_TEXTURE,Constants.BLUE);
		e.setUIPojeection(true);
		e2.setUIPojeection(true);
	    MainRenderHandler.addEntity(e);
		MainRenderHandler.addEntity(e2);
		for(int i=0;i<this.ShownElements.size();i++) {
			this.ShownElements.get(i).renderUpdate(newPosition);
			
		}
		
	}
	
	
	public void addElement(UIElement element) {
	      Vector2f nextPosition=new Vector2f();
	      
	      this.next_element_position.sub(0,element.getHeight()*2+padding.y,nextPosition);
	  
	      this.allElements.add(element);
		  if(nextPosition.y<width) {
			  
			  Vector2f elementPosition=new Vector2f();
			  this.next_element_position.sub(0,element.getHeight(),elementPosition);
			 element.setPositonInBox(elementPosition);
			 this.ShownElements.add(element);
			 this.next_element_position=nextPosition;
		  }
	}
	
	
	
	
	
    
	
	
	
	
	
}
