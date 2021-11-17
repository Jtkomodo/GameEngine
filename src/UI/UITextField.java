package UI;

import javax.swing.Renderer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import core.Constants;
import core.CoreEngine;
import core.Game;
import input.CharCallback;
import input.InputPoller;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import textrendering.TextBuilder;;


public class UITextField extends UIElement {

	
	
	
	
	private String string;
	private Model m;
	private float sizeOfStirng;
	private TextBuilder text=new TextBuilder("aakar",512);
	private boolean takingInpput=true;
	private float textOffset=20;
	private float offset=0;
	private int index=0; 
	
	
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
		
			m=new Model(Vert,uvBg);
		
		    offset=this.text.getClosestCurosorOffset(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),0-this.getWidth(),this.sizeOfStirng);
		
	}
	protected void setPositonInBox(Vector2f position) {
		position.add(this.getWidth(),0,this.position_in_box);
		
	}
	

	
	@Override
	public void leftButtonJustPressed(Vector2f cursorPosition) {
	    
		   if(this.string!="") {
           offset=this.text.getClosestCurosorOffset(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),cursorPosition.x-this.getWidth(),this.sizeOfStirng);
           index=this.text.getClosestCursorIndex(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),cursorPosition.x-this.getWidth(),this.sizeOfStirng);
	       }
	}

	@Override
	public void lefttButtonJustRealesed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void LeftButtonHeld(Vector2f cursorPosition) {
		  if(this.string!="") {
		  offset=this.text.getClosestCurosorOffset(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),cursorPosition.x-this.getWidth(),this.sizeOfStirng);
		  index=this.text.getClosestCursorIndex(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),cursorPosition.x-this.getWidth(),this.sizeOfStirng);
		  }
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
		
		
		
		
		if(this.takingInpput) {
	       CharCallback.takeInput=true;
	       String string=CharCallback.string;
	       if(this.string!="") {
	       
	       String s1=this.string.substring(0,index);
	       String s2=this.string.substring(index);
	     //  CoreEngine.DebugPrint(s2);
	       this.string=s1+string+s2;
	       
	       this.offset=this.text.getCursorOffsetFromIndex(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),this.index+string.length(), sizeOfStirng);
	       this.index=this.index+string.length();
	       
	       }else {
	        this.string=string;
	       }
	       
	       
	       CharCallback.clearString();
	      
	    }else {
	    	CharCallback.clearString();
	    	CharCallback.takeInput=false;
	    }
		
		
			
		if(this.takingInpput && InputPoller.JustPushed(GLFW.GLFW_KEY_BACKSPACE)) {
		     if(this.index>0) {
		    	 String s1=this.string.substring(0,index-1);
			     String s2=this.string.substring(index);
			     this.string=s1+s2;
			     this.index--;
		     }
			
		}
		
		
		
		text.setString(this.string);
		float textOffset=this.textOffset;
		String string=this.string;
		
	   
		
	
		
		Vector2f position=new Vector2f();
		
		this.position_in_box.add(box_Position,position);
		text.setString(string);
	    this.collision_box.debugDraw(position);
		
    if(this.string.length()!=0) {
	    
  	  
    	float length=text.getStringLength()*this.sizeOfStirng;
    	float boxBoundery=this.getWidth();
    	float stringBoundery=(textOffset*this.sizeOfStirng)-(this.getWidth())+length;
    	if(stringBoundery>boxBoundery) {
    		float newOffset=textOffset-((stringBoundery-boxBoundery)/this.sizeOfStirng);
            
    		int amount=text.getAmountOfCharsOutsideMinBound(new Vector2f(position.x+(newOffset*this.sizeOfStirng)-this.getWidth(),position.y),(position.x+(textOffset*this.sizeOfStirng)-this.getWidth()),this.sizeOfStirng);
    		 //textOffset=newOffset;
    		text.setString(string.substring(amount));
    		CoreEngine.DebugPrint("amount="+amount);
    	}
    	
    	//MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(position.x+boxBoundery,position.y,1000),0,new Vector2f(1,text.getStringHieght()/2),Game.DEFAULT_TEXTURE,Constants.BLUE));
    	
    	
    	//MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f((position.x+stringBoundery),position.y,1000),0,new Vector2f(1,text.getStringHieght()/2),Game.DEFAULT_TEXTURE,Constants.RED));
    	
    	
    }
		
		
		   
	   
	
		
		
	     if(this.takingInpput) {
	    		
			MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(position.x+offset,position.y,1000),0,new Vector2f(1,text.getStringHieght()/2),Game.DEFAULT_TEXTURE,Constants.RED));
			
		}
		
	    text.UIdrawString((position.x+(textOffset*this.sizeOfStirng))-this.getWidth(),position.y,this.sizeOfStirng,Constants.BLACK);
	    
	    
	
		
		
	}

}
