package rendering;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.UUID;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.newdawn.slick.opengl.PNGDecoder;

import core.CoreEngine;
import core.PASSABLE_DATA_TYPE;
import core.PassableData;

/**Create a texture using {@linkplain #Texture(String)}
 * and then bind it to the gpu's texture location using
 * {@link #bind(int)}
 * 
 * @author Jesse Talbot
 *
 */
public class Texture{

private  int TEXid;

private static LinkedList<Texture> textures=new LinkedList<Texture>();
private int h,w;


private String Path;

/**Creates a new texture using the file path for the texture
 * 
 * @param file path path to the texture from "/res/"
 */


public Texture(String path) {
		
		Path=path;
	
		
		//create buffers for stbi to use
		
       
		ByteBuffer data;
		String location="/res/"+path+".png";	    
	                                   //load our texture 
		try {
			
			
			
		//data=stbi_load(location,width,height,comp,4);
			InputStream stream=getClass().getResourceAsStream(location);
			if(stream==null) {
				System.err.println("stream is null");
			}
			  stream = new BufferedInputStream(stream); 
			PNGDecoder decoder = new PNGDecoder(stream);
			

			    //create a byte buffer big enough to store RGBA values
			    data = MemoryUtil.memAlloc(4 * decoder.getWidth() * decoder.getHeight());

			    //decode
			    
			    
			    decoder.decode(data, decoder.getWidth() * 4, PNGDecoder.RGBA);

			    
			    
		if(data==null) {
			throw new IOException();
		}
	 
		 stream.close();
	 //   loadMapWithBuffer(data);
		
		
		
		
		
		
		
		
	  
	     data.flip();
	  
	    
		
		//get the width and height of the image
		 w =decoder.getWidth();
		 h= decoder.getHeight();
	
	    this.TEXid = glGenTextures();
	    textures.add(this);
	  loadTexture(data);
	  MemoryUtil.memFree(data);
	  CoreEngine.DebugPrint("[OK]Texture "+location+" loaded");
	  } catch (IOException e) {
		     System.err.println("[ERROR]Texture "+location+" failed");
		     e.printStackTrace();
		}
		
		
	
	}
	
	
	/**
	 * binds the texture to a sampler location2d to allow the GPU to use it
	 * <strong> only 31 locations<strong>
	 * @param sampler the location to bind the texture to
	 */
	public void bind(int sampler) {
		  
		if(sampler>=0 && sampler<=31) {
		glActiveTexture(GL_TEXTURE0+sampler);
		glBindTexture(GL_TEXTURE_2D,TEXid);
		   
		} 
	

		
		
	}
	/**
	 * unbinds the texture from the GPU
	 */
	public void  unbind() {
		glBindTexture(GL_TEXTURE_2D,0);
	}
 /**'
  * deletes the textures from GPU memory
  */
  public void delete() {
      GL11.glDeleteTextures(this.TEXid);
  }




private void loadTexture(ByteBuffer data) {
	
	  glBindTexture(GL_TEXTURE_2D,TEXid);//binds
	  GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
      glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
      glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,w,h,0,GL_RGBA,GL_UNSIGNED_BYTE,data);
   
   glBindTexture(GL_TEXTURE_2D,0);//unbinds
 
   
  
}
/**
 * deletes all the textures from GPU memory
 */
public static void deleteAllTextures() {
	for(int i=0;i<textures.size();i++) {
	textures.get(i).delete();
	}
	}




/**
 * gets the height of the texture
 * @return the height of the texture
 */
public int getH() {
	return h;
}

/**
 * gets the width of the texture
 * @return the width of the textue
 */

public int getW() {
	return w;
}




}
