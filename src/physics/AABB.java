package physics;

import org.joml.Vector2f;

public class AABB {

	
	private Vector2f position,bottom_left,top_right; 
	private float width,height;
	private float resistance;
	private Vector2f position_before_collision=new Vector2f();;
	public AABB(Vector2f position, float width, float height, float resistance) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.resistance = resistance;
		position.add(width/2,-height/2, top_right);
		position.sub(width/2,height/2, bottom_left);
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
	
	
	public boolean vsAABB(AABB box) {
		
		Vector2f lcB=new Vector2f(0,0);
		Vector2f rcB=new Vector2f(0,0);
		Vector2f lcA=new Vector2f(0,0);
		Vector2f rcA=new Vector2f(0,0);
		
		lcA=this.bottom_left;
		rcA=this.top_right;
		lcB=box.bottom_left;
		rcB=box.top_right;
	
		
		if((rcA.x<lcB.x) || (rcB.x<lcA.x)) {// if the right side of A comes before the left side of B or vice versa return false(they can not be colliding)
		
		
			return false;
			
		}
		else if((rcA.y<lcB.y) || (rcB.y<lcA.y)) {//if the Bottom side of A comes before the top side of B or vice versa return false
		
		  
			
			return false;
		}
		
		else {//if both all sides have been checked and not resulted in no collision then there must be a collision			
			return true;
		}
	  
		

	}
	
   
	
	public Vector2f findVector(Vector2f position, Vector2f movement,Vector2f direction,AABB box) {
		//position is the current position before movement
		//movement is the current step we are taking
		//direction is a normalized version of the current velocity
		//box is the box we are checking collision with


		//  Start.DebugPrint("("+ColisionHandeler.amountThrough+")");



		Vector2f currentmovement=new Vector2f(0,0);
		movement.add(new Vector2f(position.x,position.y),currentmovement);//this is the current position after addition of the movement
		//		 Vector2f magangle=VectorMath.getMagAndAngleComponet(movement);
		//	
		//		 
		//		//   MainRenderHandler.addEntity(new Entity(piont, new Vector3f(position,z+10),-magangle.y,magangle.x*10,Start.VectorTex,Constants.RED));
		//		   
		//		 Start.DebugPrint(""+magangle.y);
		//		   



		Vector2f newMOvement=currentmovement; //this is the return value   



		Vector2f penetration=new Vector2f(0,0);//this is the vector representing how much into the box we are in




		float amount=box.resistance;





		if(amount!=0 && amount!=1) {     


			newMOvement=position.lerp(currentmovement, amount);

		}else if(amount==0) {


				Vector2f d3= new Vector2f(0,0);
				//Vector2f d32= new Vector2f(0,0);
				this.position_before_collision.sub(box.position,d3);

			   Vector2f ClosestPosition=new Vector2f(clamp(box.position.x+d3.x,box.bottom_left.x,box.bottom_left.x),clamp(box.position.y+d3.y,box.bottom_left.y,box.top_right.y));


				Vector2f closesta=ClosestPosition;
				Vector2f closestb;
				Vector2f d2=new Vector2f();
				closesta.sub(this.position_before_collision,d2);
				closestb=new Vector2f(clamp(currentmovement.x+d2.x,bottom_left.x,bottom_left.x),clamp(currentmovement.y+d2.y,bottom_left.y,bottom_left.y));
				closestb.sub(closesta, penetration);




				currentmovement.sub(penetration.add(direction.mul(.001f,new Vector2f()),new Vector2f()), newMOvement); 





			

		}else if(amount==1) {
			return currentmovement;
		}


		//   Start.DebugPrint("("+amountThrough+")"+"nm="+newMOvement);

		return newMOvement;
		   
		   
		   
		
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


	public Vector2f getPosition() {
		return this.position;
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
	
	
	
      
	
	
	
}
