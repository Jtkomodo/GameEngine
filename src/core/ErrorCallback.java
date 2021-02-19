package core;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
public class ErrorCallback implements GLFWErrorCallbackI {

	@Override
	public void invoke(int error, long description) {
		System.err.println("GLFW error [" + Integer.toHexString(error) + "]: " + GLFWErrorCallback.getDescription(description));
		
	}

	
	
	
	
}
