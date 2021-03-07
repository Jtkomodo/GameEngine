package animation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import rendering.Texture;


public class SpriteSheet {
 
    private HashMap<Integer,float[]> Values=new HashMap<Integer,float[]>();//this is the frame(the int) and the correct u and v coords,width and height
    private int lastFrame;
    private float size;
    private Texture texture;

	
	
	public SpriteSheet(String name,float size) {
		ReadFile(name);
		texture=new Texture("SpriteSheets/"+name);
		this.size=size;
		
	}
	
	

	public void setValues(HashMap<Integer, float[]> values) {
		Values = values;
	}


	private void  ReadFile(String name) {
		
		
		InputStream ist=this.getClass().getResourceAsStream("/res/SpriteSheets/"+name+".data");//loads the spriter sheet data file
		
		
		
		if(ist==null) {
		 System.out.println("SpriteSheet "+name+" can't be found");
		}	
		else {	
		InputStreamReader isr = new InputStreamReader(ist);//creates a input stream so that we can read the file
		BufferedReader br=new BufferedReader(isr);//makes the bufferd reader that takes in the input stream from the file
		String line="";//just intializing the line
	
		while(true) {
			
		
	     try {
			line=br.readLine();//reads the line
			String[] valuest=line.split("/");//splits the line into string arrays at the / for easy parsing
			
			
			if(line.charAt(0)=='*') {//if we have reached the end of data symbol we break the loop
				
				break;
			}
			float[] v=new float[valuest.length-1];//this makes a array for all the data needed for a single sprite
			int id=0;//sets the id to 0 at first
			for(int i=0;i<valuest.length;i++) {//this is just a loop that reads the data and stores it into a array
				if(i!=0) {
					
				v[i-1]=Float.parseFloat(valuest[i]);//loads in the data to our array
				}else {
					id=Integer.parseInt(valuest[i]);//the first number in the data for a single sprite is it's id this allows for easy switching
					
				}
			}
			
			if(id>lastFrame)
			lastFrame=id;//this just lets us know what the final sprite is in the sprite sheet
			Values.put(id,v);//this loads in the data to a hashmap to easily retreive the data from the sprite's id
			
		
			
		
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		}
		
		try {
			br.close();
			isr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		}
		
 
		
	}
	
	
	
	
	

	public float[] getValueInMap(int key) {
		if(Values.containsKey(key)) {
			return Values.get(key);
			
		}else {
			return null;
		}
	}

	public int getLastFrame() {
		return this.lastFrame;
	}



	public float getSize() {
		return size;
	}



	public void setSize(float size) {
		this.size = size;
	}



	public Texture getTexture() {
		return texture;
	}



	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	
	
	
	
	
}
