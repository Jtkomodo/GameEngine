package input;

import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

public class WindowSizeCallback implements GLFWWindowSizeCallbackI {

	
	public WindowSizeCallback(int width,int height) {
		InputPoller.windowhHeight=height;
		InputPoller.windowWidth=width;
		
	}
	
	@Override
	public void invoke(long window, int width, int height) {
		 InputPoller.windowhHeight=height;
		 InputPoller.windowWidth=width;

	}

}
