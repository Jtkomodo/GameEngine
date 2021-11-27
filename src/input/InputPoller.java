package input;

import java.util.HashMap;
import java.util.LinkedList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import UI.UIManager;
import core.CoreEngine;
import core.Game;
import core.Window;
import events.Condition;
import events.EventAction;
import events.Events;
import events.Flag;
import rendering.Camera;
import rendering.Render;
import static events.Operation.*;

public class InputPoller {

	
	public static final byte JUST_PUSHED=0b01;
	public static final byte JUST_REALEASED=0b10;
	public static final byte STILL_REALEASED=0b00;
	public static final byte STILL_PUSHED=0b11;
	protected static int windowWidth,windowhHeight;
	public static String string="";
	
	
	public  static Flag charsChanged=new Flag(false);
	private static HashMap<Integer,Flag> keysToWatchFlags=new HashMap<Integer,Flag>();
	private static HashMap<Integer,Byte> States=new HashMap<Integer,Byte>();
	private static LinkedList<Integer> keyUpdated=new LinkedList<Integer>();
	private static LinkedList<Integer> keysReset=new LinkedList<Integer>();
	private static LinkedList<Integer> justPushed=new LinkedList<Integer>();
	
	
	
	protected static void updateKey(int key,boolean oldValue) {
		keyUpdated.add(key);
		
		byte state=States.getOrDefault(key, STILL_REALEASED);
		if(oldValue) {
			States.put(key,(byte) (state | 0b10));
		}else {
			States.put(key,(byte) (state & 0b01));
		}
		
	}
	
	
	
	
	
	public static void POll() {
		if(CharCallback.takeInput && CharCallback.changed) {
			string=CharCallback.string;
			charsChanged.setState(true);
			CharCallback.changed=false;
		}else {
			charsChanged.setState(false);
			CharCallback.string="";
		}
		
		
		
		
		
		while(!justPushed.isEmpty()) {
		    int key=justPushed.pop();
		    States.put(key,STILL_PUSHED);
		}
		while(!keysReset.isEmpty()) {
			int key=keysReset.pop();
			States.put(key,STILL_REALEASED);
		}
	
		while(!keyUpdated.isEmpty()) {
			
			int key=keyUpdated.pop();
			if(keysToWatchFlags.containsKey(key)) {
			     keysToWatchFlags.get(key).toggleState();
			}
			boolean now;
			if(key!=GLFW.GLFW_MOUSE_BUTTON_1 && key!=GLFW.GLFW_MOUSE_BUTTON_2 && key!=GLFW.GLFW_MOUSE_BUTTON_3) {
			now=KeyCallback.keys[key];
			}else {
		    now=MouseButtonCallback.Buttons[key];
			}
			byte state=States.getOrDefault(key, STILL_REALEASED);
		  	
			   
			   if(now) {
                	state=((byte) (state | 0b01));
					
                }else {
                	state=((byte) (state & 0b10));
                }
			   
               if(state==JUST_REALEASED) {
            	   keysReset.add(key);
               }
		      States.put(key,state);
			if(state==JUST_PUSHED) {
				justPushed.add(key);
			}
			
		}
	
		
		
	}
	public static Events makeEventOnKeyUpdated(int key,EventAction action) {
		if(!keysToWatchFlags.containsKey(key)) {
			keysToWatchFlags.put(key,new Flag(false));
		}
		Flag f=keysToWatchFlags.get(key);
		Condition keyTriggered=new Condition(f,CHANGED,true);
		return new Events(new Condition(keyTriggered,AND,new Condition(UIManager.takingInput,EQUALS,false)),action);
	}
	public static Events makeEventOnUIKeyUpdated(int key,EventAction action) {
		if(!keysToWatchFlags.containsKey(key)) {
			keysToWatchFlags.put(key,new Flag(false));
		}
		Flag f=keysToWatchFlags.get(key);
		Condition keyTriggered=new Condition(f,CHANGED,true);
		return new Events(keyTriggered,action);
	}
	
	public static Condition keyTriggered(int key) {
		if(!keysToWatchFlags.containsKey(key)) {
			keysToWatchFlags.put(key,new Flag(false));
		}
		Flag f=keysToWatchFlags.get(key);
		Condition keyTriggered=new Condition(f,CHANGED,true);
		return new Condition(keyTriggered,AND,new Condition(UIManager.takingInput,EQUALS,false));
	}
	
	public static Condition UIkeyTriggered(int key) {
		if(!keysToWatchFlags.containsKey(key)) {
			keysToWatchFlags.put(key,new Flag(false));
		}
		Flag f=keysToWatchFlags.get(key);
		Condition keyTriggered=new Condition(f,CHANGED,true);
		return keyTriggered;
	
	}
	
	public static boolean JustPushed(int key) {
		return checkState(key)==JUST_PUSHED;
		
	}
	public static boolean JustRealesed(int key) {
		return checkState(key)==JUST_REALEASED;
		
	}
	public static boolean Held(int key) {
		return checkState(key)==STILL_PUSHED;
		
	}
	public static boolean Still_Realeased(int key) {
		return checkState(key)==STILL_REALEASED;
		
	}
	public static boolean NOT_REALESED(int key) {
		 int state=checkState(key);
		return state>0 && state!=JUST_REALEASED;
	}
	
	public static byte checkState(int key) {
	     return States.getOrDefault(key, STILL_REALEASED);
	}
	
	
	public static Vector2f getScreenMousePosition() {
	    float x=(2f*MousePositionCallback.X)/windowWidth-1;
	    float y=(2f*MousePositionCallback.Y)/windowhHeight-1;
	    Matrix4f invert=new Matrix4f();
	    Render.cam.getUIprojectionMatrix().invert(invert);
	    Vector4f eyeCords=new Vector4f(x,-y,0,1).mul(invert,new Vector4f());
		Vector2f returnVec=new Vector2f(eyeCords.x,eyeCords.y);    
	    
	    
		return returnVec;
		
	     
		
	}

	public static Vector2f getWorldMousePosition() {
		float x=(2f*MousePositionCallback.X)/windowWidth-1;
		float y=(2f*MousePositionCallback.Y)/windowhHeight-1;
	    Matrix4f invert=new Matrix4f();
	    Render.cam.getprojectionMatrix().invert(invert);
	    Vector4f eyeCords=new Vector4f(x,-y,0,1).mul(invert);
	    eyeCords.mul(Render.cam.getVeiwMatrix().invert(new Matrix4f()));
		Vector2f returnVec=new Vector2f(eyeCords.x,eyeCords.y);    
	    
	    
		return returnVec;
		
	     
		
	}
   

	public static Vector2f NO_FOV_getScreenMousePosition() {
		float x=(2f*MousePositionCallback.X)/windowWidth-1;
		float y=(2f*MousePositionCallback.Y)/windowhHeight-1;
	    Matrix4f invert=new Matrix4f();
	    Render.cam.getNON_FOV_UIprojection().invert(invert);
	    Vector4f eyeCords=new Vector4f(x,-y,0,1).mul(invert,new Vector4f());
		Vector2f returnVec=new Vector2f(eyeCords.x,eyeCords.y);    
	    
	    
		return returnVec;
		
	     
		
	}

	public static Vector2f NO_FOV_getWorldMousePosition() {
		float x=(2f*MousePositionCallback.X)/windowWidth-1;
		float y=(2f*MousePositionCallback.Y)/windowhHeight-1;
	    Matrix4f invert=new Matrix4f();
	    Render.cam.getNON_FOV_projection().invert(invert);
	    Vector4f eyeCords=new Vector4f(x,-y,0,1).mul(invert);
	    eyeCords.mul(Render.cam.getVeiwMatrix().invert(new Matrix4f()));
		Vector2f returnVec=new Vector2f(eyeCords.x,eyeCords.y);    
	    
	    
		return returnVec;
		
	     
		
	}
	
}
