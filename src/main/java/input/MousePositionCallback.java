package main.java.input;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;




public class MousePositionCallback extends GLFWCursorPosCallback {

	protected static float X,Y;
	
	@Override
	public void invoke(long window, double x, double y) {
		
		  X=(float)x;
	      Y=(float)y;
		
	}

}
