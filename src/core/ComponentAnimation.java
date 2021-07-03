package core;

import java.util.UUID;

import animation.Animation;
import animation.AnimationEngine;
import animation.SpriteSheet;
import rendering.Model;

public class ComponentAnimation extends EntityComponent {

	
	public static final UUID ID=UUID.randomUUID();
	
	public Animation a;
	
	
	
	public ComponentAnimation(Animation a) {
		this.a=new Animation(a);
	}
	
	
	@Override
	protected void INIT(Entity entity) {
     this.currentEntity=entity;  
     this.currentEntity.INITVar(Entity.VAR_TEXTURE,Game.DEFAULT_TEXTURE);
     this.currentEntity.INITVar(Entity.VAR_ANAIMATION_PAUSE,false);
     this.currentEntity.INITVar(Entity.VAR_ANIMATION_UPDATED,false);
     this.currentEntity.INITVar(Entity.VAR_MODEL_UPDATED, false);
     
	 AnimationEngine.addEntityAnimation(entity.ID, a);

	}
	
    protected void changeFrame(SpriteSheet sheet,int frame) {
		if(this.currentEntity.hasVAR(Entity.VAR_MODEL)) {this.currentEntity.setVar(Entity.VAR_ANIMATION_UPDATED,false);
    	Model model=this.currentEntity.getVar(Entity.VAR_MODEL);	
    	if(model!=null) {
    	
		float  Texwidth=sheet.getSize();//gets the size of the sheet itself
		float    Texheight=Texwidth;//always is one to one
		float[] data=sheet.getValueInMap(frame);//gets the data of the next sprite so that we can change the u v coords
		if(data!=null) {//just makes sure no null pionter exception happens 
	    	 
	    	 
	     
			
	
		float wi=data[2];//gets the width of the sprite
		float  h=data[3];//gets the height
		float Texx=data[0];//u
		
		float    Texy=data[1];///v	
		   float height2=h/64;
		   float width2=wi/64;     
		 
		  
	       float[] uv={
			Texx/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,Texy/Texheight,
			(Texx+wi)/Texwidth,(Texy+h)/Texheight,
			Texx/Texwidth,(Texy+h)/Texheight  };
		
	
		model.changeUV(uv);//change the uv of the model
		this.currentEntity.setVar(Entity.VAR_MODEL_UPDATED,true);
	 //   CoreEngine.DebugPrint("changed frame"); 
		}	
    	}
    	}
		
		
	}
	@Override
	protected void GAMELOOP_TICK() {
	

	}

	@Override
	protected void RENDER_TICK() {
	
		if(this.currentEntity.hasAllVars(new VAR<?>[] {Entity.VAR_ANIMATION_UPDATED,Entity.VAR_SPRITE_SHEET,
				Entity.VAR_FRAME
				})) {
			if(this.currentEntity.getVar(Entity.VAR_ANIMATION_UPDATED)) {
				SpriteSheet sheet= this.currentEntity.getVar(Entity.VAR_SPRITE_SHEET);
				int frame=this.currentEntity.getVar(Entity.VAR_FRAME);
			     changeFrame(sheet,frame);
				}

			}

		}
	    
	
	

	@Override
	protected boolean DISABLE() {
		AnimationEngine.removeEntityAnimation(currentEntity.ID);
		this.currentEntity.removeVAR(Entity.VAR_FRAME);
		this.currentEntity.removeVAR(Entity.VAR_ANIMATION_UPDATED);
		return true;
	}


	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}


	

}
