package core;

import java.util.UUID;

public class ComponentScript extends EntityComponent {


	public final UUID ID_INSTANCE=UUID.randomUUID();
	
	
	
	
	
	
	
	@Override
	protected void INIT(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	protected void GAMELOOP_TICK_AFTER_PHYSICS() {

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
		return ID_INSTANCE;
	}

}
