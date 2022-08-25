package main.java.rendering;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import main.java.core.MatrixMath;

public class Render {

	
public static int drawcalls=0;	
public static ShaderProgram s;
public static Camera cam;
public static int UIProjection;
public static int location;
public static int Projection;
public static int RTS;


protected static void draw(MultipleTextureBatchedModel model,Vector2f position,float angle,float scale,Texture[] textures) {
		
		
		int[] samplers=new int[textures.length];
		for(int i=0;i<textures.length;i++) {
			textures[i].bind(i);
			samplers[i]=i;
		}
		 
		   
		   Matrix4f rts=MatrixMath.getMatrix(new Vector2f(position.x/scale,position.y/scale),angle,scale);

			   
			
          
		   s.loadMat(UIProjection, cam.getUIProjection());
		   s.loadIntegers(location, samplers); 
		   s.loadMat(Projection,cam.getProjection());
		   s.loadMat(RTS, rts);
		     
		   model.draw();	 
			
		
			drawcalls++;
}



}
