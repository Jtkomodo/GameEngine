package rendering;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import core.CoreEngine;
import core.PASSABLE_DATA_TYPE;
import core.PassableData;

/**This is the class that makes the model for the gpu to use
 * every thing that is drawn to the screen has some sort of model whether it be
 * this one or {@link OneTextureBatchedModel} we make a model by using the constructors
 * then we draw it using the {@link #draw()} method.We can change the uvs and vertices using the
 * change methods(this will delete and reallocate buffers)
 * <p>
 * 
 * <strong>Constructors:<strong>
 * {@link #Model(float[], float[])  Model(float[] vertices,float[] uv_coords)},<P>{@link #Model(float[], float[], int[]) Model(float[] vertices,float[] uv_coords,int[] indeces)}
 * ,<P>{@link #Model(float, float, float, float, float, float)  Model(float width,float height,float u,float v,float texWidth,float texHeight)}
 * 
 * 
 * 
 * 
 * 
 * @author Jesse Talbot
 *
 */
public class Model extends ModelFramwork implements PassableData{
    private int drawCount;
	private int vao_id,v_id,tex_id,ind_id;
	private static  boolean draw=true;
	
	private int[] indeces= {
			0,1,2,
			2,3,0	
				
		};;
		

		

/**
 * Since this is a 2D game this treats the model as just a rectangle sprite we will us this one mostly.
 * This automatically generates the verts,uvs and indices based off of the parameters.	
 * @param width the width of the sprite on the texture
 * @param height the height of the sprite on the texture
 * @param u the x position on the texture
 * @param v the y position on the texture
 * @param texWidth the width of the texture
 * @param texHeight the height of the textrure
 */
public Model(float width,float height,float u,float v,float texWidth,float texHeight) {
		 
	        float  Texwidth=texWidth;
			float Texheight=texHeight;		
			float  wi=width;
			float  h=height;
			float  Texx=u;
			float Texy=v;		
			
			  UV_coords=new float[]{
					Texx/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,Texy/Texheight,
					(Texx+wi)/Texwidth,(Texy+h)/Texheight,
					Texx/Texwidth,(Texy+h)/Texheight
					};
			
			 Vertices=new float[]{
					   -wi,+h,
						wi,h,
						wi,-h,
						-wi,-h};
			
			MakeModel(Vertices,UV_coords,indeces);
			
			
		
	}
		
		
		
		
	/**
	 * makes a new models with the specified verts and uvs
	 * Indices will act ass though it is two triangles	
	 * @param vertices the verts of the model
	 * @param uv_coords the uvs of the model
	 */
	public Model(float[] vertices,float[] uv_coords) {
		
	 MakeModel(vertices,uv_coords,indeces);
		
	}
		
	/**
	 * Makes a new Model with the specified verts,uv_coords,and indices	
	 * @param vertices the vertices of the model
	 * @param uv_coords the uvs of the model
	 * @param indeces the indeces of the model
	 */
	public Model(float[] vertices,float[] uv_coords,int[] indeces) {
		
		 MakeModel(vertices,uv_coords,indeces);
			
		}
				
		

	





/**
 * Changes both vertices and UVs<strong> this deletes and reallocates buffers<strong>	
 * @param vertices the new vertices
 * @param uv_coords the new uv_coords
 */
public void changeValues(float[] vertices,float[] uv_coords) {
	
	changeVert(vertices);
	changeUV(uv_coords);
}
		
		
		
		
		
	private void MakeModel(float[] vertices,float[] uv_coords,int[] indices) {
		this.Vertices=vertices;
		this.UV_coords=uv_coords;
		this.indeces=indices;
		
		      drawCount=indices.length;
		vao_id=glGenVertexArrays();
		glBindVertexArray(vao_id);
		            //make buffer
		   v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
			FloatBuffer Vertsbuffer=MemoryUtil.memAllocFloat(vertices.length);	
			FloatBuffer Uvsbuffer=MemoryUtil.memAllocFloat(uv_coords.length);	
			IntBuffer Indbuffer=MemoryUtil.memAllocInt(indeces.length);
			Vertsbuffer.put(vertices);
			Uvsbuffer.put(uv_coords);
		    Indbuffer.put(indeces);
		    Vertsbuffer.flip();
		    Uvsbuffer.flip();
		    Indbuffer.flip();
				
			
			
		
		            //bind buffers and store the data in them

			
	
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glBufferData(GL_ARRAY_BUFFER,Vertsbuffer,GL_DYNAMIC_DRAW);
		
		
	    glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glBufferData(GL_ARRAY_BUFFER,Uvsbuffer,GL_DYNAMIC_DRAW);
			
	
	    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glBufferData(GL_ELEMENT_ARRAY_BUFFER,Indbuffer,GL_STATIC_DRAW);

	
	      enableAtributes();
	      
	      glBindBuffer(GL_ARRAY_BUFFER,v_id);//bind so we can use     
		  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
		  glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
				
	     	    
		   //unbind buffers
//		glBindBuffer(GL_ARRAY_BUFFER,0);	     
		glBindVertexArray(0);

 //  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		
	
			listOfModels.add(this);
			 MemoryUtil.memFree(Vertsbuffer);
			 MemoryUtil.memFree(Uvsbuffer);
			 MemoryUtil.memFree(Indbuffer);
		
CoreEngine.DebugPrint("made new model");
	}
	
	public float[] getVertices() {
		return Vertices;
	}

	

	public float[] getUv_coords() {
		return UV_coords;
	}

	public int[] getIndeces() {
		return indeces;
	}
	
	protected void draw() {
	if(draw) {	
		
		
		glBindVertexArray(vao_id);
	
	//	enableAtributes();
	
	
		   glDrawElements(DrawMethod,drawCount,GL_UNSIGNED_INT,0);			
    //  disableAtributes();
  	glBindVertexArray(0);  
	}
	}
	
	
	//make buffers and put data in them
	/**
	 * Change the UV's data.<strong> this will delete and deallocate buffers<string>
	 * @param data the new UV data
	 */
		public void  changeUV(float[] data) {
		 glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		FloatBuffer buffer= MemoryUtil.memAllocFloat(data.length);
		buffer.put(data);
		buffer.flip();
		
		  glBufferSubData(GL_ARRAY_BUFFER, 0,buffer);
		  glBindBuffer(GL_ARRAY_BUFFER,0);
		 MemoryUtil.memFree(buffer);
		  this.UV_coords=data;
			}
	/**
	 * Change the vertice's data.<strong> this will delete and deallocate buffers<string>
	 * @param data the new vertices data
	 */
	public void  changeVert(float[] data) {
		  glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  FloatBuffer buffer= MemoryUtil.memAllocFloat(data.length);
			buffer.put(data);
			buffer.flip();
		  glBufferSubData(GL_ARRAY_BUFFER, 0,buffer);
		  glBindBuffer(GL_ARRAY_BUFFER,0);
		  MemoryUtil.memFree(buffer);
		  this.Vertices=data;
			}
	

  
private void enableAtributes() {
	glEnableVertexAttribArray(0);
	   glEnableVertexAttribArray(1);
}
private void disableAtributes() {
	glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}





@Override
protected void delete() {
	glDeleteVertexArrays(vao_id);
	glDeleteBuffers(new int[] {this.v_id,this.tex_id,this.ind_id});
	
}




@Override
public PASSABLE_DATA_TYPE getType() {
	return PASSABLE_DATA_TYPE.MODEL;
}




}

