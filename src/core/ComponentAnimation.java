package core;

import animation.Animation;
import animation.AnimationEngine;
import animation.SpriteSheet;
import rendering.Model;

public class ComponentAnimation extends EntityComponent {

	
	
	
	public Animation a;
	
	
	
	public ComponentAnimation(Animation a) {
		this.a=new Animation(a);
	}
	
	
	@Override
	protected void INIT(Entity entity) {
     this.currentEntity=entity;  
     this.currentEntity.INITData(Entity.VAR_TEXTURE,Game.DEFAULT_TEXTURE);
	 AnimationEngine.addEntityAnimation(entity.ID, a);

	}
	
    protected void changeFrame(SpriteSheet sheet,int frame) {
		this.currentEntity.TakeInData(Entity.VAR_ANIMATION_UPDATED,new PASSABLE_BOOL(false));
    	Model model=this.currentEntity.getData(Entity.VAR_MODEL);	
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
		this.currentEntity.TakeInData(Entity.VAR_MODEL_UPDATED,new PASSABLE_BOOL(true));
	 //   CoreEngine.DebugPrint("changed frame"); 
		}	
		
    	}
		
		
	}
	@Override
	protected void GAMELOOP_TICK() {
	

	}

	@Override
	protected void RENDER_TICK() {
		PASSABLE_BOOL animaitonChanged=this.currentEntity.getData(Entity.VAR_ANIMATION_UPDATED);
		if(animaitonChanged!=null) {
			if(animaitonChanged.value) {
				SpriteSheet sheet= this.currentEntity.getData(Entity.VAR_SPRITE_SHEET);
				PASSABLE_INT pass_i=this.currentEntity.getData(Entity.VAR_FRAME);
				if(pass_i!=null && sheet!=null) {
					int frame=pass_i.Value;
					changeFrame(sheet,frame);
				}else {
					CoreEngine.DebugPrint("ERROR "+Entity.VAR_FRAME+" does not exist in the entity!");
					return;
				}

			}

		}
	}
	

	@Override
	protected boolean DISABLE() {
		AnimationEngine.removeEntityAnimation(currentEntity.ID);
		this.currentEntity.Entity_Data.remove(Entity.VAR_SPRITE_SHEET);
		this.currentEntity.Entity_Data.remove(Entity.VAR_FRAME);
		this.currentEntity.Entity_Data.remove(Entity.VAR_ANIMATION_UPDATED);
		return false;
	}


	@Override
	public COMPONENT_TYPE getID() {
		return COMPONENT_TYPE.ANIMAITON;
	}

}
