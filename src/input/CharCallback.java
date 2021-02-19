package input;

import org.lwjgl.glfw.GLFWCharCallback;


import static  org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;

public class CharCallback extends GLFWCharCallback {

	public static String string="";
	public static boolean takeInput=false;
	
	
	@Override
	public void invoke(long  window, int codepoint) {
		if(takeInput) {
				string =(string+String.valueOf(Character.toChars(codepoint)));
	
		}
			

	}
	
	public static void backspace() {
		if(string.length()>0) {
		string=(string.substring(0,string.length()-1));
		}
	}
	
	public static void clearString() {
		string="";
	}

	public static void removeWord() {
		if(string.length()!=0) {
			int lastspaceIndex=string.lastIndexOf(" ");
			 if(lastspaceIndex!=-1){
				string=string.substring(0,lastspaceIndex);
			}else {
				string="";
			}
		}
		
	}
	
}
