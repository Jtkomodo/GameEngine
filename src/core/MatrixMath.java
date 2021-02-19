package core;

import org.joml.Matrix4f;
import org.joml.Vector2f;
public class MatrixMath {

	private static Matrix4f matrix=new Matrix4f().identity();
	
	public static Matrix4f getMatrix(Vector2f translation, float angle, float scale) {
		//load the identity matrix
		
		matrix.identity();
		
		
		matrix.scale(scale);
		matrix.translate(translation.x,translation.y,0);
		matrix.rotateZ((float) java.lang.Math.toRadians(angle));
		//System.out.println(matrix);
		
		return matrix;
	}
	
	public static Matrix4f getMatrix(Vector2f translation,float angle,Vector2f scale ) {
		
       matrix.identity();
		
		
		matrix.scale(scale.x,scale.y,1);
		matrix.translate(translation.x,translation.y,0);
		matrix.rotateZ((float) java.lang.Math.toRadians(angle));
		//System.out.println(matrix);
		
		return matrix;
		
	}
	
	
	
	
	
	public static Matrix4f mirror(Matrix4f matrix) {
		matrix.scale(-1,1,1);
		return matrix;
	}
	
	
	
	
	
	
	
	
	
}
