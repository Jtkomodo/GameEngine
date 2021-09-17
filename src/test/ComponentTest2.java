package test;


import java.util.UUID;

import core.Entity;
import core.EntityComponent;
import core.PASSABLE_BOOL;
import core.PASSABLE_INT;
import core.VAR_RW;

public class ComponentTest2 extends EntityComponent {
	public static final UUID ID=UUID.randomUUID();

	public static final VAR_RW<PASSABLE_INT> VAR_TEST=createNewVAR("TEST",PASSABLE_INT.getHandle());
	
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		   entity.setVar(VAR_TEST,155);
		
		   
		   
	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
		// TODO Auto-generated method stub
		
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
