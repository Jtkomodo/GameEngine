package main.java.test;

import java.util.UUID;

import main.java.core.Constants;
import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.EntityComponent;
import main.java.core.PASSABLE_BOOL;
import main.java.core.PASSABLE_VEC4F;
import main.java.core.VAR_RW;

public class ComponentTest extends EntityComponent {
	public static final UUID ID=UUID.randomUUID();
	
	
	
	
	
	public static final VAR_RW<PASSABLE_BOOL> VAR_TEST=createNewVAR("TEST",PASSABLE_BOOL.getHandle());

	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		
		entity.INITVar(VAR_TEST,false);
		this.currentEntity=entity;
	
		
		
		
	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
				
		if(this.currentEntity.hasVAR(VAR_TEST)) {
			
			if(this.currentEntity.getVar(VAR_TEST)) {

				this.currentEntity.setVar(Entity.VAR_COLOR,Constants.SPRITE_NOT_SELECTED_COLOR);
			
			
			}else {
				this.currentEntity.setVar(Entity.VAR_COLOR,Constants.DEFAULT_COLOR);
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
