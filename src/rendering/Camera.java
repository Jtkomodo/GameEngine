package rendering;

import org.joml.*;

public class Camera {

	private Vector2f position;
	private Matrix4f projection,UIprojection,ViewMatrix;
	private float width,height;
	
	
	public Camera(float width,float height) {
		this.width=width;
		this.height=height;
		
		
		position= new Vector2f(0,0);
		projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
	
		UIprojection=projection;
		
		}
	
	public void addVector(Vector2f vector ) {
		position=position.add(vector.x,vector.y);
		}
	public void setPosition(Vector2f position) {
		this.position=new Vector2f(position.x,position.y);
	}
	public void subtractVector(Vector2f vector ) {
		position=position.sub(vector.x,vector.y);
		}
	
	public Vector2f getPosition() {
		return new Vector2f(this.position.x,this.position.y);
	}
	
	
	
	
	
	public Matrix4f getProjection() {
		Matrix4f target= new Matrix4f();
		   ViewMatrix= new Matrix4f().setTranslation(new Vector3f(position,0));
			target=projection.mul(ViewMatrix,target);
	return target;
	}
	
	public Matrix4f getUIProjection() {
		Matrix4f target= new Matrix4f();
	   ViewMatrix= new Matrix4f().setTranslation(new Vector3f(position,0));
		target=UIprojection.mul(ViewMatrix,target);
	return target;
	}

	public Matrix4f getViewMatrix() {
		return ViewMatrix;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		
		projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	
		projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
		
	}
	
	public void setSize(float width,float height) {
		this.height=height;
		this.width=width;
		
		
		
		projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
		
	}
	
	
	
}
