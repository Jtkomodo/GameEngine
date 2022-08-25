package main.java.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import java.nio.*;

import  org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import main.java.core.CoreEngine;



/*
   This is to allow batch rendering we first just allocate buffers for the vertexes,uvcoords,and indeces then we use the method addvaluestoVBO() to add in a new model(don't forget to to make sure to change the positions so
   they are not rendered on top of each other) then in order to draw all the models in one draw call we call the method draw() after first calling the enable() to change values on the fly we put in the section and the target
   either v_id,tex_id,or ind_id and lastly the data in float array and integer array for v_id,tex_id and ind_id.  
*/

public class OneTextureBatchedModel extends ModelFramwork{
	 private int drawCount,drawCount2;
		private int v_id,tex_id,ind_id;//made these public so that we can get to them from another method to change values
	private int indBase=0,pionter,sections;
    private final int MaxSections=100;
  
	public OneTextureBatchedModel() {
		CoreEngine.DebugPrint("made new one texture batched model");
		//setting everything to 0 
		listOfModels.add(this);
		drawCount=0;
		drawCount2=0;
		indBase=0;
		pionter=0;
		sections=0;
		//making space for buffers
		 v_id=glGenBuffers();//for vertices		
		   tex_id=glGenBuffers();//for uv Coords
			ind_id=glGenBuffers();//for indices  
			
		
		
		
		//all dynamic draw so we can change them on the fly
		init();
		}
	
	@Override
	public void draw() {
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		  glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
			
	   glBindBuffer(GL_ARRAY_BUFFER,tex_id);
		  glVertexAttribPointer(1,2,GL_FLOAT,false,0,0);
		 
		  
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	      glDrawElements(DrawMethod,drawCount,GL_UNSIGNED_INT,0);/* this is a drawcall it takes all the data in the binded buffers and actually draws them to the screen with indices */
	  	
		}
	

	
	
	
public void addvaluestoVBO(float[] v,float[] uv) {//used to add a new model to the buffers
	if((v.length == uv.length) && (v.length%8==0)) {//just makes sure they have the correct size of data
	    int quads=v.length/8;	
        sections+=quads; //this is for the method change values
		//sections++; //this is for the method change values
		if(sections>this.MaxSections) {
			sections-=quads;
			return;
		}
	
		 int[] indeces=new int[6*quads];
			for(int i=0;i<quads;i++) {
				int i2=i*6;
				indeces[i2]=indBase;indeces[i2+1]=indBase+1;indeces[i2+2]=indBase+2;
				indeces[i2+3]=indBase+2;indeces[i2+4]=indBase+3;indeces[i2+5]=indBase;	
				indBase+=4;
			}

		FloatBuffer Vertsbuffer=MemoryUtil.memAllocFloat(v.length);	
		FloatBuffer Uvsbuffer=MemoryUtil.memAllocFloat(uv.length);	
		IntBuffer Indbuffer=MemoryUtil.memAllocInt(indeces.length);
		Vertsbuffer.put(v);
		Uvsbuffer.put(uv);
	    Indbuffer.put(indeces);
	    Vertsbuffer.flip();
	    Uvsbuffer.flip();
	    Indbuffer.flip();
		
		
	glBindBuffer(GL_ARRAY_BUFFER, v_id);

	glBufferSubData(GL_ARRAY_BUFFER,pionter, Vertsbuffer);//adds the vertex values to the vertex buffer

	
	glBindBuffer(GL_ARRAY_BUFFER, tex_id);
	 glBufferSubData(GL_ARRAY_BUFFER, pionter,Uvsbuffer);//adds the uv values to the uv buffer

	 
	
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
	glBufferSubData(GL_ELEMENT_ARRAY_BUFFER,drawCount2,Indbuffer);//remember all offsets are in bytes so a int is 4 bytes and a float is as well so we need to multiply be 4 to get the correct offset 

	drawCount=drawCount+(6*quads);//used for telling the amount of triangles to draw when the drawcall is made
	drawCount2=drawCount*4;//this is for the correct pionter to the next value in the indeces buffer
	
//	indBase=indBase+4;//this makes sure that the indeces are loaded in right other wise it will  draw the same model multiple times
	 pionter=pionter+(v.length*4);
	  
	 glBindBuffer(GL_ARRAY_BUFFER,0);
	 
	 
	 //free memory
	 
	 MemoryUtil.memFree(Vertsbuffer);
	 MemoryUtil.memFree(Uvsbuffer);
	 MemoryUtil.memFree(Indbuffer);
	
	}else {
		CoreEngine.DebugPrint("[ERROR]sorry but both verts and uv must have a size of 8",this.getClass());
	}
	

	
	
	
}


	

	
public void enable() {
	   glEnableVertexAttribArray(0);
	   glEnableVertexAttribArray(1);
}
public void disable() {
	   glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}

private static void unbindBuffers() {
	glBindBuffer(GL_ARRAY_BUFFER,0);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);		
	
}	
	

public void flushBuffers() {//clears all the buffers and moves the pionter back to one
	
//	init();
    drawCount=0;
	drawCount2=0;
	indBase=0;
	pionter=0;
	sections=0;
   
	
	
}	
private void init() {
	//enable();
	
	
	glBindBuffer(GL_ARRAY_BUFFER,v_id);
	  glBufferData(GL_ARRAY_BUFFER,this.MaxSections*(8*4),GL_DYNAMIC_DRAW);
	
	 
glBindBuffer(GL_ARRAY_BUFFER,tex_id);
	  glBufferData(GL_ARRAY_BUFFER,this.MaxSections*(8*4),GL_DYNAMIC_DRAW);
		
	  
glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ind_id);
glBufferData(GL_ELEMENT_ARRAY_BUFFER,this.MaxSections*(6*4),GL_DYNAMIC_DRAW);

unbindBuffers();

}

@Override
public float[] getUv_coords() {
	float[] data = new float[sections*8];
	glBindBuffer(GL_ARRAY_BUFFER,tex_id);
	glGetBufferSubData(GL_ARRAY_BUFFER, 0, data);;
return data;
}
@Override
public float[] getVertices() {

	float[] data = new float[sections*8];
	glBindBuffer(GL_ARRAY_BUFFER,v_id);
	glGetBufferSubData(GL_ARRAY_BUFFER, 0, data);;
	
	
	return data;
}

@Override
protected void delete() {
	glDeleteBuffers(new int[] {this.v_id,this.ind_id,this.tex_id});
	
}




}
