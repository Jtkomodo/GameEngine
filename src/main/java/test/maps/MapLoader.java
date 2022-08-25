package main.java.test.maps;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import main.java.core.CoreEngine;
import main.java.rendering.MainRenderHandler;
import main.java.rendering.OneTextureBatchedModel;
import main.java.rendering.RenderEntity;
import main.java.rendering.Texture;

public class MapLoader {

	
	private float sizeOfEachTile;
	private OneTextureBatchedModel model=new OneTextureBatchedModel(); 
	



	private int[][] map,keychart;


	private int a=0,tilesRenderd=0;

	protected HashMap<Integer,float[]> tilePosData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,float[]> tileUVData=new HashMap<Integer,float[]>();
	protected HashMap<Integer,int[]> test2=new HashMap<Integer,int[]>();

	
public MapLoader(Texture texture,MapFIle file,float sizeOfEachTile){
		
		this.sizeOfEachTile=sizeOfEachTile;
	
		loadInData(texture,file);
		
	}
	
	public void loadInData(Texture texture, MapFIle file) {
	this.map=file.getMap();
	float[] vert;	
	float[] uv;
	float texWidth=texture.getW();
	float texHeight=texture.getH();
	Vector2f[] uvData=file.getTextures();
	keychart=new int[map[0].length][map.length];	
	int key=0;	
	CoreEngine.DebugPrint(map.length+" "+map[0].length);
	
	
	for(int i=0;i<map[0].length;i++) {
		for(int j=0;j<map.length;j++) {
			
			int index=map[j][i];
			float addi=(0.5f*i)*2;	
	        float addj=(0.5f*j)*2;	
	    	keychart[i][j]=key;
				 vert=new float[]{
							addj-0.5f,addi+0.5f,
							addj+0.5f,addi+0.5f,
							addj+0.5f,addi-0.5f,
							addj-0.5f,addi-0.5f
		
				 };
				 

					
if(index>0) {
	      Vector2f uvCoords=uvData[index-1];
		   
		   
			tilePosData.put(key, vert); 
			
			
			uv=new float[]{
				uvCoords.x/texWidth,uvCoords.y/texHeight,
				(uvCoords.x+64)/texWidth,uvCoords.y/texHeight,
				(uvCoords.x+64)/texWidth,(uvCoords.y+64)/texHeight,
				uvCoords.x/texWidth,(uvCoords.y+64)/texHeight,
			};
			
			
			
			
		
			tileUVData.put(key, uv); 	} 
		key++;
		}
		
			
		}
		
		
		
	}
	
	
		
		
	
	


						

	
	//test method
	public void loadTile(int tilex,int tiley) {
		
	if((tilex<map.length && tilex>=0)&&(tiley<map[0].length && tiley>=0))	{
		int key=keychart[tiley][tilex];
		
		float[] uv=tileUVData.get(key);		
		float[] vert=tilePosData.get(key);	
		
		if(tileUVData.containsKey(key)) {
		model.addvaluestoVBO(vert, uv);
		}
		this.a++;	  
		
		
	}
		
	}
	public void drawtiles(Texture tex) {

		MainRenderHandler.addEntity(new RenderEntity(model,new Vector3f(0,0,-1000),0, sizeOfEachTile,tex));
tilesRenderd=a;
		a=0;

	}
	
	
	
	public void flushModel() {
		model.flushBuffers();
		
		
		
	}
	
	
	public OneTextureBatchedModel getModel() {
		return model;
	}
	
	public int getTilesrenderd() {
		
		return this.tilesRenderd;
	}
	public Vector2f findPositionOnMap(float x,float y) {
		  Vector2f mapPosition=new Vector2f(x/sizeOfEachTile,y/sizeOfEachTile);
		
			
			return mapPosition;
			
			
			
			
			
		}
	
	
}