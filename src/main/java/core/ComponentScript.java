package main.java.core;

import java.util.UUID;

public class ComponentScript extends EntityComponent {


	
	
	private UUID ID_INSTANCE;
	private Script script;
	private Entity e;
	
	
	public ComponentScript(Script script) {
		this.script=script;
		this.ID_INSTANCE=script.getSCRIPTID();
	}
	
	
	
	@Override
	protected void INIT(Entity entity) {
		this.e=entity;
		script.Start(e);
	}

	@Override
	protected void GAMELOOP_TICK_BEFORE_PHYSICS() {
	   script.GAMELOOP_TICK_BEFORE_PHYSICS();

	}
	
	
	@Override
	protected void GAMELOOP_TICK_AFTER_PHYSICS() {
	   script.GAMELOOP_TICK_AFTER_PHYSICS();
	}
	@Override
	protected void RENDER_TICK() {
		  script.RENDER_TICK();

	}

	@Override
	protected boolean DISABLE() {
	
		return script.DISABLE();
	}

	@Override
	public UUID getCOMPONENTID() {
		return ID_INSTANCE;
	}
	public Script getScript() {
		return this.script;
	}

}
