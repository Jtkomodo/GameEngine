package input;

import java.util.HashMap;
import java.util.LinkedList;

import core.CoreEngine;

public class InputPoller {

	
	public static final byte JUST_PUSHED=0b01;
	public static final byte JUST_REALEASED=0b10;
	public static final byte STILL_REALEASED=0b00;
	public static final byte STILL_PUSHED=0b11;
	
	
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
			boolean now=KeyCallback.keys[key];
		
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
	
	
	
}
