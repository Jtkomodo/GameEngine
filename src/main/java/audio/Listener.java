package main.java.audio;

import org.joml.Vector2f;

import static org.lwjgl.openal.AL10.*;




public class Listener {

	private static Vector2f position;
	
      public static void  InitListener() {
    	 Listener.position=new Vector2f();
    	 alListener3f(AL_POSITION,0, 0, 0);
    	 alListener3f(AL_VELOCITY,0,0, 0); 
      }
	
	  
	
	  public static void ChangePosition(Vector2f position) {
			 Listener.position=position;
			 alListener3f(AL_POSITION,position.x, -position.y, 1);
		  
	  }
	
	
	
	
	
	
}
