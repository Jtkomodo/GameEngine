package animation;

import java.util.UUID;

import core.ComponentRenderModel;
import core.Entity;
import core.EntityComponent;
import core.Game;
import core.PASSABLE_BOOL;
import core.PASSABLE_INT;
import core.PASSABLE_SPRITESHEET;
import core.VAR_RW;
import core.VAR_W;
import core.VAR_R;
import core.VAR_RW;
import rendering.Model;
import rendering.Texture;

public class ComponentAnimation extends EntityComponent {

	protected final static VAR_RW<PASSABLE_BOOL> VAR_MODEL_UPDATED=createNewVAR("MODEL_UPDATED",PASSABLE_BOOL.getHandle());
	protected final static VAR_RW<PASSABLE_SPRITESHEET> VAR_SPRITE_SHEET=createNewVAR("SPRITESHEET",PASSABLE_SPRITESHEET.getHandle());
	public final static VAR_RW<PASSABLE_BOOL> VAR_ANAIMATION_PAUSE=createNewVAR("ANIMATION_PAUSE",PASSABLE_BOOL.getHandle());
	protected final static VAR_RW<PASSABLE_INT> VAR_FRAME=createNewVAR("FRAME",PASSABLE_INT.getHandle());
	public final static VAR_RW<PASSABLE_BOOL> VAR_ANAIMATION_RESET=createNewVAR("ANIMATION_RESET",PASSABLE_BOOL.getHandle());
	protected final static VAR_RW<PASSABLE_BOOL> VAR_ANIMATION_UPDATED=createNewVAR("ANIMATION_UPDATED",PASSABLE_BOOL.getHandle());
	public static final UUID ID=UUID.randomUUID();
	
	public Animation a;
	
	
	
	public ComponentAnimation(Animation a) {
		this.a=new Animation(a);
	}
	
	
	@Override
	protected void INIT(Entity entity) {
     this.currentEntity=entity;  
     this.currentEntity.INITVar(ComponentRenderModel.VAR_TEXTURE,Game.DEFAULT_TEXTURE);
     this.currentEntity.INITVar(VAR_ANAIMATION_PAUSE,false);
     this.currentEntity.INITVar(VAR_ANIMATION_UPDATED,false);
     this.currentEntity.INITVar(VAR_MODEL_UPDATED, false);
     
	 AnimationEngine.addEntityAnimation(entity.ID, a);

	}
	
    protected void changeFrame(SpriteSheet sheet,int frame) {
		if(this.currentEntity.hasVAR(ComponentRenderModel.READ_VAR_MODEL())) {this.currentEntity.setVar(VAR_ANIMATION_UPDATED,false);
    	Model model=this.currentEntity.getVar(ComponentRenderModel.READ_VAR_MODEL());	
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
		this.currentEntity.setVar(VAR_MODEL_UPDATED,true);
	 //   CoreEngine.DebugPrint("changed frame"); 
		}	
    	}
    	}
		
		
	}
	@Override
	protected void RENDER_TICK() {
	
		if(this.currentEntity.hasAllVars(new VAR_RW<?>[] {VAR_ANIMATION_UPDATED,VAR_SPRITE_SHEET,
				VAR_FRAME
				})) {
			if(this.currentEntity.getVar(VAR_ANIMATION_UPDATED)) {
				SpriteSheet sheet= this.currentEntity.getVar(VAR_SPRITE_SHEET);
				int frame=this.currentEntity.getVar(VAR_FRAME);
			     changeFrame(sheet,frame);
				}

			}

		}
	    
	
	

	@Override
	protected boolean DISABLE() {
		AnimationEngine.removeEntityAnimation(currentEntity.ID);
		this.currentEntity.removeVAR(VAR_FRAME);
		this.currentEntity.removeVAR(VAR_ANIMATION_UPDATED);
		return true;
	}
	
	
	@Override
	public UUID getCOMPONENTID() {
		// TODO Auto-generated method stub
		return ID;
	}
    


}
