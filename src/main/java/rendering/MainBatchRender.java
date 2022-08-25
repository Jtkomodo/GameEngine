package main.java.rendering;

import java.util.LinkedList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import main.java.core.Constants;
import main.java.core.CoreEngine;
import main.java.core.MatrixMath;
import main.java.rendering.Texture;
/**
 * This will handle drawing all the {@linkplain ModelFramwork models} in as few calls as possible
 * we will add the models using the various add model() methods
 * then when all the models are added we use {@link #draw} to draw them to the screen.
 * <p>
 * <Strong>AddModelFunctions:<Strong>
 * <p>
 * {@linkplain #addModel(float[], float[], Vector2f, float, float, Texture, boolean) addModel(float[] cuvs,
 * float[] LocalVerts,Vector2f position,float angle,float scale,Texture texture, boolean UIprojection)};
 * <P>
 * {@link #addModel(float[], float[], Vector2f, float, Vector2f, Texture, boolean)  addModel(float[] cuvs,float[] LocalVerts, Vector2f position, float angle, Vector2f scale,
			Texture texture, boolean UIprojection)};
 * <p>
 * {@linkplain #addModel(float[], float[], Vector2f, float, float, Texture, Vector4f, boolean) addModel(float[] cuvs,float[] LocalVerts,Vector2f position,float angle,
 * float scale,Texture texture,Vector4f color,boolean UIprojection)};
 * <p>
 * {@link #addModel(float[], float[], Vector2f, float, Vector2f, Texture, Vector4f, boolean)  addModel(float[] cuvs,float LocalVerts[], Vector2f position, float angle
 * , Vector2f size,Texture texture, Vector4f color, boolean UIprojection)};
 * 
 * @author jtkom
 *
 */
public class MainBatchRender {

	private static LinkedList<Texture> textures=new LinkedList<Texture>();
	private static LinkedList<MultipleTextureBatchedModel> models=new LinkedList<MultipleTextureBatchedModel>();
	private static int currentbatch=0;
	private static boolean Mirror=false; 
	
	
	
	/**
	 * 
	 */

	public static void draw() {
	    
	//	Start.DebugPrint(" Number of BatchedModels="+ models.size());
		for(int i=0;i<models.size();i++) {
		Render.draw(models.get(i),new Vector2f(0),0,1, textures.toArray( new Texture[textures.size()]));
		}
		
		
		
	}
	
	
	
	
	
	public static void addModel(float[] cuvs,float[] LocalVerts,Vector2f position,float angle,float scale,Texture texture,Vector4f color,boolean UIprojection) {
		
		
		
		
		if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
				
		int TextureSampler=0;
		if(!textures.contains(texture)) {
			 textures.add(texture); 
		   
	   }
	   TextureSampler=textures.indexOf(texture);
	   int sections=LocalVerts.length/8;
	 
	   float[] verts=new float[sections*8];
	   float[] uvs=new float[sections*12];
	   float[] colors=new float[sections*16]; 
	   float[] translations=new float[sections*12];
		
	   Matrix4f trs=new Matrix4f();
	   trs.identity();
	    trs.scale(scale);
	    
	    trs.rotateZ((float) java.lang.Math.toRadians(angle));
	   float pr=0;
	   if(UIprojection) {
		   pr=1;
	   }
			   
	for(int i=0;i<sections;i++) {
		Matrix4f mat=new Matrix4f();
	      
		int i2=i*8;
	    int i3=i*12;
	    int i4=i*16;		
		mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
	    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
	    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
	    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
	    		
	    		);
	    

	  mat.transpose();
	    mat.mul(trs);
	    if(Mirror) {
	    	MatrixMath.mirror(mat);
	    	Mirror=false;
	    }
		  mat.transpose();  
	 
	   
	  //  trs.transpose();
	    Vector4f vert1=new Vector4f();
	    Vector4f vert2=new Vector4f();
	    Vector4f vert3=new Vector4f();
	    Vector4f vert4=new Vector4f();
	    mat.getColumn(0,vert1);
	    mat.getColumn(1,vert2);
	    mat.getColumn(2,vert3);
	    mat.getColumn(3,vert4);
	  
	    
	  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
	  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
	  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
	  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
	    
	   
	   
	  translations[i3+0]=position.x;translations[i3+1]=position.y;translations[i3+2]=pr;   
	  translations[i3+3]=position.x;translations[i3+4]=position.y;translations[i3+5]=pr;
	  translations[i3+6]=position.x;translations[i3+7]=position.y; translations[i3+8]=pr;
	  translations[i3+9]=position.x;translations[i3+10]=position.y;translations[i3+11]=pr;
	    
	   
		
		
		

	  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
	  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
	  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
	  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
	
	  Vector4f newcolor=new Vector4f();
	color.div(255,newcolor);		
		
		
	 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
	 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
	 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
	 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
	}

	 
	
	addDataToBatch(verts,uvs,colors,translations);
	}
		
		   	
		
	}
	
	
public static void addModel(float[] cuvs,float[] LocalVerts,Vector2f position,float angle,float scale,Texture texture, boolean UIprojection) {
		
	
	
	if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
			
	int TextureSampler=0;
	if(!textures.contains(texture)) {
		 textures.add(texture); 
	   
   }
   TextureSampler=textures.indexOf(texture);
   int sections=LocalVerts.length/8;
 
   float[] verts=new float[sections*8];
   float[] uvs=new float[sections*12];
   float[] colors=new float[sections*16]; 
   float[] translations=new float[sections*12];
	
   Matrix4f trs=new Matrix4f();
   trs.identity();
    trs.scale(scale);
    
    trs.rotateZ((float) java.lang.Math.toRadians(angle));
   float pr=0;
   if(UIprojection) {
	   pr=1;
   }
		   
for(int i=0;i<sections;i++) {
	Matrix4f mat=new Matrix4f();
      
	int i2=i*8;
    int i3=i*12;
    int i4=i*16;		
	mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
    		
    		);
    

  mat.transpose();
    mat.mul(trs);
    if(Mirror) {
    	MatrixMath.mirror(mat);
    	Mirror=false;
    }
	  mat.transpose();  
 
   
  //  trs.transpose();
    Vector4f vert1=new Vector4f();
    Vector4f vert2=new Vector4f();
    Vector4f vert3=new Vector4f();
    Vector4f vert4=new Vector4f();
    mat.getColumn(0,vert1);
    mat.getColumn(1,vert2);
    mat.getColumn(2,vert3);
    mat.getColumn(3,vert4);
  
    
  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
    
   
   
  translations[i3+0]=position.x;translations[i3+1]=position.y;translations[i3+2]=pr;   
  translations[i3+3]=position.x;translations[i3+4]=position.y;translations[i3+5]=pr;
  translations[i3+6]=position.x;translations[i3+7]=position.y; translations[i3+8]=pr;
  translations[i3+9]=position.x;translations[i3+10]=position.y;translations[i3+11]=pr;
    
		
   

	
	
	

  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
Vector4f newcolor=Constants.DEFAULT_COLOR;
		
	
	
 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
}
addDataToBatch(verts,uvs,colors,translations);
	}

	   	
	}
	
	
	
	
	
	
	public static  void flushModel() {
		for(int i=0;i<models.size();i++) {
		models.get(i).flushBuffers();
		
		}
	
		
		currentbatch=0;
	}
	private static void addDataToBatch(float[] verts,float[] uvs,float[] colors,float[] translations) {
		if(models.isEmpty()) {
			models.add(new MultipleTextureBatchedModel());
			currentbatch=0;
		
		}



		 boolean batchedHasReachedMax=MainBatchRender.models.get(currentbatch).addvaluestoVBO(verts,uvs,colors,translations);

		 if(batchedHasReachedMax) {
			   currentbatch++;
			 if(models.size()<=currentbatch) { 
		 
				models.add(new MultipleTextureBatchedModel());
			
				CoreEngine.DebugPrint("BatchedRender added new BatchedModel because the amount of sections added exceded max");
			}
			 
		   models.get(currentbatch).addvaluestoVBO(verts, uvs, colors, translations);
		 }
		
	}
	
	public static void Mirror() {
		
		Mirror=true;
		
		
	}





	public static void addModel(float[] cuvs,float LocalVerts[], Vector2f position, float angle, Vector2f size,Texture texture, Vector4f color, boolean UIprojection) {
		

	
	if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
			
	int TextureSampler=0;
	if(!textures.contains(texture)) {
		 textures.add(texture); 
	   
   }
   TextureSampler=textures.indexOf(texture);
   int sections=LocalVerts.length/8;
 
   float[] verts=new float[sections*8];
   float[] uvs=new float[sections*12];
   float[] colors=new float[sections*16]; 
   float[] translations=new float[sections*12];
	
   Matrix4f trs=new Matrix4f();
   trs.identity();
    trs.scale(new Vector3f(size,0));
    
    trs.rotateZ((float) java.lang.Math.toRadians(angle));
   float pr=0;
   if(UIprojection) {
	   pr=1;
   }
		   
for(int i=0;i<sections;i++) {
	Matrix4f mat=new Matrix4f();
      
	int i2=i*8;
    int i3=i*12;
    int i4=i*16;		
	mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
    		
    		);
    

  mat.transpose();
    mat.mul(trs);
    if(Mirror) {
    	MatrixMath.mirror(mat);
    	Mirror=false;
    }
	  mat.transpose();  
 
   
  //  trs.transpose();
    Vector4f vert1=new Vector4f();
    Vector4f vert2=new Vector4f();
    Vector4f vert3=new Vector4f();
    Vector4f vert4=new Vector4f();
    mat.getColumn(0,vert1);
    mat.getColumn(1,vert2);
    mat.getColumn(2,vert3);
    mat.getColumn(3,vert4);
  
    
  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
    
   
   
  translations[i3+0]=position.x;translations[i3+1]=position.y;translations[i3+2]=pr;   
  translations[i3+3]=position.x;translations[i3+4]=position.y;translations[i3+5]=pr;
  translations[i3+6]=position.x;translations[i3+7]=position.y; translations[i3+8]=pr;
  translations[i3+9]=position.x;translations[i3+10]=position.y;translations[i3+11]=pr;
    
	    
	   
		
   

	
	
	

  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
  Vector4f newcolor=new Vector4f();
	color.div(255,newcolor);
		
	
	
 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
}
addDataToBatch(verts,uvs,colors,translations);
	}

		
	}





	public static void addModel(float[] cuvs,float[] LocalVerts, Vector2f position, float angle, Vector2f scale,
			Texture texture, boolean UIprojection) {
		// TODO Auto-generated method stub
		
		
		if(LocalVerts.length!=0 && LocalVerts.length%8==0) { 
				
		int TextureSampler=0;
		if(!textures.contains(texture)) {
			 textures.add(texture); 
		   
	   }
	   TextureSampler=textures.indexOf(texture);
	   int sections=LocalVerts.length/8;
	 
	   float[] verts=new float[sections*8];
	   float[] uvs=new float[sections*12];
	   float[] colors=new float[sections*16]; 
	   float[] translations=new float[sections*12];
		
	   Matrix4f trs=new Matrix4f();
	   trs.identity();
	    trs.scale(new Vector3f(scale,0));
	    
	    trs.rotateZ((float) java.lang.Math.toRadians(angle));
	   float pr=0;
	   if(UIprojection) {
		   pr=1;
	   }
			   
	for(int i=0;i<sections;i++) {
		Matrix4f mat=new Matrix4f();
	      
		int i2=i*8;
	    int i3=i*12;
	    int i4=i*16;		
		mat.set(LocalVerts[i2+0],LocalVerts[i2+1],0,0,
	    		LocalVerts[i2+2],LocalVerts[i2+3],0,0,
	    		LocalVerts[i2+4],LocalVerts[i2+5],0,0,
	    		LocalVerts[i2+6],LocalVerts[i2+7],0,0
	    		
	    		);
	    

	  mat.transpose();
	    mat.mul(trs);
	    if(Mirror) {
	    	MatrixMath.mirror(mat);
	    	Mirror=false;
	    }
		  mat.transpose();  
	 
	   
	  //  trs.transpose();
	    Vector4f vert1=new Vector4f();
	    Vector4f vert2=new Vector4f();
	    Vector4f vert3=new Vector4f();
	    Vector4f vert4=new Vector4f();
	    mat.getColumn(0,vert1);
	    mat.getColumn(1,vert2);
	    mat.getColumn(2,vert3);
	    mat.getColumn(3,vert4);
	  
	    
	  verts[i2+0]=vert1.x;verts[i2+1]=vert1.y;   
	  verts[i2+2]=vert2.x;verts[i2+3]=vert2.y;
	  verts[i2+4]=vert3.x;verts[i2+5]=vert3.y; 
	  verts[i2+6]=vert4.x;verts[i2+7]=vert4.y;
	    
	   
	   
	  translations[i3+0]=position.x;translations[i3+1]=position.y;translations[i3+2]=pr;   
	  translations[i3+3]=position.x;translations[i3+4]=position.y;translations[i3+5]=pr;
	  translations[i3+6]=position.x;translations[i3+7]=position.y; translations[i3+8]=pr;
	  translations[i3+9]=position.x;translations[i3+10]=position.y;translations[i3+11]=pr;
	    
		    
		   
			
	   

		
		
		

	  uvs[i3+0]=(cuvs[i2+0]);uvs[i3+1]=(cuvs[i2+1]);    uvs[i3+2]=(TextureSampler);
	  uvs[i3+3]=(cuvs[i2+2]);uvs[i3+4]=(cuvs[i2+3]);    uvs[i3+5]=(TextureSampler);
	  uvs[i3+6]=(cuvs[i2+4]);uvs[i3+7]=(cuvs[i2+5]);    uvs[i3+8]=(TextureSampler);
	  uvs[i3+9]=(cuvs[i2+6]);uvs[i3+10]=(cuvs[i2+7]);    uvs[i3+11]=(TextureSampler);
	  Vector4f newcolor=Constants.DEFAULT_COLOR;
			
		
		
	 colors[i4+0]=newcolor.x;colors[i4+1]=newcolor.y;colors[i4+2]=newcolor.z;colors[i4+3]=newcolor.w;
	 colors[i4+4]=newcolor.x;colors[i4+5]=newcolor.y;colors[i4+6]=newcolor.z;colors[i4+7]=newcolor.w;
	 colors[i4+8]=newcolor.x;colors[i4+9]=newcolor.y;colors[i4+10]=newcolor.z;colors[i4+11]=newcolor.w;
	 colors[i4+12]=newcolor.x;colors[i4+13]=newcolor.y;colors[i4+14]=newcolor.z;colors[i4+15]=newcolor.w;
	}
	addDataToBatch(verts,uvs,colors,translations);
		}

	}
	
	public static int getQuads() {
		int quads=0;
		//Start.DebugPrint(""+models.size());
		for(int i=0;i<models.size();i++) {
			
			quads+=models.get(i).getSections();
		//	Start.DebugPrint(i+","+quads);
		}
		return quads;
		
	}
	public static void deleteResources() {
		
		for(int i=0;i<models.size();i++) {
			models.get(i).delete();
		}
		
		ModelFramwork.deleteALL();
		Texture.deleteAllTextures();
		
	}
	
	
	
}
