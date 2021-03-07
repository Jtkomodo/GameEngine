package core;

import org.joml.Vector3f;

import animation.SpriteSheet;
import rendering.MainRenderHandler;
import rendering.Model;
import rendering.RenderEntity;

public class ComponentRenderModel extends EntityComponent {

	public final static int COMPID=0x2002;
	
	
	
	protected Model model;
	
	
	
	public ComponentRenderModel(Model model) {
		super(COMPID);
		this.model=new Model(model.getVertices(),model.getUv_coords());
	
	}

	protected void changeFrame(SpriteSheet sheet,int frame) {
		
		
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
	     }	
		
	
		
		
	}
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
	     this.currentEntity=entity;	}

	@Override
	protected void GAMELOOP_TICK() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void RENDER_TICK() {
		// TODO Auto-generated method stub

        MainRenderHandler.addEntity(new RenderEntity(model, new Vector3f(this.currentEntity.position,10),0,1,this.currentEntity.texture));

	}

	@Override
	protected boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

}
