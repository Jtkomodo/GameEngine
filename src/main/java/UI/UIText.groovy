package main.java.UI;

import java.util.UUID;

import org.joml.Vector2f;
import org.joml.Vector4f
import main.java.core.ComponentScript;
import main.java.core.Entity;
import main.java.core.EntityComponent;
import main.java.core.PASSABLE_FLOAT;
import main.java.core.PASSABLE_STRING;
import main.java.core.PASSABLE_VEC2F;
import main.java.core.Script;
import main.java.core.VAR_RW;
import main.java.textRendering.Fontloader;
import main.java.textRendering.TextBuilder;
import main.java.core.PASSABLE_VEC4F;

public class UIText extends EntityComponent {

	
	
	public static final VAR_RW<PASSABLE_VEC2F> VAR_UITEXT_POSITION=createNewVAR("VAR_UITEXT_POSITION",PASSABLE_VEC2F.getHandle());
	public static final VAR_RW<PASSABLE_FLOAT> VAR_UITEXT_SCALE=createNewVAR("VAR_UITEXT_SCALE",PASSABLE_FLOAT.getHandle());
	public static final VAR_RW<PASSABLE_STRING> VAR_UITEXT_STRING=createNewVAR("VAR_UITEXT_STRING",PASSABLE_STRING.getHandle());
	public static final VAR_RW<PASSABLE_VEC4F> VAR_UITEXT_COLOR=createNewVAR("VAR_UITEXT_COLOR",PASSABLE_VEC4F.getHandle());
	
	
	private TextBuilder builder;
    private Vector2f offset;
	private float size;
	private String string;
	private Vector4f color
	
	
	public UIText(Fontloader font,Vector2f offset=new Vector2f(),float size=0.5f,String string="",Vector4f color=main.java.core.Constants.DEFAULT_COLOR) {
		builder=new TextBuilder(font);
		this.offset=offset;
		this.size=size;
		this.string=string;
		this.color=color;
	}

	
	
	
	@Override
	protected void INIT(Entity entity) {
		this.currentEntity=entity;
		entity.INITVar(VAR_UITEXT_POSITION,offset);
		entity.INITVar(VAR_UITEXT_SCALE,size);
		entity.INITVar(VAR_UITEXT_STRING,string);
		entity.INITVar(VAR_UITEXT_COLOR,color);
		
	}
	
	protected  void RENDER_TICK(){
		this.offset=currentEntity.getVar(VAR_UITEXT_POSITION);
		this.size=currentEntity.getVar(VAR_UITEXT_SCALE);
		this.string=currentEntity.getVar(VAR_UITEXT_STRING);
		this.color=currentEntity.getVar(VAR_UITEXT_COLOR);
		
		
		builder.setString(string);
		builder.UIdrawString(offset.x, offset.y,0, size, color);
	
    }
	
	
	

	@Override
	protected boolean DISABLE() {
	
		return false;
	}

	@Override
	public UUID getCOMPONENTID() {
	
		return null;
	}




	@Override
	protected void enable() {
		INIT(this.currentEntity);
		
	}

}
