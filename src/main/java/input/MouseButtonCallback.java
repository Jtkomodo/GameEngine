package main.java.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;

public class MouseButtonCallback extends GLFWMouseButtonCallback {

	public static boolean[] Buttons= new boolean[3];
	
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		
		if(button==GLFW_MOUSE_BUTTON_1 || button==GLFW_MOUSE_BUTTON_2 || button==GLFW_MOUSE_BUTTON_3) {
			boolean oldValue=Buttons[button];
			Buttons[button]=action !=GLFW.GLFW_RELEASE;
			InputPoller.updateKey(button,oldValue);
			
		}
		
		
	}

}
