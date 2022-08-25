package main.java.rendering;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import main.java.core.ResourceLoader;
import main.java.core.ResourceNotFoundException;

/**The constructor for the ShaderProgram can be either {@link #ShaderProgram(String)}
 * with a single path to both shaders(vs,and frag) both with the same name and format IE
 * name.vs name.fs or {@link #ShaderProgram(String, String)} different names for the vs 
 * and frag shaders we bind the shader program to the gpu using {@link #bind()} once the
 * shaders  are compiled and linked we can add uniform variables using {@link #makeLocation(String)}
 * we can also add attributes using {@link #makeAttribLocation(String)} to set a value in a shader
 * you need to use the various load methods.(the integer location is the value returned from makeLocation(String)) 
 * <p>
 * <strong> Load Methods:<strong>
 * <p>
 * {@link #loadFloat(int, float)}; for float
 * <p>
 * {@link #loadInt(int, int)}; for integers
 * <p>
 * {@link #loadIntegers(int, int[])}; for integer arrays
 * <p>
 * {@link #loadMat(int, Matrix4f)}; for matrix4fs
 * <p>
 * {@link #loadVec2(int, Vector2f)}; for 2d vectors
 * <p>
 * {@link #loadVec4(int, Vector4f)} for 4d vectors(this is used for rgba colors mostly)
 * 
 * 
 * 
 * 
 * 
 * @author Jesse Talbot
 *
 */
public  class ShaderProgram {
private int program,vs,fs,locationSlot;
//private File file=new File(System.getProperty("user.dir"));	

	     //make our shader
	public ShaderProgram(String path) {
	
	//System.out.print(file.getAbsolutePath());
		program=glCreateProgram();
		createShaders(path);
	    Locations();
	    linkandValidate();
	
	}
	
	public ShaderProgram(String vertShader,String fragShader) {
		program=glCreateProgram();
		createShaders(vertShader,fragShader);
	    Locations();
	    linkandValidate();
		
	}
	
	
	
	
	//turning our text file into a string 
	
	private String readFile(String path) {//just the method to store our file contents in a string
		
		StringBuilder string=new StringBuilder();
		BufferedReader br;
		
		try{
		    ResourceLoader r=new ResourceLoader("/shaders/"+path);
			
			  InputStreamReader isr = new InputStreamReader(r.getInputStream());
			br=new BufferedReader(isr);
			String line;
			while((line= br.readLine())!= null) {
				string.append(line);
				string.append("\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("shader does not exist");
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return string.toString();
		
	}
	
	//creating our shader from source
	private  void createShaders(String path) {
		
		vs=glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs,readFile(path+".vs"));//tells opengl where the source code is and loads it
	glCompileShader(vs);//compiles the shader so it can be used by the graphics card
	if(glGetShaderi(vs,GL_COMPILE_STATUS)!=1) {//print out any compile errors
		System.err.println(glGetShaderInfoLog(vs));
		System.err.println("shader vs not compiled");//this tells us which shader failed
		System.exit(1);
	}
	
	fs=glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs,readFile(path+".fs"));
	
	glCompileShader(fs);
	if(glGetShaderi(fs,GL_COMPILE_STATUS)!=1) {
		System.err.println(glGetShaderInfoLog(fs));
		System.err.println("shader fs not compiled");
		System.exit(1);
	
		
	}
	//attach our shaders to the program
	glAttachShader(program,vs);
	glAttachShader(program,fs);
	
	}
private  void createShaders(String path1,String path2) {
		
		vs=glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs,readFile(path1+".vs"));//tells opengl where the source code is and loads it
	glCompileShader(vs);//compiles the shader so it can be used by the graphics card
	if(glGetShaderi(vs,GL_COMPILE_STATUS)!=1) {//print out any compile errors
		System.err.println(glGetShaderInfoLog(vs));
		System.err.println("shader vs not compiled");//this tells us which shader failed
		System.exit(1);
	}
	
	fs=glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs,readFile(path2+".fs"));
	
	glCompileShader(fs);
	if(glGetShaderi(fs,GL_COMPILE_STATUS)!=1) {
		System.err.println(glGetShaderInfoLog(fs));
		System.err.println("shader fs not compiled");
		System.exit(1);
	
		
	}
	
	//attach our shaders to the program
	glAttachShader(program,vs);
	glAttachShader(program,fs);
	
	}
	
	 private void Locations() { //this method tells our shader where the location of our attributes is
		   
		 glBindAttribLocation(program,0,"vertices");
		 glBindAttribLocation(program,1,"textureCoords");
			
		   locationSlot=2;
	   }

   private  void linkandValidate() {
	
	glLinkProgram(program);
	if(glGetProgrami(program,GL_LINK_STATUS)!=1) {
		System.err.println(glGetProgramInfoLog(program));
		System.err.println("program link failed");
		System.exit(1);
	}
	glValidateProgram(program);
	if(glGetProgrami(program,GL_VALIDATE_STATUS)!=1) {
		System.err.println(glGetProgramInfoLog(program));
		System.err.println("program Validate failed");
		System.exit(1);
	}
	
}
   public void bind() {
		glUseProgram(program);//tells opengl we want to use this program
   }
   
   
   public void unbind() {
		glUseProgram(0);
		
	}
	
	
	
	
	//load values
	public void loadInt(int location,int value) {
	     	glUniform1i(location,value);

}
	
	public void loadIntegers(int location,int values[]) {
	 glUniform1iv(location, values);
	}

	public void loadFloat(int location,float value) {
	   	     	glUniform1f(location,value);
		
	}
	
	public void loadVec2(int location,Vector2f vec) {
	     	glUniform2f(location,vec.x,vec.y);
			
}
	
	public void loadVec4(int location,Vector4f vec) {
     	glUniform4f(location,vec.x,vec.y,vec.z,vec.w);
		
}	
	
	
	public void loadMat(int location,Matrix4f matrix) {
         FloatBuffer matrixBuffer=BufferUtils.createFloatBuffer(16);	
         matrix.get(matrixBuffer);
         
        GL20.glUniformMatrix4fv(location,false, matrixBuffer);
    		
	
	}
	
	
	
	//allowing our uniforms to be written to by defining  a location based on the name of the uniform
	
	 public int makeLocation(String name) throws Exception {
	    	int location=glGetUniformLocation(program,name);
	    	if(location==-1) {//if it failed to find the location print a error 
	    		throw new IllegalStateException("Uniform \""+name+"\" :Location not found");
	    	}
	    	
	 return location;  
	 }
	public void makeAttribLocation(String location) {
		
		 glBindAttribLocation(program,locationSlot,location);
		 linkandValidate();
		 locationSlot++;
	}

	

}
   