package input;



import java.util.HashMap;
import java.util.LinkedList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

import events.Flag;

import static org.lwjgl.glfw.GLFW.*;

public class GetInput {

	//this can be a object or just a static class the object is needed to have unique input for things that will require it the static type will be used by input handler class

	private static HashMap<Integer,Flag> KeyFlags=new HashMap<Integer,Flag>();
	private static HashMap<Integer,Byte> buttonsInAFrame=new HashMap<Integer,Byte>();
	public static final byte NOT_PUSHED=0,JUST_PUSHED=1,HELD=3,JUST_REALESED=2;

	private static boolean Find(int key) {
		try {
			Boolean newKey;

			newKey=KeyCallback.keys[key];
			return newKey;	
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("sorry there is no"+key+"key");
		}}

	public static void newFrame() {
		buttonsInAFrame.clear();
	}


	public static byte getStateofButton(int button) {
		byte state;
		if(!KeyFlags.containsKey(button)) {
			KeyFlags.put(button,new Flag(false));
		}
		Flag keyFlag=KeyFlags.get(button); 
		boolean now=Find(button);

		if(!buttonsInAFrame.containsKey(button)) {	                                
			if(!keyFlag.setState(now)) { 

				if(now) {
					state=HELD;
				}else {
					state=NOT_PUSHED;
				}

			}else {
				if(now) {
					state=JUST_PUSHED;
				}else {
					state=JUST_REALESED;
				}

			}
			buttonsInAFrame.put(button,state);
		}else {
			state=buttonsInAFrame.get(button);

		}

		return state;
	}




}
