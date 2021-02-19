package input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BIndingNameParser {

	
	
	
	private  HashMap<Integer,String> nameOfButtons=new HashMap<Integer,String>();
	
	public BIndingNameParser(String name) {
		Parse(name);
	}
	
	
	
	private  void Parse(String name) {
	
		String   location=new String("/res/"+name+".ButtonBInd");
		
	InputStream ist=getClass().getResourceAsStream(location);
    ist = new BufferedInputStream(ist);
		
		
		if(ist==null) {
		 System.out.println("BindingFile "+name+" can't be found");
		}
		else {	
		InputStreamReader isr = new InputStreamReader(ist);
		BufferedReader br=new BufferedReader(isr);
		String line="";
		int lineNumber=0;
		while(true) {
			
		
	     try {
			line=br.readLine();//reads the line
			
			
			
			if(line.charAt(0)=='*') {//if we have reached the end of data symbol we break the loop
				
				break;
			}
			
			
			String[] button=line.split(" ");
			
			nameOfButtons.put(Integer.parseInt(button[1]),button[0]);
			
			
			
			
			
		
			
		lineNumber++;	
			}	
		 catch (IOException e) {
			
			e.printStackTrace();
		}
	     
		}
		try {
			ist.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}	
	}
	
		
		
	
	
	public String getNameofKey(int key) {
		return nameOfButtons.get(key);
		
	}
	
	
	
	
	
	
	
}
