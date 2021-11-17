package textrendering;

import java.util.LinkedList;


import org.joml.*;

import core.Constants;
import core.CoreEngine;
import core.Game;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.OneTextureBatchedModel;
import rendering.Render;
import rendering.RenderEntity;
import  java.lang.Math;
/**This is a class that will allow us to draw a string to the screen.
 * Once the the class is initiated by loading a new {@link Fontloader} or using a existing one
 * ,use {@link #setString(String)} to set the string then use the various draw() methods to draw 
 * in the desired way.
 *
 * @author Jesse Talbot
 *
 */
public class TextBuilder{


    private LinkedList<Float> cursorPositions=new LinkedList<Float>();
	private String text;
	private float z=200000;
	

	private OneTextureBatchedModel 	textModel;
	private Fontloader loader;
    private float stringLength,stringHieght;
	private RenderEntity e;
	private Vector2fc cursotPositionX;
	/**
	 * *Creates a new TextBuilder  that has the specified font
	 * @param Font The name of the font texture file to load
	 * @param AtlusSize the size of the font texture
	 *
	 */
	public TextBuilder(String Font, float AtlusSize) {
		loader=new Fontloader(Font, AtlusSize);//actually loads the the font file to be used
	    textModel=new OneTextureBatchedModel();
	   
	}
	
	
	public Fontloader getLoader() {
		return loader;
	}
    /**Creates a new TextBuilder using a already loaded Font
     * @param loader the already loaded font
     */

	public TextBuilder(Fontloader loader) {
		this.loader=loader;//actually loads the the font file to be used
		textModel=new OneTextureBatchedModel();;
		
	
	    
	}	
	
	
	
	
	/**Sets the string to have it ready to be drawn to the screen
	 * 
	 * @param text the text we will want to draw
	 */
	public void setString(String text) {//this makes our string model from a string to be drawn with drawstring()
		//this just checks to see if it is the same string as the last rendered 
		float lengthOfString=0;
		float heightOfString=0;
		//------------init values-------------------	
	   textModel.flushBuffers();;//important this makes sure that we start with a clean state so that nothing will go wrong
		
		this.text=text;
		 Vector2f offset=new Vector2f(0,0);//offset from our x coord of the current cursor position 
		float[] l;//uv coords (don't know why I called it l) 
		float[]  v;//vertex
	    Vector2f cursor=new Vector2f(0,0);// the curent cursor position 
	   
	//actually make the model from values in font file	
	    this.cursorPositions.clear();
	 
		for(int i=0;i<text.length();i++) {//simple for loop
		
			int a=text.charAt(i);//this gets each char in order and uses it as the key to the hexmap that has all the info we need to draw in the correct place  
		  
		   
			
			Float[] val=loader.Values.get(a);// this gets all the values for the current char
			float x=val[0];// x value of the topleft hand corner
			float y=val[1];//y value of the topleft hand corner
			float width=val[2];//this is the width of the char used for both find the correct uv values and vert
			float height=val[3];
			float xoff=val[4];//offset from the begging of the cursor in x
			float yoff=val[5];//in y
	         //loading into vertex for easy addition
			 offset.x=xoff;
			 offset.y=-yoff;
			float xadv=val[6];//how much the cursor needs to advance for the next char
			//makes easy reading 
			float Xz=x/loader.Texwidth;
			float Yz=y/loader.Texheight;
			float Xo=(x+width)/loader.Texwidth;
			float Yo=(y+height)/loader.Texheight;
			float height2=(height/2);
			float width2=(width/2);
			
			cursor.add(offset);//adds the offset to the cursor
			
			if((char)a!='\n'){
			 l=new float[]{// loads in the correct uv coords
					Xz,Yz,
					Xo,Yz,
					Xo,Yo,
					Xz,Yo
					};
			
			v=new float[]{// finds the positons of all the corners of the char
				   cursor.x+ -width2,cursor.y+height2,
				   cursor.x+width2, cursor.y+height2,
				   cursor.x+width2,cursor.y-height2,
				   cursor.x+-width2,cursor.y-height2
				};
			textModel.addvaluestoVBO(v, l);//this is what actually adds the char into the batched model with the correct uv and vertex pionts
			
				}
			
				else {
				 yoff-=100;
				cursor=new Vector2f(0,cursor.y);
				 
			}
				
				
	
				 if((cursor.x+width2)>lengthOfString) {
						lengthOfString=cursor.x+width2;
					}
			if(i==0) {	
			 this.cursorPositions.add(cursor.x-width2);						
			}
			 cursor.add(xadv,yoff);//moves the cursor for the next char		 
			

			 if((cursor.y+height2+yoff)>heightOfString) {
					heightOfString=cursor.y+height2+yoff;
				}
				
			 this.cursorPositions.add(cursor.x-width2);					
		}
	
	   this.stringLength=lengthOfString;
	   this.stringHieght=heightOfString;
		
		
	}

	public OneTextureBatchedModel getTextModel() {
		return this.textModel;
	}

   
	public void UIdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
	RenderEntity	e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		e.setPosition(new Vector3f(x,y,z));
		e.setColor(color);
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
	
	}
	public void UIDebugdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		if(CoreEngine.Debugdraw) {
		RenderEntity	 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		e.setPosition(new Vector3f(x,y,z));
		e.setColor(color);
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
		}
		
	}
	public void UIDebugdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		if(CoreEngine.Debugdraw) {
		RenderEntity	 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		e.setPosition(new Vector3f(x,y,z));
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
		}
	}
	
	public void UIdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		e.setPosition(new Vector3f(x,y,z));
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
	
		
	}
	
	public void UIdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		e.setPosition(new Vector3f(x,y,z));
		e.setColor(color);
		e.setAngle(angle);
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
	
	}
	public void UIDebugdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		if(CoreEngine.Debugdraw) {
			e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
		e.setColor(color);
		e.setAngle(angle);
		e.setSize(scale);
		e.setUIPojeection(true);
		MainRenderHandler.addEntity(e);
		}
	}
	public void UIDebugdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		if(CoreEngine.Debugdraw) {
			e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setAngle(angle);
			e.setSize(scale);
			e.setUIPojeection(true);
			MainRenderHandler.addEntity(e);
			}
	}
	
	public void UIdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
		    e.setAngle(angle);
			e.setSize(scale);
			e.setUIPojeection(true);
			MainRenderHandler.addEntity(e);
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void drawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		    e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setColor(color);
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			
	
	}
	public void DebugdrawString(float x,float y,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		if(CoreEngine.Debugdraw) {
			e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setColor(color);
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			}
		
	}
	public void DebugdrawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
		 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
		if(CoreEngine.Debugdraw) {
			e.setPosition(new Vector3f(x,y,z));
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			}
	}
	
	public void drawString(float x,float y,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
					
	}
	
	public void drawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
		
		 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setColor(color);
			e.setAngle(angle);
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			
	
	}
	public void DebugdrawString(float x,float y,float angle,float scale,Vector4f color) {//this is the method that actually draws the text to the screen at the desired location and scale
	
		if(CoreEngine.Debugdraw) {
				 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setColor(color);
			e.setAngle(angle);
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			}
		
	}
	public void DebugdrawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	
			if(CoreEngine.Debugdraw) {
				 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
				e.setPosition(new Vector3f(x,y,z));
				e.setAngle(angle);
				e.setSize(scale);
				MainRenderHandler.addEntity(e);
				}
		
	}
	
	public void drawString(float x,float y,float angle,float scale) {//this is the method that actually draws the text to the screen at the desired location and scale
	 e=new RenderEntity(textModel,new Vector3f(0,0,z),0,1,loader.tex);
			e.setPosition(new Vector3f(x,y,z));
			e.setAngle(angle);
			e.setSize(scale);
			MainRenderHandler.addEntity(e);
			
	}
	public float getZ() {
		return z;
	}


	public void setZ(float z) {
		this.z = z;
	}
    public float getClosestCurosorOffset(Vector2f position,float cursorPositionX,float sizeOfString) {
    	   
       
		 
    	float offset=(cursorPositionX-position.x)/sizeOfString;
    	int closestIndex=0;
    	float smallest=0;
    	 for(int i=0;i<this.cursorPositions.size();i++) {
    		    float cursorPosition=this.cursorPositions.get(i);
    		    float dif=Math.abs(cursorPosition-offset);
    		  //  CoreEngine.DebugPrint("dif="+dif);    		    
    		    if(dif<=smallest || i==0) {
    		    	smallest=dif;
    		    	closestIndex=i;
    		    }else {
    		    	break;
    		    }
    		 
    	 }
    	
    	 
    	 return position.x+(this.cursorPositions.get(closestIndex)*sizeOfString);
    	
    }

	public int getClosestCursorIndex(Vector2f position,float cursorPositionX,float sizeOfString) {
		float offset=(cursorPositionX-position.x)/sizeOfString;
    	
    	int closestIndex=0;
    	float smallest=0;
    	 for(int i=0;i<this.cursorPositions.size();i++) {
    		    float cursorPosition=this.cursorPositions.get(i);
    		    float dif=Math.abs(cursorPosition-offset);
    		  //  CoreEngine.DebugPrint("dif="+dif);    		    
    		    if(dif<=smallest || i==0) {
    		    	smallest=dif;
    		    	closestIndex=i;
    		    }else {
    		    	return closestIndex;
    		    }
    		 
    	 }
    	// CoreEngine.DebugPrint("CloseestOffset="+this.cursorPositions.get(closestIndex));
    	
    	 return closestIndex;
    	 
    	
	}
	public float getCursorOffsetFromIndex(Vector2f position,int index,float sizeOfString) {
		float offset=0;
		if(index<this.cursorPositions.size()) {
			offset= position.x+(this.cursorPositions.get(index)*sizeOfString);
		}
		
		return offset;
	}
	
	public float getAmountOutsideBound(Vector2f position,float bound,float sizeOfString) {
		
		float amount=0;
		float length=position.x+(this.cursorPositions.getLast()*sizeOfString);
		if(length>=bound) {
		    amount=length-bound;
		}
		
		return amount;
	}
	
	
	public int getAmountOfCharsOutsideMAxBound(Vector2f position,float bound,float sizeOfString) {
	
		int amount=0;
		for(int i=this.cursorPositions.size()-1;i>=0;i--) {
			float cursorPosition=position.x+(this.cursorPositions.get(i)*sizeOfString);
		    if(cursorPosition>=bound) {
		    	amount++;
		    }else {
		    	break;
		    }
			
		}
		
		return amount;
	}
	public int getAmountOfCharsOutsideMinBound(Vector2f position,float bound,float sizeOfString) {
		
		int amount=0;
		for(int i=0;i<this.cursorPositions.size();i++) {
			float cursorPosition=position.x+(this.cursorPositions.get(i)*sizeOfString);
		    
			if(cursorPosition<bound) {
		    	amount++;
		    }else {
		    	break;
		    }
		}
		
		return amount;
	}
	
	
	
	
	public float getStringLength() {
		return stringLength;
	}


	public float getStringHieght() {
		return stringHieght;
	}
}


