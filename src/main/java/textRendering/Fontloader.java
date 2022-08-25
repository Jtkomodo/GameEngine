package main.java.textRendering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import main.java.core.ResourceLoader;
import main.java.core.ResourceNotFoundException;
import main.java.rendering.Texture;


public class Fontloader {
private String Font="aakar";

protected Texture tex;
protected float Texwidth,Texheight;
protected HashMap<Integer,Float[]> Values=new HashMap<Integer,Float[]>();



 public Fontloader(String Font,float size) {//has to be 1 to 1
	 this.Font=Font;
	 this.tex= new Texture(Font);
	 loadFile();
	 this.Texwidth=size;
	 this.Texheight=size;

	 
 }
 
  
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public Texture getTex() {
	return tex;
}





















private void loadFile() {
	
		//StringBuilder string=new StringBuilder();
		BufferedReader br;
		
		try{
			
			
			ResourceLoader r=new ResourceLoader("/res/fonts/"+Font+".fnt");
			InputStream ist=r.getInputStream();
			InputStreamReader isr = new InputStreamReader(ist);
			br=new BufferedReader(isr);
			String line="";
			int i=0;
			int collums=0;
			int rows=0;
			int temp=0;
			while(true) {
			
			
			char c=(char)br.read();
			
			if(c=='*') {
			
		        line=line+" *";
				
				break;
			}
			
			
			if(c!='/') {
			line =line+c;
			
			
			}else {
				temp++;
				line=line+" ";
			}
			if(c=='\\') {
				collums++;
				rows=temp;
				temp=0;
			}
			
					
			
			
			
			i++;
			
			}
			
			br.close();
		
			i=0;
			int index=0;
			Float[] val=new Float[7]; 
			Integer ascii=0;
			String line2="";
			while(true){
				
			char c=line.charAt(i);
			if(c=='*') {
				ist.close();
				isr.close();
				br.close();
					
				break;
			}
			if((c==' ' || c=='\\')) {	
				if(index==1) {
					
					ascii=Integer.parseInt(line2);
				}
				if(index>1) {
				    val[index-2]=Float.parseFloat(line2);
				    }
			
				index++;
				if(c=='\\') {
				
					
					Values.put(ascii, val);
					index=0;
					val= new Float[7];
					
					
				}
				line2="";
				
			}else {
				line2=line2+c;}	
				
	
				
			
		i++;
			}
			
		
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	     
   }





















public void setLoader(String fontFileName, float atlusSize) {
	// TODO Auto-generated method stub this.Font=Font;
	 this.tex= new Texture(fontFileName);
	 loadFile();
	 this.Texwidth=atlusSize;
	 this.Texheight=atlusSize;

	
}
	 
  
	
	
	
}
