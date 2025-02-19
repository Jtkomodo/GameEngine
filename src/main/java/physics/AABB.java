package main.java.physics;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector3f;

import main.java.core.Constants;
import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.Game;
import main.java.core.PASSABLE_VEC2F;
import main.java.core.VAR_RW;
import main.java.events.Flag;
import main.java.rendering.MainRenderHandler;
import main.java.rendering.Model;
import main.java.rendering.RenderEntity;

public class AABB {

	private float width,height;
	private UUID ENTITY_ID;
	private float resistance;
	private Flag flag=new Flag(false);
	private Model m;
    
	
	public AABB(float width, float height,float resistance) {
		
	    CoreEngine.InitData(ENTITY_ID,ComponentColision.VAR_BEFORE_POSITION,new Vector2f(0));
		this.width = width;
		this.height = height;
		this.resistance=resistance;
		
		
		float[] Vert= {
				 -0.5f,+0.5f,
					0.5f,0.5f,
					0.5f,-0.5f,
					-0.5f,-0.5f
				 };
			float[] uvBg={
					0,0,
					1,0,
					1,1,
					0,1
					
					};
			int[] ind= {
					0,1,2,
					2,3,0	
						
				};
			m=new Model(Vert,uvBg);
		
		/*
	       _____________rc
	       |           |        
	       |     A     |     
	       |___________|
       lc            		       
	       _____________rc
	       |           | 
	       |     B     |     
	       |___________|
       lc             		
	
	
	*/
	}
	protected void TIE_ENTITY(UUID ID) {
		this.ENTITY_ID=ID;
	}
	
	
	protected boolean vsAABB(AABB box) {
		
		Vector2f lcB=new Vector2f(0,0);
		Vector2f rcB=new Vector2f(0,0);
		Vector2f lcA=new Vector2f(0,0);
		Vector2f rcA=new Vector2f(0,0);
		
		
		if(CoreEngine.HasVar(ENTITY_ID,Entity.VAR_POSITION) && CoreEngine.HasVar(box.ENTITY_ID,Entity.VAR_POSITION) ) {
        Vector2f positionA =CoreEngine.RecieveData(ENTITY_ID,Entity.VAR_POSITION);
	    Vector2f positionB =CoreEngine.RecieveData(box.ENTITY_ID,Entity.VAR_POSITION);	
		
		
		
		
		
		positionA.sub(width,height,lcA);
		positionB.sub(box.width,box.height,lcB);
		positionA.add(width,height,rcA);
		positionB.add(box.width,box.height,rcB);
		
	;
	
		
		if((rcA.x<lcB.x) || (rcB.x<lcA.x)) {// if the right side of A comes before the left side of B or vice versa return false(they can not be colliding)
			return false;
			
		}
		else if((rcA.y<lcB.y) || (rcB.y<lcA.y)) {//if the Bottom side of A comes before the top side of B or vice versa return false
		
		  
			
			return false;
		}
		
		else {//if both all sides have been checked and not resulted in no collision then there must be a collision
			return true;
		}
		}else {
			return false;
		}
		

	}
	
   protected boolean vsPoint(Vector2f point,Vector2f position) {

		
		Vector2f lcA=new Vector2f(0,0);
		Vector2f rcA=new Vector2f(0,0);
		
		
		position.sub(width,height,lcA);
		position.add(width,height,rcA);
	
		if((point.x>=lcA.x) && (point.x<=rcA.x) && (point.y<=rcA.y) && (point.y>=lcA.y)) {
			return true;
			
		}else {
			return false;
		}
		
		
	   	   
	   
   }
	
	protected Vector2f findVector(Vector2f positionNow,Vector2f velocity,Vector2f direction,AABB box) {
		//oldposition is the current position before movement
		//movement is the current step we are taking
		//direction is a normalized version of the current velocity
		//box is the box we are checking collision with

     

		Vector2f oldPosition=new Vector2f();
		positionNow.sub(velocity,oldPosition);

	
		Vector2f newMOvement=positionNow; //this is the return value   
		Entity e=CoreEngine.getEntity(ENTITY_ID);
		Entity e2=CoreEngine.getEntity(box.ENTITY_ID);
		
		
		
		if(e!=null && e2!=null) {
	
			
		//check to mare sure we have all the required VARS
		if(e.hasAllVars(new VAR_RW<?>[]{Entity.VAR_POSITION,ComponentColision.VAR_BEFORE_POSITION}) && e2.hasAllVars(new VAR_RW<?>[] {ComponentColision.VAR_BEFORE_POSITION,Entity.VAR_POSITION})) {	
			
			
		
			
			
			
			
		
			
			
	
		



		



		float amount=box.resistance;





		if(amount!=0 && amount!=1) {     


			newMOvement=oldPosition.lerp(positionNow, amount);

		}else if(amount==0) {
			//get all of the varaibles we will need to get the resultant vector
			
			Vector2f before_position_a=e.getVar(ComponentColision.VAR_BEFORE_POSITION);
			Vector2f before_position_b=e2.getVar(ComponentColision.VAR_BEFORE_POSITION);
			Vector2f position_b=e2.getVar(Entity.VAR_POSITION);
			
			Vector2f penetration=new Vector2f(0,0);//this is the vector representing how much into the box we are in


		     Vector2f d3= new Vector2f(0,0);
				//Vector2f d32= new Vector2f(0,0);
			Vector2f lcB=new Vector2f(0,0);
			Vector2f rcB=new Vector2f(0,0);
			Vector2f lcA=new Vector2f(0,0);
			Vector2f rcA=new Vector2f(0,0);
				 
			positionNow.sub(width,height,lcA);
			position_b.sub(box.width,box.height,lcB);
			positionNow.add(width,height,rcA);
			position_b.add(box.width,box.height,rcB);	
				
			//find the closest positionns
			
			
		
			
			
			
			
			Vector2f Point_B=new Vector2f();//this is the point that will be the closest point to the before collision box on box B
			Vector2f d2=new Vector2f();//this is the vector of the position of box B  minus the position before collision
		    before_position_a.sub(position_b,d2);
		    
		    
		    
		    
		    
		    //now we clamp a point on that vector to the box b that way we can find the closest position on box B to the box before collision
		    float Point_B_X=clamp(position_b.x+d2.x,lcB.x,rcB.x);
		    float Point_B_Y=clamp(position_b.y+d2.y,lcB.y,rcB.y);
		    Point_B=new Vector2f(Point_B_X,Point_B_Y); 
			if( e2.DEBUG && CoreEngine.Debugdraw) {
				MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(Point_B,100),0, 3,Game.DEFAULT_TEXTURE,Constants.RED));
			}
			
			Vector2f Point_A=new Vector2f();//this will be the point that is the point on the opposite side of box A this is the second point we need to find out how much we have penetrated the box 
			Vector2f d=new Vector2f();//this is the vector of the position of box A minus the position before collision
			Point_B.sub(before_position_a,d);
			float Point_A_X=clamp(positionNow.x+d.x,lcA.x,rcA.x);
			float Point_A_Y=clamp(positionNow.y+d.y,lcA.y,rcA.y);   
			Point_A=new Vector2f(Point_A_X,Point_A_Y); 
			if( e2.DEBUG && CoreEngine.Debugdraw) {
				MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(Point_A,100),0, 3,Game.DEFAULT_TEXTURE,Constants.RED));
			}
			
			//now that we have those two points we have the penetration vector be subtracting them
			Point_A.sub(Point_B,penetration);
			
			//now we just make sure we move the box out just a bit more than the exact value so it is not in the box at all
			
			
			//now subtract that from the posiition now to get the correct movement
		
		
			
			//now subtract that from the posiition now to get the correct movemet
			Vector2f smallMovement=new Vector2f();
			direction.mul(.001f,smallMovement);
			
			penetration.add(smallMovement);
			//now subtract that from the posiition now to get the correct movement
			positionNow.sub(penetration,newMOvement);
			

				}


			
		
		
		else if(amount==1) {
			return positionNow;
		}


		//   Start.DebugPrint("("+amountThrough+")"+"nm="+newMOvement);
		}}
		return newMOvement;
		   
		   
		   
		
	   }
	
	protected Vector2f reject(Vector2f a,Vector2f b) {
      	Vector2f vector=new Vector2f();
    	
    	a.sub(project(a,b),vector);
    	
    	return vector;
		
	}
    protected Vector2f project(Vector2f a,Vector2f b) {
    	Vector2f vector=new Vector2f();
    	float dot=a.dot(b);
    	float mag=b.lengthSquared();
    	if(mag==0) {
    		mag=1;
    	}
        float scaler=dot/mag;
        b.mul(scaler,vector);
    	
    	
    	return vector;
    }

	 protected  float clamp(float value,float min,float max) {
		   
		   if(value<min) {
			   return min;
			   
		   }
		   
		   else if(value>max) {
			   return max;
		   }else {
			   
			   return value;
		   }
		   }
	 public Vector2f getOffsetinBox(Vector2f position,Vector2f point) {
			Vector2f Offsetpostion=new Vector2f();
			
			
			Vector2f lc=new Vector2f(0,0);
			Vector2f rc=new Vector2f(0,0);
			
			position.sub(width,height,lc);
			
			position.add(width,height,rc);
			
		    Offsetpostion.x=point.x-lc.x;
		    Offsetpostion.y=(height*2)-(point.y-lc.y);	
			
			return Offsetpostion;
		}
		
	public void debugDraw(Vector2f position) {
		   MainRenderHandler.addEntity(new RenderEntity(m,new Vector3f(position,900),0,new Vector2f(width*2,height*2),Game.DEFAULT_TEXTURE,Constants.COL_COLOR_BLUE));
	}


	public void setFlagState(boolean state) {
		this.flag.setState(state);
	}
    public Flag getFlag() {
    	return this.flag;
    }

	public float getwidth() {
		// TODO Auto-generated method stub
		return this.width;
	}


	public float getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}


	public float getResistance() {
		// TODO Auto-generated method stub
		return this.resistance;
	}

	
	public UUID getID() {
		return ENTITY_ID;
	}
	protected float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	
	
      
	
	
	
}
