package UIMouse;

import javax.swing.Renderer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;



import core.Constants;
import core.CoreEngine;
import core.Game;
import core.Timer;
import events.Events;
import input.CharCallback;
import input.InputPoller;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;
import textrendering.TextBuilder;
import events.Condition;
import events.EventAction;

import static events.Operation.*;


public class UITextField extends UIElement {	
	
	
	private String string;
	private Model m;
	private float sizeOfStirng;
	private TextBuilder text=new TextBuilder("aakar",512);
	private boolean takingInpput=true;
	private float textOffset=20;
	private int index=0; 
	private Timer BackSpaceTimer=new Timer();
//	private Vector2f CursorPosition=new Vector2f();
	private String shownString;
	//private int amountOfCharsOutSideBounds=0;
	private float newOffset=textOffset;
	private float IndexOffset=0;
	private int amountOfCharsoutsideMinBounds=0;
	private int amountOfCharsoutsideMaxBounds=0;
	
	
	
	
	public UITextField(String string,float width,float sizeOfString) {
		super(width,sizeOfString);
		text.setString(string);
		this.setHeight(sizeOfString*text.getStringHieght());
		this.string=string;
		this.shownString=string;
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
		
		    Events textChangedEvent=new Events(new Condition(InputPoller.charsChanged,EQUALS,true),()->TextChanged());//this has what is called lambda expression which just calls the function textChanged
		    Events BackSpaceChangedEvent=InputPoller.makeEventOnUIKeyUpdated(GLFW.GLFW_KEY_BACKSPACE,()->BackSpace());
		    Events RightKey=InputPoller.makeEventOnUIKeyUpdated(GLFW.GLFW_KEY_RIGHT,()->Right());
		    Events LeftKey=InputPoller.makeEventOnUIKeyUpdated(GLFW.GLFW_KEY_LEFT,()->Left());
		    Events outsideBoundsEvent=new Events(new Condition(InputPoller.UIkeyTriggered(GLFW.GLFW_MOUSE_BUTTON_1),AND,new Condition(this.OutsideBounds,EQUALS,true)),()->outSideBounds());
		    Events insideBoundsEvent=new Events(new Condition(InputPoller.UIkeyTriggered(GLFW.GLFW_MOUSE_BUTTON_1),AND,new Condition(this.OutsideBounds,EQUALS,false)),()->inSideBounds());
		    //Events leftkeyUpdated=new Events
		    
		    insideBoundsEvent.ActivateFlags();
		    outsideBoundsEvent.ActivateFlags();
		    RightKey.ActivateFlags();
		    LeftKey.ActivateFlags();
		    BackSpaceChangedEvent.ActivateFlags();
		    textChangedEvent.ActivateFlags();
		 
	}
	protected void inSideBounds() {
		if(InputPoller.JustPushed(GLFW.GLFW_MOUSE_BUTTON_1)) {
			this.takingInpput=true;
		}
	}
	
	protected void outSideBounds() {
		if(InputPoller.JustPushed(GLFW.GLFW_MOUSE_BUTTON_1)) {
			this.takingInpput=false;
		}
		
	}
	
	
	private void Left() {
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_LEFT)&& this.takingInpput) {
		 if(this.index>0) {
			 this.index--;
		 }
		}
	}

	private void  Right() {
		if(InputPoller.JustPushed(GLFW.GLFW_KEY_RIGHT) && this.takingInpput) {
		if(this.index<this.string.length()) {
			this.index++;
		}
		}
	}
   
	
	
	protected void setPositonInBox(Vector2f position) {
		position.add(this.getWidth(),0,this.position_in_box);
		
	}
	

	@Override
	public void leftButtonJustPressed(Vector2f mousePosition) {
	    
	       if(!string.equals("")) {
	    	this.text.setString(shownString);   
	        int index=this.text.getClosestCursorIndex(new Vector2f(((textOffset*this.sizeOfStirng))-this.getWidth(),0),(mousePosition.x)-this.getWidth(),this.sizeOfStirng);
	        //CoreEngine.DebugPrint("index="+index);
	        this.index=index+this.amountOfCharsoutsideMinBounds;
	        this.makeShownString();
	       }
	}

	@Override
	public void lefttButtonJustRealesed(Vector2f cursorPosition) {
	

	}

	@Override
	public void LeftButtonHeld(Vector2f mousePosition) {
		
	}

	@Override
	public void rightButtonJustPressed(Vector2f cursorPosition) {
	

	}

	@Override
	public void rightButtonJustRealesed(Vector2f cursorPosition) {
		
	}

	@Override
	public void rightButtonHeld(Vector2f cursorPosition) {
		  
      

	}
	
	
	
	
	
	
	
	private void BackSpace() {
		
		
		if(!this.string.equals("")) {
			String string=this.string;
		 if(this.takingInpput && InputPoller.JustPushed(GLFW.GLFW_KEY_BACKSPACE)) {
			CoreEngine.DebugPrint("just pushed");
			 if(this.index>0) {
		    	 String s1=string.substring(0,index-1);
			     String s2=string.substring(index);
			     string=s1+s2;
			     this.index--;
		     }
			this.BackSpaceTimer.setTimer(2); 
		}  
		

		 else if(this.takingInpput && InputPoller.Held(GLFW.GLFW_KEY_BACKSPACE)) {
			 if(this.index>=2) {
		    	 String s1=string.substring(0,index-2);
			     String s2=string.substring(index);
			     string=s1+s2;
			     this.index-=2;
		     }else if(this.index>0){
		    	
		    	 string=string.substring(index);
		    	 this.index=0;
		    	
		     }
			 this.BackSpaceTimer.resetTimer();
			 
		}
	 this.string=string;
	}
		
	}
	
	public void TextChanged() {
		if(index>string.length()) {
			index=string.length();
		}
	 	String charsGotten=InputPoller.string;
	 	//make the actual string from the keys just pressed
		if(!string.equals("")) {
           
			String s1=string.substring(0,index);
			String s2=string.substring(index);
			//  CoreEnginDebugPrint(s2);
			string=s1+charsGotten+s2;
			index=s1.length()+charsGotten.length();
		}else {
			string=charsGotten;
			index=charsGotten.length();
		}
		
	
	
	}

	
	private void makeShownString() {
		
		this.shownString=string;
		this.text.setString(string);
		
		 if(this.string.length()!=0) {
			    
			
		    	float length=text.getStringLength()*this.sizeOfStirng;//this is the size of the string
		    	float boxBoundery=this.getWidth();
		    	float stringStartPosition=(((textOffset*this.sizeOfStirng))-this.getWidth());
		    	float stringBoundery=stringStartPosition+length;//when added to the box position this is the end point of the whole string
		    
		    	
		    	if(stringBoundery>boxBoundery) {//if the string  goes past the boundery then we need to scroll the string based off of the index
		    		
//		    		
//		    		float newOffset=textOffset-((stringBoundery-boxBoundery)/this.sizeOfStirng);//this is the offset 
//		    		this.IndexOffset=this.text.getCursorOffsetFromIndex(new Vector2f((newOffset*this.sizeOfStirng)-this.getWidth(),0), this.index, this.sizeOfStirng);
//		    	
//		    		
//		    		this.amountOfCharsOutSideBounds=text.getAmountOfCharsOutsideMinBound(new Vector2f((newOffset*this.sizeOfStirng)-this.getWidth(),0),((textOffset*this.sizeOfStirng)-this.getWidth()),this.sizeOfStirng);
//		    	    this.newOffset=newOffset;
//		    	    
//		    	    this.shownString=string.substring(this.amountOfCharsOutSideBounds);
//		    	    
//		    		text.setString(shownString);
		    		this.IndexOffset=this.text.getCursorOffsetFromIndex(new Vector2f((textOffset*this.sizeOfStirng)-this.getWidth(),0), this.index, this.sizeOfStirng);
		    		float newBoundery=IndexOffset;
		    		
		    		if(newBoundery>boxBoundery) {
		    		float newOffset=this.textOffset-((newBoundery-boxBoundery)/this.sizeOfStirng);//this is the offset 
		    		
		    		this.amountOfCharsoutsideMinBounds=text.getAmountOfCharsOutsideMinBound(new Vector2f((newOffset*this.sizeOfStirng)-this.getWidth(),0),-this.getWidth(),this.sizeOfStirng);
		    		this.amountOfCharsoutsideMaxBounds=text.getAmountOfCharsOutsideMAxBound(new Vector2f((newOffset*this.sizeOfStirng)-this.getWidth(),0),((textOffset*this.sizeOfStirng)+this.getWidth()),this.sizeOfStirng);
                    this.newOffset=newOffset;
		    		this.shownString=string.substring(this.amountOfCharsoutsideMinBounds,(this.string.length()-(this.amountOfCharsoutsideMaxBounds)));  
		    		}else {
		    			this.newOffset=this.textOffset;
		    			this.amountOfCharsoutsideMaxBounds=text.getAmountOfCharsOutsideMAxBound(new Vector2f((newOffset*this.sizeOfStirng)-this.getWidth(),0),((textOffset*this.sizeOfStirng)+this.getWidth()),this.sizeOfStirng);
		    		    this.amountOfCharsoutsideMinBounds=0;
		    			this.shownString=string.substring(0,(this.string.length()-this.amountOfCharsoutsideMaxBounds));
		    		}
		    	//	CoreEngine.DebugPrint("minbounds="+this.amountOfCharsoutsideMinBounds+"maxbounds="+this.amountOfCharsoutsideMaxBounds);
		    		
		    		
		    	}else {
		    		this.shownString=string;
		    		this.amountOfCharsoutsideMinBounds=0;
		    	}
	    	    
	    
	    	
	    }else {
	    	this.amountOfCharsoutsideMinBounds=0;
	    	this.shownString=string;
	    }
		 
	}
	
	

	@Override
	protected void renderUpdate(Vector2f box_Position) {
		UIManager.takingInput.setState(this.takingInpput);
		
		 makeShownString();
		
		//CoreEngine.DebugPrint("INDEX="+index);
		
		
		if(this.takingInpput) {
		       CharCallback.takeInput=true;
		}else {
			CharCallback.takeInput=false;
		}
		
		text.setString(shownString);
		
		
			
	
	
		float textOffset=this.textOffset;
	
		
	   
		
	
		
		Vector2f position=new Vector2f();
		
		this.position_in_box.add(box_Position,position);
	    this.collision_box.debugDraw(position);
	    float offset=-this.getWidth();
	    if(index>0) {
	      offset=this.text.getCursorOffsetFromIndex(new Vector2f((textOffset*this.sizeOfStirng)-this.getWidth(),0),index-this.amountOfCharsoutsideMinBounds,this.sizeOfStirng);
		}
	   
	
		
		
	     if(this.takingInpput) {
	    		
			MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(position.x+offset,position.y,1000),0,new Vector2f(1,this.sizeOfStirng*75),Game.DEFAULT_TEXTURE,Constants.BLACK));
		}
		
	    text.UIdrawString((position.x+(textOffset*this.sizeOfStirng))-this.getWidth(),position.y,this.sizeOfStirng,Constants.BLACK);
	    

		
		
	}
	
}
