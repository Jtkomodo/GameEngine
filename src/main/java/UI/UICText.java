package main.java.UI;

import java.util.UUID;

import org.joml.Vector2f;

import main.java.core.Entity;
import main.java.rendering.Render;
import main.java.rendering.RenderingEngine;
import main.java.textRendering.Fontloader;
import main.java.textRendering.TextBuilder;

public class UICText extends UIComponent {
    private static UUID ID=UUID.randomUUID();
	
    
    
    
	
	private String text;
	private Vector2f LocalPosition;
	private TextBuilder builder;
	private float scale;
	
	
	/**
	 * 
	 * @param anchor 
	 * @param offset
	 * @param font
	 * @param text
	 * @param scale
	 */
	public UICText(Vector2f LocalPosition,Fontloader font,String text,float scale) {
		this.builder=new TextBuilder(font);
		this.text=text;
		this.scale=scale;
		this.LocalPosition=LocalPosition;
		
		
		this.builder.setString(text);
	}
		
	
	
	@Override
	protected void INIT(Entity entity) {
	        this.currentEntity=entity;
            RenderingEngine.addEntity(entity);
	}
	protected  void RENDER_TICK(){
			Vector2f position=new Vector2f();
			Vector2f camPosition=Render.cam.getPosition();
			Vector2f anchor=this.currentEntity.getVar(Entity.VAR_POSITION);
			anchor.add(this.LocalPosition,position);
			
			anchor.add(-camPosition.x,-camPosition.y,position);
			this.builder.drawString(position.x,position.y,scale);
		
	}
	public void setString(String s) {
		this.text=s;
		this.builder.setString(text);
	}
	@Override
	protected boolean DISABLE() {
		RenderingEngine.removeEntity(currentEntity);
		return true;
	}

	@Override
	public UUID getCOMPONENTID() {
		
		return ID;
	}



	@Override
	protected void enable() {
		INIT(this.currentEntity);
		
	}

}
