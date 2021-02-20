package input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;



//this class is needed to use glfwKeyCallback in Window class 

public class KeyCallback extends  GLFWKeyCallback {

	
	public static boolean[] keys= new boolean[349];
	
	
	@Override
	public void invoke(long window, int key, int scancode, int action , int mods ) {
	if(key!=-1){
		boolean oldValue=keys[key];
		keys[key]=action !=GLFW.GLFW_RELEASE;
		InputPoller.updateKey(key,oldValue);
	}
	
	}

	
	
	
	

	
	
	
	
	
	
}
