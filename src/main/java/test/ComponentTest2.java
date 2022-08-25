package main.java.test;


import java.util.UUID;

import main.java.core.CoreEngine;
import main.java.core.Entity;
import main.java.core.EntityComponent;
import main.java.core.PASSABLE_BOOL;
import main.java.core.PASSABLE_FLOAT;
import main.java.core.PASSABLE_INT;
import main.java.core.VAR_RW;

public class ComponentTest2 extends EntityComponent {
	public static final UUID ID=UUID.randomUUID();

	public static final VAR_RW<PASSABLE_FLOAT> VAR_TEST=createNewVAR("TEST",PASSABLE_FLOAT.getHandle());
	
	
	
	Entity e;
	
	@Override
	protected void INIT(Entity entity) {
		   
		   this.e=entity;
		   this.e.setVar(VAR_TEST, 100f);
		   
	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
		//  CoreEngine.DebugPrint("test="+e.getVar(VAR_TEST));
		
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
		// TODO Auto-generated method stub
		return ID;
	}

}
