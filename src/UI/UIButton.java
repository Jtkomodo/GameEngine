package UI;

import org.joml.Vector2f;
import org.joml.Vector3f;

import core.Constants;
import core.CoreEngine;
import core.Entity;
import core.Game;
import events.Flag;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;

public class UIButton extends UIElement {

	

	
	
	private Flag on=new Flag(false);
	private Model m;
	
	public UIButton(float width, float height) {
		super(width, height);
		float widthR=width;
		float heightR=height;
		float[] Vert= {
				 -widthR,+heightR,
					widthR,heightR,
					widthR,-heightR,
					-widthR,-heightR
				 };
			float[] uvBg={
					0,0,
					1,0,
					1,1,
					0,1
					
					};
			m=new Model(Vert,uvBg);
		
		
		
	}
	
	
	protected void setPositonInBox(Vector2f position) {
		position.add(this.getWidth(),0,this.position_in_box);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void leftButtonJustPressed(Vector2f cursorPosition) {
         this.on.setState(!this.on.State());

	}

	@Override
	public void lefttButtonJustRealesed(Vector2f cursorPosition) {
		
		
	}

	@Override
	public void LeftButtonHeld(Vector2f cursorPosition) {
	  

	}

	@Override
	public void rightButtonJustPressed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightButtonJustRealesed(Vector2f cursorPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightButtonHeld(Vector2f cursorPosition) {
	
	}










    public Flag getONFlag() {
    	return this.on;
    }



	@Override
	public void renderUpdate(Vector2f Box_position) {
		
		
		Vector2f position_in_box=new Vector2f();
		this.position_in_box.add(Box_position,position_in_box);
	    this.collision_box.debugDraw(position_in_box);
	
	 if(this.on.State()) {
		     RenderEntity e=new RenderEntity(m,new Vector3f(position_in_box,200),0, 1,Game.DEFAULT_TEXTURE,Constants.BAR_COLOR_GREEN);
		     e.setUIPojeection(true);
			 MainRenderHandler.addEntity(e);
			 
		 }else {
			  RenderEntity e=new RenderEntity(m,new Vector3f(position_in_box,200),0, 1,Game.DEFAULT_TEXTURE,Constants.BAR_COLOR_RED);
			  e.setUIPojeection(true);
		      MainRenderHandler.addEntity(e);
		 }
		
	}


	

}
