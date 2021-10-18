package UI;

import javax.swing.Renderer;

import org.joml.Vector2f;
import org.joml.Vector3f;

import core.Constants;
import core.CoreEngine;
import core.Game;
import input.CharCallback;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import textrendering.TextBuilder;;


public class UITextField extends UIElement {

	
	
	
	
	private String string;
	private Model m;
	private float sizeOfStirng;
	private TextBuilder text=new TextBuilder("aakar",512);
	private Vector2f cursorOffset=new Vector2f();
	private boolean takingInpput=true;
	
	
	public UITextField(String string,float width,float sizeOfString) {
		super(width,sizeOfString);
		text.setString(string);
		this.setHeight(sizeOfString*text.getStringHieght());
		this.string=string;
		this.sizeOfStirng=sizeOfString;
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
		
			
		
	}
	protected void setPositonInBox(Vector2f position) {
		position.add(this.getWidth(),0,this.position_in_box);
		
	}
	
	private void setString(String string) {
		
	}
	
	
	@Override
	public void leftButtonJustPressed(Vector2f cursorPosition) {
	       
          
             this.cursorOffset=cursorPosition;
	}

	@Override
	public void lefttButtonJustRealesed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void LeftButtonHeld(Vector2f cursorPosition) {
		    this.cursorOffset=cursorPosition;

	}

	@Override
	public void rightButtonJustPressed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightButtonJustRealesed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightButtonHeld(Vector2f cursorPosition) {
		  
      

	}

	@Override
	protected void renderUpdate(Vector2f box_Position) {
	
		
		Vector2f position=new Vector2f();
		
		this.position_in_box.add(box_Position,position);
		text.setString(string);
	    this.collision_box.debugDraw(position);
		
		float length=text.getStringLength()*this.sizeOfStirng;
		
		
		
		Vector2f offsetInBox=new Vector2f();
	//	position.add(100,this.cursorOffset.y,offsetInBox);
	    this.cursorOffset.add(position,offsetInBox);
	
		
	  
		
		CoreEngine.DebugPrint("V="+this.cursorOffset);
		
		
        if(this.takingInpput && this.cursorOffset!=null) {
			
			MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(offsetInBox.x-this.getWidth(),position.y,1000),0,new Vector2f(1,text.getStringHieght()/2),Game.DEFAULT_TEXTURE,Constants.RED));
			
		}
		
	    text.UIdrawString(position.x-this.getWidth()+10,position.y,this.sizeOfStirng,Constants.BLACK);
	    
	    
	
	
		
		
	}

}
