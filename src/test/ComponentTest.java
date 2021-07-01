package test;

import java.util.UUID;


import core.Constants;
import core.CoreEngine;
import core.Entity;
import core.EntityComponent;
import core.PASSABLE_BOOL;
import core.PASSABLE_VEC4F;
import core.VAR;

public class ComponentTest extends EntityComponent {
	public static final UUID ID=UUID.randomUUID();
	
	
	
	
	
	public static final VAR<PASSABLE_BOOL> VAR_TEST=createNewVAR("TEST",PASSABLE_BOOL.getHandle());
	
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		
		entity.INITData(VAR_TEST,new PASSABLE_BOOL(false));
		this.currentEntity=entity;
		
		
		
		
	}

	@Override
	protected void GAMELOOP_TICK() {
		PASSABLE_BOOL test=this.currentEntity.getData(VAR_TEST);
		
		if(test!=null) {
			
			if(test.getValue()) {

				this.currentEntity.TakeInData(Entity.VAR_COLOR, new PASSABLE_VEC4F(Constants.SPRITE_NOT_SELECTED_COLOR));
			
			
			}else {
				this.currentEntity.TakeInData(Entity.VAR_COLOR, new PASSABLE_VEC4F(Constants.DEFAULT_COLOR));
			}
			
		}
	}

	@Override
	protected void RENDER_TICK() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getCOMPONENTID() {
	
		return ID;
	}



	
	
	
	
}
