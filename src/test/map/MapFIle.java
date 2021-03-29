package test.map;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.Vector2f;

import core.CoreEngine;
import physics.AABB;

public class MapFIle {
	
 /*               LAYOUT OF A MAP FILE
  * 
  * .MAP
  * width and height of the map(Both ints)
  * .UV
  * amount of tile types(int)
  * this is where all the uvs for each tile type(each is always 64 by 64) will be placed along with their ID(0 is no tile so we can have blank spaces)
   * ID(int)
  * U(float)
  * V(float)
  * repeat for each tile
  * .TILES
  * array of ints(these are the tile ids) this our "map"
  *.Col
  * for the moment only AABBs are allowed
  * x(float)
  * y(float)
  * width(float)
  * height(float)
  * resistance(float)
  *repeat for each AABB
  *
  * EXAMPLE FILE(look at exampleMAP.png to see result)
  * 
  * .MAP//begining of map data
  * 4,4 //width is 4 height is 4
  * .UV //begging of uv data
  * 2//because there are only 2 tile types
  * 1
  * 0.0f
  * 0.0f
  * 2
  * 64.0f
  * 0.0f
  *.TILES
  *1122
  *2222
  *2222
  *2220
  *.COL
  * 32.0f
  * 32.0f
  * 32
  * 32
  * 0
  * 
  * */	
  private String name;
  private final String colDataIndicator=".COL";
  private final String mapDataIndicator=".MAP";
  private final String tileUVIndicator=".UV";
  private final String tileDataIndicator=".TILES";
  private ArrayList<AABB> aabbs=new ArrayList<AABB>();
  private ArrayList<Vector2f> uvData=new ArrayList<Vector2f>();
  private int[][] map;
  private int width,height;
  private int amountOfTiles;
  
  
  
	public MapFIle(String name) {
	this.name=name;
		
		
		
		
	}

	public void makeFile(int[][] map,ArrayList<Vector2f> UVDATA) {
		
		
		
		try {
			DataOutputStream stream = new DataOutputStream(new FileOutputStream("src/res/Maps/"+name+".bin"));
		    stream.writeUTF(this.mapDataIndicator);
		    stream.writeInt(map.length);
		    stream.writeInt(map[0].length);
		    
		    
		    stream.writeUTF(this.tileUVIndicator);
		    stream.writeInt(UVDATA.size());
		    for(int i=0;i<UVDATA.size();i++) {
		    	stream.writeInt(i+1);
		    	Vector2f uv=UVDATA.get(i);
		    	stream.writeFloat(uv.x);
		    	stream.writeFloat(uv.y);
		    }
		    
		    
		    stream.writeUTF(this.tileDataIndicator);
		    for(int i=0;i<map[0].length;i++) {
		    	for(int j=0;j<map.length;j++) {
		    		stream.writeInt(map[j][i]);
		    		
		    		
		    	}
		    	
		    	
		    	
		    }
		stream.close();
		CoreEngine.DebugPrint("Finished saving map");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public void makeFile(AABB[] AABB) {
		
		
		
		
		
		
		try {
			DataOutputStream stream = new DataOutputStream(new FileOutputStream("src/res/Maps/"+name+".bin"));
		    stream.writeUTF(this.colDataIndicator);
			
			
			
			
			for(int i=0;i<AABB.length;i++) {
				
				writeAABB(AABB[i],stream);
			}
			
			
			
			
			
			
			
			
			
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	private void writeAABB(AABB a,DataOutputStream stream) {
		
	
			
		   
			Vector2f position=a.getPosition();
			float x=position.x;
			float y=position.y;
			float width=a.getwidth();
			float height=a.getHeight();
			float r=a.getResistance();
		   try {
			stream.writeFloat(x);
		    stream.writeFloat(y);
		    stream.writeFloat(width);
		    stream.writeFloat(height);
		    stream.writeFloat(r);
		    
		   }catch(IOException e){
			   e.printStackTrace();
			   return;
			   
		   }	
		
			
			
		
       CoreEngine.DebugPrint("done"+(int)x+","+(int)y);

		
	}
	

/*	public void addAABB (AABB aabb) {
		aabbs.add(aabb);
	}*/
		
		
	public void readMap() {
		
	
		DataInputStream stream;
		try {
			stream = new DataInputStream(new FileInputStream("src/res/Maps/"+name+".bin"));
		} catch (FileNotFoundException e1) {
		    
			System.err.print("Sorrry the mapfile "+name+" is not found in /res/maps");
			return;
		}
	
		try {
		while(stream.available()>0) {
		  
	    	String indicator=stream.readUTF();
	    	
	    	if(indicator.equals(this.mapDataIndicator)) {
	    		     this.width=stream.readInt();
	    		     this.height=stream.readInt();
	    		     
		    	  }
	    	else if(indicator.equals(this.tileUVIndicator)) {
	    		this.amountOfTiles=stream.readInt();
	    	
	    		for(int i=0;i<this.amountOfTiles;i++) {
	    			readTileUVDATA(stream);
	    		}
	    		
	    		
	    	}else if(indicator.equals(this.tileDataIndicator)) {
	    		map=new int[width][height];
	    		
	    		for(int i=0;i<height;i++) {
	    			for(int j=0;j<width;j++) {
	    				map[j][i]=stream.readInt();
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		
	    	}else if(stream.readUTF().equals(this.colDataIndicator)) {
	    		
	    	AABB result;
	    	 do{
	    		result=ReadAABB(stream);
	    		  
	    		 if(result!=null) {
	    			aabbs.add(result);
	    			 
	    		 }
	    	  
	    	  
	    	  }while(result!=null);
	    		  
	    	 
	    	 
	    	  }
	    	
	    	
	    	
	    	
	    }
	    CoreEngine.DebugPrint("Finished reading map file\nDATA:");
	    stream.close();
		 for(int i=0;i<this.height;i++) {
				String s="";
			    for(int j=0;j<this.width;j++) {
					
		         s=s+map[j][i]+",";
		
				}
		    CoreEngine.DebugPrint(s);	    
		 }
		 CoreEngine.DebugPrint("TILEUVS:");
		 for(int i=0;i<this.amountOfTiles;i++) {
			    CoreEngine.DebugPrint("tile "+(i+1)+"=("+this.uvData.get(i).x+","+this.uvData.get(i).y+")");
		 }
		 
		 
	    
		}catch (IOException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	}
		
		
		
		
	
	
	
	
	
	
   	private void readTileUVDATA(DataInputStream stream) {
	
   		/*stream.writeInt(i+1);
    	Vector2f uv=UVDATA.get(i);
    	stream.writeFloat(uv.x);
    	stream.writeFloat(uv.y);*/
   		
   		
		try {
			int index = stream.readInt();
	        float u=stream.readFloat();
	        float v=stream.readFloat();
   		if(this.uvData.size()>index) {
   			
   		
   		this.uvData.add(index,new Vector2f(u,v));
   		}else {
   			this.uvData.add(new Vector2f(u,v));
   		}
   		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
   		
   		
   		
		
	}

	private AABB ReadAABB(DataInputStream stream) {
   		try {
		
	
   		   
   		    	 
   		    	float x,y,width,height,r;	 
   		    	 x=stream.readFloat();
   		 	     y=stream.readFloat();
   		 	     width=stream.readFloat();
   			     height=stream.readFloat();
   			     r=stream.readFloat();
   			    x-=width;
   			    y+=height;
   			     
   			  AABB A=new AABB(new Vector2f(x,y),width,height,r);
   			  //A.setPosition(new Vector2f(x,y));  
   			     return A;
   			   
			
   		     
   		     
   		        		
   		} catch (IOException e) {
			// TODO Auto-generated catch block
		
         	return null;
		}
   		
   		
   		
   		
   		
   	}
	
	
	public AABB[] getAABBS() {
		AABB[] as=new AABB[aabbs.size()];
		return aabbs.toArray(as);
		
		
	}

	public int[][] getMap() {
		return map;
	}
	public Vector2f[] getTextures() {
	
		
		return this.uvData.toArray(new Vector2f[this.uvData.size()]);
		
	}
	
	
	
	
	
	
	
	
	
	

}
