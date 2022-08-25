package main.java.input;

import org.lwjgl.glfw.GLFWCharCallback;


import static  org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;

public class CharCallback extends GLFWCharCallback {

	protected static String string="";
	protected static boolean changed=false;
	public static boolean takeInput=false;
	
	
	@Override
	public void invoke(long  window, int codepoint) {
		if(takeInput) {
				string =(string+String.valueOf(Character.toChars(codepoint)));
	            changed=true;
		}
			

	}
	
}
