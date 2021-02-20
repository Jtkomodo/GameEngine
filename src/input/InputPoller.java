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
			
		}
   
	}
	
	
	public static byte checkState(int key) {
	     return States.getOrDefault(key, STILL_REALEASED);
	}
	
	
	
}
