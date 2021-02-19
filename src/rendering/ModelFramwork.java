package rendering;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import org.lwjgl.opengl.GL15;

import java.util.LinkedList;
/**
 * A class that just allows us to hold all models in a single list
 * it also makes sure to implement very important methods that every model
 * must have.
 * <p>
 * <strong> Classes that inherit this Class:(Since 1/23/01)
 * <p>
 * {@link Model}
 * <p>
 * {@link OneTextureBatchedModel}
 * 
 * 
 * @author Jesse Talbot
 *
 */


public abstract class ModelFramwork {

	protected static int DrawMethod=GL_TRIANGLES;
	protected float[] Vertices,UV_coords; 
	protected boolean BatchedModel=false;
	protected static LinkedList<ModelFramwork> listOfModels=new LinkedList<ModelFramwork>();
	
	/**
	 * Calls the GPU to draw this model to the screen
	 */
	protected abstract void draw();
	
	
	
	/**
	 * Changes the draw method
	 * @param drawMethod is a int and can be {@link  org.lwjgl.opengl.GL11#GL_TRIANGLES GL_TRIANGLES},{@link  org.lwjgl.opengl.GL11#GL_LINES GL_LINES},
	 * or {@link  org.lwjgl.opengl.GL11#GL_POINTS GL_POINTS}
	 */
	public static void setDrawMethod(int drawMethod) {
		if((drawMethod==GL_TRIANGLES) ||(drawMethod==GL_LINES)||(drawMethod==GL_POINTS)) {
			if(drawMethod!=DrawMethod) {  
			System.out.println("Switching Draw Method to "+drawMethod );
			DrawMethod = drawMethod;}}
	}

    /**
     * get the vertices of the model
     * @return float[] vertices
     */
	public float[] getVertices() {
	
		return this.Vertices;
	}
   /**
    * Deletes all the model's buffers from memory
    */
   public static void deleteALL() {
	   for(int i=0;i<listOfModels.size();i++) {
		   listOfModels.get(i).delete();
		   
	   }
	   
   }

   /**
    * <strong> IMPORTANT MAKE SURE ALL BUFFERS ARE DELETED FROM MEMORY <strong>
    */
	protected abstract void delete();
	
	




   /**
    * reruns the uv coordinates of the model
    * @return the uv coords
    */
	public float[] getUv_coords() {
		
		return UV_coords;
	}



   /**
    * this will tell us if we are using a batched model or not
    * @return whether we are using a batched model or not
    */
	public boolean isBatchedModel() {
		return BatchedModel;
	}




	
	
	
}