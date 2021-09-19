package core;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import input.CharCallback;
import input.KeyCallback;
import input.WindowCallback;

public class Window {

private long window;
private  Long fullscreen;
private GLFWVidMode vidmode;
private int width,height;
private int dH,dW;


public Window(int width, int height,String name) {

	
		this.width=width;
		this.height=height;
		dH=height;
		dW=width;
		glfwSetErrorCallback(new ErrorCallback());
		
		if(!glfwInit()) {
		throw new IllegalStateException("failed to initialize window");
			
		}
		glfwWindowHint(GLFW_VISIBLE,GLFW_TRUE);//this makes it so that we don't show the window till it is ready
		glfwWindowHint(GLFW_DECORATED,GLFW_TRUE);//this makes it so the top bar is there if this is set to false  it will not show it
		glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);//this allows the window to be resized by the user
	
		//	glfwWindowHint(GLFW_FOCUSED,GLFW_TRUE);
		
		window=glfwCreateWindow(width,height,name,0,0);//creats the glfw window to be draw to
		if(window==0) {
			throw new IllegalStateException("failed to create window");//this is just a error message if somehow window creation failed		
			}
		
		vidmode=glfwGetVideoMode(glfwGetPrimaryMonitor());//this tells witch monitor the screen will be viewed 
	
		
		glfwSetWindowPos(window,(vidmode.width()-width)/2,(vidmode.height()-height)/2);//sets it in the center of the screen
		glfwSetWindowTitle(window,name);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		
		//glfwSwapInterval(1);
		glfwSwapInterval( 0 );CoreEngine.DebugPrint("vsnc off"); //this is for the vsync uncomment this when testing frame rates 
		
        GL.createCapabilities();

		glEnable(GL_TEXTURE_2D);
		//glClearColor(.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//allow gfwKeycallbacks for Callback
		
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
	
		glfwSetWindowSizeLimits(window,width,height,GLFW_DONT_CARE,GLFW_DONT_CARE);
		glfwSetWindowAspectRatio(window, width, height);
	  
		glfwSetKeyCallback(window,new KeyCallback());
		glfwSetCharCallback(window,new CharCallback());
		glfwSetWindowPosCallback(window,new WindowCallback());
		}

	public boolean isExited() {
		return glfwWindowShouldClose(window);
	}
	public void CloseWIndow() {
	 glfwSetWindowShouldClose(window, true);
	}
	
	
	public void update() {
		fullscreen=glfwGetWindowMonitor(window);
		glfwPollEvents();
		int[] wi=new int[1], he=new int[1];
glfwGetWindowSize(window, wi, he);
int h=he[0],w=wi[0];



if(h!=height || w!=width) {
	
	
	
glViewport(0, 0,w ,h);
width=w;height=h;

}

}
	
	
	
	
	public void destroy() {
		glfwDestroyWindow(window);
		
	}
	public void render() {
		glfwSwapBuffers(window);
	}
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT);
	}
    

   

	
	public void toggleFullscreen() {	
    	fullscreen=glfwGetWindowMonitor(window);    	
    	
    	if(fullscreen==0) {
		
			glfwSetWindowMonitor(window,glfwGetPrimaryMonitor(),0,0,vidmode.width(),vidmode.height(),vidmode.refreshRate());// this allows fullscreen window .the value that actually changes if fullscreen is used is the second argument
		//	glViewport(0,0,width,height);	
			glViewport(0, 0, vidmode.width(),vidmode.height()); //this changes the view to the size of the monitor so that it won't be small
				//System.out.println(fullscreen);
			width=vidmode.width();height=vidmode.height();
		
    	}
    	else {
    		
    		glfwSetWindowMonitor(window,0,(vidmode.width()-dW)/2,(vidmode.height()-dH)/2,dW,dH,vidmode.refreshRate());
    		glViewport(0,0,dW,dH);
    		width=dW;height=dH;
    	
    	
		}
    		
    	
    }

	 public long geWindow() {
	    	return window;
	    }
	    public int getWidth() {
			return width;
		}

		
		public int getHeight() {
			return height;
		}

	

		
}
