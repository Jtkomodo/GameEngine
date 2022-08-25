package main.java.rendering;

import org.joml.*;

public class Camera {


		private Vector2f position;
		private Matrix4f projection,UIprojection,VeiwMatrix=new Matrix4f();
		private Matrix4f NON_FOV_projection,NON_FOV_UIprojection;
		private float width,height;
		private float fov;
		
		

		public Camera(float width,float height,float fov) {
			this.width=width;
			this.height=height;
			this.fov=fov;
			
			position= new Vector2f(0,0);
			NON_FOV_projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
			projection=new Matrix4f().setOrtho2D(-width/2*fov, width/2*fov,-height/2*fov, height/2*fov);
		
			UIprojection=projection;
		    this.NON_FOV_UIprojection=NON_FOV_projection;
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
			  VeiwMatrix= new Matrix4f().setTranslation(new Vector3f(position,0));
				target=projection.mul(VeiwMatrix,target);
		return target;
		}
		public Matrix4f getprojectionMatrix() {
			return projection;
		}
		public Matrix4f getUIprojectionMatrix() {
			return UIprojection;
		}
		
		
		public Matrix4f getVeiwMatrix() {
			return VeiwMatrix;
		}

		public Matrix4f getUIProjection() {
			Matrix4f target= new Matrix4f();
		   VeiwMatrix= new Matrix4f().setTranslation(new Vector3f(position,0));
			target=NON_FOV_UIprojection.mul(VeiwMatrix,target);
		return target;
		}

		public float getWidth() {
			return width;
		}

		public void setWidth(float width) {
			this.width = width;
			
			NON_FOV_projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
		    projection=new Matrix4f().setOrtho2D(-width/2*fov, width/2*fov,-height/2*fov, height/2*fov);
		}

		public float getHeight() {
			return height;
		}

		public void setHeight(float height) {
			this.height = height;
		
			NON_FOV_projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
		    projection=new Matrix4f().setOrtho2D(-width/2*fov, width/2*fov,-height/2*fov, height/2*fov);		
		}
		
		public void setSize(float width,float height) {
			this.height=height;
			this.width=width;
			
			
			
			NON_FOV_projection=new Matrix4f().setOrtho2D(-width/2, width/2,-height/2, height/2);
		    projection=new Matrix4f().setOrtho2D(-width/2*fov, width/2*fov,-height/2*fov, height/2*fov);
		
			
		}
		public float getFov() {
			return fov;
		}

		public Matrix4f getNON_FOV_projection() {
			return NON_FOV_projection;
		}

		public Matrix4f getNON_FOV_UIprojection() {
			return NON_FOV_UIprojection;
		}

		public void setFov(float fov) {
			if(this.fov!=fov) {
			this.fov = fov;
			this.projection=new Matrix4f().setOrtho2D(-width/2*fov, width/2*fov,-height/2*fov, height/2*fov);
			}
		}
		
	
	
}
